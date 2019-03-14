/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: O2oStoreDetailDao
 * Author:   88396455_白振华
 * Date:     2018-8-17 14:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.dao.vgo;

import java.util.List;

import com.suning.sntk.admin.vo.CompanyVo;
import com.suning.sntk.admin.vo.StoreVo;
import com.suning.sntk.dto.vgo.O2oUnitDetailDto;
import com.suning.sntk.dto.vgo.OrgInfoDto;
import com.suning.sntk.dto.vgo.StaffUnitDto;
import com.suning.sntk.dto.vgo.VgoCityStoreInfoDto;
import com.suning.sntk.entity.vgo.O2oStoreDetail;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-8-17
 */
@DalMapper("store_detail")
public interface O2oStoreDetailDao extends DalBaseDao<O2oStoreDetail, Long> {

    /**
     * 查询区域详情
     *
     * @param storeCode 门店编码
     * @param cityId    城市id
     * @param regionId  大区id
     * @author 88396455_白振华
     * @since 14:25  2018-8-17
     */
    @DalParams({ "storeCode", "cityId", "regionId" })
    O2oUnitDetailDto queryStaffUnit(String storeCode, String cityId, String regionId);

    /**
     * 根据门店编码查询门店信息
     *
     * @param storeCode 门店编码
     * @author 88396455_白振华
     * @since 10:12  2018-8-18
     */
    @DalParams({ "storeCode" })
    O2oUnitDetailDto queryStoreInfoByStoreCode(String storeCode);

    /**
     * 查询大区下拉列表
     *
     * @param regionCode 大区编码（8位）
     * @author 88397670_张辉
     * @since 10:48 2018-8-22
     */
    List<OrgInfoDto> queryRegionList(@DalParam("regionId") String regionId);

    /**
     * 查询分公司下拉列表
     *
     * @param regionCode 大区编码（8位）
     * @author 88397670_张辉
     * @since 10:48 2018-8-22
     */
    List<CompanyVo> queryCompanyList(@DalParam("regionCode") String regionCode);

    /**
     * 查询门店下拉列表
     *
     * @param compCode 分公司编码
     * @author 88397670_张辉
     * @since 11:05 2018-8-22
     */
    List<StoreVo> queryStoreList(@DalParam("compCode") String compCode);

    /**
     * 更新门店V购标识
     *
     * @param vgoFlag   0-关闭  1-开启
     * @param storeCode 门店编码
     * @author 88397670_张辉
     * @since 17:56 2018-8-22
     */
    @DalParams({ "vgoFlag", "storeCode" })
    void updateStoreVgoFlag(String vgoFlag, String storeCode);

    /**
     * 查询城市下所有v购标识的门店信息
     *
     * @param cityId 城市编码
     * @author 88397670_张辉
     * @since 15:14 2018-8-24
     */
    List<VgoCityStoreInfoDto> queryVgoCityStoreInfo(@DalParam("cityId") String cityId);

    /**
     * 查询城市下非超市类型的门店信息
     *
     * @param cityId 城市信息
     * @author 88397670_张辉
     * @since 10:57 2018-8-27
     */
    List<VgoCityStoreInfoDto> queryVgoCityNoMarketInfo(@DalParam("cityId") String cityId);

    /**
     * 根据员工层级查询大区信息
     *
     * @param staffUnitDto 层级信息
     * @author 88396455_白振华
     * @since 15:09  2018-8-31
     */
    @DalParams({ "staffUnitDto" })
    List<OrgInfoDto> queryRegionInfoByStaffUnit(StaffUnitDto staffUnitDto);

    /**
     * 查询大区下的分公司列表
     *
     * @param staffUnitDto 层级信息
     * @author 88396455_白振华
     * @since 15:24  2018-8-31
     */
    @DalParams({ "staffUnitDto" })
    List<OrgInfoDto> queryCompanyInfoList(StaffUnitDto staffUnitDto);

    /**
     * 查询分公司下的门店
     *
     * @param staffUnitDto 层级信息
     * @author 88396455_白振华
     * @since 15:41  2018-8-31
     */
    @DalParams({ "staffUnitDto" })
    List<OrgInfoDto> queryStoreInfoList(StaffUnitDto staffUnitDto);
}
