/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CategoryDao
 * Author:   88397670_张辉
 * Date:     2018-8-31 14:50
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import java.util.List;

import com.suning.sntk.dto.vgo.CategoryDto;
import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParam;

/**
 * 功能描述：导购品类
 *
 * @author 88397670_张辉
 * @since 2018-8-31
 */
@DalMapper(namespace = "o2obvCategory")
public interface CategoryDao {

    /**
     * 品类列表查询
     *
     * @author 88397670_张辉
     * @since 14:52 2018-8-31
     */
    List<CategoryDto> queryCategoryList();
    
    /** 
     * 根据品类编码查询品类信息
     *
     * @author 88395115_史小配
     * @param codes 品类编码集合
     * @since 16:07 2018/8/31
     */
    List<VcategoryRelInfoDto> queryGuideCategory(@DalParam("codes") List<String> codes);

}
