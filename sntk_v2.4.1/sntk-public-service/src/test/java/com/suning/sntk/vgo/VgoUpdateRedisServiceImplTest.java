package com.suning.sntk.vgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.baozi.rsfservice.dto.StoreGuideDto;
import com.suning.sntk.consumer.BaoziConsumerService;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dto.vgo.CategoryOutRelDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.dto.vgo.StoreGuideInfoDto;
import com.suning.sntk.dto.vgo.StoreInfoDto;
import com.suning.sntk.service.vgo.VgoCommonService;
import com.suning.sntk.service.vgo.impl.VgoUpdateRedisServiceImpl;
import com.suning.sntk.support.common.redis.RedisCacheUtils;

/**
 * 功能描述：
 *
 * @author 18041004_余长杰
 * @since 2018/10/11
 */
public class VgoUpdateRedisServiceImplTest {

    @InjectMocks
    private VgoUpdateRedisServiceImpl vgoUpdateRedisService;

    @Mock
    private RedisCacheUtils redisCacheUtils;

    @Mock
    private GuideInfoDao guideInfoDao;

    @Mock
    private VgoCommonService vgoCommonService;

    @Mock
    private BaoziConsumerService baoziConsumerService;


    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveAllCustManagerToCache() {
        List<ManagerInfoDto> custManagerList = new ArrayList<>();
        ManagerInfoDto managerInfoDto = new ManagerInfoDto();
        managerInfoDto.setBusinessType("1");
        managerInfoDto.setCustNo("1");
        managerInfoDto.setStaffId("18041004");
        custManagerList.add(managerInfoDto);
        BDDMockito.when(guideInfoDao.queryAllCustManager()).thenReturn(custManagerList);

        StoreGuideInfoDto guideInfo = new StoreGuideInfoDto();
        guideInfo.setGuideId("123546");
        BDDMockito.when(guideInfoDao.queryAllGuideInfoByGuideId(BDDMockito.anyString())).thenReturn(guideInfo);
        queryGuideOrderNumAndPraise();

        vgoUpdateRedisService.saveAllCustManagerToCache();
    }

    @Test
    public void testSaveAllThreeDirectoryCategoryToCache() {
        List<CategoryOutRelDto> categoryOutRelList = new ArrayList<>();
        CategoryOutRelDto categoryOutRelDto = new CategoryOutRelDto();
        categoryOutRelDto.setCategoryCode("1");
        categoryOutRelDto.setOutCode("111");
        categoryOutRelList.add(categoryOutRelDto);
        BDDMockito.when(guideInfoDao.queryAllThreeDirectoryCategory()).thenReturn(categoryOutRelList);

        vgoUpdateRedisService.saveAllThreeDirectoryCategoryToCache();
    }

    @Test
    public void testSaveGuideInfoToCacheByStore() {

        BDDMockito.when(guideInfoDao.queryAllVgoStoreListCount(BDDMockito.anyString())).thenReturn(1L);

        List<StoreInfoDto> storeInfoList = new ArrayList<>();
        StoreInfoDto storeInfoDto = new StoreInfoDto();
        storeInfoDto.setStoreId("7611");
        storeInfoDto.setBusinessType("1");
        storeInfoList.add(storeInfoDto);
        BDDMockito.when(guideInfoDao.queryAllVgoStoreList(BDDMockito.anyInt(), BDDMockito.anyInt(), BDDMockito.anyString())).
                thenReturn(storeInfoList);

        List<StoreGuideDto> storeGuideList = new ArrayList<>();
        StoreGuideDto storeGuideDto = new StoreGuideDto();
        storeGuideDto.setGuideId("123456");
        storeGuideDto.setCategoryId("1");
        storeGuideList.add(storeGuideDto);
        BDDMockito.when(baoziConsumerService.queryStoreBestGuideId(BDDMockito.anyString())).thenReturn(storeGuideList);

        StoreGuideInfoDto guideInfo = new StoreGuideInfoDto();
        guideInfo.setGuideId("123546");
        BDDMockito.when(guideInfoDao.queryAllGuideInfoByGuideId(BDDMockito.anyString())).thenReturn(guideInfo);
        queryGuideOrderNumAndPraise();

        vgoUpdateRedisService.saveGuideInfoToCacheByStore();
    }

    @Test
    public void testSaveGuideInfoToCacheByStore1() {

        BDDMockito.when(baoziConsumerService.queryStoreBestGuideId(BDDMockito.anyString())).thenReturn(new ArrayList<StoreGuideDto>());

        BDDMockito.when(guideInfoDao.queryAllVgoStoreListCount("1")).thenReturn(1L);
        BDDMockito.when(guideInfoDao.queryAllVgoStoreListCount("2")).thenReturn(1L);

        List<StoreInfoDto> storeInfoList = new ArrayList<>();
        StoreInfoDto storeInfoDto = new StoreInfoDto();
        storeInfoDto.setStoreId("7611");
        storeInfoDto.setBusinessType("1");
        storeInfoDto.setCityId("025");
        storeInfoDto.setDistrictId("02501");
        storeInfoList.add(storeInfoDto);
        BDDMockito.when(guideInfoDao.queryAllVgoStoreList(BDDMockito.anyInt(), BDDMockito.anyInt(), BDDMockito.anyString())).
                thenReturn(storeInfoList);

        List<CategoryOutRelDto> categoryOutRelList = new ArrayList<>();
        CategoryOutRelDto categoryOutRelDto = new CategoryOutRelDto();
        categoryOutRelDto.setCategoryCode("1");
        categoryOutRelDto.setOutCode("111");
        categoryOutRelList.add(categoryOutRelDto);
        BDDMockito.when(guideInfoDao.queryAllThreeDirectoryCategory()).thenReturn(categoryOutRelList);

        List<StoreGuideInfoDto> electricGuideList = new ArrayList<>();
        StoreGuideInfoDto storeGuideInfoDto = new StoreGuideInfoDto();
        storeGuideInfoDto.setGuideId("123");
        electricGuideList.add(storeGuideInfoDto);
        BDDMockito.when(guideInfoDao.queryElectricStoreGuide(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(electricGuideList);

        List<StoreGuideInfoDto> infantGuideList = new ArrayList<>();
        StoreGuideInfoDto storeGuideInfoDto1 = new StoreGuideInfoDto();
        storeGuideInfoDto1.setGuideId("123");
        infantGuideList.add(storeGuideInfoDto1);
        BDDMockito.when(guideInfoDao.queryInfantStoreGuide(BDDMockito.anyString())).thenReturn(infantGuideList);

        vgoUpdateRedisService.saveGuideInfoToCacheByStore();
    }

    @Test
    public void testSaveGuideInfoToCacheByCity() {
        List<String> cityList = new ArrayList<>();
        cityList.add("025");
        BDDMockito.when(guideInfoDao.queryAllGuideCity()).thenReturn(cityList);

        List<CategoryOutRelDto> categoryOutRelList = new ArrayList<>();
        CategoryOutRelDto categoryOutRelDto = new CategoryOutRelDto();
        categoryOutRelDto.setCategoryCode("1");
        categoryOutRelDto.setOutCode("111");
        categoryOutRelList.add(categoryOutRelDto);
        BDDMockito.when(guideInfoDao.queryAllThreeDirectoryCategory()).thenReturn(categoryOutRelList);

        List<StoreGuideInfoDto> guideInfoList = new ArrayList<>();
        StoreGuideInfoDto storeGuideInfoDto = new StoreGuideInfoDto();
        storeGuideInfoDto.setGuideId("123");
        storeGuideInfoDto.setBusinessType("2");
        guideInfoList.add(storeGuideInfoDto);
        BDDMockito.when(guideInfoDao.queryGuideByCityAndCategory(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(guideInfoList);

        vgoUpdateRedisService.saveGuideInfoToCacheByCity();
    }

    @Test
    public void testSaveGuideInfoToCacheByCityAndBusinessType() {
        List<String> cityList = new ArrayList<>();
        cityList.add("025");
        BDDMockito.when(guideInfoDao.queryAllGuideCity()).thenReturn(cityList);

        List<StoreGuideInfoDto> guideInfoList = new ArrayList<>();
        StoreGuideInfoDto storeGuideInfoDto = new StoreGuideInfoDto();
        storeGuideInfoDto.setGuideId("123");
        storeGuideInfoDto.setBusinessType("2");
        guideInfoList.add(storeGuideInfoDto);
        BDDMockito.when(guideInfoDao.queryGuideByCity(BDDMockito.anyString())).thenReturn(guideInfoList);

        vgoUpdateRedisService.saveGuideInfoToCacheByCityAndBusinessType();
    }

    @Test
    public void testSaveAllGuideInfoToCache() {
        BDDMockito.when(guideInfoDao.queryAllGuideInfoCount()).thenReturn(1L);

        List<StoreGuideInfoDto> guideInfoList = new ArrayList<>();
        StoreGuideInfoDto storeGuideInfoDto = new StoreGuideInfoDto();
        storeGuideInfoDto.setGuideId("123");
        storeGuideInfoDto.setBusinessType("2");
        guideInfoList.add(storeGuideInfoDto);
        BDDMockito.when(guideInfoDao.queryAllGuideInfo(BDDMockito.anyInt(), BDDMockito.anyInt())).thenReturn(guideInfoList);

        vgoUpdateRedisService.saveAllGuideInfoToCache();
    }

    @Test
    public void testSaveAllVgoStoreListToCache() {
        BDDMockito.when(guideInfoDao.queryAllVgoStoreListCount(BDDMockito.anyString())).thenReturn(1L);

        List<StoreInfoDto> storeInfoList = new ArrayList<>();
        StoreInfoDto storeInfoDto = new StoreInfoDto();
        storeInfoDto.setStoreId("7611");
        storeInfoDto.setBusinessType("1");
        storeInfoDto.setCityId("025");
        storeInfoDto.setDistrictId("02501");
        storeInfoList.add(storeInfoDto);
        BDDMockito.when(guideInfoDao.queryAllVgoStoreList(BDDMockito.anyInt(), BDDMockito.anyInt(), BDDMockito.anyString())).
                thenReturn(storeInfoList);

        vgoUpdateRedisService.saveAllVgoStoreListToCache();
    }

    @Test
    public void testQueryGuideOrderNumAndPraise() {
        StoreGuideInfoDto storeGuideInfoDto = new StoreGuideInfoDto();
        storeGuideInfoDto.setGuideId("123");
        storeGuideInfoDto.setBusinessType("2");

        Map<String, String> map = new HashMap<>();
        map.put("orderNum", "1");
        map.put("receivePraise", "2");
        BDDMockito.when(vgoCommonService.queryOrderNumAndReceivePraise(BDDMockito.anyString())).thenReturn(map);


        BDDMockito.when(vgoCommonService.queryGuideDefaultPhoto(BDDMockito.anyString())).thenReturn("1");
        vgoUpdateRedisService.queryGuideOrderNumAndPraise(storeGuideInfoDto);
    }

    private void queryGuideOrderNumAndPraise(){
        StoreGuideInfoDto storeGuideInfoDto = new StoreGuideInfoDto();
        storeGuideInfoDto.setGuideId("123");
        storeGuideInfoDto.setBusinessType("2");

        Map<String, String> map = new HashMap<>();
        map.put("orderNum", "1");
        map.put("receivePraise", "2");
        BDDMockito.when(vgoCommonService.queryOrderNumAndReceivePraise(BDDMockito.anyString())).thenReturn(map);


        BDDMockito.when(vgoCommonService.queryGuideDefaultPhoto(BDDMockito.anyString())).thenReturn("1");
    }
}