/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: NsfbusConsumerService
 * Author:   17061157_王薛
 * Date:     2018/7/7 11:28
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer;

import com.suning.nsfbus.logistics.dto.RegionInfoDto;
import com.suning.nsfbus.logistics.dto.RegionInfoListDto;
import com.suning.nsfbus.organization.rsfservice.dto.DistrictSaleOrgDto;
import com.suning.nsfbus.organization.rsfservice.dto.SiteInfoDto;

/**
 * 功能描述：Nsfbus 系统调用 consumer
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public interface NsfbusConsumerService {

    /**
     * 功能描述: 查询门店信息 <br>
     *
     * @param storeCode
     * @return com.suning.nsfbus.organization.rsfservice.dto.SiteInfoDto
     * @author 17061157_王薛
     * @since 11:38  2018/7/7
     */
    SiteInfoDto queryStoreInfo(String storeCode);

    /**
     * 功能描述: 查询大区信息 <br>
     *
     * @param orgCode
     * @return com.suning.nsfbus.organization.rsfservice.dto.DistrictSaleOrgDto
     * @author 17061157_王薛
     * @since 18:02  2018/7/13
     */
    DistrictSaleOrgDto queryAreaInfo(String orgCode);

    /**
     * 查询省、市信息
     *
     * @param parentRegionCode 父区域编码
     * @param regionalLevel    层级编码
     * @return RegionInfoListDto
     * @author 18041004_余长杰
     * @since 16:34 2018/8/30
     */
    RegionInfoListDto queryRegionListByParentCode(String parentRegionCode, String regionalLevel);

    /**
     * 查询当前城市信息
     *
     * @param regionCode    区域编码
     * @param regionalLevel 层级编码
     * @return RegionInfoDto
     * @author 18041004_余长杰
     * @since 19:53 2018/9/11
     */
    RegionInfoDto queryRegionInfoByRegionalCode(String regionCode, String regionalLevel);

    /**
     * 查询销售组织名称
     *
     * @param saleOrgCode 销售组织编码
     * @author 88396455_白振华
     * @since 10:45  2018-9-19
     */
    String querySaleOrgName(String saleOrgCode);
}
