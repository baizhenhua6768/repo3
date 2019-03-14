/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: NsfbusConsumerServiceImpl
 * Author:   17061157_王薛
 * Date:     2018/7/7 14:58
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.suning.nsfbus.common.Result;
import com.suning.nsfbus.logistics.dto.RegionInfoDto;
import com.suning.nsfbus.logistics.dto.RegionInfoListDto;
import com.suning.nsfbus.logistics.service.RegionInfoRsfService;
import com.suning.nsfbus.organization.rsfservice.SaleOrgManagementRsfService;
import com.suning.nsfbus.organization.rsfservice.SiteRsfService;
import com.suning.nsfbus.organization.rsfservice.dto.DistrictSaleOrgDto;
import com.suning.nsfbus.organization.rsfservice.dto.SaleOrgNameDto;
import com.suning.nsfbus.organization.rsfservice.dto.SiteInfoDto;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.consumer.NsfbusConsumerService;
import com.suning.sntk.support.exception.enums.CommonErrorEnum;
import com.suning.store.commons.lang.validator.Validators;

/**
 * 功能描述：Nsfbus 系统调用 consumer
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@Service
public class NsfbusConsumerServiceImpl implements NsfbusConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NsfbusConsumerServiceImpl.class);

    private SiteRsfService siteRsfService = ServiceLocator.getService(SiteRsfService.class, null);

    private RegionInfoRsfService regionInfoRsfService = ServiceLocator.getService(RegionInfoRsfService.class, null);

    private SaleOrgManagementRsfService saleOrgManagementRsfService = ServiceLocator.getService(SaleOrgManagementRsfService.class, null);

    @Override
    public SiteInfoDto queryStoreInfo(String storeCode) {
        LOGGER.info("queryStoreInfo param:{}", storeCode);
        SiteInfoDto siteInfo = siteRsfService.querySiteInfo(storeCode);
        LOGGER.info("queryStoreInfo response:{}", siteInfo);
        return (siteInfo != null && SiteInfoDto.SUCCESS == siteInfo.getStatus()) ? siteInfo : null;
    }

    @Override
    public DistrictSaleOrgDto queryAreaInfo(String orgCode) {
        LOGGER.info("queryAreaInfo param:{}", orgCode);
        DistrictSaleOrgDto orgDto = siteRsfService.queryDistrictCompanyRelation(orgCode);
        LOGGER.info("queryAreaInfo response:{}", orgDto);
        return (orgDto != null && DistrictSaleOrgDto.SUCCESS == orgDto.getStatus()) ? orgDto : null;
    }

    @Override
    public RegionInfoListDto queryRegionListByParentCode(String parentRegionCode, String regionalLevel) {
        LOGGER.info("queryRegionListByParentCode,params[parentRegionCode:{},regionalLevel:{}]", parentRegionCode,
                regionalLevel);
        RegionInfoListDto regionInfoListDto = regionInfoRsfService.queryRegionListByParentCode(parentRegionCode, regionalLevel);
        LOGGER.info("queryRegionListByParentCode,result[{}]", regionInfoListDto);
        Validators.ifInValid(null == regionInfoListDto || Result.FAIL == regionInfoListDto.getStatus()).thenThrow(CommonErrorEnum
                .CALL_REGION_RSF_ERROR);
        return regionInfoListDto;
    }

    @Override
    public RegionInfoDto queryRegionInfoByRegionalCode(String regionCode, String regionalLevel) {
        LOGGER.info("queryRegionInfoByRegionalCode,params[parentRegionCode:{},regionalLevel:{}]", regionCode,
                regionalLevel);
        RegionInfoDto regionInfoDto = regionInfoRsfService.queryRegionInfoByRegionalCode(regionCode, regionalLevel);
        LOGGER.info("queryRegionInfoByRegionalCode,result[{}]", regionInfoDto);
        Validators.ifInValid(null == regionInfoDto || Result.FAIL == regionInfoDto.getStatus()).thenThrow(CommonErrorEnum
                .CALL_REGION_RSF_ERROR);
        return regionInfoDto;
    }

    @Override
    public String querySaleOrgName(String saleOrgCode) {
        LOGGER.info("querySaleOrgName,params[saleOrgCode:{}]", saleOrgCode);
        SaleOrgNameDto saleOrgNameDto = saleOrgManagementRsfService.querySaleOrgName(saleOrgCode);
        LOGGER.info("querySaleOrgName,result[saleOrgNameDto:{}]", saleOrgNameDto);
        return null == saleOrgNameDto || Result.FAIL == saleOrgNameDto.getStatus() ? StringUtils.EMPTY : saleOrgNameDto.getSaleOrgName();
    }
}
