/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: WechatPublicAccountServiceImpl
 * Author:   18032490_赵亚奇
 * Date:     2018/10/10 15:34
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.vgo.impl;

import java.text.MessageFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.suning.sntk.admin.dao.vgo.GuideInfoAdminDao;
import com.suning.sntk.admin.kafka.KafkaService;
import com.suning.sntk.admin.service.vgo.WechatPublicAccountService;
import com.suning.sntk.admin.util.SCMConfigUtil;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.PositionDto;
import com.suning.sntk.dto.vgo.WechatJsonDto;
import com.suning.sntk.dto.vgo.WechatPublicAccountDto;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.support.util.URLEnCodeUtils;

/**
 * 微信公众号相关功能
 *
 * @author 18032490_赵亚奇
 * @since 2018/10/10
 */
public class WechatPublicAccountServiceImpl implements WechatPublicAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatPublicAccountServiceImpl.class);

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private GuideInfoAdminDao guideInfoAdminDao;

    /**
     * 小程序appId
     */
    private static final String MINI_APP_ID = "wx82dcefe32842671e";

    /**
     * 易购业态下h5跳转路径
     */
    private static final String ELECTRICAL_H5_URL = "{0}/sntk-web/vgo/salesManInfo.html?guideId={1}&channel=36&businessType={2}";

    /**
     * 母婴业态下h5跳转路径
     */
    private static final String MOTHER_H5_URL = "{0}/sntk-web/vgo/redBaby.html?channel=36&businessType=2&_organ={1}&_cusdata={2}";

    /**
     * 小程序跳转路径
     */
    private static final String APP_URL = "/pages/my/online/detail/detail?guideId={0}&storeType={1}&storeCode={2}&isShare=1&position={3}";

    /**
     * 微信公众号跳转路径 1.跳转到小程序 2.跳转到h5页面
     */
    private static final String WECHAT_URL_TRANSITION_KEY = "wechatUrlTransitionKey";

    /**
     * 跳转到小程序标识符
     */
    private static final String APP_URL_ID = "1";

    /**
     * 手机号匹配正则表达式
     */
    private static final String PHONE_REGEX = "(\\d{3})\\d{4}(\\d{4})";

    /**
     * 手机脱敏格式
     */
    private static final String PHONE_FORM = "$1****$2";

    /**
     * 易购业态
     */
    private static final String ELECTRICAL_TYPE = "1";

    /**
     * 母婴业态
     */
    private static final String MOTHER_TYPE = "2";

    /**
     * scm易购appId的key
     */
    private static final String ELECTRICAL_APP_ID = "electricalAppId";

    /**
     * scm母婴appId的key
     */
    private static final String MOTHER_APP_ID = "motherAppId";

    /**
     * scm易购模板消息的key
     */
    private static final String ELECTRICAL_TEMPLATE_ID = "electricalTemplateId";

    /**
     * scm母婴模板消息的key
     */
    private static final String MOTHER_TEMPLATE_ID = "motherTemplateId";

    /**
     * 模板消息头信息
     */
    private static final String HEAD_MSG = "您的专属导购来了，请让我为您提供专业服务";
    /**
     * 模板消息尾信息
     */
    private static final String END_MSG = "详情";
    private String tipUrl;

    private String wechatTopic;

    public void setTipUrl(String tipUrl) {
        this.tipUrl = tipUrl;
    }

    public void setWechatTopic(String wechatTopic) {
        this.wechatTopic = wechatTopic;
    }

    /**
     * 用户扫码和关注事件发送消息到会员集市STORM系统
     *
     * @param openId    公众号openId
     * @param staffId   员工工号
     * @param storeCode 门店编码
     */
    @Override
    public void scanCodeAffair(String openId, String staffId, String storeCode) {
        LOGGER.info("发送模板消息,staffId:{},storeCode:{},openId:{}", staffId, storeCode, openId);
        //获取导购和门店信息
        GuideInfoDto guideInfo = guideInfoAdminDao.queryByGuideId(staffId);
        if (null == guideInfo) {
            LOGGER.info("从数据库查出的店员信息为空");
            return;
        }
        String businessType = guideInfo.getBusinessType();
        //跳转小程序或者h5的开关
        String key = SCMConfigUtil.getConfig(WECHAT_URL_TRANSITION_KEY, APP_URL_ID);
        WechatPublicAccountDto dto = new WechatPublicAccountDto();
        WechatJsonDto wx = new WechatJsonDto();
        dto.setReceiveTime(DateUtils.formatPatten10(new Date()));
        wx.setOpenId(openId);
        if (ELECTRICAL_TYPE.equals(businessType)) {
            //易购
            wx.setAppId(SCMConfigUtil.getConfig(ELECTRICAL_APP_ID));
            wx.setTemplateId(SCMConfigUtil.getConfig(ELECTRICAL_TEMPLATE_ID));
            wx.setUrl(MessageFormat.format(ELECTRICAL_H5_URL, tipUrl, staffId, businessType));
        } else if (MOTHER_TYPE.equals(businessType)) {
            //母婴
            wx.setAppId(SCMConfigUtil.getConfig(MOTHER_APP_ID));
            wx.setTemplateId(SCMConfigUtil.getConfig(MOTHER_TEMPLATE_ID));
            wx.setUrl(MessageFormat.format(MOTHER_H5_URL, tipUrl, storeCode, staffId));
        } else {
            LOGGER.info("业态类型错误，不推送消息; bussinessType:{}", businessType);
            return;
        }
        wx.setFirst(HEAD_MSG);
        wx.setKeyword1(guideInfo.getGuideName());
        wx.setKeyword2(guideInfo.getTele().replaceAll(PHONE_REGEX, PHONE_FORM));
        wx.setKeyword3(guideInfo.getAddress());
        wx.setRemark(END_MSG);
        if (APP_URL_ID.equals(key)) {
            //获取门店经纬度
            PositionDto positionDto = guideInfoAdminDao.findStoreInfo(storeCode);
            if (null != positionDto) {
                positionDto.setCityCode(guideInfo.getCityCode());
                positionDto.setCityName(guideInfo.getCityName());
            }
            //跳转到小程序页面，需要传送appId
            wx.setMiniProAppId(MINI_APP_ID);
            wx.setMiniPagepath(MessageFormat.format(APP_URL, staffId, businessType, storeCode, URLEnCodeUtils.urlEncode(JsonUtils
                    .object2Json(positionDto))));
        }

        dto.setWechatJsonDto(wx);
        kafkaService.sendMessage(wechatTopic, JsonUtils.object2Json(dto));
    }

}
