/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AdvisorServiceImpl
 * Author:   88396455_白振华
 * Date:     2018-7-10 15:02
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.advisor.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.suning.aimp.intf.dto.CustAliasInfo;
import com.suning.aimp.intf.resp.social.QuerySocialInfoResp;
import com.suning.aimp.intf.resp.thirdparty.special.QueryWechatRelationResp;
import com.suning.nsfbus.organization.rsfservice.dto.DistrictSaleOrgDto;
import com.suning.nsfbus.organization.rsfservice.dto.SiteInfoDto;
import com.suning.nsfuaa.employee.dto.Employee;
import com.suning.sntk.constant.CustomerConstants;
import com.suning.sntk.constant.MemShoppingConstants;
import com.suning.sntk.consumer.NsfbusConsumerService;
import com.suning.sntk.consumer.NsfuaaConsumerService;
import com.suning.sntk.consumer.WechatConsumerService;
import com.suning.sntk.consumer.esb.ShopFrequencyService;
import com.suning.sntk.dao.relation.CustomerAdvisorDao;
import com.suning.sntk.dao.relation.CustomerBuyAdditionDao;
import com.suning.sntk.dao.relation.CustomerLabelRelationDao;
import com.suning.sntk.dao.relation.WechatFansDao;
import com.suning.sntk.dto.advisor.CustomerListDto;
import com.suning.sntk.dto.advisor.CustomerQueryDto;
import com.suning.sntk.dto.relation.CustomerAttrDto;
import com.suning.sntk.dto.relation.CustomerLabelStringDto;
import com.suning.sntk.dto.relation.CustomerPurchasePriceDto;
import com.suning.sntk.entity.relation.CustomerAdvisor;
import com.suning.sntk.entity.relation.CustomerLabelRelation;
import com.suning.sntk.entity.relation.WechatFans;
import com.suning.sntk.enums.MobileFlagEnum;
import com.suning.sntk.enums.SexEnum;
import com.suning.sntk.service.advisor.AdvisorService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.store.dal.base.DalBaseDao;
import com.suning.store.dal.service.AbstractDalService;

/**
 * 功能描述：专业顾问 service 类
 *
 * @author 88396455_白振华
 * @since 2018-7-10
 */
@Service
public class AdvisorServiceImpl extends AbstractDalService<CustomerAdvisor, Long> implements AdvisorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvisorServiceImpl.class);

    @Autowired
    private CustomerAdvisorDao customerAdvisorDao;

    @Autowired
    private CustomerLabelRelationDao labelRelationDao;

    @Autowired
    private WechatFansDao wechatFansDao;

    @Autowired
    private NsfuaaConsumerService nsfuaaConsumerService;

    @Autowired
    private NsfbusConsumerService nsfbusConsumerService;

    @Autowired
    private WechatConsumerService wechatConsumerService;

    @Autowired
    private ShopFrequencyService shopFrequencyService;

    @Autowired
    private CustomerBuyAdditionDao customerBuyAdditionDao;

    @Override
    protected DalBaseDao<CustomerAdvisor, Long> getDalDao() {
        return customerAdvisorDao;
    }

    @Override
    public CustomerAdvisor queryCustomer(String staffId, String unionId, String storeCode) {
        return customerAdvisorDao.queryCustomerInfo(staffId, unionId, storeCode);
    }

    @Override
    public List<CustomerListDto> queryCustomerList(CustomerQueryDto queryParam) {
        Date expectEndTime = null;
        if ("1".equals(queryParam.getIsToday())) {
            // 优先处理"是否当日跟进"标志
            expectEndTime = new Date();
        } else if (queryParam.getBuyDateType() > 0) {
            // 处理预计购买日期选项 1-最近一个月 2-最近两个月
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, queryParam.getBuyDateType());
            expectEndTime = cal.getTime();
        }
        queryParam.setExpectEndTime(expectEndTime);

        LOGGER.info("queryCustomerList,param:{}", queryParam);
        List<CustomerListDto> customerList;
        if (CustomerConstants.DEFAULT_REMARK_NAME.equals(queryParam.getCustomerName())) {
            customerList = customerAdvisorDao.queryCustomerListDefaultName(queryParam);
        } else {
            customerList = customerAdvisorDao.queryCustomerList(queryParam);
        }

        LOGGER.info("queryCustomerList,result:{}", customerList);

        if (CollectionUtils.isEmpty(customerList)) {
            return customerList;
        }

        List<String> customerNos = Lists.newArrayList();
        List<String> unionIds = Lists.newArrayList();
        for (CustomerListDto dto : customerList) {
            if (dto != null) {
                if (StringUtils.isBlank(dto.getCustName())) {
                    dto.setCustName(CustomerConstants.DEFAULT_REMARK_NAME);
                }
                customerNos.add(dto.getCustId());
                unionIds.add(dto.getUnionId());
                dto.setCreateTime(DateUtils.format(dto.getCreateTimeDate(), DateUtils.PATTEN_10));
            }
        }

        // 组装标签
        fillLabel(customerList, unionIds, queryParam.getStaffId());

        // 组装预计购买金额
        fillPurchasePrice(customerList, unionIds, queryParam.getStaffId());

        // 组装首复购信息
        fillShopFrequecy(customerList, customerNos);

        return customerList;
    }

    private void fillLabel(List<CustomerListDto> customerList, List<String> unionIds, String staffId) {
        List<CustomerLabelStringDto> customerLabelStrList = customerAdvisorDao.queryCustomersLabel(staffId, unionIds);
        //自定义标签
        List<CustomerLabelStringDto> customerSelfLabelList = customerBuyAdditionDao.querySelfLabelList(staffId, unionIds);

        Map<String, CustomerListDto> unionIdCustomerMap = Maps.newHashMap();
        for (CustomerListDto customer : customerList) {
            if (customer != null) {
                unionIdCustomerMap.put(customer.getUnionId(), customer);
            }
        }

        for (CustomerLabelStringDto dto : customerLabelStrList) {
            if (dto != null) {
                unionIdCustomerMap.get(dto.getUnionId()).getCustLabelList().add(dto.getLabelStr());
            }
        }

        for (CustomerLabelStringDto dto : customerSelfLabelList) {
            if (dto != null && StringUtils.isNotBlank(dto.getLabelStr())) {
                unionIdCustomerMap.get(dto.getUnionId()).getCustLabelList().addAll(Arrays.asList(dto.getLabelStr().split("#")));
            }
        }
    }

    private void fillPurchasePrice(List<CustomerListDto> customerList, List<String> unionIds, String staffId) {
        List<CustomerPurchasePriceDto> customerPurchaseList = customerAdvisorDao.queryCustomersPurchase(staffId, unionIds);
        Map<String, CustomerPurchasePriceDto> purchaseMap = Maps.newHashMap();
        for (CustomerPurchasePriceDto dto : customerPurchaseList) {
            if (dto != null) {
                purchaseMap.put(dto.getUnionId(), dto);
            }
        }

        for (CustomerListDto customer : customerList) {
            if (customer != null && purchaseMap.get(customer.getUnionId()) != null) {
                customer.setMinPrice(purchaseMap.get(customer.getUnionId()).getMinPrice());
                customer.setMaxPrice(purchaseMap.get(customer.getUnionId()).getMaxPrice());
            }
        }
    }

    private void fillShopFrequecy(List<CustomerListDto> customerList, List<String> customerNos) {
        Map<String, String> shopFreMap = shopFrequencyService.queryShopFrequency(customerNos);
        for (CustomerListDto dto : customerList) {
            String customerNo = dto.getCustId();
            if (customerNo == null) {
                dto.setBuyStatus(MemShoppingConstants.NOT_MEMBER);
            }

            String memValue = shopFreMap.get(customerNo);
            if (StringUtils.isNotBlank(memValue)) {
                int cnt = NumberUtils.toInt(memValue);
                dto.setBuyStatus(cnt == 0 ? MemShoppingConstants.NO_BUY
                        : (cnt == 1 ? MemShoppingConstants.FIRST_BUY : MemShoppingConstants.AGAIN_BUY));
            } else {
                dto.setBuyStatus(MemShoppingConstants.NO_BUY);
            }
        }
    }

    @Override
    public void setManagerFlag(String customerNo, String staffId) {
        customerAdvisorDao.setManagerFlag(customerNo, staffId);
    }

    @Override
    @Transactional
    public void buildFansAndAdvisorRelation(CustomerAdvisor customerAdvisor, WechatFans wechatFans) {
        //建立粉丝关系 (判断粉丝是否存在在新增)
        WechatFans wechatFan = wechatFansDao.findByOpenId(wechatFans.getOpenId());
        if (null == wechatFan || StringUtils.isBlank(wechatFan.getOpenId())) {
            wechatFansDao.insert(wechatFans);
        }

        if (!existCustomerAdvisor(customerAdvisor)) {
            //建立专业顾问关系
            customerAdvisorDao.insert(customerAdvisor);

            // 创建性别必选标签
            CustomerLabelRelation labelRelation = new CustomerLabelRelation();
            // 此处须保证性别的枚举值和标签表中的ID一致
            labelRelation.setLabelId(customerAdvisor.getSex());
            labelRelation.setStaffId(customerAdvisor.getStaffId());
            labelRelation.setUnionId(customerAdvisor.getUnionId());
            labelRelation.setCreater(customerAdvisor.getStaffId());
            labelRelation.setCreateTime(new Date());
            labelRelation.setUpdater(customerAdvisor.getStaffId());
            labelRelation.setUpdateTime(new Date());
            labelRelationDao.insert(labelRelation);
        }

    }

    /**
     * 判断顾客店员是否存在专业顾问关系
     *
     * @param customerAdvisor 专业顾问关系请求参数
     * @author 88396455_白振华
     * @since 14:00  2018-8-13
     */
    private boolean existCustomerAdvisor(CustomerAdvisor customerAdvisor) {
        CustomerAdvisor customerAdvisorResult = customerAdvisorDao.queryCustomerInfo(customerAdvisor
                .getStaffId(), customerAdvisor.getUnionId(), customerAdvisor.getStoreCode());

        return null != customerAdvisorResult && StringUtils.isNotBlank(customerAdvisorResult.getUnionId());
    }

    @Override
    public void updateLastScanTimeSkipNull(Long id, String lastScanTime) {
        customerAdvisorDao.updateLastScanTimeSkipNull(id, lastScanTime);
    }

    @Override
    public CustomerAdvisor createAdvisorEntity(CustomerAdvisor customerAdvisorReq) {
        CustomerAdvisor customerAdvisor = new CustomerAdvisor();
        // 设置店员工号
        String staffId = customerAdvisorReq.getStaffId();
        customerAdvisor.setStaffId(staffId);
        //设置店员姓名
        Employee employee = nsfuaaConsumerService.queryEmployeeBasicInfo(staffId);
        if (null != employee) {
            customerAdvisor.setStaffName(employee.getEmployeeName());
        } else {
            LOGGER.error("根据工号查询店员信息失败：employee is null");
            return null;
        }
        //设置大区、分公司信息
        String storeCode = customerAdvisorReq.getStoreCode();
        customerAdvisor.setStoreCode(storeCode);
        SiteInfoDto storeInfo = nsfbusConsumerService.queryStoreInfo(storeCode);
        if (null != storeInfo) {
            customerAdvisor.setStoreName(storeInfo.getSiteName());
            customerAdvisor.setCompanyCode(storeInfo.getCompanyCode());
            customerAdvisor.setCompanyName(storeInfo.getCompanyName());
            // 组织编码
            String orgCode = storeInfo.getSalesOrgCode();
            DistrictSaleOrgDto orgDto = nsfbusConsumerService.queryAreaInfo(orgCode);
            if (orgDto != null) {
                customerAdvisor.setAreaCode(orgDto.getAreaCode());
                customerAdvisor.setAreaName(orgDto.getAreaName());
            }
        }
        // 设置UnionId
        String unionId = customerAdvisorReq.getUnionId();
        customerAdvisor.setUnionId(unionId);
        customerAdvisor.setRemarkName(
                StringUtils.isNotBlank(customerAdvisorReq.getRemarkName()) ? customerAdvisorReq.getRemarkName() : StringUtils.EMPTY);
        customerAdvisor.setChannelType(CommonConstants.CHANNEL_GUIDE_CODE);
        String currTimeStr = DateUtils.format(new Date(), DateUtils.PATTEN_19);
        customerAdvisor.setRelationTime(currTimeStr);
        customerAdvisor.setLastScanTime(currTimeStr);
        customerAdvisor.setCreater(CommonConstants.SYS);
        customerAdvisor.setCreateTime(new Date());
        customerAdvisor.setUpdateTime(new Date());
        customerAdvisor.setUpdater(CommonConstants.SYS);
        //获取会员信息
        CustomerAttrDto customerAttrDto = obtainCustomerInfo(customerAdvisorReq.getRemarkName(), customerAdvisorReq.getSex(),
                unionId);
        customerAdvisor.setCustomerNo(customerAttrDto.getCustomerNo());
        customerAdvisor.setMemberName(customerAttrDto.getMemberName());
        customerAdvisor.setRemarkPhone(customerAttrDto.getRemarkPhone());
        customerAdvisor.setMobileFlag(customerAttrDto.getMobileFlag());
        customerAdvisor.setSex(customerAttrDto.getSex());
        return customerAdvisor;
    }

    private CustomerAttrDto obtainCustomerInfo(String nickName, String sex, String unionId) {
        CustomerAttrDto customerAttrDto = new CustomerAttrDto();
        QueryWechatRelationResp relationResp = wechatConsumerService.queryWechatRelation(unionId);
        String socialSex = null;
        if (null != relationResp && StringUtils.isNotBlank(relationResp.getCustNum())) {
            // 会员编号
            String customerNo = relationResp.getCustNum();
            customerAttrDto.setCustomerNo(customerNo);
            QuerySocialInfoResp socialInfo = wechatConsumerService.queryCustomerSocialInfo(customerNo);
            if (null != socialInfo) {
                socialSex = socialInfo.getGender();
                // 会员名称
                String partyName = socialInfo.getPartyName();
                customerAttrDto.setMemberName(StringUtils.isNotBlank(partyName) ? partyName : nickName);
                // 设置会员手机号
                String memberPhone = getMemberPhone(socialInfo.getAliasInfoList());
                if (StringUtils.isNotBlank(memberPhone)) {
                    customerAttrDto.setRemarkPhone(memberPhone);
                    customerAttrDto.setMobileFlag(MobileFlagEnum.MEMBER.getCode());
                }
            }
        } else {
            LOGGER.info("当前顾客不是易购会员");
            // 若未绑定易购会员，则会员名为微信昵称
            customerAttrDto.setMemberName(StringUtils.isNotBlank(nickName) ? nickName : CustomerConstants.DEFAULT_REMARK_NAME);
            // 无法从微信方获取此时扫码的手机号码，故手机号为空
        }

        // 性别
        customerAttrDto.setSex(getCustomerSex(sex, socialSex));
        LOGGER.info("顾客会员信息为：{}", customerAttrDto);
        return customerAttrDto;
    }

    /**
     * 功能描述: 获取会员手机号 <br>
     *
     * @param custAliasInfos 会员手机号列表
     * @return java.lang.String
     * @author 17061157_王薛
     * @since 15:42  2018/7/7
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
     * 获取性别
     *
     * @param weixinSex 微信记录性别
     * @param socialSex 会员中心记录性别
     * @author 88396455_白振华
     * @since 11:48  2018-7-11
     */
    private String getCustomerSex(String weixinSex, String socialSex) {
        if (StringUtils.isNotBlank(socialSex)) {
            return CustomerConstants.MEMBER_SEX_WOMAN.equals(socialSex) ? SexEnum.WOMAN.getCode() : SexEnum.MAN.getCode();
        } else {
            return StringUtils.isNotBlank(weixinSex) && !SexEnum.UNKNOW.getCode().equals(weixinSex)
                    ? weixinSex : SexEnum.MAN.getCode();
        }
    }
}
