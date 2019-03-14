/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoServiceImplTest
 * Author:   18010645_黄成
 * Date:     2018/9/21 11:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.vgo;

import com.suning.mzss.rsf.video.dto.VFrontContentDto;
import com.suning.sntk.consumer.BaoziConsumerService;
import com.suning.sntk.consumer.MzssConsumerService;
import com.suning.sntk.consumer.NsfbusConsumerService;
import com.suning.sntk.consumer.SuisConsumerService;
import com.suning.sntk.dao.o2o.GuideDao;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dao.vgo.O2oGuideInfoDao;
import com.suning.sntk.dto.vgo.*;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.service.vgo.VgoCommonService;
import com.suning.sntk.service.vgo.impl.GuideInfoServiceImpl;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.utils.DateUtils;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：店员主页和pc门店基本信息和门店下导购人员单元测试
 *
 * @author 18010645_黄成
 * @since 2018/9/21
 */
public class GuideInfoServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuideInfoServiceImplTest.class);

    @InjectMocks
    private GuideInfoServiceImpl guideInfoServiceImpl;

    @Mock
    private RedisCacheUtils redisCacheUtils;

    @Mock
    private GuideInfoDao guideInfoDao;

    @Mock
    private O2oGuideInfoDao o2oGuideInfoDao;

    @Mock
    private NsfbusConsumerService nsfbusConsumerService;

    @Mock
    private GuideDao guideDao;

    @Mock
    private VgoCommonService vgoCommonService;

    @Mock
    private BaoziConsumerService baoziConsumerService;

    @Mock
    private SuisConsumerService suisConsumerService;

    @Mock
    private MzssConsumerService mzssConsumerService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGuideInfoAndVgoVideo() {
        String customerNo = "958652123";
        String guideId = "223355";
        Integer fromType = 4;
        Integer pageNo = 1;
        Integer pageSize = 20;

        CustomerBookDto customerBookDto = new CustomerBookDto();
        customerBookDto.setBookCode("V22222000123");
        customerBookDto.setBookingCreateTime(DateUtils.format(new Date()));
        customerBookDto.setBookingTime("2018-09-21");
        String videoCustomerNo = "123456";
        String value = "123";
        StaffCustRel staffCustRel = new StaffCustRel();
        staffCustRel.setBusinessType("1");
        List<VFrontContentDto> vFrontContentDto = new ArrayList<>();
        BDDMockito.when(redisCacheUtils.get(BDDMockito.anyString())).thenReturn(value);
        BDDMockito.when(guideInfoDao.queryIsCustomerManager(customerNo, guideId)).thenReturn(staffCustRel);
        BDDMockito.when(suisConsumerService.queryVedioCustomerNum(guideId)).thenReturn(videoCustomerNo);
        BDDMockito.when(mzssConsumerService.queryVgoVideoList(videoCustomerNo, fromType, pageNo, pageSize)).thenReturn(vFrontContentDto);
        try {
            StaffPageDto staffPageDtoResponse = guideInfoServiceImpl.queryGuideInfoAndVgoVideo(customerNo, guideId, fromType, pageNo, pageSize);
        } catch (Exception e) {
            LOGGER.info("queryGuideInfoAndVgoVideo.error");
        }
    }


    @Test
    public void testGuideInfoAndVgoVideo3() {
        String customerNo = "958652123";
        String guideId = "223355";
        Integer fromType = 4;
        Integer pageNo = 1;
        Integer pageSize = 20;

        CustomerBookDto customerBookDto = new CustomerBookDto();
        customerBookDto.setBookCode("V22222000123");
        customerBookDto.setBookingCreateTime(DateUtils.format(new Date()));
        customerBookDto.setBookingTime("2018-09-21");
        String videoCustomerNo = "123456";
        String value = "123";
        StaffCustRel staffCustRel = new StaffCustRel();
        List<VFrontContentDto> vFrontContentDto = new ArrayList<>();
        BDDMockito.when(redisCacheUtils.get(BDDMockito.anyString())).thenReturn(value);
        BDDMockito.when(guideInfoDao.queryIsCustomerManager(customerNo, guideId)).thenReturn(staffCustRel);
        try {
            BDDMockito.when(guideInfoDao
                    .queryNearBookingInfo(customerNo, guideId, DateUtils.format(new Date(), DateUtils.PATTEN_10))).thenReturn(customerBookDto);
        } catch (Exception e) {
            LOGGER.info("queryNearBookingInfo.error");
        }
        BDDMockito.when(suisConsumerService.queryVedioCustomerNum(guideId)).thenReturn(videoCustomerNo);
        BDDMockito.when(mzssConsumerService.queryVgoVideoList(videoCustomerNo, fromType, pageNo, pageSize)).thenReturn(vFrontContentDto);
        StaffPageDto staffPageDtoResponse;
        try {
            staffPageDtoResponse = guideInfoServiceImpl.queryGuideInfoAndVgoVideo(customerNo, guideId, fromType, pageNo, pageSize);
        } catch (Exception e) {
            LOGGER.info("queryGuideInfoAndVgoVideo.error");
        }
    }


    @Test
    public void testGuideInfoAndVgoVideo4() {
        String customerNo = "";
        String guideId = "223355";
        Integer fromType = 4;
        Integer pageNo = 1;
        Integer pageSize = 20;
        CustomerBookDto customerBookDto = new CustomerBookDto();
        customerBookDto.setBookCode("V22222000123");
        customerBookDto.setBookingCreateTime(DateUtils.format(new Date()));
        customerBookDto.setBookingTime("2018-09-21");
        String videoCustomerNo = "123456";
        String value = "123";
        List<VFrontContentDto> vFrontContentDto = new ArrayList<>();
        BDDMockito.when(guideInfoDao
                .queryNearBookingInfo(customerNo, guideId, DateUtils.format(new Date(), DateUtils.PATTEN_10))).thenReturn(customerBookDto);
        BDDMockito.when(suisConsumerService.queryVedioCustomerNum(guideId)).thenReturn(videoCustomerNo);
        BDDMockito.when(mzssConsumerService.queryVgoVideoList(videoCustomerNo, fromType, pageNo, pageSize)).thenReturn(vFrontContentDto);
        StaffPageDto staffPageDtoResponse = null;
        try {
            staffPageDtoResponse = guideInfoServiceImpl.queryGuideInfoAndVgoVideo(customerNo, guideId, fromType, pageNo, pageSize);
        } catch (Exception e) {
            LOGGER.info("queryGuideInfoAndVgoVideo.error:");
        }
        LOGGER.info("staffPageDtoResponse:", staffPageDtoResponse);
    }

    @Test
    public void testGuideInfoAndVgoVideo2() {
        String customerNo = "958652123";
        String guideId = "223355";
        Integer fromType = 4;
        Integer pageNo = 1;
        Integer pageSize = 20;
        CustomerBookDto customerBookDto = new CustomerBookDto();
        customerBookDto.setBookCode("V22222000123");
        customerBookDto.setBookingCreateTime(DateUtils.format(new Date()));
        customerBookDto.setBookingTime("2018-09-21");
        String videoCustomerNo = "123456";
        List<VFrontContentDto> vFrontContentDto = new ArrayList<>();
        BDDMockito.when(guideInfoDao
                .queryNearBookingInfo(customerNo, guideId, DateUtils.format(new Date(), DateUtils.PATTEN_10))).thenReturn(customerBookDto);
        BDDMockito.when(redisCacheUtils.get(BDDMockito.anyString())).thenReturn(null);
        BDDMockito.when(suisConsumerService.queryVedioCustomerNum(guideId)).thenReturn(videoCustomerNo);
        BDDMockito.when(mzssConsumerService.queryVgoVideoList(videoCustomerNo, fromType, pageNo, pageSize)).thenReturn(vFrontContentDto);
        StaffPageDto staffPageDtoResponse = guideInfoServiceImpl.queryGuideInfoAndVgoVideo(customerNo, guideId, fromType, pageNo, pageSize);
        LOGGER.info("staffPageDtoResponse:", staffPageDtoResponse);

    }

    @Test
    public void testGuideInfoAndVgoVideo10() {
        String customerNo = "958652123";
        String guideId = "223355";
        Integer fromType = 4;
        Integer pageNo = 1;
        Integer pageSize = 20;
        CustomerBookDto customerBookDto = new CustomerBookDto();
        customerBookDto.setBookCode("V22222000123");
        customerBookDto.setBookingCreateTime(DateUtils.format(new Date()));
        customerBookDto.setBookingTime("2018-09-21");
        String videoCustomerNo = "123456";
        GuideDetailDto guideDetailDtoResponse = new GuideDetailDto();
        guideDetailDtoResponse.setGuideName("李四");
        List<VFrontContentDto> vFrontContentDto = new ArrayList<>();
        VFrontContentDto dto = new VFrontContentDto();
        dto.setId(123456L);
        vFrontContentDto.add(dto);
        BDDMockito.when(guideInfoDao
                .queryNearBookingInfo(customerNo, guideId, DateUtils.format(new Date(), DateUtils.PATTEN_10))).thenReturn(customerBookDto);
        BDDMockito.when(redisCacheUtils.get(BDDMockito.anyString())).thenReturn(null);
        BDDMockito.when(suisConsumerService.queryVedioCustomerNum(guideId)).thenReturn(videoCustomerNo);
        BDDMockito.when(mzssConsumerService.queryVgoVideoList(videoCustomerNo, fromType, pageNo, pageSize)).thenReturn(vFrontContentDto);
        BDDMockito.when(guideInfoDao.queryStaffPageGuideDetail(guideId)).thenReturn(guideDetailDtoResponse);
        StaffPageDto staffPageDtoResponse = null;
        try {
            staffPageDtoResponse = guideInfoServiceImpl.queryGuideInfoAndVgoVideo(customerNo, guideId, fromType, pageNo, pageSize);
        } catch (Exception e) {
            LOGGER.info("queryGuideInfoAndVgoVideo.error");
        }


    }

    @Test
    public void testGuideInfoAndVgoVideo11() {
        String customerNo = "958652123";
        String guideId = "223355";
        Integer fromType = 4;
        Integer pageNo = 1;
        Integer pageSize = 20;
        CustomerBookDto customerBookDto = new CustomerBookDto();
        customerBookDto.setBookCode("V22222000123");
        customerBookDto.setBookingCreateTime(DateUtils.format(new Date()));
        customerBookDto.setBookingTime("2018-09-21");
        String videoCustomerNo = "123456";
        GuideDetailDto guideDetailDtoResponse = new GuideDetailDto();
        guideDetailDtoResponse.setGuideName("李四");
        List<VFrontContentDto> vFrontContentDto = new ArrayList<>();
        VFrontContentDto dto = new VFrontContentDto();
        StaffCustRel staffCustRel = new StaffCustRel();
        staffCustRel.setStaffName("哈哈");
        dto.setId(123456L);
        vFrontContentDto.add(dto);
        BDDMockito.when(guideInfoDao
                .queryNearBookingInfo(customerNo, guideId, DateUtils.format(new Date(), DateUtils.PATTEN_10))).thenReturn(customerBookDto);
        BDDMockito.when(redisCacheUtils.get(BDDMockito.anyString())).thenReturn(null);
        BDDMockito.when(suisConsumerService.queryVedioCustomerNum(guideId)).thenReturn(videoCustomerNo);
        BDDMockito.when(mzssConsumerService.queryVgoVideoList(videoCustomerNo, fromType, pageNo, pageSize)).thenReturn(vFrontContentDto);
        BDDMockito.when(guideInfoDao.queryStaffPageGuideDetail(guideId)).thenReturn(guideDetailDtoResponse);
        BDDMockito.when(guideInfoDao.queryIsCustomerManager(customerNo, guideId)).thenReturn(staffCustRel);
        StaffPageDto staffPageDtoResponse = guideInfoServiceImpl.queryGuideInfoAndVgoVideo(customerNo, guideId, fromType, pageNo, pageSize);
        LOGGER.info("staffPageDtoResponse:", staffPageDtoResponse);
    }

    @Test
    public void testStoreGuides() {
        String storeCode = "7611";
        List<ServantInfoPcDto> vServantList = new ArrayList<>();
        BDDMockito.when(guideDao.getStoreServants(storeCode)).thenReturn(vServantList);
        StoreInfoPcDto storeInfoPcDto = guideDao.getPcStoreInfo(storeCode);
        StoreServantRespDto storeServantRespDto = new StoreServantRespDto();
        storeServantRespDto.setuServantInfoList(vServantList);
        storeServantRespDto.setStoreInfo(storeInfoPcDto);
        StoreServantRespDto storeServantResponse = guideInfoServiceImpl.queryStoreGuides(storeCode, "123456");
        LOGGER.info("storeServantResponse:", storeServantResponse);

    }

}
