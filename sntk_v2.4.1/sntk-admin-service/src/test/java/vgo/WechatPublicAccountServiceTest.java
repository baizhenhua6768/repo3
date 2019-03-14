package vgo;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.admin.dao.vgo.GuideInfoAdminDao;
import com.suning.sntk.admin.kafka.KafkaService;
import com.suning.sntk.admin.service.vgo.impl.WechatPublicAccountServiceImpl;
import com.suning.sntk.dto.vgo.GuideInfoDto;

/**
 * @author 18032490_赵亚奇
 * @since 2018/10/12
 */
public class WechatPublicAccountServiceTest {

    @InjectMocks
    private WechatPublicAccountServiceImpl wechatPublicAccountService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private KafkaService kafkaService;

    @Mock
    private GuideInfoAdminDao guideInfoAdminDao;

    @Test
    public void testScanCodeAffair1() throws Exception {
        BDDMockito.when(guideInfoAdminDao.queryByGuideId(BDDMockito.anyString())).thenReturn(null);
        wechatPublicAccountService.scanCodeAffair("aaa", "18032490", "708B");
    }

    @Test
    public void testScanCodeAffair2() throws Exception {
        GuideInfoDto guideInfo = new GuideInfoDto();
        guideInfo.setBusinessType("0");
        BDDMockito.when(guideInfoAdminDao.queryByGuideId(BDDMockito.anyString())).thenReturn(guideInfo);
        wechatPublicAccountService.scanCodeAffair("aaa", "18032490", "708B");
    }

    @Test
    public void testScanCodeAffair3() throws Exception {
        GuideInfoDto guideInfo = new GuideInfoDto();
        guideInfo.setBusinessType("1");
        guideInfo.setTele("11111111111");
        BDDMockito.when(guideInfoAdminDao.queryByGuideId(BDDMockito.anyString())).thenReturn(guideInfo);
        wechatPublicAccountService.scanCodeAffair("aaa", "18032490", "708B");
    }

    @Test
    public void testScanCodeAffair4() throws Exception {
        GuideInfoDto guideInfo = new GuideInfoDto();
        guideInfo.setBusinessType("2");
        guideInfo.setTele("11111111111");
        BDDMockito.when(guideInfoAdminDao.queryByGuideId(BDDMockito.anyString())).thenReturn(guideInfo);
        wechatPublicAccountService.scanCodeAffair("aaa", "18032490", "708B");
    }
}