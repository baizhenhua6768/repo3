/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: RegionInfoServiceImpl
 * Author:   88396455_白振华
 * Date:     2018-7-6 9:38
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.region.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.suning.nsfbus.common.Result;
import com.suning.nsfbus.logistics.dto.RegionInfoDto;
import com.suning.nsfbus.logistics.dto.RegionInfoListDto;
import com.suning.nsfbus.logistics.service.RegionInfoRsfService;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.support.exception.enums.CommonErrorEnum;
import com.suning.sntk.web.service.region.RegionInfoService;
import com.suning.store.commons.lang.validator.Validators;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-7-6
 */
@Service
public class RegionInfoServiceImpl implements RegionInfoService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RegionInfoServiceImpl.class);

    private RegionInfoRsfService regionInfoRsfService = ServiceLocator.getService(RegionInfoRsfService.class, null);

    @Override
    public RegionInfoListDto queryAllCity(String country) {
        LOGGER.info("regionInfoRsfService.queryAllCity,params[country:{}]", country);
        RegionInfoListDto regionInfoListDto = regionInfoRsfService.queryAllCity(country);
        LOGGER.info("regionInfoRsfService.queryAllCity,result[{}]", regionInfoListDto);
        Validators.ifInValid(null == regionInfoListDto || Result.FAIL == regionInfoListDto.getStatus()).thenThrow(CommonErrorEnum
                .CALL_REGION_RSF_ERROR);
        return regionInfoListDto;
    }

    @Override
    public RegionInfoListDto queryRegionListByParentCode(String parentRegionCode, String regionalLevel) {
        LOGGER.info("regionInfoRsfService.queryRegionListByParentCode,params[parentRegionCode:{},regionalLevel:{}]", parentRegionCode,
                regionalLevel);
        RegionInfoListDto regionInfoListDto = regionInfoRsfService.queryRegionListByParentCode(parentRegionCode, regionalLevel);
        LOGGER.info("regionInfoRsfService.queryRegionListByParentCode,result[{}]", regionInfoListDto);
        Validators.ifInValid(null == regionInfoListDto || Result.FAIL == regionInfoListDto.getStatus()).thenThrow(CommonErrorEnum
                .CALL_REGION_RSF_ERROR);
        return regionInfoListDto;
    }

    @Override
    public RegionInfoDto queryRegionInfoByRegionalCode(String regionalCode, String regionalLevel) {
        LOGGER.info("regionInfoRsfService.queryRegionInfoByRegionalCode,params[regionalCode:{},regionalLevel:{}]", regionalCode,
                regionalLevel);
        RegionInfoDto regionInfoDto = regionInfoRsfService.queryRegionInfoByRegionalCode(regionalCode, regionalLevel);
        LOGGER.info("regionInfoRsfService.queryRegionInfoByRegionalCode,result[{}]", regionInfoDto);
        Validators.ifInValid(null == regionInfoDto || Result.FAIL == regionInfoDto.getStatus()).thenThrow(CommonErrorEnum
                .CALL_REGION_RSF_ERROR);
        return regionInfoDto;
    }

    @Override
    public RegionInfoDto queryParentRegionByCode(String regionalCode, String regionalLevel) {
        LOGGER.info("regionInfoRsfService.queryParentRegionByCode,params[regionalCode:{},regionalLevel:{}]", regionalCode,
                regionalLevel);
        RegionInfoDto regionInfoDto = regionInfoRsfService.queryParentRegionByCode(regionalCode, regionalLevel);
        LOGGER.info("regionInfoRsfService.queryParentRegionByCode,result[{}]", regionInfoDto);
        Validators.ifInValid(null == regionInfoDto || Result.FAIL == regionInfoDto.getStatus()).thenThrow(CommonErrorEnum
                .CALL_REGION_RSF_ERROR);
        return regionInfoDto;
    }
}
