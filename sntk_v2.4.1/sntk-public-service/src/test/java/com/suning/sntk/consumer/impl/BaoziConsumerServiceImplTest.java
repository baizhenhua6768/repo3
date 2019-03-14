package com.suning.sntk.consumer.impl;

import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.baozi.rsfservice.StoreGuideService;
import com.suning.baozi.rsfservice.constant.ResponseContent;
import com.suning.baozi.rsfservice.dto.StoreGuideDto;

/**
 * @author 18032490_赵亚奇
 * @since 2018/9/22
 */
public class BaoziConsumerServiceImplTest {

    @InjectMocks
    private BaoziConsumerServiceImpl baoziConsumerService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private StoreGuideService storeGuideService;

    @Test
    public void testQueryStoreBestGuideId1() throws Exception {
        String storeCode = "70B0";
        ResponseContent<List<StoreGuideDto>> storeGuideInfo = new ResponseContent<>();
        BDDMockito.when(storeGuideService.queryStoreBestGuideId(BDDMockito.anyString())).thenReturn(storeGuideInfo);
        baoziConsumerService.queryStoreBestGuideId(storeCode);
    }

    @Test
    public void testQueryStoreBestGuideId2() throws Exception {
        String storeCode = "70B0";
        ResponseContent<List<StoreGuideDto>> storeGuideInfo = new ResponseContent<>();
        storeGuideInfo.setResponseCode("0");
        BDDMockito.when(storeGuideService.queryStoreBestGuideId(BDDMockito.anyString())).thenReturn(storeGuideInfo);
        baoziConsumerService.queryStoreBestGuideId(storeCode);
    }
}