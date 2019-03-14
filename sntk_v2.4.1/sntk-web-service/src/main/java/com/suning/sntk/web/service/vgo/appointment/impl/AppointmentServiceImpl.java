/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MyAppointmentServiceImpl
 * Author:   18032490_赵亚奇
 * Date:     2018/8/16 11:15
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.vgo.appointment.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.GuideOrderLogDto;
import com.suning.sntk.rsf.vgo.appointment.AppointmentRsfService;
import com.suning.sntk.support.exception.vgo.AppointmentErrorEnum;
import com.suning.sntk.web.service.vgo.appointment.AppointmentService;
import com.suning.sntk.web.util.FunctionSwitchUtils;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 易购app预约模块
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/16
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRsfService appointmentRsfService = ServiceLocator
            .getService(AppointmentRsfService.class, "AppointmentRsfServiceImpl");

    /**
     * 查询我的预约列表
     *
     * @param custNo
     * @return
     */
    @Override
    public RsfResponseDto<List<BookingInfoDto>> queryMyAppointmentList(String custNo) {
        //判断是否降级处理
        Validators.ifInValid(!FunctionSwitchUtils.fourthPageSwitchOpen()).thenThrow(AppointmentErrorEnum.LOW_GRADE_DEAL);
        //调用rsf查询数据
        return appointmentRsfService.queryMyAppointmentList(custNo);

    }

    /**
     * 查询预约详情
     *
     * @param custNo   会员编码
     * @param bookCode 预约单号
     * @return
     */
    @Override
    public RsfResponseDto<BookingInfoDto> queryAppoinmentInfo(String custNo, String bookCode) {

        return appointmentRsfService.queryAppoinmentInfo(custNo, bookCode);
    }

    /**
     * 取消预约
     *
     * @param bookCode
     * @return
     */
    @Override
    public RsfResponseDto cancelAppointment(String bookCode, String custNo) {
        return appointmentRsfService.cancelAppointment(bookCode, custNo);
    }

    /**
     * 查询操作日志
     *
     * @param bookCode
     * @return
     */
    @Override
    public RsfResponseDto<List<GuideOrderLogDto>> queryGuideOrderLog(String bookCode) {
        return appointmentRsfService.queryGuideOrderLog(bookCode);
    }

    @Override
    public RsfResponseDto<List<BookingInfoDto>> queryNearAppointment(String custNo) {
        return appointmentRsfService.queryNearAppointment(custNo);
    }

}
