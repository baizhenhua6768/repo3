/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CategoryService
 * Author:   88397670_张辉
 * Date:     2018-8-18 10:42
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo;

import java.util.List;

import com.suning.sntk.dto.vgo.CategoryDto;
import com.suning.sntk.dto.vgo.ServiceItemDto;
import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;

/**
 * 功能描述：导购品类服务
 *
 * @author 88397670_张辉
 * @since 2018-8-18
 */
public interface CategoryService {

    /**
     * 查询品类列表
     *
     * @author 88397670_张辉
     * @since 14:42 2018-8-31
     */
    List<CategoryDto> queryCategoryList();
    
    /** 
     * 根据品类编码查询品类信息
     *
     * @author 88395115_史小配
     * @param codes 品类编码集合
     * @since 15:57 2018/8/31
     */
    List<VcategoryRelInfoDto> queryGuideCategory(List<String> codes);
    
    /** 
     * 根据导购id查询导购擅长品类
     *
     * @author 88395115_史小配
     * @param guideId 导购id
     * @since 16:11 2018/8/31
     */
    List<VcategoryRelInfoDto> queryGuideCategoryRel(String guideId);

    /**
     * 查询母婴服务项目列表
     *
     * @author 88397670_张辉
     * @since 17:06 2018-9-3
     */
    List<ServiceItemDto> queryServiceItemList();
}
