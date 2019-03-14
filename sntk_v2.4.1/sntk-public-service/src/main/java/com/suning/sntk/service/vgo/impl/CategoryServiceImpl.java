/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CategoryServiceImpl
 * Author:   88397670_张辉
 * Date:     2018-8-18 10:47
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.sntk.dao.vgo.CategoryDao;
import com.suning.sntk.dao.vgo.CategoryRelDao;
import com.suning.sntk.dao.vgo.ServerItemDao;
import com.suning.sntk.dto.vgo.CategoryDto;
import com.suning.sntk.dto.vgo.ServiceItemDto;
import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;
import com.suning.sntk.service.vgo.CategoryService;
import com.suning.store.commons.lang.advice.Trace;

/**
 * 功能描述：导购品类服务
 *
 * @author 88397670_张辉
 * @since 2018-8-18
 */
@Service
@Trace
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRelDao categoryRelDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ServerItemDao serverItemDao;

    @Override
    public List<CategoryDto> queryCategoryList() {
        return categoryDao.queryCategoryList();
    }

    /**
     * 根据品类编码查询品类信息
     *
     * @param codes 品类编码集合
     * @author 88395115_史小配
     * @since 15:57 2018/8/31
     */
    @Override
    public List<VcategoryRelInfoDto> queryGuideCategory(List<String> codes) {
        return categoryDao.queryGuideCategory(codes);
    }

    /**
     * 根据导购id查询导购擅长品类
     *
     * @param guideId 导购id
     * @author 88395115_史小配
     * @since 16:11 2018/8/31
     */
    @Override
    public List<VcategoryRelInfoDto> queryGuideCategoryRel(String guideId) {
        return categoryRelDao.queryGuideCategoryRel(guideId);
    }

    /**
     * 查询母婴服务项目列表
     *
     * @author 88397670_张辉
     * @since 17:06 2018-9-3
     */
    @Override
    public List<ServiceItemDto> queryServiceItemList() {
        return serverItemDao.queryServiceItemList();
    }
}
