/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreDetailDao
 * Author:   88396455_白振华
 * Date:     2018-10-9 11:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-10-9
 */
@DalMapper(namespace = "o2oStore_detail")
public interface StoreDetailDao {

    /**
     * 根据门店编码查询城市编码
     *
     * @param storeCode 门店编码
     * @author 88396455_白振华
     * @since 11:30  2018-10-8
     */
    @DalParams({ "storeCode" })
    String queryCityCodeByStoreCode(String storeCode);
}
