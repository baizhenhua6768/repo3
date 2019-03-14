/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CategoryRelDao
 * Author:   88397670_张辉
 * Date:     2018-8-18 10:48
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import java.util.List;

import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;
import com.suning.sntk.entity.vgo.CategoryRelInfo;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-8-18
 */
@DalMapper(namespace = "categoryRel")
public interface CategoryRelDao extends DalBaseDao<CategoryRelInfo, Long> {

    /**
     * 根据导购id查询导购擅长品类
     *
     * @param guideId 导购id
     * @author 88395115_史小配
     * @since 16:13 2018/8/31
     */
    List<VcategoryRelInfoDto> queryGuideCategoryRel(@DalParam("guideId") String guideId);

    /**
     * 根据导购工号查询三级类目
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 14:04  2018-10-8
     */
    @DalParams({ "guideId" })
    List<String> queryCategoryOutRel(String guideId);

}
