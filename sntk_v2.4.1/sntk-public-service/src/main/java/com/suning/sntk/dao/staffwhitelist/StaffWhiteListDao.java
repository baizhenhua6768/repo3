/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: StaffWhiteListDao
 * Author:   88402362 欧小冬
 * Date:
 * Description: //模块目的、功能描述
 * History: //修改记录
 * &lt;author&gt;      &lt;time&gt;      &lt;version&gt;    &lt;desc&gt;
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.staffwhitelist;

import com.suning.sntk.entity.staffwhitelist.Staff;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：
 *
 * @author 88402362_欧小冬
 * @since 2018/7/11
 */
@DalMapper("staffWhiteList")
public interface StaffWhiteListDao extends DalBaseDao<Staff,Long> {

    /**
     * 功能描述：根据店员工号和门店编号查询店员白名单数据
     *
     * @author 88402362 欧小冬
     * @since 2018/7/11
     */
    @DalParams({"staffId","storeCode"})
    Staff queryByStaffIdWithStore(String staffId ,String storeCode);
}
