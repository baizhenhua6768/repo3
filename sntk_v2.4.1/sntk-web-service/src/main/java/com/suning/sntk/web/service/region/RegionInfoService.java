/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: RegionInfoService
 * Author:   88396455_白振华
 * Date:     2018-7-6 9:38
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.region;

import com.suning.nsfbus.logistics.dto.RegionInfoDto;
import com.suning.nsfbus.logistics.dto.RegionInfoListDto;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-7-6
 */
public interface RegionInfoService {
    /**
     * 查询所有城市
     *
     * @param country 国家编码
     * @author 88396455_白振华
     * @since 9:14  2018-7-5
     */
    RegionInfoListDto queryAllCity(String country);

    /**
     * 查询下属区域信息
     *
     * @param parentRegionCode 父区域编码
     * @param regionalLevel    层级编码
     * @author 88396455_白振华
     * @since 9:26  2018-7-5
     */
    RegionInfoListDto queryRegionListByParentCode(String parentRegionCode, String regionalLevel);

    /**
     * 查询当前区域信息
     *
     * @param regionalCode  区域编码
     * @param regionalLevel 层级编码
     * @author 88396455_白振华
     * @since 9:26  2018-7-5
     */
    RegionInfoDto queryRegionInfoByRegionalCode(String regionalCode, String regionalLevel);

    /**
     * 根据区域编码和层级编码查询上级区域信息
     *
     * @param regionalCode  区域编码
     * @param regionalLevel 层级编码
     * @author 88396455_白振华
     * @since 9:26  2018-7-5
     */
    RegionInfoDto queryParentRegionByCode(String regionalCode, String regionalLevel);
}
