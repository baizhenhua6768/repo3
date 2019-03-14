/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: AdvisorRsfServiceImpl
 * Author:   17061157_王薛
 * Date:     2018/7/7 20:42
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.impl.advisor;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.suning.rsf.provider.annotation.Implement;
import com.suning.sntk.dto.advisor.CustomerDetailDto;
import com.suning.sntk.dto.advisor.CustomerDetailEditDto;
import com.suning.sntk.dto.advisor.CustomerListDto;
import com.suning.sntk.dto.advisor.CustomerManagerDto;
import com.suning.sntk.dto.advisor.CustomerQueryDto;
import com.suning.sntk.dto.advisor.ShopperDto;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.rsf.advisor.AdvisorRsfService;
import com.suning.sntk.service.advisor.AdvisorService;
import com.suning.sntk.service.customer.CustomerService;
import com.suning.sntk.service.o2o.GuideService;
import com.suning.sntk.service.shopper.ShopperService;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.rsf.RsfResponseDto;
import com.suning.store.commons.rsf.advice.RsfAdvice;

/**
 * 功能描述：专业顾问 rsf 实现类
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@Implement(contract = AdvisorRsfService.class, implCode = "1.0.0")
@Service
@Trace
@Validated
@RsfAdvice
public class AdvisorRsfServiceImpl implements AdvisorRsfService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvisorRsfServiceImpl.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private GuideService guideService;

    @Autowired
    private ShopperService shopperService;

    @Override
    public RsfResponseDto<ShopperDto> queryShopperInfo(String staffId, String storeCode, String customerNo) {
        LOGGER.info("AdvisorRsfServiceImpl.queryShopperInfo,param[staffId:{},storeCode:{},customerNo:{}]"
                , staffId, storeCode, customerNo);
        ShopperDto shopperDto = shopperService.queryShopperInfo(staffId, storeCode, customerNo);
        LOGGER.info("AdvisorRsfServiceImpl.queryShopperInfo,result[[]]", shopperDto);
        return RsfResponseDto.of(shopperDto);
    }

    @Override
    public RsfResponseDto<List<CustomerListDto>> queryCustomerList(CustomerQueryDto queryParam) {
        LOGGER.info("AdvisorRsfServiceImpl.queryCustomerList,param[queryParam:{}]", queryParam);
        List<CustomerListDto> customerList = advisorService.queryCustomerList(queryParam);
        LOGGER.info("AdvisorRsfServiceImpl.queryCustomerList,result[{}]", customerList);
        return RsfResponseDto.of(customerList);
    }

    @Override
    @Transactional
    public RsfResponseDto<Void> bindCustomerManager(CustomerManagerDto dto) {
        LOGGER.info("AdvisorRsfServiceImpl.bindCustomerManager,param[CustomerManagerDto:{}]", dto);

        // 如果已有客户经理(只会有一条)，则删除
        StaffCustRel custRel = guideService.queryRelByCustNo(dto.getCustomerNo());
        if (custRel != null && custRel.getStaffId() != null) {
            // 解除已有的客户经理关系
            guideService.deleteRelByStaffIdAndCustNo(custRel.getStaffId(), dto.getCustomerNo());
        }

        // 设置新的客户经理
        saveStaffCustRel(dto);

        // 更新专业顾问表的客户经理标志
        advisorService.setManagerFlag(dto.getCustomerNo(), dto.getStaffId());

        return RsfResponseDto.empty();
    }

    /**
     * 根据会员编码和导购工号，做绑定关系
     */
    private void saveStaffCustRel(CustomerManagerDto dto) {
        StaffCustRel staffCustRel = new StaffCustRel();
        staffCustRel.setCustNo(dto.getCustomerNo());
        staffCustRel.setStaffId(dto.getStaffId());
        staffCustRel.setStaffName(dto.getStaffName());
        staffCustRel.setStoreCode(dto.getStoreCode());
        staffCustRel.setChannel(O2OConstants.CHANNEL_ONLINE);
        staffCustRel.setCreateTime(DateUtils.format(new Date()));
        LOGGER.info("AdvisorRsfServiceImpl.saveStaffCustRel,param: [{}]", staffCustRel);
        guideService.saveStaffCustRel(staffCustRel);
    }

    @Override
    public RsfResponseDto<CustomerDetailEditDto> queryCustomerEditInfo(String staffId, String unionId) {
        LOGGER.info("AdvisorRsfServiceImpl.queryCustomerEditInfo,params[staffId:{},unionId:{}]",
                staffId, unionId);
        CustomerDetailEditDto detailEditDto = customerService.queryCustomerEditInfo(staffId, unionId);
        LOGGER.info("AdvisorRsfServiceImpl.queryCustomerEditInfo,result[detailEditDto:{}]", detailEditDto);
        return RsfResponseDto.of(detailEditDto);
    }

    @Override
    public RsfResponseDto<Boolean> updateCustomerDetail(CustomerDetailDto detailDto) {
        LOGGER.info("AdvisorRsfServiceImpl.updateCustomerDetail[CustomerDetailDto:{}]", detailDto);
        return RsfResponseDto.of(customerService.saveCustomerInfo(detailDto));
    }
}
