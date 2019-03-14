/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: AdvisorController
 * Author:   17061157_王薛
 * Date:     2018/7/10 19:28
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.controller.advisor;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suning.sntk.dto.advisor.CustomerManagerDto;
import com.suning.sntk.dto.advisor.ShopperDto;
import com.suning.sntk.web.service.advisor.AdvisorService;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：专业顾问 controller
 *
 * @author 17061157_王薛
 * @since 2018/7/10
 */
@RestController
@Validated
@RequestMapping("/advisor/")
public class AdvisorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvisorController.class);

    @Autowired
    private AdvisorService advisorService;

    /**
     * 功能描述: 设置客户经理 <br>
     *
     * @param customerManagerDto
     * @return com.suning.store.commons.rsf.RsfResponseDto<java.lang.String>
     * @author 17061157_王薛
     * @since 19:44  2018/7/10
     */
    @RequestMapping("bindCustomerManager.do")
    public RsfResponseDto<Void> bindCustomerManager(HttpServletRequest request, @Valid CustomerManagerDto customerManagerDto) {
        customerManagerDto.setCustomerNo(request.getRemoteUser());
        LOGGER.info("bindCustomerManager param: customerManagerDto={}", customerManagerDto);
        return advisorService.bindCustomerManager(customerManagerDto);
    }

    /**
     * 功能描述：查询店员详细信息
     *
     * @param staffId 店员工号
     * @param storeCode 店员所属门店编号
     * @author 88402362 欧小冬
     * @since 11:28 2018-07-11
     */
    @RequestMapping(value = "queryShopperInfo.do", produces = "application/json; charset=utf-8")
    public RsfResponseDto<ShopperDto> queryShopperInfo(HttpServletRequest request, String staffId, String storeCode){
        String customerNo = request.getRemoteUser();
        if(StringUtils.isBlank(customerNo) || StringUtils.isBlank(staffId) || StringUtils.isBlank(storeCode)){
            return RsfResponseDto.empty();
        }
        RsfResponseDto<ShopperDto> rsfResponseDto = advisorService.queryShopperInfo(staffId, storeCode, customerNo);
        LOGGER.info("queryShopperInfo , result[{}]", rsfResponseDto);
        return rsfResponseDto;
    }
}
