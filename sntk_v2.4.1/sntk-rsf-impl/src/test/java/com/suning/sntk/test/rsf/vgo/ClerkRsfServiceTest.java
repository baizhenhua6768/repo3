/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ClerkRsfServiceTest
 * Author:   88395115_史小配
 * Date:     2018/9/13 15:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.test.rsf.vgo;

import java.util.ArrayList;
import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.dto.vgo.CategoryDto;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ServiceItemDto;
import com.suning.sntk.rsf.impl.vgo.ClerkRsfServiceImpl;
import com.suning.sntk.service.vgo.CategoryService;
import com.suning.sntk.service.vgo.VgoGuideService;

/**
 * 功能描述：
 *
 * @author 88395115_史小配
 * @since 2018/9/13
 */
public class ClerkRsfServiceTest {

    @InjectMocks
    private ClerkRsfServiceImpl clerkRsfService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private VgoGuideService vgoGuideService;


    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryGuideDetailTest() {
        System.out.println(clerkRsfService.queryGuideDetail("06789854", "20"));
    }

    @Test
    public void queryCategoryList() {
        List<CategoryDto> categoryList = new ArrayList<>();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryCode("001");
        categoryDto.setCategoryName("MockTest");
        categoryList.add(categoryDto);
        BDDMockito.when(categoryService.queryCategoryList()).thenReturn(categoryList);
        clerkRsfService.queryCategoryList();
    }

    @Test
    public void queryServiceItemList(){
        List<ServiceItemDto> itemDtos = new ArrayList<>();
        ServiceItemDto itemDto= new ServiceItemDto();
        itemDto.setItemVal("shdjhsj,sjdk");
        itemDtos.add(itemDto);
        BDDMockito.when(categoryService.queryServiceItemList()).thenReturn(itemDtos);
        clerkRsfService.queryServiceItemList();
    }

    @Test
    public void modifyGuideAuditInfo(){
        GuideInfoDto guideInfoDto = new GuideInfoDto();
        guideInfoDto.setGuideId("MockTest");
        guideInfoDto.setGuideName("MockTest");
        guideInfoDto.setIntroduction("MockTest");
        clerkRsfService.modifyGuideAuditInfo(guideInfoDto);
    }

    @Test
    public void checkClerkInfoIntegrity(){
        String guideId = "MockTest";
        clerkRsfService.checkClerkInfoIntegrity(guideId);
    }

    @Test
    public void checkClerkIsVgo(){
        String guideId = "MockTest";
        clerkRsfService.queryClerkMarkInfo(guideId);
    }
}
