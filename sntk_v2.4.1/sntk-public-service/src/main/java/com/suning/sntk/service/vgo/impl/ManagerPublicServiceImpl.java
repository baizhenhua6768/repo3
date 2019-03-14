/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ManagerPublicServiceImpl
 * Author:   18032490_赵亚奇
 * Date:     2018/8/23 9:25
 * Description: 客户经理模块实现类
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 * 赵亚奇                2018/8/23 9:25      2.2.1
 */

package com.suning.sntk.service.vgo.impl;

import com.google.common.collect.Lists;
import com.suning.aimp.intf.dto.CustAliasInfo;
import com.suning.aimp.intf.resp.social.QuerySocialInfoResp;
import com.suning.nsfbus.organization.rsfservice.dto.DistrictSaleOrgDto;
import com.suning.nsfbus.organization.rsfservice.dto.SiteInfoDto;
import com.suning.nsfuaa.employee.dto.Employee;
import com.suning.sntk.constant.CustomerConstants;
import com.suning.sntk.consumer.NsfbusConsumerService;
import com.suning.sntk.consumer.NsfuaaConsumerService;
import com.suning.sntk.consumer.WechatConsumerService;
import com.suning.sntk.dao.o2o.GuideDao;
import com.suning.sntk.dao.relation.CustomerAdvisorDao;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dao.vgo.StaffBeStoreDao;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.entity.relation.CustomerAdvisor;
import com.suning.sntk.enums.SexEnum;
import com.suning.sntk.service.vgo.ManagerPublicService;
import com.suning.sntk.service.vgo.VgoModifyRedisService;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.exception.vgo.AppointmentErrorEnum;
import com.suning.sntk.support.util.scm.ScmPropertiesUtil;
import com.suning.sntk.support.util.scm.ScmPropertyFileEnum;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 客户经理模块实现类
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/23
 */
@Service
@Trace
public class ManagerPublicServiceImpl implements ManagerPublicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerPublicServiceImpl.class);

    @Autowired
    private NsfuaaConsumerService employeeService;

    @Autowired
    private GuideDao guideDao;

    @Autowired
    private VgoModifyRedisService vgoModifyRedisService;

    @Autowired
    private StaffBeStoreDao staffBeStoreDao;

    @Autowired
    private CustomerAdvisorDao customerAdvisorDao;

    @Autowired
    private NsfbusConsumerService nsfbusConsumerService;

    @Autowired
    private WechatConsumerService wechatConsumerService;

    @Autowired
    private GuideInfoDao guideInfoDao;

    private static final String SYSTEM = "system";
    /**
     * 是客户经理标志
     */
    private static final int MANAGER_FLAG = 1;

    /**
     * 会员手机号
     */
    private static final int CUST_PHONE = 1;

    private static final String SCM_CHANNEL = "scmChannel";

    /**
     * 客户经理渠道：微信面对面0,微信线上1,微信会话（预留）2,小程序面对面3,小程序线上4,小程序会话5,易购微服务6,预存7,预售8,易购店员主页9,易购会话10
     * v购渠道：易购app_店员主页-四级页 30,易购app_卡片页-四级页	31,易购app_我的客户经理	32,易购app_会话	33,易购app_预约列表,34,易购app_预约详情页
     * 35,微信36,微信线上37，微信会话	38,门店小程序-面对面场景39,	门店小程序-线上场景40,门店小程序会话页41
     */
    private static final Map<String, String> channelMap = new HashMap<>();

    static {
        //把V购渠道转换为客户经理的渠道
        // scmChannel=30-9,31-9,32-9,34-9,35-9,33-10,36-0,37-1,38-2,39-3,40-4,41-5,42-11
        String scmChannel = ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG, SCM_CHANNEL);
        if (StringUtils.isNotBlank(scmChannel)) {
            String[] channels = scmChannel.split(",");
            for (String channel : channels) {
                String[] s = channel.split("-");
                channelMap.put(s[0], s[1]);
            }
        }
    }

    /**
     * 查询客户经理关系(所有业态)
     *
     * @param custNo 会员编码
     * @author 18032490_赵亚奇
     * @since 10:23  2018/9/5
     */
    @Override
    public List<ManagerInfoDto> queryManagerList(String custNo) {
        //查询客户经理列表
        List<ManagerInfoDto> managerList = guideDao.queryManagerByCustNo(custNo);
        LOGGER.info("查询客户经理关系列表结果：managerList:{}", managerList);
        return managerList;
    }

    /**
     * 查询原有的客户经理
     *
     * @param custNo    会员编码
     * @param staffId   员工工号
     * @param storeCode 门店编码
     * @author 18032490_赵亚奇
     * @since 10:23  2018/9/5
     */
    @Override
    public List<GuideInfoDto> queryOldManager(String custNo, String staffId, String storeCode) {
        //查询门店对应的业态
        String businessType = staffBeStoreDao.queryBusinessType(staffId);
        Validators.ifInValid(StringUtils.isBlank(businessType)).thenThrow(AppointmentErrorEnum.STORE_CODE_NOT_EXIST);
        //查询会员是否存在此业态下的客户经理
        ManagerInfoDto managerInfoDto = guideDao.queryManagerByCustNoAndBusiness(custNo, businessType);
        LOGGER.info("会员已绑定的客户经理 manager:{}", managerInfoDto);
        if (managerInfoDto != null) {
            //查找原有客户经理的信息
            return guideInfoDao.queryGuideDetailList(Lists.newArrayList(managerInfoDto
                    .getStaffId()));
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * 建立客户经理关系
     *
     * @param custNo    会员编码
     * @param staffId   员工工号
     * @param storeCode 门店编码
     * @param channel   渠道
     * @author 18032490_赵亚奇
     */
    @Override
    @Transactional
    public RsfResponseDto buildManagerRelation(String custNo, String staffId, String storeCode, String channel) {
        //查询门店对应的业态
        String businessType = staffBeStoreDao.queryBusinessType(staffId);
        Validators.ifInValid(StringUtils.isBlank(businessType)).thenThrow(AppointmentErrorEnum.STORE_CODE_NOT_EXIST);
        //查询会员是否存在此业态下的客户经理
        ManagerInfoDto managerInfoDto = guideDao.queryManagerByCustNoAndBusiness(custNo, businessType);
        LOGGER.info("会员已绑定的客户经理 manager:{}", managerInfoDto);
        //如果存在则解除客户经理关系
        if (managerInfoDto != null) {
            guideDao.deleteFlagById(managerInfoDto.getId());
        }
        //客户经理渠道转换对应关系
        String newChannel = channelMap.get(channel);
        Validators.ifInValid(null == newChannel).thenThrow(AppointmentErrorEnum.NOT_FIND_DATA);
        //建立客户经理关系
        StaffCustRel staffCustRel = saveManager(custNo, staffId, storeCode, businessType, newChannel);
        //设置专属顾问
        intercalateCustomerAdvisor(custNo, staffId, storeCode, staffCustRel);
        //更新缓存
        vgoModifyRedisService.changeCustomerManagerCache(custNo, staffId, businessType);

        return RsfResponseDto.success();
    }

    /**
     * 将客户经理保存到数据库当中
     *
     * @param custNo       会员编码
     * @param staffId      工号
     * @param storeCode    门店编码
     * @param businessType 业态
     * @param channel      渠道
     * @author 18032490_赵亚奇
     * @since 14:09  2018/9/3
     */
    private StaffCustRel saveManager(String custNo, String staffId, String storeCode, String businessType, String channel) {
        //建立关系（定位到业态）
        StaffCustRel staffCustRel = saveStaffCustRel(custNo, staffId, storeCode, businessType, channel);
        guideDao.insert(staffCustRel);
        return staffCustRel;
    }

    /**
     * 设置专属顾问关系
     *
     * @param custNo       会员编码
     * @param staffId      工号
     * @param storeCode    门店编码
     * @param staffCustRel 客户经理信息
     * @author 18032490_赵亚奇
     * @since 16:30  2018/8/30
     */
    private void intercalateCustomerAdvisor(String custNo, String staffId, String storeCode, StaffCustRel staffCustRel) {
        //查询是否存在专属顾问
        CustomerAdvisor advisor = customerAdvisorDao.queryInfoByStaffAndCustNo(custNo, staffId);
        LOGGER.info("从数据库中查询此会员是否存在专属顾问关系 advisor:{}", advisor);
        //存在设置为客户经理标志
        if (null != advisor && MANAGER_FLAG != advisor.getManagerFlag()) {
            advisor.setManagerFlag(MANAGER_FLAG);
            advisor.setUpdateTime(new Date());
            customerAdvisorDao.updateSkipNull(advisor);
            return;
        }
        if (null == advisor) {
            advisor = new CustomerAdvisor();
            //获取大区分公司信息
            SiteInfoDto storeInfo = nsfbusConsumerService.queryStoreInfo(storeCode);
            // 组织编码
            String orgCode = storeInfo.getSalesOrgCode();
            DistrictSaleOrgDto orgDto = nsfbusConsumerService.queryAreaInfo(orgCode);
            if (orgDto != null) {
                advisor.setAreaCode(orgDto.getAreaCode());
                advisor.setAreaName(orgDto.getAreaName());
            }
            //不存在则插入一条数据
            advisor.setStaffId(staffId);
            advisor.setStaffName(staffCustRel.getStaffName());
            advisor.setCompanyCode(storeInfo.getCompanyCode());
            advisor.setCompanyName(storeInfo.getCompanyName());
            advisor.setStoreCode(storeCode);
            advisor.setStoreName(storeInfo.getSiteName());
            advisor.setCreater(SYSTEM);
            advisor.setUpdater(SYSTEM);
            advisor.setMobileFlag(CUST_PHONE);
            advisor.setMemberName(CustomerConstants.DEFAULT_REMARK_NAME);
            advisor.setChannelType(staffCustRel.getChannel());
            advisor.setCustomerNo(custNo);
            advisor.setRelationTime(DateUtils.format(new Date(), DateUtils.PATTEN_19));
            advisor.setManagerFlag(MANAGER_FLAG);
            advisor.setCreateTime(new Date());
            advisor.setUpdateTime(new Date());
            //获取会员信息
            QuerySocialInfoResp socialInfo = wechatConsumerService.queryCustomerSocialInfo(custNo);
            if (socialInfo != null) {
                advisor.setRemarkName(socialInfo.getPartyName());
                // 转换会员接口的性别,为空则设置默认性别，名称
                advisor.setSex(CustomerConstants.MEMBER_SEX_MAN.equals(socialInfo.getGender()) ?
                        SexEnum.MAN.getCode() : SexEnum.WOMAN.getCode());
                // 设置会员手机号
                String memberPhone = getMemberPhone(socialInfo.getAliasInfoList());
                advisor.setRemarkPhone(memberPhone);
            }
            LOGGER.info("绑定专属顾问关系,customerAdvisor:{}", advisor);
            customerAdvisorDao.insert(advisor);
        }
    }

    /**
     * 获取会员手机号
     *
     * @param custAliasInfos 会员信息列表
     */
    private String getMemberPhone(List<CustAliasInfo> custAliasInfos) {
        if (CollectionUtils.isNotEmpty(custAliasInfos)) {
            for (CustAliasInfo custAliasInfo : custAliasInfos) {
                if (custAliasInfo.getAliasType().equals(CustomerConstants.MEMBER_MOBILE_KEY)) {
                    return custAliasInfo.getCustAlias();
                }
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * 查询客户经理关系(区分业态)
     *
     * @param custNo  会员编码
     * @param staffId 员工编码
     * @author 18032490_赵亚奇
     */
    @Override
    public ManagerInfoDto queryManagerInfo(String custNo, String staffId) {
        //查询门店对应的业态
        String businessType = staffBeStoreDao.queryBusinessType(staffId);
        Validators.ifInValid(StringUtils.isBlank(businessType)).thenThrow(AppointmentErrorEnum.STORE_CODE_NOT_EXIST);
        //查询客户经理
        return guideDao.queryManagerByCustNoAndBusiness(custNo, businessType);
    }

    /**
     * 根据会员编码和导购工号，绑定关系
     *
     * @param custNo       会员编码
     * @param staffId      工号
     * @param storeCode    门店编码
     * @param businessType 业态
     * @param channel      渠道
     */
    private StaffCustRel saveStaffCustRel(String custNo, String staffId, String storeCode, String businessType, String channel) {
        StaffCustRel staffCustRel = new StaffCustRel();
        Employee employee = employeeService.queryEmployeeBasicInfo(staffId);
        LOGGER.info("调用nsf接口根据工号查询员工姓名,staffId:{},staff:{}", staffId, employee);
        if (employee != null) {
            staffCustRel.setStaffName(employee.getEmployeeName());
        }
        staffCustRel.setCustNo(custNo);
        staffCustRel.setStaffId(staffId);
        staffCustRel.setStoreCode(storeCode);
        staffCustRel.setChannel(channel);
        staffCustRel.setBusinessType(businessType);
        staffCustRel.setDeleteFlag(O2OConstants.BUILD_FLAG);
        staffCustRel.setCreateTime(DateUtils.format(new Date()));
        return staffCustRel;
    }

    /**
     * 功能：查询导购工号
     *
     * @param customerNo   会员编码
     * @param businessType 业态
     * @author 18010645_黄成
     * @since 17:12 2018/10/16
     */
    @Override
    public String queryManagerInfoNew(String customerNo, String businessType) {
        //查询导购工号
        String guideId = staffBeStoreDao.queryManagerInfoNew(customerNo, businessType);
        LOGGER.info("根据会员编码、业态查询导购工号,customerNo:{},businessType：{}", customerNo, businessType);
        if (StringUtils.isNotBlank(guideId)) {
            return guideId;
        }
        return null;
    }


}
