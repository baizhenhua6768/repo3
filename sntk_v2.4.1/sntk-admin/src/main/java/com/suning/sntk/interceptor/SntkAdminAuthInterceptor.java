/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SntkAdminAuthInterceptor
 * Author:   88396455_白振华
 * Date:     2018-7-2 9:53
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.interceptor;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.suning.ids.client.util.CookieUtils;
import com.suning.nsfuaa.orgnization.OrganizationService;
import com.suning.nsfuaa.orgnization.dto.OrgAttribution;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.dto.region.EmployeeInfo;
import com.suning.sntk.dto.region.OrgInfo;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.utils.AuthCryptUtil;
import com.suning.sntk.support.common.utils.JsonUtils;

/**
 * 功能描述：添加用户信息拦截器
 *
 * @author 88396455_白振华
 * @since 2018-7-2
 */
public class SntkAdminAuthInterceptor extends HandlerInterceptorAdapter {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SntkAdminAuthInterceptor.class);
    /**
     * redis缓存时间
     */
    private static final int AUTH_INFO_LIVE_TIME_IN_REDIS = 30 * 60;
    /**
     * request.getHeader("x-requested-with");为null，则为传统同步请求；
     * 为XMLHttpRequest，则为Ajax请求
     */
    private static final String XMLHTTPREQUEST = "XMLHttpRequest";

    private static final String UTF_8_CHARACTER = "UTF-8";

    private static final String ADMIN_USER_COOKIE_KEY = "sntkAdminUserKey";

    private static final String ADMIN_USER_CACHE_KEY_PREFIX = "sntk_admin_";

    private static final String REQ_REJECT_MSG = "{\"retFlag\":\"0\",\"errorCode\":\"1000\",\"errorMessage\":\"非法请求\"}";
    /**
     * 管理台主页地址
     */
    @Value("@{sf_admin_home_url}")
    private String sfAdminHomeUrl;

    @Resource
    RedisCacheUtils redisClient;
    private int percent = 4;
    private OrganizationService organizationService = ServiceLocator.getService(OrganizationService.class, null);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            request.setCharacterEncoding(UTF_8_CHARACTER);
            //校验用户是否处于有效登陆状态，默认不在
            boolean authResult = false;

            //从请求中获取用户信息
            EmployeeInfo userInfo = this.getEmployeeBasicInfo(request);
            LOGGER.info("SntkAdminAuthInterceptor.preHandle, userInfo:{}", userInfo);
            if (null == userInfo) {
                // 这种场景有两种情况，1、所属页面中涉及的ajax请求等 2、在浏览器中单独访问菜单链接（非法）
                // 从cookie中读取key
                Cookie userInfoKeyCookie = CookieUtils.getCookie(request, ADMIN_USER_COOKIE_KEY);
                if (null != userInfoKeyCookie) {
                    String userInfoKey = userInfoKeyCookie.getValue();
                    String userInfoJson = getUserInfoCache(userInfoKey);
                    if (StringUtils.isNotBlank(userInfoJson)) {
                        // 能正常取到用户信息说明尚处于登录状态
                        authResult = true;
                    }
                }
            } else {
                // 放入到cookie和redis,threadLocal中去,此为用户点击菜单链接
                if (toCookieAndRedis(userInfo, response)) {
                    authResult = true;
                } else {
                    authResult = false;
                }
            }

            if (!authResult) {
                // 登录状态校验不通过
                if (isAjaxRequest(request)) {
                    // 判断是不是ajax请求
                    writeAjaxData(response);
                } else {
                    // 非ajax请求重定向至登录页面
                    response.sendRedirect(sfAdminHomeUrl);
                }
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("SntkAdminAuthInterceptor.preHandle error", e);
        }
        return true;
    }

    /**
     * 登录状态校验不通过，返回错误信息<br>
     *
     * @param response
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private void writeAjaxData(HttpServletResponse response) {
        try {
            response.setCharacterEncoding(UTF_8_CHARACTER);
            response.setContentType("application/json");
            response.getOutputStream().write(REQ_REJECT_MSG.getBytes(UTF_8_CHARACTER));
            response.getOutputStream().flush();
            response.getOutputStream().close();
            // 这个地方不要关闭out，关了其它地方用response就不行了。
        } catch (Exception e) {
            LOGGER.error("SntkAdminAuthInterceptor.writeAjaxData error :{}", e);
        }
    }

    /**
     * 判断请求是否为ajax请求<br>
     *
     * @param request
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxStr = request.getHeader("X-Requested-With");
        if (StringUtils.isNotBlank(ajaxStr) && XMLHTTPREQUEST.equalsIgnoreCase(ajaxStr)) {
            return true;
        }
        return false;
    }

    /**
     * 将用户信息保存到redis<br>
     *
     * @param response
     * @param userInfo 用户信息
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private boolean toCookieAndRedis(EmployeeInfo userInfo, HttpServletResponse response) {
        try {
            // 取UUID做为用户信息在redis中键值
            final String userInfoKey = UUID.randomUUID().toString();
            Cookie cookie = new Cookie(ADMIN_USER_COOKIE_KEY, userInfoKey);
            // 后台都是.cnsuning.com的域名
            cookie.setDomain(".cnsuning.com");
            cookie.setPath("/");
            response.addCookie(cookie);
            // 将用户信息放入redis缓存中，设置30分钟有效期
            final String userInfoJson = JsonUtils.object2Json(userInfo);
            redisClient.setex(ADMIN_USER_CACHE_KEY_PREFIX + userInfoKey, AUTH_INFO_LIVE_TIME_IN_REDIS, userInfoJson);

            // 放到threadLocal中去
            RequestUserHolder.setRequestUser(userInfo);
            return true;
        } catch (Exception e) {
            LOGGER.error("SntkAdminAuthInterceptor.toCookieAndRedis error.", e);
            return false;
        }
    }

    /**
     * 从redis中查询用户信息<br>
     *
     * @param userInfoKey 键值
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private String getUserInfoCache(String userInfoKey) {
        String userInfoJson = null;
        if (StringUtils.isNotBlank(userInfoKey)) {
            // cookie中存在userInfo的key, 从redis中取对应的用户信息json串
            final String key = ADMIN_USER_CACHE_KEY_PREFIX + userInfoKey;
            userInfoJson = redisClient.get(key);

            if (StringUtils.isNotBlank(userInfoJson)) {
                // 将redis缓存中取到的用户信息放到threadLocal中去
                EmployeeInfo userInfo = JsonUtils.json2Object(userInfoJson, EmployeeInfo.class);
                RequestUserHolder.setRequestUser(userInfo);
                //顺延时间 ,一定概率
                addExpareAuthTime(key);
            }
        }

        return userInfoJson;
    }

    /**
     * 功能描述: 延长redis中的用户信息有效期30分钟<br>
     * 〈功能详细描述〉
     *
     * @param key
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private void addExpareAuthTime(final String key) {
        Random ran = new Random();
        int random = ran.nextInt(percent);
        // 判断随机的与最大的相等，接进概率事件
        if (random == percent - 1) {
            redisClient.expire(key, AUTH_INFO_LIVE_TIME_IN_REDIS);
        }
    }

    /**
     * 解析UUA带过来的请求加密串中获取用户信息
     *
     * @param request
     * @author 88396455_白振华
     * @since 11:20  2018-7-2
     */
    private EmployeeInfo getEmployeeBasicInfo(HttpServletRequest request) {
        EmployeeInfo employeeInfo = null;
        //取UUA带过来的加密字符串
        String authKey = request.getParameter("authKey");
        LOGGER.info("SntkAdminAuthInterceptor.getEmployeeBasicInfo:authkey:{}", authKey);
        if (StringUtils.isNotBlank(authKey)) {
            //只有点击菜单时，才会执行这段代码
            String decrypt = AuthCryptUtil.decrypt(authKey);
            LOGGER.info("SntkAdminAuthInterceptor.getEmployeeBasicInfo:decrypt:{}", decrypt);
            //将获取后的解密数据放入cookie和redis
            if (StringUtils.isNotBlank(decrypt)) {
                try {
                    String[] cipherTexts = decrypt.split("\\|");
                    // 工号 、姓名人员所属组织 人员所属组织层级(总部，分公司，门店)、门店编码、岗位（营业员、促销员、督导、店长等）
                    // 工号
                    String staffId = cipherTexts[0];
                    // 员工姓名
                    String staffName = cipherTexts[1];
                    // 时间戳
                    String timestamp = cipherTexts[2];
                    String orgStr = cipherTexts[3];
                    OrgInfo org = JsonUtils.json2Object(orgStr, OrgInfo.class);
                    if (staffId.equals(request.getRemoteUser())) {
                        // UUA返回的工号和内部passport返回的一致，则准备人员数据
                        employeeInfo = new EmployeeInfo();
                        employeeInfo.setStaffId(staffId);
                        employeeInfo.setStaffName(staffName);

                        employeeInfo.setOrgCode(org.getOrgCode());
                        employeeInfo.setOrgName(org.getOrgName());
                        String orgLevelCode = OrgInfo.ORG_LEVEL.HQ_LEVEL;
                        // 角色层级非总部，此时 org.getLevel()的值不准确，使用 ORGID 查询其组织层级
                        if (isNotHeadquarters(org.getLevel()) && StringUtils.isNotBlank(org.getOrgCode())) {
                            OrgAttribution orgAttr = organizationService.queryOrgAttribution(org.getOrgId());
                            orgLevelCode = orgAttr.getOrgLevelCode();

                            if (OrgInfo.ORG_LEVEL.REGION_LEVEL.equals(orgLevelCode)) {
                                employeeInfo.setRegionCode(orgAttr.getDistinctCode());
                                employeeInfo.setRegion5Code(orgAttr.getAreaCode());
                                employeeInfo.setRegionName(orgAttr.getDistinctName());
                            } else if (OrgInfo.ORG_LEVEL.COMPANY_LEVEL.equals(orgLevelCode)) {
                                employeeInfo.setRegionCode(orgAttr.getDistinctCode());
                                employeeInfo.setRegion5Code(orgAttr.getAreaCode());
                                employeeInfo.setRegionName(orgAttr.getDistinctName());
                                employeeInfo.setBranchCode(orgAttr.getFinancialCode());
                                employeeInfo.setBranchName(orgAttr.getFinancialName());
                            } else if (OrgInfo.ORG_LEVEL.STORE_LEVEL.equals(orgLevelCode)) {
                                employeeInfo.setRegionCode(orgAttr.getDistinctCode());
                                employeeInfo.setRegion5Code(orgAttr.getAreaCode());
                                employeeInfo.setRegionName(orgAttr.getDistinctName());
                                employeeInfo.setBranchCode(orgAttr.getFinancialCode());
                                employeeInfo.setBranchName(orgAttr.getFinancialName());
                                employeeInfo.setStoreCode(orgAttr.getStoreCode());
                                employeeInfo.setStoreName(orgAttr.getStoreName());
                            }
                        }
                        // else 总部层级不需要处理
                        employeeInfo.setOrgLevel(orgLevelCode);
                        employeeInfo.setPositionId(org.getPosId());
                        employeeInfo.setPositionName(org.getPositionName());
                        employeeInfo.setTimestamp(timestamp);
                    }
                } catch (Exception e) {
                    LOGGER.error("SntkAdminAuthInterceptor.getEmployeeBasicInfo error.", e);
                }
            }
        }
        return employeeInfo;
    }

    /*
     * 判断角色非总部层级
     */
    private boolean isNotHeadquarters(String level) {
        return StringUtils.isNotBlank(level) && !OrgInfo.ORG_LEVEL.HQ_LEVEL.equals(level);
    }

}
