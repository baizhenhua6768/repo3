/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MyAppointmentService
 * Author:   18032490_赵亚奇
 * Date:     2018/8/16 11:14
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.vgo.appointment;

import java.util.List;

import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.GuideOrderLogDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 易购app预约模块
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/16
 */
public interface AppointmentService {

    RsfResponseDto<List<BookingInfoDto>> queryMyAppointmentList(String custNo);

    RsfResponseDto<BookingInfoDto> queryAppoinmentInfo(String custNo, String bookCode);

    RsfResponseDto cancelAppointment(String bookCode, String custNo);

    RsfResponseDto<List<GuideOrderLogDto>> queryGuideOrderLog(String bookCode);

    RsfResponseDto<List<BookingInfoDto>> queryNearAppointment(String custNo);
}