/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VCategoryDao
 * Author:   88396455_白振华
 * Date:     2018-8-18 17:20
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.dao.vgo;

import java.util.List;

import com.suning.sntk.entity.vgo.VcategoryInfo;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;

/**
 * 功能描述：导购擅长品类数据库操作
 *
 * @author 88396455_白振华
 * @since 2018-8-18
 */
@DalMapper("v_category")
public interface VCategoryDao {

    /**
     * 根据品类编码批量查询平类信息
     *
     * @param categoryCodes 品类编码集合
     * @author 88396455_白振华
     * @since 17:42  2018-8-18
     */
    @DalParams({ "categoryCodes" })
    List<VcategoryInfo> batchQueryByCatregoryCode(List<Long> categoryCodes);
}
