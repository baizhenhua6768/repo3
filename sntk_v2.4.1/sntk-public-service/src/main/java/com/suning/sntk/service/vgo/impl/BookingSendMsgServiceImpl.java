/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookingSendMsgServiceImpl
 * Author:   18032490_赵亚奇
 * Date:     2018/8/31 11:30
 * Description: 预约导购发短信处理
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 * 赵亚奇                2018/8/23 9:25       2.2.1
 */

package com.suning.sntk.service.vgo.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.sntk.dao.vgo.BookingSendMsgDao;
import com.suning.sntk.entity.vgo.BookingSendMsg;
import com.suning.sntk.service.vgo.BookingSendMsgService;
import com.suning.store.commons.lang.advice.Trace;

/**
 * 预约导购发短信处理
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/31
 */
@Service
@Trace
public class BookingSendMsgServiceImpl implements BookingSendMsgService {

    @Autowired
    private BookingSendMsgDao bookingSendMsgDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingSendMsgServiceImpl.class);

    /**
     * 短信未发送
     */
    private static final String UNSENT_TYPE = "1";

    /**
     * 入库操作
     *
     * @param appointDate   预约时间
     * @param customerPhone 预约顾客电话
     * @param guidePhone    店员电话
     * @param type          1：建立预约,2 :取消预约
     * @param bookingCode   预约编码
     * @author 18032490_赵亚奇
     * @since 10:23  2018/8/31
     */
    @Override
    public void delaySendMsg(String appointDate, String customerPhone, String guidePhone, String type, String bookingCode) {
        //入库操作
        BookingSendMsg bookingSendMsg = new BookingSendMsg();
        bookingSendMsg.setAppointmentTime(appointDate);
        bookingSendMsg.setBookingCode(bookingCode);
        bookingSendMsg.setCustPhone(customerPhone);
        bookingSendMsg.setGuidePhone(guidePhone);
        bookingSendMsg.setType(type);
        bookingSendMsg.setStatus(UNSENT_TYPE);
        bookingSendMsg.setCreateTime(new Date());
        LOGGER.info("延迟发送短信入库操作:{}", bookingSendMsg);
        bookingSendMsgDao.insert(bookingSendMsg);
    }
}
