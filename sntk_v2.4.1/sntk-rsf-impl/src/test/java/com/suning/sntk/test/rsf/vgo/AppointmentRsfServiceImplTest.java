package com.suning.sntk.test.rsf.vgo;

import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.rsf.impl.vgo.appointment.AppointmentRsfServiceImpl;
import com.suning.sntk.service.vgo.AppointmentPublicService;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * @author 18032490_赵亚奇
 * @since 2018/9/18
 */
public class AppointmentRsfServiceImplTest {

    @InjectMocks
    private AppointmentRsfServiceImpl appointmentRsfService;

    @Mock
    private AppointmentPublicService appointmentPublicService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testQueryMyAppointmentList() throws Exception {
        RsfResponseDto<List<BookingInfoDto>> dto = RsfResponseDto.empty();
        String custNo = "123456";
        BDDMockito.when(appointmentPublicService.queryMyAppointmentList(BDDMockito.anyString(), BDDMockito.anyInt())).thenReturn(dto);
        appointmentRsfService.queryMyAppointmentList(custNo);
    }

    @Test
    public void testQueryAppoinmentInfo() throws Exception {
        RsfResponseDto<BookingInfoDto> dto = RsfResponseDto.empty();
        String custNo = "123456";
        String bookCode = "1000000";
        BDDMockito.when(appointmentPublicService.queryAppoinmentInfo(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(dto);
        appointmentRsfService.queryAppoinmentInfo(custNo, bookCode);
    }

    @Test
    public void testCancelAppointment() throws Exception {
        RsfResponseDto dto = RsfResponseDto.empty();
        String custNo = "123456";
        String bookCode = "1000000";
        BDDMockito.when(appointmentPublicService.cancelAppointment(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(dto);
        appointmentRsfService.cancelAppointment(bookCode, custNo);
    }

    @Test
    public void testQueryGuideOrderLog() throws Exception {
        RsfResponseDto dto = RsfResponseDto.empty();
        String bookCode = "1000000";
        BDDMockito.when(appointmentPublicService.queryGuideOrderLog(BDDMockito.anyString())).thenReturn(dto);
        appointmentRsfService.queryGuideOrderLog(bookCode);
    }

    @Test
    public void testQueryNearAppointment() throws Exception {
        RsfResponseDto dto = RsfResponseDto.empty();
        String custNo = "1000000";
        BDDMockito.when(appointmentPublicService.queryNearAppointment(BDDMockito.anyString())).thenReturn(dto);
        appointmentRsfService.queryNearAppointment(custNo);
    }
}