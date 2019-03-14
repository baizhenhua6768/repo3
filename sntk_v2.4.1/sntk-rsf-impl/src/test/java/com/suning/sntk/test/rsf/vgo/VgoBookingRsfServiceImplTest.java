/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoBookingRsfServiceTest
 * Author:   18010645_黄成
 * Date:     2018/9/18 19:10
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.test.rsf.vgo;

import com.suning.sntk.consumer.MemberConsumerService;
import com.suning.sntk.dto.vgo.AppointInitQueryRespDto;
import com.suning.sntk.dto.vgo.AppointPromptDto;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.StatisticsInfoDto;
import com.suning.sntk.rsf.impl.vgo.BookingRsfServiceImpl;
import com.suning.sntk.service.vgo.BookingPageService;
import com.suning.sntk.service.vgo.VgoGuideService;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * 功能描述：预约Rsf单测
 *
 * @author 18010645_黄成
 * @since 2018/9/18
 */
public class VgoBookingRsfServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VgoBookingRsfServiceImplTest.class);

    @InjectMocks
    private BookingRsfServiceImpl bookingRsfServiceImpl;

    @Mock
    private VgoGuideService vgoGuideService;

    @Mock
    private MemberConsumerService memberConsumerService;

    @Mock
    private BookingPageService bookingPageService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBookingGuide() {
        BookingInfoDto bookingInfoDto = new BookingInfoDto();
        // 会员编码
        bookingInfoDto.setCust("7013707968");
        // 导购工号
        bookingInfoDto.setGuideId("123456");
        // 会员电话号码
        bookingInfoDto.setTelephone("18551651320");
        // 预约时间
        bookingInfoDto.setBookingTime(DateUtils.format(new Date(), "yyyy-MM-dd") + 1);
        // 门店编码
        bookingInfoDto.setStoreCode("7961");
        // 完成状态：0:未到店,1:到店购买,2:到店未购买,3:未销单,4:通用,5:用户取消,6:系统取消
        bookingInfoDto.setComplete(VgoConstants.COMPLETE_STATUS_NO_ORDER);
        // 预约状态：0：未完成，1：已完成
        bookingInfoDto.setBookingStatus(VgoConstants.BOOKING_STATUS_NO_COMPLETE);
        // 是否评价：0:未到店,1：已到店（可评价）,2:已评价
        bookingInfoDto.setJudgeFlag(VgoConstants.EVALUATE_NO_STORE);
        // 业态(1:v购;2:母婴)
        bookingInfoDto.setBusinessCode("1");
        StatisticsInfoDto statisticsInfoDto = new StatisticsInfoDto();
        // 渠道
        statisticsInfoDto.setChannel("3");
        // 预约与否
        statisticsInfoDto.setResourceType(VgoConstants.RESOURCE_TYPE);
        // 预约创建时间
        statisticsInfoDto.setCreateTime(DateUtils.format(new Date()));
        String bookCode = "V123564";
        BDDMockito.when(vgoGuideService.addBooking(bookingInfoDto, statisticsInfoDto)).thenReturn(bookCode);
        RsfResponseDto<String> message = bookingRsfServiceImpl.bookingGuide(bookingInfoDto, statisticsInfoDto);
        LOGGER.info("VgoBookingRsfServiceImplTest.bookingGuide,message={}", message);
    }

    @Test
    public void testAppointInit() {
        String customerNum = "7013707968";
        String guideId = "123456";
        RsfResponseDto<AppointInitQueryRespDto> message = bookingRsfServiceImpl.queryAppointInit(customerNum, guideId);
        LOGGER.info("VgoBookingRsfServiceImplTest.queryAppointment,message={}", message);
    }

    @Test
    public void testAppointment() {
        String customerNum = "7013707968";
        String bookingTime = "2018-09-20";
        RsfResponseDto<AppointPromptDto> message = bookingRsfServiceImpl.queryAppointment(customerNum, bookingTime);
        LOGGER.info("VgoBookingRsfServiceImplTest.queryAppointment,message={}", message);
    }

    @Test
    public void testMemberMobile() {
        String customerNum = "7013707968";
        RsfResponseDto<String> message = bookingRsfServiceImpl.queryMemberPhone(customerNum);
        LOGGER.info("VgoBookingRsfServiceImplTest.queryMemberPhone,message={}", message);
    }

}
