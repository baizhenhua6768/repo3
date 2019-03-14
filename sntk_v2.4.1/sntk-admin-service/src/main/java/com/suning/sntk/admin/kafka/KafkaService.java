package com.suning.sntk.admin.kafka;

/**
 * kafka发送服务
 *
 * @author 18032490_赵亚奇
 * @since 2018/10/10
 */
public interface KafkaService {

    void sendMessage(String topic, String msg);
}
