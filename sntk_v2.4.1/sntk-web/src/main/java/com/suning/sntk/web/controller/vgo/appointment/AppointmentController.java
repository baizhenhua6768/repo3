/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MyAppointment
 * Author:   18032490_赵亚奇
 * Date:     2018/8/16 9:58
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.controller.vgo.appointment;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.GuideOrderLogDto;
import com.suning.sntk.support.exception.vgo.AppointmentErrorEnum;
import com.suning.sntk.web.service.vgo.appointment.AppointmentService;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 易购app预约模块
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/16
 */
@RestController
@Trace
@Validated
@RequestMapping("/vgo/appointment")
public class AppointmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;

    /**
     * 查询我的预约列表
     *
     * @param request
     * @author 18032490_赵亚奇
     * @since 10:16  2018/8/29
     */
    @RequestMapping("/queryMyAppointmentList")
    @ResponseBody
    public RsfResponseDto<List<BookingInfoDto>> queryMyAppointmentList(HttpServletRequest request) {
        String custNo = request.getRemoteUser();
        LOGGER.info("查询我的预约列表 会员编码:{}", custNo);
        Validators.ifBlank(custNo).thenThrow(AppointmentErrorEnum.PARAM_IS_NULL);
        return appointmentService.queryMyAppointmentList(custNo);
    }

    /**
     * 查询预约详情
     *
     * @param bookCode 预约编码
     * @author 18032490_赵亚奇
     * @since 10:16  2018/8/29
     */
    @RequestMapping("/queryAppoinmentInfo")
    @ResponseBody
    public RsfResponseDto<BookingInfoDto> queryAppoinmentInfo(HttpServletRequest request, @NotBlank String bookCode) {
        String custNo = request.getRemoteUser();
        LOGGER.info("查询我的预约列表 会员编码:{}", custNo);
        Validators.ifBlank(custNo).thenThrow(AppointmentErrorEnum.PARAM_IS_NULL);
        return appointmentService.queryAppoinmentInfo(custNo, bookCode);
    }

    /**
     * 取消预约
     *
     * @param bookCode 预约编码
     * @author 18032490_赵亚奇
     * @since 10:17  2018/8/29
     */
    @RequestMapping("/cancelAppointment")
    @ResponseBody
    public RsfResponseDto cancelAppointment(HttpServletRequest request, @NotBlank String bookCode) {
        String custNo = request.getRemoteUser();
        LOGGER.info("取消预约 会员编码:{}", custNo);
        Validators.ifBlank(custNo).thenThrow(AppointmentErrorEnum.PARAM_IS_NULL);
        return appointmentService.cancelAppointment(bookCode, custNo);
    }

    /**
     * 查询操作日志
     *
     * @param bookCode 预约编码
     * @author 18032490_赵亚奇
     * @since 10:17  2018/8/29
     */
    @RequestMapping("/queryGuideOrderLog")
    @ResponseBody
    public RsfResponseDto<List<GuideOrderLogDto>> queryGuideOrderLog(HttpServletRequest request, @NotBlank String bookCode) {
        String custNo = request.getRemoteUser();
        LOGGER.info("queryGuideOrderLog: custNo={},bookCode={}", custNo,bookCode);
        Validators.ifBlank(custNo).thenThrow(AppointmentErrorEnum.PARAM_IS_NULL);
        RsfResponseDto<List<GuideOrderLogDto>> rsfResponseDto = appointmentService.queryGuideOrderLog(bookCode);
        if(rsfResponseDto.isSuccess() && CollectionUtils.isNotEmpty(rsfResponseDto.getData())
                && !custNo.equals(rsfResponseDto.getData().get(0).getCustNumber())){
            return RsfResponseDto.empty();
        }
        return rsfResponseDto;
    }

    /**
     * 查询会员最近一次未完成的预约记录
     *
     * @author 18032490_赵亚奇
     * @since 14:34  2018/9/14
     */
    @RequestMapping("/queryNearAppointment.htm")
    @ResponseBody
    public RsfResponseDto<List<BookingInfoDto>> queryNearAppointment(HttpServletRequest request) {
        String custNo = request.getRemoteUser();
        LOGGER.info("取消预约 会员编码:{}", custNo);
        //获取不到会员编码则直接返回空集合
        if (StringUtils.isBlank(custNo)) {
            return RsfResponseDto.of(Collections.<BookingInfoDto>emptyList());
        }
        return appointmentService.queryNearAppointment(custNo);
    }
}
