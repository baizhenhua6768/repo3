/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreInfoDao
 * Author:   17061157_王薛
 * Date:     2018/9/19 16:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.common;

import java.util.Date;
import java.util.List;

import com.suning.sntk.dto.common.StoreGeoDto;
import com.suning.sntk.entity.staffwhitelist.StoreInfo;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalMaster;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * @author 17061157_王薛
 * @since 2018/7/9
 */
@DalMapper(namespace = "storeInfo")
public interface StoreInfoDao extends DalBaseDao<StoreInfo, String> {

    /**
     * 新增或修改门店信息
     *
     * @param storeInfo
     */
    @DalParams({ "storeInfo" })
    void replaceInto(StoreInfo storeInfo);

    /**
     * 功能描述: queryStoreInfo <br>
     *
     * @param storeCode
     * @return com.suning.sntk.entity.staffwhitelist.StoreInfo
     * @since 14:57  2018/9/22
     */
    StoreInfo queryStoreInfo(@DalParam("storeCode") String storeCode);

    /**
     * 功能描述: 查询所有有地理信息的门店总数 <br>
     *
     * @return Long
     * @author 17061157_王薛
     * @since 14:58  2018/9/22
     */
    Long queryStoreCount();

    /**
     * 功能描述: 分页查询门店信息 <br>
     *
     * @param offset
     * @param size
     * @return Page<com.suning.sntk.dto.common.StoreGeoDto>
     * @author 17061157_王薛
     * @since 14:58  2018/9/22
     */
    @DalParams({ "offset", "size" })
    List<StoreGeoDto> queryStorePageList(int offset, int size);

    /**
     * 功能描述: 查询最近有变化的门店信息列表 <br>
     *
     * @param modifyTimeBegin 查询的最早变化时间
     * @return java.util.List<StoreGeoDto>
     * @author 17061157_王薛
     * @since 13:25  2018/9/22
     */
    @DalParams({ "modifyTimeBegin" })
    List<StoreGeoDto> queryModifyStoreList(Date modifyTimeBegin);

    /**
     * 功能描述: 查询门店信息 <br>
     *
     * @param storeCode
     * @return com.suning.sntk.dto.common.StoreGeoDto
     * @author 17061157_王薛
     * @since 15:57  2018/10/11
     */
    StoreGeoDto queryStoreGeoInfo(@DalParam("storeCode") String storeCode);

    /**
     * 查询当前门店下有效导购数量
     *
     * @param storeCode 门店编码
     */
    @DalMaster
    Long queryValidVgoCount(@DalParam("storeCode") String storeCode);

}
