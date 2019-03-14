/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: AdvisorServiceImpl
 * Author:   17061157_王薛
 * Date:     2018/7/10 19:41
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.advisor.impl;

import org.springframework.stereotype.Service;

import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.dto.advisor.CustomerManagerDto;
import com.suning.sntk.dto.advisor.ShopperDto;
import com.suning.sntk.rsf.advisor.AdvisorRsfService;
import com.suning.sntk.web.service.advisor.AdvisorService;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：专业顾问 service 实现类
 *
 * @author 17061157_王薛
 * @since 2018/7/10
 */
@Service
public class AdvisorServiceImpl implements AdvisorService {

    private AdvisorRsfService advisorRsfService = ServiceLocator.getService(AdvisorRsfService.class, "1.0.0");

    @Override
    public RsfResponseDto<Void> bindCustomerManager(CustomerManagerDto customerManagerDto) {
        return advisorRsfService.bindCustomerManager(customerManagerDto);
    }

    @Override
    public RsfResponseDto<ShopperDto> queryShopperInfo(String staffId, String storeCode, String customerNo) {
        return advisorRsfService.queryShopperInfo(staffId, storeCode, customerNo);
    }
}
