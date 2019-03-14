/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: VgoAppointmentJob
 * Author:   88395115_史小配
 * Date:     2018/8/30 14:10
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.job.vgo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.suning.sntk.dao.vgo.GuideAppointmentDao;
import com.suning.sntk.entity.vgo.AppointmentInfo;
import com.suning.sntk.support.enums.vgo.BookingStatusEnum;
import com.suning.sntk.support.enums.vgo.CompleteStatusEnum;

/**
 * 功能描述：预约单定时任务
 *
 * @author 88395115_史小配
 * @since 2018/8/30
 */
@Component
public class VgoAppointmentJob {

    @Autowired
    private GuideAppointmentDao appointmentDao;

    /**
     * 将30天前建立的未完成预约单改为系统取消
     *
     * @author 88395115_史小配
     * @since 14:13 2018/8/30
     */
    public void systemCancelAppointment() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -30);
        List<AppointmentInfo> list = appointmentDao.queryAppointmentByCreatTime(new Timestamp(c.getTimeInMillis()));
        for (AppointmentInfo appointmentInfo:list) {
            appointmentInfo.setBookingStatus(BookingStatusEnum.FINISH.getStatus());
            appointmentInfo.setComplete(CompleteStatusEnum.SYSTEM_CANCEL.getStatus());
        }
        appointmentDao.batchUpdateSkipNull(list);
    }
}
