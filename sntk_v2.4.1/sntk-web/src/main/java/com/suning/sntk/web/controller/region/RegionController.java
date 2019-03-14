/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: RegionController
 * Author:   88396455_白振华
 * Date:     2018-7-5 9:51
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.controller.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suning.nsfbus.logistics.dto.RegionInfoDto;
import com.suning.nsfbus.logistics.dto.RegionInfoListDto;
import com.suning.sntk.web.service.region.RegionInfoService;
import com.suning.store.commons.lang.advice.Trace;

/**
 * 功能描述：区域信息Controller
 *
 * @author 88396455_白振华
 * @since 2018-7-5
 */
@RestController
@Trace
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionInfoService regionInfoService;

    /**
     * 查询所有城市
     *
     * @param country 国家编码
     * @author 88396455_白振华
     * @since 9:14  2018-7-5
     */
    @RequestMapping("/queryAllCity")
    public RegionInfoListDto queryAllCity(String country) {
        return regionInfoService.queryAllCity(country);
    }

    /**
     * 查询下属区域信息
     *
     * @param parentRegionCode 父区域编码
     * @param regionalLevel    层级编码
     * @author 88396455_白振华
     * @since 9:26  2018-7-5
     */
    @RequestMapping("/queryRegionList")
    public RegionInfoListDto queryRegionListByParentCode(String parentRegionCode, String regionalLevel) {
        return regionInfoService.queryRegionListByParentCode(parentRegionCode, regionalLevel);
    }

    /**
     * 查询当前区域信息
     *
     * @param regionalCode  区域编码
     * @param regionalLevel 层级编码
     * @author 88396455_白振华
     * @since 9:26  2018-7-5
     */
    @RequestMapping("/queryRegionInfo")
    public RegionInfoDto queryRegionInfoByRegionalCode(String regionalCode, String regionalLevel) {
        return regionInfoService.queryRegionInfoByRegionalCode(regionalCode, regionalLevel);
    }

    /**
     * 根据区域编码和层级编码查询上级区域信息
     *
     * @param regionalCode  区域编码
     * @param regionalLevel 层级编码
     * @author 88396455_白振华
     * @since 9:26  2018-7-5
     */
    @RequestMapping("/queryParentRegion")
    public RegionInfoDto queryParentRegionByCode(String regionalCode, String regionalLevel) {
        return regionInfoService.queryParentRegionByCode(regionalCode, regionalLevel);
    }
}
