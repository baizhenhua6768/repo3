/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AppointmentRsfServiceImpl
 * Author:   18032490_赵亚奇
 * Date:     2018/8/16 11:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.impl.vgo.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.suning.rsf.provider.annotation.Implement;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.GuideOrderLogDto;
import com.suning.sntk.rsf.util.ScmRsfConfigUtil;
import com.suning.sntk.rsf.vgo.appointment.AppointmentRsfService;
import com.suning.sntk.service.vgo.AppointmentPublicService;
import com.suning.sntk.support.common.AppointmentConstant;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 易购app预约模块
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/16
 */
@Implement(contract = AppointmentRsfService.class, implCode = "AppointmentRsfServiceImpl")
@Service
@Validated
public class AppointmentRsfServiceImpl implements AppointmentRsfService {

    @Autowired
    private AppointmentPublicService appointmentPublicService;

    /**
     * 查询我的预约列表
     *
     * @param custNo 会员编码
     */
    @Override
    public RsfResponseDto<List<BookingInfoDto>> queryMyAppointmentList(String custNo) {
        //从scm中获取页面展示数量
        Integer size = Integer.parseInt(ScmRsfConfigUtil.getConfig(AppointmentConstant.CUST_BOOKING_LIST_MAX_COUNT_DEFAULT_SCM,
                AppointmentConstant.VGO_APPOINTMENT_LIST_MAX_COUNT_DEFAULT));
        return appointmentPublicService.queryMyAppointmentList(custNo, size);
    }

    /**
     * 查询预约详情
     *
     * @param custNo   会员编码
     * @param bookCode 预约编码
     */
    @Override
    public RsfResponseDto<BookingInfoDto> queryAppoinmentInfo(String custNo, String bookCode) {

        return appointmentPublicService.queryAppoinmentInfo(custNo, bookCode);

    }

    /**
     * 取消预约
     *
     * @param bookCode 预约编码
     */
    @Override
    public RsfResponseDto cancelAppointment(String bookCode, String custNo) {
        return appointmentPublicService.cancelAppointment(bookCode, custNo);
    }

    /**
     * 查询操作日志
     *
     * @param bookCode 预约编码
     */
    @Override
    public RsfResponseDto<List<GuideOrderLogDto>> queryGuideOrderLog(String bookCode) {
        return appointmentPublicService.queryGuideOrderLog(bookCode);
    }

    /**
     * 查询最近的预约信息
     *
     * @param custNo 会员编码
     * @author 18032490_赵亚奇
     * @since 10:42  2018/9/14
     */
    @Override
    public RsfResponseDto<List<BookingInfoDto>> queryNearAppointment(String custNo) {
        return appointmentPublicService.queryNearAppointment(custNo);
    }
}
