/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: VgoRsfServiceTest
 * Author:   88395115_史小配
 * Date:     2018/9/12 17:35
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.test.rsf.vgo;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.dto.vgo.BookingReqDto;
import com.suning.sntk.dto.vgo.OrderCompletionDto;
import com.suning.sntk.dto.vgo.QueryAppointmentReqDto;
import com.suning.sntk.rsf.impl.vgo.VgoGuideRsfServiceImpl;
import com.suning.sntk.service.vgo.VgoGuideService;
import com.suning.store.dal.base.ParamBuilder;

/**
 * 功能描述：
 *
 * @author 88395115_史小配
 * @since 2018/9/12
 */
public class VgoGuideRsfServiceTest {

    @InjectMocks
    private VgoGuideRsfServiceImpl vgoGuideRsfService;

    @Mock
    private VgoGuideService guideService;


    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1(){
        vgoGuideRsfService.updateRemark(3L,"太贵了","849384");
    }

    @Test
    public void test2(){
        vgoGuideRsfService.returnVisit(3L,"1","32983923");
    }

    @Test
    public void test3(){
        vgoGuideRsfService.addBooking(new BookingReqDto());
    }

    @Test
    public void test4(){
        QueryAppointmentReqDto reqDto = new QueryAppointmentReqDto();
        reqDto.setUnComplete("3");
        reqDto.setComplete("7");
        reqDto.setCreateTime("-3");
        vgoGuideRsfService.queryAppointmentList(reqDto, ParamBuilder.newPageable(0,10));
    }
    @Test
    public void test5(){
        QueryAppointmentReqDto reqDto = new QueryAppointmentReqDto();
        reqDto.setUnComplete("2");
        reqDto.setCreateTime("-1");
        vgoGuideRsfService.queryAppointmentList(reqDto, ParamBuilder.newPageable(0,10));
    }
    @Test
    public void test6(){
        vgoGuideRsfService.orderCompletion(new OrderCompletionDto());
    }

}
