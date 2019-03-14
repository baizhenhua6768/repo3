package com.suning.sntk.vgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.suning.sntk.BaseTest;
import com.suning.sntk.dao.vgo.GuideAppointmentDao;
import com.suning.sntk.dao.vgo.GuideOrderLogDao;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.GuideDto;
import com.suning.sntk.dto.vgo.GuideOrderLogDto;
import com.suning.sntk.service.message.SendMsgService;
import com.suning.sntk.service.vgo.BookingSendMsgService;
import com.suning.sntk.service.vgo.VgoCommonService;
import com.suning.sntk.service.vgo.VgoGuideService;
import com.suning.sntk.service.vgo.impl.AppointmentPublicServiceImpl;
import com.suning.sntk.support.common.redis.RedisCacheUtils;

/**
 * @author 18032490_赵亚奇
 * @since 2018/9/5
 */
public class AppointmentPublicServiceTest extends BaseTest {

    @InjectMocks
    private AppointmentPublicServiceImpl appointmentPublicService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private RedisCacheUtils redisCacheUtils;

    @Mock
    private GuideAppointmentDao appointmentDao;

    @Mock
    private VgoGuideService guideService;

    @Mock
    private GuideOrderLogDao guideOrderLogDao;

    @Mock
    private SendMsgService sendMsgService;

    @Mock
    private BookingSendMsgService bookingSendMsgService;

    @Mock
    private VgoCommonService vgoCommonService;

    @Test
    public void testQueryMyAppointmentList1() throws Exception {
        String custNo = "6114710766";
        Integer size = 100;
        Set<String> set = new HashSet<>();
        set.add("String");
        List<String> list = new ArrayList<>();
        list.add("{'bookCode':'V5809948012','createTime':'2018-09-11 10:48:07.0','cust':'6114710766','guideId':'10102457'}");
        list.add("{'bookCode':'V5809948013','createTime':'2018-09-10 10:48:07.0','cust':'6114710767','guideId':'10102457'}");
        list.add("{'bookCode':'V5809948016','createTime':'2018-09-15 10:48:07.0','cust':'6114710767','guideId':'10102457'}");
        Map<String, String> map = new HashMap<>();
        map.put("orderNum", "100");
        BDDMockito.when(redisCacheUtils.zcard(BDDMockito.anyString())).thenReturn(20L);
        BDDMockito.when(redisCacheUtils.zrevrange(BDDMockito.anyString(), BDDMockito.anyLong(), BDDMockito.anyLong())).thenReturn(set);
        BDDMockito.when(redisCacheUtils.hmget(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(list);
        BDDMockito.when(vgoCommonService.queryOrderNumAndReceivePraise(BDDMockito.anyString())).thenReturn(map);

        appointmentPublicService.queryMyAppointmentList(custNo, size);
    }

    @Test
    public void testQueryMyAppointmentList2() throws Exception {
        String custNo = "6114710766";
        Integer size = 100;
        Set<String> set = new HashSet<>();
        set.add("String");
        List<String> list = new ArrayList<>();
        List<BookingInfoDto> bookList = Lists.newArrayList();
        BookingInfoDto dto = new BookingInfoDto();
        dto.setCreateTime("2018-09-11 10:48:07");
        bookList.add(dto);
        BDDMockito.when(redisCacheUtils.zcard(BDDMockito.anyString())).thenReturn(0L);
        BDDMockito.when(redisCacheUtils.zrevrange(BDDMockito.anyString(), BDDMockito.anyLong(), BDDMockito.anyLong())).thenReturn(set);
        BDDMockito.when(redisCacheUtils.hmget(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(list);
        BDDMockito.when(appointmentDao.queryBookList(BDDMockito.anyString(), BDDMockito.anyInt())).thenReturn(bookList);
        BDDMockito.when(redisCacheUtils.hexists(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(true);
        BDDMockito.when(redisCacheUtils.exists(BDDMockito.anyString())).thenReturn(false);//差一个分支为true的
        appointmentPublicService.queryMyAppointmentList(custNo, size);
    }

    @Test
    public void testQueryMyAppointmentList3() throws Exception {
        String custNo = "6114710766";
        Integer size = 100;
        Set<String> set = new HashSet<>();
        set.add("String");
        List<String> list = new ArrayList<>();
        List<BookingInfoDto> bookList = Lists.newArrayList();
        BookingInfoDto dto = new BookingInfoDto();
        dto.setCreateTime("2018-09-11 10:48:07");
        bookList.add(dto);
        BDDMockito.when(redisCacheUtils.zcard(BDDMockito.anyString())).thenReturn(0L);
        BDDMockito.when(redisCacheUtils.zrevrange(BDDMockito.anyString(), BDDMockito.anyLong(), BDDMockito.anyLong())).thenReturn(set);
        BDDMockito.when(redisCacheUtils.hmget(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(list);
        BDDMockito.when(appointmentDao.queryBookList(BDDMockito.anyString(), BDDMockito.anyInt())).thenReturn(bookList);
        BDDMockito.when(redisCacheUtils.hexists(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(true);
        BDDMockito.when(redisCacheUtils.exists(BDDMockito.anyString())).thenReturn(true);
        appointmentPublicService.queryMyAppointmentList(custNo, size);
    }

    @Test
    public void testQueryAppoinmentInfo() throws Exception {
        String custNo = "100100";
        String bookCode = "0000001";
        BookingInfoDto info = new BookingInfoDto();
        info.setGuideId("11111");
        Map<String, String> map = new HashMap<>();
        map.put("orderNum", "100");
        BDDMockito.when(redisCacheUtils.hget(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn("");
        BDDMockito.when(appointmentDao.findBookingByBookCode(BDDMockito.anyString())).thenReturn(info);
        BDDMockito.when(vgoCommonService.queryOrderNumAndReceivePraise(BDDMockito.anyString())).thenReturn(map);
        appointmentPublicService.queryAppoinmentInfo(custNo, bookCode);
    }

    @Test
    public void testCancelAppointment() throws Exception {
        String bookCode = "0000001";
        BookingInfoDto infoDto = new BookingInfoDto();
        infoDto.setCust("0001");
        infoDto.setCreateTime("2018-09-11 10:48:07");
        GuideDto guideInfo = new GuideDto();
        BDDMockito.when(appointmentDao.getBookingInfoByBookCode(BDDMockito.anyString())).thenReturn(infoDto);
        BDDMockito.when(redisCacheUtils.getBin(BDDMockito.anyString(), BDDMockito.eq(Integer.class))).thenReturn(4);
        BDDMockito.when(appointmentDao.getBookingInfoByBookCode(BDDMockito.anyString())).thenReturn(infoDto);
        BDDMockito.when(redisCacheUtils.hexists(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(true);
        BDDMockito.when(redisCacheUtils.zrem(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(1L);
        BDDMockito.when(redisCacheUtils.exists(BDDMockito.anyString())).thenReturn(true);
        BDDMockito.when(redisCacheUtils.zadd(BDDMockito.anyString(), BDDMockito.anyDouble(), BDDMockito.anyString())).thenReturn(1L);
        BDDMockito.when(redisCacheUtils.hset(BDDMockito.anyString(), BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(1L);
        BDDMockito.when(redisCacheUtils.del(BDDMockito.anyString())).thenReturn(1L);
        BDDMockito.when(guideService.getGuideInfo(BDDMockito.anyString())).thenReturn(guideInfo);
        BDDMockito.when(sendMsgService.sendVgoCancelMsg(BDDMockito.anyString(), BDDMockito.anyString(), BDDMockito.anyString()))
                .thenReturn(true);
        appointmentPublicService.cancelAppointment(bookCode, "0001");
    }

    @Test
    public void testQueryGuideOrderLog() throws Exception {
        String bookCode = "0000001";
        List<GuideOrderLogDto> list = Lists.newArrayList();
        BDDMockito.when(guideOrderLogDao.queryGuideOrderLog(BDDMockito.anyString())).thenReturn(list);
        appointmentPublicService.queryGuideOrderLog(bookCode);
    }

    @Test
    public void queryNearAppointment() throws Exception {
        String custNo = "100100";
        List<BookingInfoDto> list = Lists.newArrayList();
        BookingInfoDto dto = new BookingInfoDto();
        list.add(dto);
        BDDMockito.when(appointmentDao.queryNearAppointment(BDDMockito.anyString())).thenReturn(list);
        appointmentPublicService.queryNearAppointment(custNo);
    }
}