package com.suning.sntk.vgo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.baozi.rsfservice.dto.StoreGuideDto;
import com.suning.sntk.consumer.BaoziConsumerService;
import com.suning.sntk.dao.vgo.CategoryRelDao;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dao.vgo.StoreDetailDao;
import com.suning.sntk.dto.vgo.StoreGuideInfoDto;
import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;
import com.suning.sntk.entity.vgo.GuideInfo;
import com.suning.sntk.service.vgo.VgoUpdateRedisService;
import com.suning.sntk.service.vgo.impl.VgoModifyRedisServiceImpl;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.utils.JsonUtils;

/**
 * 功能描述：v购修改redis服务测试类
 *
 * @author 88396455_白振华
 * @since 2018-10-11
 */
public class VgoModifyRedisServiceImplTest {

    /**
     * 会员编号
     */
    private static final String CUST_NO = "678";
    /**
     * 导购工号
     */
    private static final String GUIDE_ID = "10071578";
    /**
     * 电器业态
     */
    private static final String ELECTRIC = "1";
    @InjectMocks
    private VgoModifyRedisServiceImpl vgoModifyRedisService;
    @Mock
    private RedisCacheUtils redisCacheUtils;
    @Mock
    private CategoryRelDao categoryRelDao;
    @Mock
    private StoreDetailDao storeDetailDao;
    @Mock
    private GuideInfoDao guideInfoDao;
    @Mock
    private VgoUpdateRedisService vgoUpdateRedisService;
    @Mock
    private BaoziConsumerService baoziConsumerService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testChangeCustomerManagerCache() {
        StoreGuideInfoDto guideInfo = new StoreGuideInfoDto();
        guideInfo.setGuideId("10071578");
        BDDMockito.when(guideInfoDao.queryAllGuideInfoByGuideId(BDDMockito.anyString())).thenReturn(guideInfo);
        vgoModifyRedisService.changeCustomerManagerCache(CUST_NO, GUIDE_ID, ELECTRIC);
    }

    @Test
    public void testDeleteGuideInfoCache() {
        BDDMockito.when(storeDetailDao.queryCityCodeByStoreCode(BDDMockito.anyString())).thenReturn("025");
        List<VcategoryRelInfoDto> vcategoryRelInfoDtos = new ArrayList<VcategoryRelInfoDto>();
        VcategoryRelInfoDto vcategoryRelInfoDto = new VcategoryRelInfoDto();
        vcategoryRelInfoDto.setCategoryCode("1");
        vcategoryRelInfoDto.setGuideId(GUIDE_ID);
        vcategoryRelInfoDto.setCategoryName("反倒是地方");
        vcategoryRelInfoDtos.add(vcategoryRelInfoDto);
        VcategoryRelInfoDto vcategoryRelInfoDto1 = new VcategoryRelInfoDto();
        vcategoryRelInfoDto1.setCategoryCode("2");
        vcategoryRelInfoDto1.setGuideId(GUIDE_ID);
        vcategoryRelInfoDto1.setCategoryName("是非得失");
        vcategoryRelInfoDtos.add(vcategoryRelInfoDto1);
        BDDMockito.when(categoryRelDao.queryGuideCategoryRel(BDDMockito.anyString())).thenReturn(vcategoryRelInfoDtos);
        StoreGuideInfoDto guideInfo = new StoreGuideInfoDto();
        guideInfo.setGuideId("10071578");
        BDDMockito.when(redisCacheUtils.get(BDDMockito.anyString())).thenReturn(JsonUtils.object2Json(guideInfo));
        BDDMockito.when(redisCacheUtils.del(BDDMockito.anyString())).thenReturn(1L);
        List<StoreGuideDto> storeGuideList = new ArrayList<StoreGuideDto>();
        StoreGuideDto storeGuideInfoDto = new StoreGuideDto();
        storeGuideInfoDto.setGuideId("01050044");
        storeGuideInfoDto.setBusinessType(ELECTRIC);
        storeGuideInfoDto.setCategoryId("1");
        storeGuideList.add(storeGuideInfoDto);
        BDDMockito.when(baoziConsumerService.queryStoreBestGuideId("7613")).thenReturn(null);
        BDDMockito.when(guideInfoDao.queryGuideInfoByGuideId(BDDMockito.anyString())).thenReturn(guideInfo);
        BDDMockito.when(redisCacheUtils.setex(BDDMockito.anyString(), BDDMockito.anyInt(), BDDMockito.anyString())).thenReturn
                (StringUtils.EMPTY);
        vgoModifyRedisService.deleteGuideInfoCache(GUIDE_ID, "7613", ELECTRIC);
    }

    @Test
    public void testDeleteGuideInfoCache1() {
        BDDMockito.when(storeDetailDao.queryCityCodeByStoreCode(BDDMockito.anyString())).thenReturn("025");
        List<VcategoryRelInfoDto> vcategoryRelInfoDtos = new ArrayList<VcategoryRelInfoDto>();
        VcategoryRelInfoDto vcategoryRelInfoDto = new VcategoryRelInfoDto();
        vcategoryRelInfoDto.setCategoryCode("1");
        vcategoryRelInfoDto.setGuideId(GUIDE_ID);
        vcategoryRelInfoDto.setCategoryName("反倒是地方");
        vcategoryRelInfoDtos.add(vcategoryRelInfoDto);
        VcategoryRelInfoDto vcategoryRelInfoDto1 = new VcategoryRelInfoDto();
        vcategoryRelInfoDto1.setCategoryCode("2");
        vcategoryRelInfoDto1.setGuideId(GUIDE_ID);
        vcategoryRelInfoDto1.setCategoryName("是非得失");
        vcategoryRelInfoDtos.add(vcategoryRelInfoDto1);
        BDDMockito.when(categoryRelDao.queryGuideCategoryRel(BDDMockito.anyString())).thenReturn(vcategoryRelInfoDtos);
        StoreGuideInfoDto guideInfo = new StoreGuideInfoDto();
        guideInfo.setGuideId(GUIDE_ID);
        guideInfo.setCategoryId("1");
        guideInfo.setBusinessType("1");
        BDDMockito.when(redisCacheUtils.get(BDDMockito.anyString())).thenReturn(JsonUtils.object2Json(guideInfo));
        BDDMockito.when(redisCacheUtils.del(BDDMockito.anyString())).thenReturn(1L);
        List<StoreGuideDto> storeGuideList = new ArrayList<StoreGuideDto>();
        StoreGuideDto storeGuideInfoDto = new StoreGuideDto();
        storeGuideInfoDto.setGuideId("01050044");
        storeGuideInfoDto.setBusinessType("1");
        storeGuideInfoDto.setCategoryId("1");
        storeGuideList.add(storeGuideInfoDto);
        BDDMockito.when(baoziConsumerService.queryStoreBestGuideId(BDDMockito.anyString())).thenReturn(storeGuideList);
        BDDMockito.when(guideInfoDao.queryGuideInfoByGuideId(BDDMockito.anyString())).thenReturn(guideInfo);
        BDDMockito.when(redisCacheUtils.setex(BDDMockito.anyString(), BDDMockito.anyInt(), BDDMockito.anyString())).thenReturn
                (StringUtils.EMPTY);
        BDDMockito.when(redisCacheUtils.exists(BDDMockito.anyString())).thenReturn(true);
        BDDMockito.when(redisCacheUtils.del(BDDMockito.anyString())).thenReturn(1L);
        Set<String> guideInfos = new HashSet<String>();
        StoreGuideInfoDto storeGuideInfoDto1 = new StoreGuideInfoDto();
        storeGuideInfoDto1.setGuideId(GUIDE_ID);
        StoreGuideInfoDto storeGuideInfoDto3 = new StoreGuideInfoDto();
        storeGuideInfoDto3.setGuideId("45345435");
        guideInfos.add(JsonUtils.object2Json(storeGuideInfoDto1));
        guideInfos.add(JsonUtils.object2Json(storeGuideInfoDto3));
        BDDMockito.when(redisCacheUtils.smembers(BDDMockito.anyString())).thenReturn(guideInfos);
        List<String> outRels = new ArrayList<String>();
        outRels.add("12321");
        BDDMockito.when(categoryRelDao.queryCategoryOutRel(BDDMockito.anyString())).thenReturn(outRels);
        Set<String> guideInfos1 = new HashSet<String>();
        StoreGuideInfoDto storeGuideInfoDto2 = new StoreGuideInfoDto();
        storeGuideInfoDto2.setGuideId(GUIDE_ID);
        StoreGuideInfoDto storeGuideInfoDto4 = new StoreGuideInfoDto();
        storeGuideInfoDto4.setGuideId("45345435");
        guideInfos1.add(JsonUtils.object2Json(storeGuideInfoDto2));
        guideInfos1.add(JsonUtils.object2Json(storeGuideInfoDto4));
        BDDMockito.when(redisCacheUtils.smembers(BDDMockito.anyString())).thenReturn(guideInfos1);
        List<StoreGuideInfoDto> electricGuideList = new ArrayList<StoreGuideInfoDto>();
        StoreGuideInfoDto storeGuideInfoDto5 = new StoreGuideInfoDto();
        storeGuideInfoDto5.setGuideId("21212");
        electricGuideList.add(storeGuideInfoDto5);
        BDDMockito.when(guideInfoDao.queryElectricStoreGuide(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(electricGuideList);
        vgoModifyRedisService.deleteGuideInfoCache(GUIDE_ID, "7613", ELECTRIC);
    }

    @Test
    public void testDeleteGuideInfoCache2() {
        BDDMockito.when(storeDetailDao.queryCityCodeByStoreCode(BDDMockito.anyString())).thenReturn("025");
        List<VcategoryRelInfoDto> vcategoryRelInfoDtos = new ArrayList<VcategoryRelInfoDto>();
        VcategoryRelInfoDto vcategoryRelInfoDto = new VcategoryRelInfoDto();
        vcategoryRelInfoDto.setCategoryCode("1");
        vcategoryRelInfoDto.setGuideId(GUIDE_ID);
        vcategoryRelInfoDto.setCategoryName("反倒是地方");
        vcategoryRelInfoDtos.add(vcategoryRelInfoDto);
        VcategoryRelInfoDto vcategoryRelInfoDto1 = new VcategoryRelInfoDto();
        vcategoryRelInfoDto1.setCategoryCode("2");
        vcategoryRelInfoDto1.setGuideId(GUIDE_ID);
        vcategoryRelInfoDto1.setCategoryName("是非得失");
        vcategoryRelInfoDtos.add(vcategoryRelInfoDto1);
        BDDMockito.when(categoryRelDao.queryGuideCategoryRel(BDDMockito.anyString())).thenReturn(vcategoryRelInfoDtos);
        StoreGuideInfoDto guideInfo = new StoreGuideInfoDto();
        guideInfo.setGuideId(GUIDE_ID);
        guideInfo.setCategoryId("1");
        guideInfo.setBusinessType("2");
        BDDMockito.when(redisCacheUtils.get(BDDMockito.anyString())).thenReturn(JsonUtils.object2Json(guideInfo));
        BDDMockito.when(redisCacheUtils.del(BDDMockito.anyString())).thenReturn(1L);
        List<StoreGuideDto> storeGuideList = new ArrayList<StoreGuideDto>();
        StoreGuideDto storeGuideInfoDto = new StoreGuideDto();
        storeGuideInfoDto.setGuideId("01050044");
        storeGuideInfoDto.setBusinessType("2");
        storeGuideInfoDto.setCategoryId("1");
        storeGuideList.add(storeGuideInfoDto);
        BDDMockito.when(baoziConsumerService.queryStoreBestGuideId(BDDMockito.anyString())).thenReturn(storeGuideList);
        BDDMockito.when(guideInfoDao.queryGuideInfoByGuideId(BDDMockito.anyString())).thenReturn(guideInfo);
        BDDMockito.when(redisCacheUtils.setex(BDDMockito.anyString(), BDDMockito.anyInt(), BDDMockito.anyString())).thenReturn
                (StringUtils.EMPTY);
        BDDMockito.when(redisCacheUtils.exists(BDDMockito.anyString())).thenReturn(true);
        BDDMockito.when(redisCacheUtils.del(BDDMockito.anyString())).thenReturn(1L);
        Set<String> guideInfos = new HashSet<String>();
        StoreGuideInfoDto storeGuideInfoDto1 = new StoreGuideInfoDto();
        storeGuideInfoDto1.setGuideId(GUIDE_ID);
        StoreGuideInfoDto storeGuideInfoDto3 = new StoreGuideInfoDto();
        storeGuideInfoDto3.setGuideId("45345435");
        guideInfos.add(JsonUtils.object2Json(storeGuideInfoDto1));
        guideInfos.add(JsonUtils.object2Json(storeGuideInfoDto3));
        BDDMockito.when(redisCacheUtils.smembers(BDDMockito.anyString())).thenReturn(guideInfos);
        List<String> outRels = new ArrayList<String>();
        outRels.add("12321");
        BDDMockito.when(categoryRelDao.queryCategoryOutRel(BDDMockito.anyString())).thenReturn(outRels);
        Set<String> guideInfos1 = new HashSet<String>();
        StoreGuideInfoDto storeGuideInfoDto2 = new StoreGuideInfoDto();
        storeGuideInfoDto2.setGuideId(GUIDE_ID);
        StoreGuideInfoDto storeGuideInfoDto4 = new StoreGuideInfoDto();
        storeGuideInfoDto4.setGuideId("45345435");
        guideInfos1.add(JsonUtils.object2Json(storeGuideInfoDto2));
        guideInfos1.add(JsonUtils.object2Json(storeGuideInfoDto4));
        BDDMockito.when(redisCacheUtils.smembers(BDDMockito.anyString())).thenReturn(guideInfos1);
        List<StoreGuideInfoDto> electricGuideList = new ArrayList<StoreGuideInfoDto>();
        StoreGuideInfoDto storeGuideInfoDto5 = new StoreGuideInfoDto();
        storeGuideInfoDto5.setGuideId("21212");
        electricGuideList.add(storeGuideInfoDto5);
        BDDMockito.when(guideInfoDao.queryElectricStoreGuide(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(electricGuideList);
        vgoModifyRedisService.deleteGuideInfoCache(GUIDE_ID, "7613", ELECTRIC);
    }

    @Test
    public void testDeleteGuideInfoCache3() {
        BDDMockito.when(storeDetailDao.queryCityCodeByStoreCode(BDDMockito.anyString())).thenReturn("025");
        List<VcategoryRelInfoDto> vcategoryRelInfoDtos = new ArrayList<VcategoryRelInfoDto>();
        VcategoryRelInfoDto vcategoryRelInfoDto = new VcategoryRelInfoDto();
        vcategoryRelInfoDto.setCategoryCode("1");
        vcategoryRelInfoDto.setGuideId(GUIDE_ID);
        vcategoryRelInfoDto.setCategoryName("反倒是地方");
        vcategoryRelInfoDtos.add(vcategoryRelInfoDto);
        VcategoryRelInfoDto vcategoryRelInfoDto1 = new VcategoryRelInfoDto();
        vcategoryRelInfoDto1.setCategoryCode("2");
        vcategoryRelInfoDto1.setGuideId(GUIDE_ID);
        vcategoryRelInfoDto1.setCategoryName("是非得失");
        vcategoryRelInfoDtos.add(vcategoryRelInfoDto1);
        BDDMockito.when(categoryRelDao.queryGuideCategoryRel(BDDMockito.anyString())).thenReturn(vcategoryRelInfoDtos);
        StoreGuideInfoDto guideInfo = new StoreGuideInfoDto();
        guideInfo.setGuideId(GUIDE_ID);
        guideInfo.setCategoryId("1");
        BDDMockito.when(redisCacheUtils.get(BDDMockito.anyString())).thenReturn(JsonUtils.object2Json(guideInfo));
        BDDMockito.when(redisCacheUtils.del(BDDMockito.anyString())).thenReturn(1L);
        List<StoreGuideDto> storeGuideList = new ArrayList<StoreGuideDto>();
        StoreGuideDto storeGuideInfoDto = new StoreGuideDto();
        storeGuideInfoDto.setGuideId("01050044");
        storeGuideInfoDto.setCategoryId("1");
        storeGuideList.add(storeGuideInfoDto);
        BDDMockito.when(baoziConsumerService.queryStoreBestGuideId(BDDMockito.anyString())).thenReturn(storeGuideList);
        BDDMockito.when(guideInfoDao.queryGuideInfoByGuideId(BDDMockito.anyString())).thenReturn(guideInfo);
        BDDMockito.when(redisCacheUtils.setex(BDDMockito.anyString(), BDDMockito.anyInt(), BDDMockito.anyString())).thenReturn
                (StringUtils.EMPTY);
        BDDMockito.when(redisCacheUtils.exists(BDDMockito.anyString())).thenReturn(true);
        BDDMockito.when(redisCacheUtils.del(BDDMockito.anyString())).thenReturn(1L);
        Set<String> guideInfos = new HashSet<String>();
        StoreGuideInfoDto storeGuideInfoDto1 = new StoreGuideInfoDto();
        storeGuideInfoDto1.setGuideId(GUIDE_ID);
        StoreGuideInfoDto storeGuideInfoDto3 = new StoreGuideInfoDto();
        storeGuideInfoDto3.setGuideId("45345435");
        guideInfos.add(JsonUtils.object2Json(storeGuideInfoDto1));
        guideInfos.add(JsonUtils.object2Json(storeGuideInfoDto3));
        BDDMockito.when(redisCacheUtils.smembers(BDDMockito.anyString())).thenReturn(guideInfos);
        List<String> outRels = new ArrayList<String>();
        outRels.add("12321");
        BDDMockito.when(categoryRelDao.queryCategoryOutRel(BDDMockito.anyString())).thenReturn(outRels);
        Set<String> guideInfos1 = new HashSet<String>();
        StoreGuideInfoDto storeGuideInfoDto2 = new StoreGuideInfoDto();
        storeGuideInfoDto2.setGuideId(GUIDE_ID);
        StoreGuideInfoDto storeGuideInfoDto4 = new StoreGuideInfoDto();
        storeGuideInfoDto4.setGuideId("45345435");
        guideInfos1.add(JsonUtils.object2Json(storeGuideInfoDto2));
        guideInfos1.add(JsonUtils.object2Json(storeGuideInfoDto4));
        BDDMockito.when(redisCacheUtils.smembers(BDDMockito.anyString())).thenReturn(guideInfos1);
        List<StoreGuideInfoDto> electricGuideList = new ArrayList<StoreGuideInfoDto>();
        StoreGuideInfoDto storeGuideInfoDto5 = new StoreGuideInfoDto();
        storeGuideInfoDto5.setGuideId("21212");
        electricGuideList.add(storeGuideInfoDto5);
        BDDMockito.when(guideInfoDao.queryElectricStoreGuide(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(electricGuideList);
        vgoModifyRedisService.deleteGuideInfoCache(GUIDE_ID, "7613", ELECTRIC);
    }

    @Test
    public void testBatchDeleteGuideInfoCache() {
        List<String> guideList = new ArrayList<String>();
        guideList.add("01050044");
        List<GuideInfo> guideInfoList = new ArrayList<GuideInfo>();
        GuideInfo guideInfo = new GuideInfo();
        guideInfo.setGuideId("01050044");
        guideInfo.setBusinessType(ELECTRIC);
        guideInfoList.add(guideInfo);
        GuideInfo guideInfo1 = new GuideInfo();
        guideInfo1.setGuideId("10071578");
        guideInfo1.setBusinessType("2");
        guideInfoList.add(guideInfo1);
        BDDMockito.when(guideInfoDao.batchQueryGuideInfo(guideList)).thenReturn(guideInfoList);
        vgoModifyRedisService.batchDeleteGuideInfoCache(guideList);
    }
}