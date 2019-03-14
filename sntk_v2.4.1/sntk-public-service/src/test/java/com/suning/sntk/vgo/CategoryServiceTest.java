/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CategoryServiceTest
 * Author:   88397670_张辉
 * Date:     2018-9-6 16:13
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.vgo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.dto.vgo.CategoryDto;
import com.suning.sntk.dto.vgo.ServiceItemDto;
import com.suning.sntk.service.vgo.CategoryService;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-9-6
 */
public class CategoryServiceTest extends BaseTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void queryCategoryList(){
        List<CategoryDto> categoryDtoList = categoryService.queryCategoryList();
        logger.info("品类列表查询结果："+categoryDtoList);
    }

    @Test
    public void queryServiceItemList(){
        List<ServiceItemDto> serviceItemDtos = categoryService.queryServiceItemList();
        logger.info("服务项目查询列表："+serviceItemDtos);
    }

    @Test
    public void queryGuideCategoryTest(){
        List<String> codes = new ArrayList<>();
        codes.add("10");
        System.out.println(categoryService.queryGuideCategory(codes));
    }

    @Test
    public void queryGuideCategoryRelTest(){
        System.out.println(categoryService.queryGuideCategoryRel("05041335"));
    }

}
