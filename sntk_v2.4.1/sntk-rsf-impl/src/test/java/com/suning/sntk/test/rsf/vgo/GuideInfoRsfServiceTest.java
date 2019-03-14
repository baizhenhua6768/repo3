/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoRsfServiceTest
 * Author:   18041004_余长杰
 * Date:     2018/9/17 17:24
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.test.rsf.vgo;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.dto.vgo.StoreGuideInfoDto;
import com.suning.sntk.rsf.impl.vgo.GuideInfoRsfServiceImpl;
import com.suning.sntk.service.vgo.GuideInfoService;

/**
 * 功能描述：易购app导购查询Test
 *
 * @author 18041004_余长杰
 * @since 2018/9/17
 */

public class GuideInfoRsfServiceTest {

    @InjectMocks
    private GuideInfoRsfServiceImpl guideInfoRsfService;

    @Mock
    private GuideInfoService guideInfoService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void queryRegionListByParentCodeTest() {
        String parentRegionCode = "";
        String regionalLevel = "";
        guideInfoRsfService.queryRegionListByParentCode(parentRegionCode, regionalLevel);
    }

    @Test
    public void queryRegionInfoByRegionalCode() {
        String regionCode = "";
        String regionalLevel = "";
        guideInfoRsfService.queryRegionInfoByRegionalCode(regionCode, regionalLevel);
    }

    @Test
    public void queryStoreGuideForSmallRoutine(){
        String storeCode = "8727";
        String custNo = "6002008499";
        StoreGuideInfoDto storeGuideInfoDto = new StoreGuideInfoDto();
        BDDMockito.when(guideInfoService.queryAppletCustManager(storeCode, custNo)).thenReturn(storeGuideInfoDto);
        guideInfoRsfService.queryStoreGuideForSmallRoutine(storeCode,custNo);
    }

    @Test
    public void queryGuideListForSmallRoutine(){
        String storeCode = "8727";
        String custNo = "6002008499";
        String preGuideId = "22222";
        guideInfoRsfService.queryGuideListForSmallRoutine(storeCode,custNo,preGuideId);
    }
}
