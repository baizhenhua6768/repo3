package vgo;

import java.util.ArrayList;
import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.admin.dao.vgo.CategoryRelAdminDao;
import com.suning.sntk.admin.dao.vgo.GuideInfoAdminDao;
import com.suning.sntk.admin.service.vgo.impl.ShoppingGuideServiceImpl;
import com.suning.sntk.admin.service.vgo.impl.StoreInfoServiceImpl;
import com.suning.sntk.consumer.impl.BaoziConsumerServiceImpl;
import com.suning.sntk.consumer.impl.NsfbusConsumerServiceImpl;
import com.suning.sntk.dao.vgo.O2oGuideInfoDao;
import com.suning.sntk.dao.vgo.ServerItemDao;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.GuideInfoReqDto;
import com.suning.sntk.dto.vgo.GuideInfoRespDto;
import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;
import com.suning.sntk.service.vgo.impl.VgoModifyRedisServiceImpl;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.PageImpl;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.base.ParamBuilder;

/**
 * 功能描述：v购信息服务测试类
 *
 * @author 88396455_白振华
 * @since 2018-9-11
 */
public class ShoppingGuideServiceImplTest {

    public static final String GUIDE_ID = "12344321";
    @InjectMocks
    private ShoppingGuideServiceImpl shoppingGuideService;

    @Mock
    private GuideInfoAdminDao guideInfoAdminDao;

    @Mock
    private O2oGuideInfoDao o2oGuideInfoDao;

    @Mock
    private CategoryRelAdminDao categoryRelAdminDao;

    @Mock
    private ServerItemDao serverItemDao;

    @Mock
    private NsfbusConsumerServiceImpl nsfbusConsumerService;

    @Mock
    private StoreInfoServiceImpl storeInfoService;

    @Mock
    private VgoModifyRedisServiceImpl vgoModifyRedisService;

    @Mock
    private BaoziConsumerServiceImpl baoziConsumerService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testQueryGuideInfoForPage() {
        GuideInfoDto guideInfoDto = new GuideInfoDto();
        BDDMockito.when(guideInfoAdminDao.queryByGuideId(GUIDE_ID)).thenReturn(guideInfoDto);
        Pageable pageable = ParamBuilder.newPageable(0, 10);
        GuideInfoReqDto guideInfoReqDto = new GuideInfoReqDto();
        guideInfoReqDto.setBusinessType("1");
        List<GuideInfoRespDto> list = new ArrayList<GuideInfoRespDto>();
        GuideInfoRespDto guideInfoRespDto = new GuideInfoRespDto();
        guideInfoRespDto.setOrgId("1100");
        list.add(guideInfoRespDto);
        Page<GuideInfoRespDto> result = new PageImpl<GuideInfoRespDto>(list);
        BDDMockito.when(guideInfoAdminDao.queryGuideInfoPage(BDDMockito.any(GuideInfoReqDto.class), BDDMockito.any(Pageable.class)))
                .thenReturn(result);
        BDDMockito.when(guideInfoAdminDao.queryGuideInfoForPage(BDDMockito.any(GuideInfoReqDto.class), BDDMockito.any(Pageable.class)))
                .thenReturn(result);
        shoppingGuideService.queryGuideInfoForPage(guideInfoReqDto, pageable);
        guideInfoReqDto.setHierarchy("212121212");
        shoppingGuideService.queryGuideInfoForPage(guideInfoReqDto, pageable);
    }

    @Test
    public void testQueryGuideDetail() {
        GuideInfoDto guideInfoDto = new GuideInfoDto();
        guideInfoDto.setOrgId("1100");
        BDDMockito.when(guideInfoAdminDao.queryByGuideId(BDDMockito.anyString())).thenReturn(guideInfoDto);
        ArrayList<VcategoryRelInfoDto> vcategoryRelInfoDtos = new ArrayList<>();
        VcategoryRelInfoDto vcategoryRelInfoDto = new VcategoryRelInfoDto();
        vcategoryRelInfoDto.setCategoryId(211);
        vcategoryRelInfoDto.setCategoryName("sdfsdf");
        vcategoryRelInfoDtos.add(vcategoryRelInfoDto);
        BDDMockito.when(categoryRelAdminDao.queryByGuideId(BDDMockito.anyString())).thenReturn(vcategoryRelInfoDtos);
        List<String> serviceItems = new ArrayList<String>();
        serviceItems.add("http://fdfs,fdsf");
        BDDMockito.when(serverItemDao.queryByGuideId(BDDMockito.anyString())).thenReturn(serviceItems);
        shoppingGuideService.queryGuideDetail(GUIDE_ID, "1");
        shoppingGuideService.queryGuideDetail(GUIDE_ID, "2");
    }

    @Test
    public void testDeleteGuide() {
        BDDMockito.when(o2oGuideInfoDao.selectIdByGuideId(BDDMockito.anyString())).thenReturn(1L);
        GuideInfoDto guideInfoDto = new GuideInfoDto();
        guideInfoDto.setOpenFlag("1");
        guideInfoDto.setDimissionFlag("0");
        guideInfoDto.setIsVgo(1);
        guideInfoDto.setStoreCode("7010");
        guideInfoDto.setBusinessType("1");
        guideInfoDto.setGuideId("8912899");
        guideInfoDto.setId(9897L);
        BDDMockito.when(categoryRelAdminDao.queryByGuideId(BDDMockito.anyString())).thenReturn(null);
        BDDMockito.when(guideInfoAdminDao.queryByGuideId(BDDMockito.anyString())).thenReturn(guideInfoDto);
        shoppingGuideService.deleteGuide(GUIDE_ID, "12060621", "7611", "1");
        shoppingGuideService.deleteGuide(GUIDE_ID, "12060621",  "7611", "2");
    }

    @Test
    public void testQueryGuideInfoCount() {
        GuideInfoReqDto guideInfoReqDto = new GuideInfoReqDto();
        guideInfoReqDto.setBusinessType("1");
        BDDMockito.when(guideInfoAdminDao.queryGuideInfoCount(guideInfoReqDto)).thenReturn(1L);
        shoppingGuideService.queryGuideInfoCount(guideInfoReqDto);
    }

    @Test
    public void testCountGuideByBusinessType() {
        BDDMockito.when(guideInfoAdminDao.queryGuideInfoCount(BDDMockito.any(GuideInfoReqDto.class))).thenReturn(1L);
        GuideInfoReqDto guideInfoReqDto = new GuideInfoReqDto();
        guideInfoReqDto.setBusinessType("2");
        shoppingGuideService.countGuideByBusinessType(guideInfoReqDto);
    }
}
