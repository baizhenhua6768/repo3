/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: BookingRsfServiceImplTest
 * Author:   88395115_史小配
 * Date:     2018/10/8 19:56
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

import com.suning.sntk.dto.vgo.AppointInitQueryRespDto;
import com.suning.sntk.rsf.impl.vgo.BookingRsfServiceImpl;
import com.suning.sntk.service.vgo.BookingPageService;

/**
 * 功能描述：
 *
 * @author 88395115_史小配
 * @since 2018/10/8
 */
public class BookingRsfServiceImplTest {

    @InjectMocks
    private BookingRsfServiceImpl bookingRsfService;

    @Mock
    private BookingPageService bookingPageService;

    private String customerNum = "128912";
    private String guideId = "1832983920";

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryBookingPageInitInfoTest() {
        AppointInitQueryRespDto appointInitQueryRespDto = new AppointInitQueryRespDto();
        appointInitQueryRespDto.setCustomerMobile("11111111111");
        BDDMockito.when(bookingPageService.queryBookingPageInitInfo(customerNum, guideId)).thenReturn(appointInitQueryRespDto);
        bookingRsfService.queryAppointInit(customerNum,guideId);
    }
}
