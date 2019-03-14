/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ShopInfoDao
 * Author:   88397670_张辉
 * Date:     2018-8-18 16:45
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import com.suning.sntk.dto.vgo.ShopInfoDto;
import com.suning.sntk.entity.staffwhitelist.StoreInfo;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：门店信息服务
 *
 * @author 88397670_张辉
 * @since 2018-8-18
 */
@DalMapper(namespace = "storeDetail")
public interface StoreInfoDao extends DalBaseDao<StoreInfo, String>{

    ShopInfoDto queryStoreInfo(@DalParam("storeCode") String storeCode);
}
