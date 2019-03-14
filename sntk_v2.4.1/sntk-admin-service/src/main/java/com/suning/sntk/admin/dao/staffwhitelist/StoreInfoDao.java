/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreInfoDao
 * Author:   18032490_赵亚奇
 * Date:     2018/7/9 16:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.dao.staffwhitelist;

import com.suning.sntk.entity.staffwhitelist.StoreInfo;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * @author 18032490_赵亚奇
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

    StoreInfo queryStoreInfo(@DalParam("storeCode") String storeCode);
}
