/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookingSendMsgJob
 * Author:   18032490_赵亚奇
 * Date:     2018/8/31 14:05
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.job.vgo;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.suning.sntk.dao.vgo.BookingSendMsgDao;
import com.suning.sntk.entity.vgo.BookingSendMsg;
import com.suning.sntk.service.message.SendMsgService;
import com.suning.sntk.support.common.utils.DateUtils;

/**
 * 定时发送短信的job
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/31
 */
@Component
public class BookingSendMsgJob {

    @Autowired
    private BookingSendMsgDao bookingSendMsgDao;

    @Autowired
    private SendMsgService sendMsgService;

    /**
     * 创建预约标志
     */
    private static final String BUILD_APPOINTMENT = "1";
    /**
     * 取消预约标志
     */
    private static final String CANCEL_APPOINTMENT = "2";
    /**
     * 短信已发送的状态
     */
    private static final String SEND_STATUS = "2";

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingSendMsgJob.class);

    /**
     * 自动发送短信的方法
     */
    @Transactional
    public void sendMsgExecute() {
        LOGGER.info("执行自动发送预约短信的job");
        List<BookingSendMsg> list = bookingSendMsgDao.queryBookingByStatus();
        LOGGER.info("待发送的短信 list:{}", list);
        if (list.isEmpty()) {
            LOGGER.info("待发送的短信为空，取消操作");
            return;
        }
        //发送短息
        for (BookingSendMsg msg : list) {
            if (msg == null) {
                continue;
            }
            if (BUILD_APPOINTMENT.equals(msg.getType())) {
                sendMsgService.sendVgoAppointMsg(msg.getAppointmentTime(), msg.getCustPhone(), msg.getGuidePhone());
            }
            if (CANCEL_APPOINTMENT.equals(msg.getType())) {
                sendMsgService.sendVgoCancelMsg(msg.getAppointmentTime(), msg.getCustPhone(), msg.getGuidePhone());
            }
            //发送完成后将状态改为已完成,设置发送时间
            msg.setStatus(SEND_STATUS);
            msg.setSendMsgTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
        }
        //更新状态
        bookingSendMsgDao.batchUpdateSkipNull(list);
        LOGGER.info("发送预约短息Job执行完成");
    }
}
