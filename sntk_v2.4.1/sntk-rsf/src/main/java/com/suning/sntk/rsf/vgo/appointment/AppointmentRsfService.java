/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MyAppointmentRsf
 * Author:   18032490_赵亚奇
 * Date:     2018/8/16 11:21
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.vgo.appointment;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.GuideOrderLogDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 易购app预约模块
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/16
 */
@Contract(name = "appointmentRsfService", description = "易购app预约导购相关接口")
public interface AppointmentRsfService {

    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询我的预约列表")
    RsfResponseDto<List<BookingInfoDto>> queryMyAppointmentList(@NotBlank String custNo);

    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "预约详情")
    RsfResponseDto<BookingInfoDto> queryAppoinmentInfo(@NotBlank String custNo, @NotBlank String bookCode);

    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "取消预约")
    RsfResponseDto cancelAppointment(@NotBlank String bookCode, @NotBlank String custNo);

    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询操作日志")
    RsfResponseDto<List<GuideOrderLogDto>> queryGuideOrderLog(@NotBlank String bookCode);

    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询会员最近的预约记录")
    RsfResponseDto<List<BookingInfoDto>> queryNearAppointment(String custNo);
}