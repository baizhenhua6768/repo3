/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: SendMsgServiceImplTest
 * Author:   17061157_王薛
 * Date:     2018/8/28 16:00
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.service.message.SendMsgService;

/**
 * 功能描述：
 *
 * @author 17061157_王薛
 * @since 2018/8/28
 */
public class SendMsgServiceImplTest extends BaseTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(SendMsgServiceImplTest.class);

    @Autowired
    private SendMsgService sendMsgService;

    @Test
    public void testSendAppointMsg() {
        boolean result = sendMsgService.sendVgoAppointMsg("2018-09-20", "18501234567", "123");
        LOGGER.info("发送结果:" + result);
    }

    @Test
    public void testSendCancelMsg() {
        boolean result = sendMsgService.sendVgoCancelMsg("2018-09-15", "18966663451", "123");
        LOGGER.info("发送结果:" + result);
    }
}
