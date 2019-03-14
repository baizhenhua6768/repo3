/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: RouterAddressDao
 * Author:   88396455_白振华
 * Date:     2018-8-18 14:18
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.dao.vgo;

import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;

/**
 * 功能描述：路由地址数据库操作类
 *
 * @author 88396455_白振华
 * @since 2018-8-18
 */
@DalMapper("router_address")
public interface RouterAddressDao {

    /**
     * 根据业务类型查询路由地址
     *
     * @param moduleType 模块类型
     * @author 88396455_白振华
     * @since 14:20  2018-8-18
     */
    @DalParams({ "moduleType" })
    String queryRouterAddressInfo(String moduleType);
}
