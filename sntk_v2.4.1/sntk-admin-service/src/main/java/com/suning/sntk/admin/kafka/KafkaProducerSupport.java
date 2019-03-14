package com.suning.sntk.admin.kafka;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.suning.sntk.admin.util.SCMConfigUtil;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * kafka
 *
 * @author 18032490_赵亚奇
 * @since 2018/10/10
 */
@Service
public class KafkaProducerSupport implements InitializingBean, DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(KafkaProducerSupport.class);

    private Properties properties = new Properties();

    private Producer<String, String> producer;

    @Override
    public void afterPropertiesSet() throws Exception {
        String broker = SCMConfigUtil.getConfig("metadata.broker.list");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("metadata.broker.list", broker);
        properties.put(" request.required.acks", "1");
        producer = new kafka.javaapi.producer.Producer<>(new ProducerConfig(properties));
    }

    @Override
    public void destroy() throws Exception {
        try {
            if (producer != null) {
                producer.close();
            }
        } catch (Exception e) {
            logger.error("KafkaProducerSupport," + e, e);
        }

    }

    public void sendMessage(String key, String message, String topic) {
        try {
            logger.info("kafka发送消息,topic:{},message:{}", topic, message);
            producer.send(new KeyedMessage<String, String>(topic, key, message));
        } catch (Exception e) {
            logger.error("KafkaProducerSupport," + e, e);
        }
    }

}
