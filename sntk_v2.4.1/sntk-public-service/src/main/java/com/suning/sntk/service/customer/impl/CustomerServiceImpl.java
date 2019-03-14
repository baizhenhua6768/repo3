/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerServiceImpl
 * Author:   88395115_史小配
 * Date:     2018/7/10 15:17
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.customer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suning.mds.dto.MemShoppingGenesInfo;
import com.suning.sntk.constant.CustomerConstants;
import com.suning.sntk.consumer.MemberConsumerService;
import com.suning.sntk.dao.relation.CustomerAdvisorDao;
import com.suning.sntk.dao.relation.CustomerBuyAdditionDao;
import com.suning.sntk.dao.relation.CustomerCateIntentionDao;
import com.suning.sntk.dao.relation.CustomerLabelRelationDao;
import com.suning.sntk.dto.advisor.CustomerBrandDto;
import com.suning.sntk.dto.advisor.CustomerDetailDto;
import com.suning.sntk.dto.advisor.CustomerDetailEditDto;
import com.suning.sntk.dto.advisor.RequiredLabelTypeDto;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.entity.relation.CustomerAdvisor;
import com.suning.sntk.entity.relation.CustomerBuyAddition;
import com.suning.sntk.entity.relation.CustomerCateIntention;
import com.suning.sntk.entity.relation.CustomerLabelRelation;
import com.suning.sntk.enums.MemberLevel;
import com.suning.sntk.service.customer.CustomerService;
import com.suning.sntk.service.o2o.GuideService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.exception.enums.CommonErrorEnum;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.lang.validator.Validators;

/**
 * 功能描述：客户信息相关service实现类
 *
 * @author 88395115_史小配
 * @since 2018/7/10
 */
@Trace
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private GuideService guideService;

    @Autowired
    private CustomerAdvisorDao advisorDao;

    @Autowired
    private CustomerBuyAdditionDao buyAdditionDao;

    @Autowired
    private CustomerCateIntentionDao cateIntentionDao;

    @Autowired
    private CustomerLabelRelationDao labelRelationDao;

    @Autowired
    private MemberConsumerService memberConsumerService;

    private static final String SPLIT_FLAG = "#";

    @Override
    public CustomerDetailEditDto queryCustomerEditInfo(String staffId, String unionId) {
        CustomerDetailEditDto customerDetail = new CustomerDetailEditDto();

        //获取名称、电话
        CustomerAdvisor customerAdvisor = advisorDao.queryCustomer(staffId, unionId);
        //查询不到抛异常
        Validators.ifInValid(customerAdvisor == null).thenThrow(CommonErrorEnum.CUSTOMER_NOT_EXIST);

        customerDetail.setUnionId(customerAdvisor.getUnionId());
        customerDetail.setCustName(customerAdvisor.getMemberName());
        customerDetail.setSex(customerAdvisor.getSex());
        customerDetail.setRemarkName(customerAdvisor.getRemarkName());
        customerDetail.setCustMobile(customerAdvisor.getRemarkPhone());
        customerDetail.setMobileFlag(customerAdvisor.getMobileFlag());

        //获取必选标签
        List<RequiredLabelTypeDto> labelTypeList = labelRelationDao.queryRequiredLabelTypeList(staffId, unionId);
        for (RequiredLabelTypeDto requiredLabelTypeDto : labelTypeList) {
            requiredLabelTypeDto.setMustLabel(labelRelationDao.queryRequiredLabelList(staffId, unionId));
        }
        customerDetail.setRequiredLabel(labelTypeList);

        //获取其他标签
        customerDetail.setOtherLabel(labelRelationDao.queryOtherLabelList(staffId, unionId));

        //获取自定义标签
        CustomerBuyAddition customerBuyAddition = buyAdditionDao.querySelfLabel(staffId, unionId);
        if (customerBuyAddition != null) {
            String selfLabel = customerBuyAddition.getOtherLabel();
            customerDetail.setSelfLabel(StringUtils.isNotBlank(selfLabel) ? selfLabel.split(SPLIT_FLAG) : new String[0]);
            //获取预计购买时间、备注
            customerDetail.setBuyTime(DateUtils.format(customerBuyAddition.getExpectPurchaseTime(), CustomerConstants.TIME_FORMAT));
            customerDetail.setRemarkInfo(customerBuyAddition.getPurchaseRemark());
        }

        //获取品牌详情
        List<CustomerBrandDto> customerBrandList = cateIntentionDao.queryCateIntention(staffId, unionId);
        for (CustomerBrandDto customerBrand : customerBrandList) {
            if (null != customerBrand.getBrandName()) {
                customerBrand.setBrandName(customerBrand.getBrandName().replace("#", ","));
            }
        }
        customerDetail.setBuyDatas(customerBrandList);

        // 设置客户关系 1:客户经理,0:专业顾问
        StaffCustRel custRel = guideService.findRelByCustNoAndStaffId(staffId, customerAdvisor.getCustomerNo());
        customerDetail.setRelation(null != custRel ? CommonConstants.YES : CommonConstants.NO);
        //会员编码存在调外部接口查询会员等级、首复购信息、性别
        if (StringUtils.isNotBlank(customerAdvisor.getCustomerNo())) {
            //查询会员等级和首复购信息
            queryCustLevelAndBuyFlag(customerDetail, customerAdvisor.getCustomerNo());
        } else {
            customerDetail.setBuyFlag(CustomerConstants.NOT_MEMBER);
        }

        return customerDetail;
    }

    @Override
    @Transactional
    public boolean saveCustomerInfo(CustomerDetailDto detail) {
        // 参数校验
        CustomerAdvisor advisor = this.invalidParam(detail);
        String[] labels = detail.getCustLabel().split(SPLIT_FLAG);
        // 修改客户基本信息
        CustomerAdvisor customerAdvisor = new CustomerAdvisor();
        customerAdvisor.setId(advisor.getId());
        customerAdvisor.setRemarkName(StringUtils.isNotBlank(detail.getRemarkName()) ? detail.getRemarkName() : "");
        customerAdvisor.setRemarkPhone(StringUtils.isNotBlank(detail.getCustMobile()) ? detail.getCustMobile() : "");
        customerAdvisor.setUpdateTime(new Date());
        customerAdvisor.setUpdater(detail.getStaffId());
        customerAdvisor.setSex(labels[0]);
        advisorDao.updateCustomer(customerAdvisor);

        // 保存客户专属标签(必选，其他标签)
        this.saveLabelRelation(detail, labels);
        // 保存客户购买意向品类信息
        this.saveCateIntention(detail);
        // 保存客户购买意向附加信息(自定义标签，购买时间，备注)
        this.saveBuyAddition(detail);

        return true;
    }

    /**
     * 参数校验
     *
     * @param detail 客户信息
     * @author 88395115_史小配
     * @since 9:57 2018/7/11
     */
    private CustomerAdvisor invalidParam(CustomerDetailDto detail) {
        // 校验手机号是否11位纯数字
        String custMoblie = detail.getCustMobile();
        Validators.ifInValid(StringUtils.isNotBlank(custMoblie) && !custMoblie.matches("^\\d{11}$"))
                .thenThrow(CommonErrorEnum.MOBILE_FORMAT_ERR);
        // 校验客户是否存在
        CustomerAdvisor advisor = advisorDao.queryCustomer(detail.getStaffId(), detail.getUnionId());
        Validators.ifInValid(advisor == null)
                .thenThrow(CommonErrorEnum.CUSTOMER_NOT_EXIST);
        return advisor;
    }

    /**
     * 保存客户购买意向附加信息(自定义标签，购买时间，备注)
     *
     * @param detail 客户信息
     * @author 88395115_史小配
     * @since 17:47 2018/7/10
     */
    private void saveBuyAddition(CustomerDetailDto detail) {
        buyAdditionDao.deleteBuyAddition(detail.getStaffId(), detail.getUnionId());
        CustomerBuyAddition buyAddition = new CustomerBuyAddition();
        String buyTime = detail.getBuyTime();
        if (StringUtils.isNotBlank(detail.getRemarkInfo()) || StringUtils.isNotBlank(detail.getSelfLabel()) || StringUtils
                .isNotBlank(buyTime)) {
            String staffId = detail.getStaffId();
            buyAddition.setOtherLabel(detail.getSelfLabel());
            buyAddition.setPurchaseRemark(detail.getRemarkInfo());
            buyAddition.setStaffId(staffId);
            buyAddition.setUnionId(detail.getUnionId());
            buyAddition.setUpdater(staffId);
            buyAddition.setUpdateTime(new Date());
            if (StringUtils.isNotBlank(buyTime)) {
                buyAddition.setExpectPurchaseTime(DateUtils.parse(buyTime, DateUtils.PATTEN_10));
            }
            buyAdditionDao.insert(buyAddition);
        }
    }

    /**
     * 保存客户的购买意向品类信息
     *
     * @param detail 客户信息
     * @author 88395115_史小配
     * @since 16:56 2018/7/10
     */
    private void saveCateIntention(CustomerDetailDto detail) {
        List<CustomerBrandDto> brandDtoList = detail.getBuyDatas();
        // 删除顾客购买意向品类信息
        cateIntentionDao.deleteCateIntention(detail.getStaffId(), detail.getUnionId());
        if (CollectionUtils.isNotEmpty(brandDtoList)) {
            List<CustomerCateIntention> cateIntentionList = new ArrayList<>();
            String staffId = detail.getStaffId();
            for (CustomerBrandDto brandDto : brandDtoList) {
                CustomerCateIntention customerCateIntention = new CustomerCateIntention();
                customerCateIntention.setBrands(brandDto.getBrandName());
                customerCateIntention.setCategoryNo(brandDto.getCategoryNo());
                customerCateIntention.setCategoryName(brandDto.getCategoryName());
                customerCateIntention.setMinPrice(brandDto.getMinPrice());
                customerCateIntention.setMaxPrice(brandDto.getMaxPrice());
                customerCateIntention.setStaffId(staffId);
                customerCateIntention.setUnionId(detail.getUnionId());
                customerCateIntention.setCreater(staffId);
                customerCateIntention.setCreateTime(new Date());
                customerCateIntention.setUpdater(staffId);
                customerCateIntention.setUpdateTime(new Date());
                cateIntentionList.add(customerCateIntention);
            }
            cateIntentionDao.batchInsert(cateIntentionList);
        }
    }

    /**
     * 保存客户专属标签
     *
     * @param detail 客户信息
     * @author 88395115_史小配
     * @since 16:41 2018/7/10
     */
    private void saveLabelRelation(CustomerDetailDto detail, String[] labels) {
        String staffId = detail.getStaffId();
        // 删除数据库中已存在的专属标签
        labelRelationDao.deleteLabelRelation(detail.getUnionId(), staffId);
        List<CustomerLabelRelation> labelRelationList = new ArrayList<>();
        for (String labelId : labels) {
            CustomerLabelRelation labelRelation = new CustomerLabelRelation();
            labelRelation.setLabelId(labelId);
            labelRelation.setStaffId(staffId);
            labelRelation.setUnionId(detail.getUnionId());
            labelRelation.setCreater(staffId);
            labelRelation.setCreateTime(new Date());
            labelRelation.setUpdater(staffId);
            labelRelation.setUpdateTime(new Date());
            labelRelationList.add(labelRelation);
        }
        labelRelationDao.batchInsert(labelRelationList);

    }

    @Override
    public String queryCustomerLabelInfo(String unionId) {
        LOGGER.info("CustomerServiceImpl.queryCustomerLabelInfo,param[{}]", unionId);
        String result = labelRelationDao.queryCustomerLabelInfo(unionId);
        LOGGER.info("CustomerServiceImpl.queryCustomerLabelInfo,result[{}]", result);
        return result;
    }

    /**
     * 查询会员等级和购买标识
     *
     * @param baseDto    客户基本信息
     * @param customerNo 会员号
     * @return void
     * @author 18041004_余长杰
     * @since 9:25 2018/7/12
     */
    private void queryCustLevelAndBuyFlag(CustomerDetailEditDto baseDto, String customerNo) {
        MemShoppingGenesInfo shoppingInfo = memberConsumerService.queryMemberFirstPurchaseInfo(customerNo);
        String buyFlag = shoppingInfo.getBuyingCnt();
        if (StringUtils.isNotBlank(buyFlag)) {
            baseDto.setBuyFlag(buyFlag.equals(CustomerConstants.NO_BUY) ? CustomerConstants.NO_BUY :
                    (buyFlag.equals(CustomerConstants.FIRST_BUY) ? CustomerConstants.FIRST_BUY : CustomerConstants
                            .AGAIN_BUY));
        } else {
            baseDto.setBuyFlag(CustomerConstants.NO_BUY);
        }
        //设置等级
        if (shoppingInfo.getCustLevel().equals(MemberLevel.LEVEL_ZERO.getCode())) {
            baseDto.setMemberLevel(MemberLevel.LEVEL_ZERO.getValue());
        } else if (shoppingInfo.getCustLevel().equals(MemberLevel.LEVEL_ONE.getCode())) {
            baseDto.setMemberLevel(MemberLevel.LEVEL_ONE.getValue());
        } else if (shoppingInfo.getCustLevel().equals(MemberLevel.LEVEL_TWO.getCode())) {
            baseDto.setMemberLevel(MemberLevel.LEVEL_TWO.getValue());
        } else if (shoppingInfo.getCustLevel().equals(MemberLevel.LEVEL_THREE.getCode())) {
            baseDto.setMemberLevel(MemberLevel.LEVEL_THREE.getValue());
        } else if (shoppingInfo.getCustLevel().equals(MemberLevel.LEVEL_FOUR.getCode())) {
            baseDto.setMemberLevel(MemberLevel.LEVEL_FOUR.getValue());
        } else {
            baseDto.setMemberLevel(CustomerConstants.NO_LEVEL);
        }

    }

}
