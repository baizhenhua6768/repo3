package vgo;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.admin.kafka.KafkaProducerSupport;
import com.suning.sntk.admin.kafka.KafkaService;
import com.suning.sntk.admin.service.vgo.impl.WechatPublicAccountServiceImpl;
import static org.testng.Assert.*;

/**
 * @author 18032490_赵亚奇
 * @since 2018/10/15
 */
public class KafkaProducerSupportTest {

    @InjectMocks
    private KafkaProducerSupport kafkaProducerSupport;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testAfterPropertiesSet() throws Exception {
//        kafkaProducerSupport.afterPropertiesSet();

    }

    @Test
    public void testDestroy() throws Exception {
        kafkaProducerSupport.destroy();
    }

    @Test
    public void testSendMessage() throws Exception {
        // kafkaProducerSupport.sendMessage("a","b","c");
    }
}