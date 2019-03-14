package com.suning.sntk.admin.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * kafka发送服务
 *
 * @author 18032490_赵亚奇
 * @since 2018/10/10
 */
@Service
public class KafkaServiceImpl implements KafkaService {

    private static Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

    @Autowired
    private KafkaProducerSupport producerSupport;

    /**
     * kafka发送需要的key值
     */
    private static final String KEY = "org.apache.kafka.common.serialization.StringSerializer";

    /**
     * 发送信息
     *
     * @param topic 发送的topic
     * @param msg   发送的内容
     */
    @Override
    public void sendMessage(String topic, String msg) {
        producerSupport.sendMessage(KEY, msg, topic);
    }

}
