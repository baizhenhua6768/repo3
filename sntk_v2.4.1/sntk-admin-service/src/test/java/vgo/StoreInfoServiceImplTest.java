package vgo;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.suning.sntk.admin.service.vgo.impl.StoreInfoServiceImpl;
import com.suning.sntk.dao.common.StoreInfoDao;
import com.suning.sntk.dto.common.StoreGeoDto;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import static org.testng.Assert.*;

/**
 * 功能描述：
 *
 * @author 18041004_余长杰
 * @since 2018/10/12
 */
public class StoreInfoServiceImplTest {

    @InjectMocks
    private StoreInfoServiceImpl storeInfoService;

    @Mock
    private StoreInfoDao storeInfoDao;

    @Mock
    private RedisCacheUtils redisCacheUtils;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandleStoreLocationRedisCache() {
        StoreGeoDto storeInfo = new StoreGeoDto();
        storeInfo.setBusinessType("1");
        BDDMockito.when(storeInfoDao.queryStoreGeoInfo(BDDMockito.anyString())).thenReturn(storeInfo);

        storeInfoService.handleStoreLocationRedisCache("7611", 1);
    }

    @Test
    public void testHandleStoreLocationRedisCache1() {
        StoreGeoDto storeInfo = new StoreGeoDto();
        storeInfo.setBusinessType("2");
        BDDMockito.when(storeInfoDao.queryStoreGeoInfo(BDDMockito.anyString())).thenReturn(storeInfo);

        storeInfoService.handleStoreLocationRedisCache("7611", 1);
    }

    @Test
    public void testHandleStoreLocationRedisCache2() {
        BDDMockito.when(storeInfoDao.queryValidVgoCount(BDDMockito.anyString())).thenReturn(0L);

        storeInfoService.handleStoreLocationRedisCache("7611", 2);
    }
}