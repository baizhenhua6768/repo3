/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: VgoGuideServiceTest
 * Author:   88395115_史小配
 * Date:     2018/9/6 16:52
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.vgo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.aimp.intf.resp.social.QuerySocialInfoResp;
import com.suning.sntk.consumer.HrConsumerService;
import com.suning.sntk.consumer.WechatConsumerService;
import com.suning.sntk.dao.vgo.CategoryRelDao;
import com.suning.sntk.dao.vgo.GuideAppointmentDao;
import com.suning.sntk.dao.vgo.GuideAuditDao;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dao.vgo.GuideOrderLogDao;
import com.suning.sntk.dao.vgo.GuideStatisticsDao;
import com.suning.sntk.dao.vgo.ServerItemDao;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.GuideAppointmentDto;
import com.suning.sntk.dto.vgo.GuideDto;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.OrderCompletionDto;
import com.suning.sntk.dto.vgo.QueryAppointmentReqDto;
import com.suning.sntk.dto.vgo.StatisticsInfoDto;
import com.suning.sntk.entity.vgo.AppointmentInfo;
import com.suning.sntk.entity.vgo.GuideAuditInfo;
import com.suning.sntk.service.message.SendMsgService;
import com.suning.sntk.service.vgo.BookingSendMsgService;
import com.suning.sntk.service.vgo.CategoryService;
import com.suning.sntk.service.vgo.impl.AppointmentPublicServiceImpl;
import com.suning.sntk.service.vgo.impl.VgoGuideServiceImpl;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.enums.vgo.BookingStatusEnum;
import com.suning.sntk.support.enums.vgo.CompleteStatusEnum;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.PageImpl;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.base.ParamBuilder;

/**
 * 功能描述：
 *
 * @author 88395115_史小配
 * @since 2018/9/6
 */
public class VgoGuideServiceMockTest {

    @InjectMocks
    private VgoGuideServiceImpl vgoGuideService;

    @Mock
    private RedisCacheUtils redisUtils;

    @Mock
    private GuideAppointmentDao guideAppointmentDao;

    @Mock
    private GuideStatisticsDao guideStatisticsDao;

    @Mock
    private GuideOrderLogDao guideOrderLogDao;

    @Mock
    private GuideInfoDao guideInfoDao;

    @Mock
    private SendMsgService sendMsgService;

    @Mock
    private AppointmentPublicServiceImpl appointmentPublicService;

    @Mock
    private WechatConsumerService wechatConsumerService;

    @Mock
    private BookingSendMsgService bookingSendMsgService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private GuideAuditDao guideAuditDao;

    @Mock
    private ServerItemDao serverItemDao;

    @Mock
    private CategoryRelDao categoryRelDao;

    @Mock
    private HrConsumerService hrConsumerService;

    private String guideId = "05041335";

    private String cust = "38932990";

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addBookingTest() {
        BDDMockito.when(guideAppointmentDao.getGuideInfo(guideId)).thenReturn(getGuideDto());
        BDDMockito.when(guideAppointmentDao.queryBookGuide(guideId)).thenReturn(new BookingInfoDto());
        vgoGuideService.addBooking(getBookInfoDto(), getStatisticsInfoDto());
    }

    @Test
    public void queryAppointmentListTest() {
        Pageable pageable = ParamBuilder.newPageable(0, 10);
        QueryAppointmentReqDto reqDto = new QueryAppointmentReqDto();
        BDDMockito.when(guideAppointmentDao.queryAppointmentList(reqDto, pageable))
                .thenReturn(getPage());
        BDDMockito.when(wechatConsumerService.queryCustomerSocialInfo(cust)).thenReturn(getQuerySocialInfoResp());
        vgoGuideService.queryAppointmentList(reqDto, pageable);
    }

    @Test
    public void returnVisitTest1() {
        BDDMockito.when(guideAppointmentDao.findById(3L)).thenReturn(getAppointmentInfo("0"));
        BDDMockito.when(guideAppointmentDao.getAppointmentByBookCode(BDDMockito.anyString())).thenReturn(getBookInfoDto());
        BDDMockito.when(guideAppointmentDao.getAppointmentByBookCode(BDDMockito.anyString())).thenReturn(new BookingInfoDto());
        vgoGuideService.returnVisit(3L, "1", guideId);
    }

    @Test
    public void returnVisitTest2() {
        BDDMockito.when(guideAppointmentDao.findById(3L)).thenReturn(getAppointmentInfo("0"));
        BDDMockito.when(guideAppointmentDao.getAppointmentByBookCode(BDDMockito.anyString())).thenReturn(getBookInfoDto());
        BDDMockito.when(guideAppointmentDao.getAppointmentByBookCode(BDDMockito.anyString())).thenReturn(new BookingInfoDto());
        vgoGuideService.returnVisit(3L, "2", guideId);
    }

    @Test
    public void completeOrderTest1() {
        OrderCompletionDto orderCompletionDto = getOrderCompletionDto(0);
        BDDMockito.when(guideAppointmentDao.findById(3L)).thenReturn(getAppointmentInfo("2"));
        BDDMockito.when(guideAppointmentDao.getAppointmentByBookCode(BDDMockito.anyString())).thenReturn(getBookInfoDto());
        BDDMockito.when(guideAppointmentDao.getAppointmentByBookCode(BDDMockito.anyString())).thenReturn(new BookingInfoDto());
        vgoGuideService.orderCompletion(orderCompletionDto);
    }

    @Test
    public void completeOrderTest2() {
        OrderCompletionDto orderCompletionDto = getOrderCompletionDto(1);
        BDDMockito.when(guideAppointmentDao.findById(3L)).thenReturn(getAppointmentInfo("2"));
        BDDMockito.when(guideAppointmentDao.getAppointmentByBookCode(BDDMockito.anyString())).thenReturn(getBookInfoDto());
        BDDMockito.when(guideAppointmentDao.getAppointmentByBookCode(BDDMockito.anyString())).thenReturn(new BookingInfoDto());
        vgoGuideService.orderCompletion(orderCompletionDto);
    }

    @Test
    public void completeOrderTest3() {
        OrderCompletionDto orderCompletionDto = getOrderCompletionDto(2);
        BDDMockito.when(guideAppointmentDao.findById(3L)).thenReturn(getAppointmentInfo("2"));
        BDDMockito.when(guideAppointmentDao.getAppointmentByBookCode(BDDMockito.anyString())).thenReturn(getBookInfoDto());
        BDDMockito.when(guideAppointmentDao.getAppointmentByBookCode(BDDMockito.anyString())).thenReturn(new BookingInfoDto());
        vgoGuideService.orderCompletion(orderCompletionDto);
    }

    @Test
    public void queryGuideDetailTest2() {
        BDDMockito.when(guideInfoDao.queryGuideDetai(guideId)).thenReturn(new GuideInfoDto());
        List<String> items = new ArrayList<>();
        items.add("http:jsdjsd,宝宝理发");
        items.add("育婴咨询");
        BDDMockito.when(serverItemDao.queryByGuideId(guideId)).thenReturn(items);
        vgoGuideService.queryGuideDetail(guideId, "20");
    }

    @Test
    public void updateRemarkTest() {
        BDDMockito.when(guideAppointmentDao.findById(3L)).thenReturn(getAppointmentInfo("1"));
        vgoGuideService.updateRemark(3L, "太贵了", guideId);
    }

    @Test
    public void modifyAuditGuideInfoTest(){
        GuideInfoDto guideInfoDto = new GuideInfoDto();
        guideInfoDto.setGuideId(guideId);
        guideInfoDto.setGuidePhoto("http://jjkdfjfsdk");
        guideInfoDto.setIntroduction("hhsjdnjs");
        guideInfoDto.setSaleAge(5);
        guideInfoDto.setServerName("sdjksjdksjd");
        guideInfoDto.setCategoryIds("1,2,3");
        BDDMockito.when(guideInfoDao.queryGuideDetaiAudit(guideId)).thenReturn(new GuideInfoDto());
        vgoGuideService.modifyAuditGuideInfo(guideInfoDto);
    }

    @Test
    public void queryGuideDetailTest1() {
        BDDMockito.when(guideInfoDao.queryGuideDetaiAudit(guideId)).thenReturn(getGuideInfoDto1());
        vgoGuideService.queryGuideDetail(guideId, "10");
    }

    @Test
    public void modifyAuditGuideInfoTest2(){
        GuideInfoDto guideInfoDto = new GuideInfoDto();
        guideInfoDto.setGuideId(guideId);
        guideInfoDto.setGuidePhoto("http://jdfjfsdk");
        guideInfoDto.setIntroduction("hdhsjdnjs");
        guideInfoDto.setSaleAge(5);
        guideInfoDto.setServerName("sdjksjdksjd");
        guideInfoDto.setCategoryIds("1,2,3");
        vgoGuideService.modifyAuditGuideInfo(guideInfoDto);
    }

    @Test
    public void queryGuideDetailTest3() {
        BDDMockito.when(guideInfoDao.queryGuideDetai(guideId)).thenReturn(new GuideInfoDto());
        vgoGuideService.queryGuideDetail(guideId, "10");
    }

    @Test
    public void modifyAuditGuideInfoTest3(){
        GuideInfoDto guideInfoDto = new GuideInfoDto();
        guideInfoDto.setGuideId(guideId);
        guideInfoDto.setGuidePhoto("http://jdfjfdk");
        guideInfoDto.setIntroduction("hdhsjdjs");
        guideInfoDto.setSaleAge(5);
        guideInfoDto.setServerName("sdjksjdksjd");
        guideInfoDto.setCategoryIds("1,2,3");
        BDDMockito.when(guideAuditDao.queryAuditGuideInfo(guideId)).thenReturn(new GuideAuditInfo());
        vgoGuideService.modifyAuditGuideInfo(guideInfoDto);
    }

    @Test
    public void modifyTest(){
        vgoGuideService.modifyAuditGuideInfo(null);
    }

    private BookingInfoDto getBookInfoDto() {
        BookingInfoDto bookingDto = new BookingInfoDto();
        bookingDto.setGuideId(guideId);
        bookingDto.setTelephone("15267835847");
        bookingDto.setStoreCode("7619");
        bookingDto.setCust("dfdufs0990");
        bookingDto.setBusinessCode("1");
        bookingDto.setBookingStatus(BookingStatusEnum.UNFINISH.getStatus());
        bookingDto.setComplete(CompleteStatusEnum.UNCOMPLETE_ORDER.getStatus());
        bookingDto.setBookingTime(DateUtils.formatPatten10(new Date()));
        bookingDto.setJudgeFlag(VgoConstants.SNSAWP_JUDGE_FLAG_NO);
        bookingDto.setCreateTime("2018-09-10 00:00:00");
        return bookingDto;
    }

    private StatisticsInfoDto getStatisticsInfoDto() {
        StatisticsInfoDto statisticsDto = new StatisticsInfoDto();
        statisticsDto.setChannel(VgoConstants.SNSAWP_CHANNEL);
        statisticsDto.setResourceType(VgoConstants.SNSAWP_RESOURCE_TYPE);
        statisticsDto.setCreateTime(DateUtils.format(new Date()));
        return statisticsDto;
    }

    private GuideDto getGuideDto() {
        GuideDto guideDto = new GuideDto();
        guideDto.setGuideId(guideId);
        guideDto.setTele("15238298343");
        return guideDto;
    }

    private Page<GuideAppointmentDto> getPage() {
        List<GuideAppointmentDto> content = new ArrayList<>();
        GuideAppointmentDto guideAppointmentDto1 = new GuideAppointmentDto();
        guideAppointmentDto1.setCust(cust);
        content.add(guideAppointmentDto1);
        GuideAppointmentDto guideAppointmentDto2 = new GuideAppointmentDto();
        content.add(guideAppointmentDto2);
        return new PageImpl<>(content, ParamBuilder.newPageable(0, 10), 2);
    }

    private QuerySocialInfoResp getQuerySocialInfoResp() {
        QuerySocialInfoResp querySocialInfoResp = new QuerySocialInfoResp();
        querySocialInfoResp.setCustNum(cust);
        querySocialInfoResp.setGender("210000000110");
        querySocialInfoResp.setPartyName("李大叔");
        return querySocialInfoResp;
    }

    private AppointmentInfo getAppointmentInfo(String visit) {
        AppointmentInfo appointmentInfo = new AppointmentInfo();
        appointmentInfo.setId(3L);
        appointmentInfo.setGuideId(guideId);
        appointmentInfo.setBookingStatus("0");
        appointmentInfo.setVisit(visit);
        appointmentInfo.setComplete("3");
        appointmentInfo.setCust("789800");
        appointmentInfo.setBookCode("V3783209230");
        return appointmentInfo;
    }

    private OrderCompletionDto getOrderCompletionDto(Integer complete) {
        OrderCompletionDto orderCompletionDto = new OrderCompletionDto();
        orderCompletionDto.setGuideId(guideId);
        orderCompletionDto.setComplete(complete);
        orderCompletionDto.setBookId(3L);
        return orderCompletionDto;
    }

    private GuideInfoDto getGuideInfoDto1() {
        GuideInfoDto guideInfoDto = new GuideInfoDto();
        guideInfoDto.setServerName("宝宝理发");
        guideInfoDto.setCategoryIds("1,3,8");
        guideInfoDto.setAuditReason("1,2,3#都不符合标准");
        guideInfoDto.setGuidePhoto("http://hfjdhfjdhttp");
        return guideInfoDto;
    }

}

