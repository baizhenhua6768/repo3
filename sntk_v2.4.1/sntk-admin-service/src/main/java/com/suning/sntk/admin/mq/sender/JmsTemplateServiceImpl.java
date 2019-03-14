/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: JmsTemplateServiceImpl
 * Author:   18032490_赵亚奇
 * Date:     2018/7/4 14:18
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.mq.sender;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * MQ消息发送实现类
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/4
 */
@Service
public class JmsTemplateServiceImpl implements JmsTemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsTemplateServiceImpl.class);

    /**
     * 会话对象
     */
    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void send(String destinationName, final String msg) {
        LOGGER.info("jms 开始发送消息,{},{}", destinationName, msg);
        if (StringUtils.isBlank(destinationName) || StringUtils.isBlank(msg)) {
            LOGGER.info("jms 发送消息失败,参数为空");
            return;
        }
        try {
            jmsTemplate.send(destinationName, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage();
                    message.setText(msg);
                    return message;
                }
            });
        } catch (Exception e) {
            LOGGER.error("jms 发送消息失败", e);
        }
        LOGGER.info("jms 发送消息成功");
    }
}

