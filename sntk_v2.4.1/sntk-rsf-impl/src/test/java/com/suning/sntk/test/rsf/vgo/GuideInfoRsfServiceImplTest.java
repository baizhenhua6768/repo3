/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoRsfServiceImplTest
 * Author:   18010645_黄成
 * Date:     2018/9/20 17:53
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.test.rsf.vgo;

import com.suning.sntk.dto.vgo.ServantInfoPcDto;
import com.suning.sntk.dto.vgo.StaffPageDto;
import com.suning.sntk.dto.vgo.StoreServantRespDto;
import com.suning.sntk.rsf.impl.vgo.GuideInfoRsfServiceImpl;
import com.suning.sntk.service.vgo.GuideInfoService;
import com.suning.sntk.service.vgo.VgoCommonService;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 *
 * @author 18010645_黄成
 * @since 2018/9/20
 */
public class GuideInfoRsfServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuideInfoRsfServiceImplTest.class);

    @InjectMocks
    private GuideInfoRsfServiceImpl guideInfoRsfServiceImpl;

    @Mock
    private GuideInfoService guideInfoService;

    @Mock
    private RedisCacheUtils redisCacheUtils;

    @Mock
    private VgoCommonService vgoCommonService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStoreInfo() {
        String storeCode = "7611";
        StoreServantRespDto respDto = guideInfoRsfServiceImpl.getStoreInfo(storeCode,"123456");
        LOGGER.info("GuideInfoRsfServiceImplTest.getStoreInfo,message={}", respDto);
    }

    @Test
    public void testStaffPageInfo() {
        String customerNo = "76110007895";
        String guideId = "22334455";
        RsfResponseDto<StaffPageDto> staffPageInfos = guideInfoRsfServiceImpl.queryStaffPageInfos(customerNo, guideId, 4, 1, 20);
        LOGGER.info("GuideInfoRsfServiceImplTest.getStoreInfo,message={}", staffPageInfos);
    }


    @Test
    public void queryStoreInfo() {
        String storeCode = "8727";
        StoreServantRespDto storeServantRespDto = new StoreServantRespDto();
        List<ServantInfoPcDto> list = new ArrayList<>();
        ServantInfoPcDto dto = new ServantInfoPcDto();
        Map<String, String> mapResult = new HashMap<>();
        mapResult.put(VgoConstants.ORDER_NUM_MAP_KEY, "550");
        dto.setGuideId("123456");
        list.add(dto);
        storeServantRespDto.setInitTime("2018-09-22");
        storeServantRespDto.setuServantInfoList(list);
        BDDMockito.when(guideInfoService.queryStoreGuides(storeCode,"123456")).thenReturn(storeServantRespDto);
        BDDMockito.when(vgoCommonService.queryOrderNumAndReceivePraise(dto.getGuideId())).thenReturn(mapResult);
        StoreServantRespDto respDto = guideInfoRsfServiceImpl.getStoreInfo(storeCode,"123456");
        LOGGER.info("GuideInfoRsfServiceImplTest.getStoreInfo,respDto={}", respDto);
    }

}
