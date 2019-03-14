package vgo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.suning.sntk.admin.dao.vgo.CategoryRelAdminDao;
import com.suning.sntk.admin.dao.vgo.GuideInfoAdminDao;
import com.suning.sntk.admin.service.vgo.impl.AuditServiceImpl;
import com.suning.sntk.admin.service.vgo.impl.StoreInfoServiceImpl;
import com.suning.sntk.consumer.impl.BaoziConsumerServiceImpl;
import com.suning.sntk.consumer.impl.NsfbusConsumerServiceImpl;
import com.suning.sntk.consumer.impl.NsfuaaConsumerServiceImpl;
import com.suning.sntk.dao.vgo.CategoryDao;
import com.suning.sntk.dao.vgo.GuideAuditDao;
import com.suning.sntk.dao.vgo.O2oGuideInfoDao;
import com.suning.sntk.dao.vgo.ServerItemDao;
import com.suning.sntk.dto.vgo.AuditGuideDto;
import com.suning.sntk.dto.vgo.GuideAuditDetailDto;
import com.suning.sntk.dto.vgo.GuideAuditInfoDto;
import com.suning.sntk.dto.vgo.GuideAuditReqDto;
import com.suning.sntk.dto.vgo.GuideInfoReqDto;
import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;
import com.suning.sntk.entity.vgo.GuideAuditInfo;
import com.suning.sntk.entity.vgo.GuideInfo;
import com.suning.sntk.entity.vgo.O2oGuideInfo;
import com.suning.sntk.service.vgo.impl.VgoModifyRedisServiceImpl;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.PageImpl;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.base.ParamBuilder;

/**
 * 功能描述：v购审核服务测试类
 *
 * @author 88396455_白振华
 * @since 2018-9-11
 */
public class AuditServiceImplTest {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(AuditServiceImplTest.class);

    public static final String GUIDE_ID_AUTH = "13081932";
    public static final String STAFF_ID_O2O = "12060621";
    public static final String GUIDE_ID_ADMIN = "33332222";
    public static final String GUIDE_AUDIT = "66665555";

    @InjectMocks
    private AuditServiceImpl auditService;
    @Mock
    private GuideInfoAdminDao guideInfoAdminDao;
    @Mock
    private O2oGuideInfoDao o2oGuideInfoDao;
    @Mock
    private GuideAuditDao guideAuditDao;
    @Mock
    private NsfuaaConsumerServiceImpl nsfuaaConsumerService;
    @Mock
    private CategoryDao categoryDao;
    @Mock
    private CategoryRelAdminDao categoryRelDao;
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

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBatchOperateOpenFlag() {
        AuditGuideDto auditGuideDto = new AuditGuideDto();
        List<String> guideIds = new ArrayList<String>();
        guideIds.add(GUIDE_ID_ADMIN);
        List<GuideInfo> guideInfos = new ArrayList<GuideInfo>();
        GuideInfo guideInfo = new GuideInfo();
        guideInfo.setId(5565L);
        guideInfo.setOpenFlag("1");
        guideInfo.setDimissionFlag("0");
        guideInfo.setDeleteFlag(1);
        guideInfo.setIsVgo(1);
        guideInfo.setStoreCode("7010");
        guideInfo.setBusinessType("1");
        guideInfo.setGuideId("8912899");
        guideInfos.add(guideInfo);
        List<O2oGuideInfo> o2oGuideInfos = new ArrayList<O2oGuideInfo>();
        O2oGuideInfo o2oGuideInfo = new O2oGuideInfo();
        o2oGuideInfo.setId(9889L);
        o2oGuideInfos.add(o2oGuideInfo);
        BDDMockito.when(guideInfoAdminDao.queryGuideInfoList(BDDMockito.anyList())).thenReturn(guideInfos);
        BDDMockito.when(o2oGuideInfoDao.queryGuideInfoList(BDDMockito.anyList())).thenReturn(o2oGuideInfos);
        auditService.batchOperateOpenFlag(auditGuideDto, STAFF_ID_O2O, "0");
    }

    @Test
    public void testBatchOperateOpenFlag1() {
        AuditGuideDto auditGuideDto = new AuditGuideDto();
        List<String> guideIds = new ArrayList<String>();
        guideIds.add(GUIDE_ID_ADMIN);
        List<GuideInfo> guideInfos = new ArrayList<GuideInfo>();
        GuideInfo guideInfo = new GuideInfo();
        guideInfo.setId(5565L);
        guideInfo.setOpenFlag("1");
        guideInfos.add(guideInfo);
        List<O2oGuideInfo> o2oGuideInfos = new ArrayList<O2oGuideInfo>();
        O2oGuideInfo o2oGuideInfo = new O2oGuideInfo();
        o2oGuideInfo.setId(9889L);
        o2oGuideInfo.setOpenFlag("1");
        o2oGuideInfos.add(o2oGuideInfo);
        BDDMockito.when(guideInfoAdminDao.queryGuideInfoList(BDDMockito.anyList())).thenReturn(guideInfos);
        BDDMockito.when(o2oGuideInfoDao.queryGuideInfoList(BDDMockito.anyList())).thenReturn(o2oGuideInfos);
        auditGuideDto.setGuideIds(guideIds);
        auditService.batchOperateOpenFlag(auditGuideDto, STAFF_ID_O2O, "1");
    }
    @Test
    public void testBatchOperateVgoFlag() {
        AuditGuideDto auditGuideDto = new AuditGuideDto();
        List<String> guideIds = new ArrayList<String>();
        guideIds.add(GUIDE_ID_ADMIN);
        List<GuideInfo> guideInfos = new ArrayList<GuideInfo>();
        GuideInfo guideInfo = new GuideInfo();
        guideInfo.setId(5565L);
        guideInfos.add(guideInfo);
        List<O2oGuideInfo> o2oGuideInfos = new ArrayList<O2oGuideInfo>();
        O2oGuideInfo o2oGuideInfo = new O2oGuideInfo();
        o2oGuideInfo.setId(9889L);
        o2oGuideInfos.add(o2oGuideInfo);
        BDDMockito.when(guideInfoAdminDao.queryGuideInfoList(BDDMockito.anyList())).thenReturn(guideInfos);
        BDDMockito.when(o2oGuideInfoDao.queryGuideInfoList(BDDMockito.anyList())).thenReturn(o2oGuideInfos);
        auditGuideDto.setGuideIds(guideIds);
        auditService.batchOperateVgoFlag(auditGuideDto, STAFF_ID_O2O, 1);
    }

    @Test
    public void testBatchOperateVgoFlag1() {
        AuditGuideDto auditGuideDto = new AuditGuideDto();
        List<String> guideIds = new ArrayList<String>();
        guideIds.add(GUIDE_ID_ADMIN);
        List<GuideInfo> guideInfos = new ArrayList<GuideInfo>();
        GuideInfo guideInfo = new GuideInfo();
        guideInfo.setId(5565L);
        guideInfo.setOpenFlag("1");
        guideInfo.setDimissionFlag("0");
        guideInfo.setDeleteFlag(1);
        guideInfo.setIsVgo(1);
        guideInfo.setStoreCode("7010");
        guideInfo.setBusinessType("1");
        guideInfo.setGuideId("8912899");
        guideInfos.add(guideInfo);
        List<O2oGuideInfo> o2oGuideInfos = new ArrayList<O2oGuideInfo>();
        O2oGuideInfo o2oGuideInfo = new O2oGuideInfo();
        o2oGuideInfo.setId(9889L);
        o2oGuideInfo.setIsVgo(1);
        o2oGuideInfo.setOpenFlag("1");
        o2oGuideInfos.add(o2oGuideInfo);
        BDDMockito.when(guideInfoAdminDao.queryGuideInfoList(BDDMockito.anyList())).thenReturn(guideInfos);
        BDDMockito.when(o2oGuideInfoDao.queryGuideInfoList(BDDMockito.anyList())).thenReturn(o2oGuideInfos);
        auditGuideDto.setGuideIds(guideIds);
        auditService.batchOperateVgoFlag(auditGuideDto, STAFF_ID_O2O, 0);
    }

    @Test
    public void testQueryAuditGuideForPage() {
        GuideInfoReqDto guideInfoReqDto = new GuideInfoReqDto();
        guideInfoReqDto.setBusinessType("1");
        guideInfoReqDto.setAuditFlag(0);
        Pageable pageable = ParamBuilder.newPageable(0, 10);
        List<GuideAuditInfoDto> list = new ArrayList<GuideAuditInfoDto>();
        GuideAuditInfoDto guideAuditInfoDto = new GuideAuditInfoDto();
        guideAuditInfoDto.setOrgId("1100");
        guideAuditInfoDto.setAuditReason("3#aaaaa");
        list.add(guideAuditInfoDto);
        Page<GuideAuditInfoDto> result = new PageImpl<GuideAuditInfoDto>(list);
        BDDMockito.when(guideAuditDao.queryAuditGuidePage(BDDMockito.any(GuideInfoReqDto.class), BDDMockito.any(Pageable.class)))
                .thenReturn(result);
        BDDMockito.when(guideAuditDao.queryAuditGuideForPage(BDDMockito.any(GuideInfoReqDto.class), BDDMockito.any(Pageable.class)))
                .thenReturn(result);
        auditService.queryAuditGuideForPage(guideInfoReqDto, pageable);
        guideInfoReqDto.setHierarchy("2121212");
        auditService.queryAuditGuideForPage(guideInfoReqDto, pageable);
    }

    @Test
    public void testQueryGuideAuditDetail() {
        GuideAuditDetailDto guideAuditDetailDto = new GuideAuditDetailDto();
        guideAuditDetailDto.setOrgId("1100");
        guideAuditDetailDto.setCategoryIds("1,2,3,4");
        List<VcategoryRelInfoDto> list = new ArrayList<VcategoryRelInfoDto>();
        VcategoryRelInfoDto vcategoryRelInfoDto = new VcategoryRelInfoDto();
        vcategoryRelInfoDto.setCategoryName("add");
        vcategoryRelInfoDto.setCategoryCode("434");
        list.add(vcategoryRelInfoDto);
        BDDMockito.when(guideAuditDao.queryToBeAuditedDetail(BDDMockito.anyString())).thenReturn(guideAuditDetailDto);
        BDDMockito.when(categoryDao.queryGuideCategory(BDDMockito.anyList())).thenReturn(list);
        BDDMockito.when(guideAuditDao.queryToBeAuditedDetail(BDDMockito.anyString())).thenReturn(guideAuditDetailDto);
        auditService.queryGuideAuditDetail(GUIDE_ID_AUTH, "1");
        auditService.queryGuideAuditDetail(GUIDE_ID_AUTH, "2");
    }

    @Test
    public void testAuditGuide() {
        List<String> categoryIds = new ArrayList<>();
        categoryIds.add("3");
        List<String> serverItems = new ArrayList<String>();
        serverItems.add("飞洒");
        GuideAuditReqDto guideAuditReqDto = new GuideAuditReqDto();
        guideAuditReqDto.setGuideId(GUIDE_ID_AUTH);
        GuideAuditDetailDto guideAuditDetailDto = new GuideAuditDetailDto();
        guideAuditDetailDto.setId(111L);
        List<String> cids = new ArrayList<String>();
        cids.add("1");
        cids.add("2");
        cids.add("4");
        List<VcategoryRelInfoDto> names = new ArrayList<VcategoryRelInfoDto>();
        VcategoryRelInfoDto vcategoryRelInfoDto = new VcategoryRelInfoDto();
        vcategoryRelInfoDto.setCategoryName("gffs");
        names.add(vcategoryRelInfoDto);
        BDDMockito.when(guideAuditDao.queryToBeAuditedDetail(BDDMockito.anyString())).thenReturn(guideAuditDetailDto);
        BDDMockito.when(categoryRelDao.queryGuideCategoryCode(BDDMockito.anyString())).thenReturn(serverItems);
        BDDMockito.when(categoryDao.queryGuideCategory(BDDMockito.anyList())).thenReturn(names);
        GuideAuditInfo guideAuditInfo1 = new GuideAuditInfo();
        guideAuditInfo1.setBusinessType("1");
        guideAuditInfo1.setGuidePhoto("gaa");
        guideAuditInfo1.setSaleAge(55);
        guideAuditInfo1.setCategoryIds("1,2,4");
        guideAuditInfo1.setIsVgo(0);
        guideAuditInfo1.setServerItems("fea,fds");
        BDDMockito.when(guideAuditDao.queryToBeAuditedGuide(BDDMockito.anyString())).thenReturn(guideAuditInfo1);
        GuideAuditInfo guideAuditInfo = guideAuditDao.queryToBeAuditedGuide(GUIDE_ID_AUTH);
        guideAuditReqDto.setId(guideAuditInfo.getId());
        guideAuditReqDto.setIsVgo(1);
        guideAuditReqDto.setAuditFlag(1);
        auditService.auditGuide(guideAuditReqDto, STAFF_ID_O2O);

        GuideInfo guideInfo = new GuideInfo();
        guideInfo.setId(645L);
        guideInfo.setGuideId(GUIDE_ID_AUTH);
        BDDMockito.when(guideInfoAdminDao.getGuideInfo(BDDMockito.anyString())).thenReturn(guideInfo);
        BDDMockito.when(o2oGuideInfoDao.selectIdByGuideId(BDDMockito.anyString())).thenReturn(4447L);
        GuideAuditReqDto guideAuditReqDto1 = new GuideAuditReqDto();
        guideAuditReqDto1.setIsVgo(1);
        guideAuditReqDto1.setAuditFlag(1);
        guideAuditReqDto1.setGuideId(GUIDE_ID_AUTH);
        auditService.auditGuide(guideAuditReqDto1, GUIDE_AUDIT);

        GuideInfo guideInfo1 = new GuideInfo();
        guideInfo1.setId(646L);
        guideInfo1.setGuideId(GUIDE_ID_AUTH);
        guideAuditInfo1.setBusinessType("2");
        BDDMockito.when(guideInfoAdminDao.getGuideInfo(BDDMockito.anyString())).thenReturn(guideInfo1);
        BDDMockito.when(o2oGuideInfoDao.selectIdByGuideId(BDDMockito.anyString())).thenReturn(4466L);
        BDDMockito.when(guideAuditDao.queryToBeAuditedGuide(BDDMockito.anyString())).thenReturn(guideAuditInfo1);
        GuideAuditReqDto guideAuditReqDto2 = new GuideAuditReqDto();
        guideAuditReqDto2.setIsVgo(1);
        guideAuditReqDto2.setAuditFlag(1);
        guideAuditReqDto2.setGuideId(GUIDE_ID_AUTH);
        auditService.auditGuide(guideAuditReqDto1, GUIDE_AUDIT);

    }

    @Test
    public void testAuditGuide2() {
        List<String> categoryIds = new ArrayList<>();
        categoryIds.add("3");
        List<String> serverItems = new ArrayList<String>();
        serverItems.add("飞洒");
        GuideAuditReqDto guideAuditReqDto = new GuideAuditReqDto();
        guideAuditReqDto.setGuideId(GUIDE_ID_AUTH);
        GuideAuditDetailDto guideAuditDetailDto = new GuideAuditDetailDto();
        guideAuditDetailDto.setId(111L);
        GuideAuditInfo guideAuditInfo1 = new GuideAuditInfo();
        guideAuditInfo1.setBusinessType("2");
        BDDMockito.when(guideAuditDao.queryToBeAuditedGuide(BDDMockito.anyString())).thenReturn(guideAuditInfo1);
        GuideAuditInfo guideAuditInfo = guideAuditDao.queryToBeAuditedGuide(GUIDE_ID_AUTH);
        guideAuditReqDto.setId(guideAuditInfo.getId());
        guideAuditReqDto.setIsVgo(1);
        guideAuditReqDto.setAuditFlag(1);
        guideAuditReqDto.setGuideId(GUIDE_AUDIT);
        BDDMockito.when(guideAuditDao.queryToBeAuditedDetail(BDDMockito.anyString())).thenReturn(null);
        BDDMockito.when(categoryRelDao.queryGuideCategoryCode(BDDMockito.anyString())).thenReturn(categoryIds);
        BDDMockito.when(guideAuditDao.queryToBeAuditedGuide(BDDMockito.anyString())).thenReturn(guideAuditInfo1);
        guideAuditInfo = guideAuditDao.queryToBeAuditedGuide(GUIDE_AUDIT);
        guideAuditReqDto.setIsVgo(1);
        guideAuditReqDto.setAuditFlag(1);
        auditService.auditGuide(guideAuditReqDto, STAFF_ID_O2O);
    }

    @Test
    public void testAuditGuide3() {
        GuideAuditReqDto guideAuditReqDto = new GuideAuditReqDto();
        guideAuditReqDto.setAuditFlag(VgoConstants.AUDIT_ADOPT);
        guideAuditReqDto.setGuideId("10071578");
        GuideAuditInfo auditInfo = new GuideAuditInfo();
        auditInfo.setGuideId("10071578");
        auditInfo.setId(111L);
        auditInfo.setGuideName("吕维");
        auditInfo.setHierarchy("34343");
        auditInfo.setAttach("32");
        auditInfo.setGuidePhoto(
                "http://sdossxgpre.cnsuning.com/snsawp/staffheader/LbVBYevLCvjw1o9SEQASPPH2M-PQcUvv68L1VHVzF5Xmg6V-WVOnJEDSRTj1hCbU.jpg");
        auditInfo.setIntroduction("发送复苏阶段");
        auditInfo.setSaleAge(33);
        auditInfo.setIsVgo(1);
        auditInfo.setCategoryIds("1,2,3");
        auditInfo.setBusinessType("1");
        auditInfo.setStoreCode("7613");
        List<VcategoryRelInfoDto> vcategoryInfos = new ArrayList<VcategoryRelInfoDto>();
        VcategoryRelInfoDto vcategoryRelInfoDto = new VcategoryRelInfoDto();
        vcategoryRelInfoDto.setCategoryCode("1");
        vcategoryRelInfoDto.setCategoryName("放松的方式的");
        vcategoryInfos.add(vcategoryRelInfoDto);
        BDDMockito.when(categoryDao.queryGuideCategory(BDDMockito.anyList())).thenReturn(vcategoryInfos);
        BDDMockito.when(guideAuditDao.queryToBeAuditedGuide(BDDMockito.anyString())).thenReturn(auditInfo);
        BDDMockito.when(guideInfoAdminDao.getGuideInfo(BDDMockito.anyString())).thenReturn(null);
        auditService.auditGuide(guideAuditReqDto, "12061818");
    }

    @Test
    public void testAuditGuide4() {
        GuideAuditReqDto guideAuditReqDto = new GuideAuditReqDto();
        guideAuditReqDto.setAuditFlag(VgoConstants.AUDIT_ADOPT);
        guideAuditReqDto.setGuideId("10071578");
        GuideAuditInfo auditInfo = new GuideAuditInfo();
        auditInfo.setGuideId("10071578");
        auditInfo.setId(111L);
        auditInfo.setGuideName("吕维");
        auditInfo.setHierarchy("34343");
        auditInfo.setAttach("32");
        auditInfo.setGuidePhoto(
                "http://sdossxgpre.cnsuning.com/snsawp/staffheader/LbVBYevLCvjw1o9SEQASPPH2M-PQcUvv68L1VHVzF5Xmg6V-WVOnJEDSRTj1hCbU.jpg");
        auditInfo.setIntroduction("发送复苏阶段");
        auditInfo.setSaleAge(33);
        auditInfo.setIsVgo(1);
        auditInfo.setCategoryIds("1,2,3");
        auditInfo.setBusinessType("1");
        auditInfo.setStoreCode("7613");
        BDDMockito.when(guideAuditDao.queryToBeAuditedGuide(BDDMockito.anyString())).thenReturn(auditInfo);
        GuideInfo guideExists = new GuideInfo();
        guideExists.setId(989L);
        guideExists.setGuideName("吕维");
        guideExists.setGuidePhoto(
                "http://sdossxgpre.cnsuning.com/snsawp/staffheader/LbVBYevLCvjw1o9SEQASPPH2M-PQcUvv68L1VHVzF5Xmg6V-WVOnJEDSRTj1hCbU.jpg");
        guideExists.setStarLevel("5");
        guideExists.setIntroduction("dfafssa");
        guideExists.setTele("12123231");
        guideExists.setOrderNum(89L);
        guideExists.setStoreCode("7613");
        guideExists.setSaleAge(4);
        guideExists.setBusinessCode("1");
        guideExists.setOpenFlag("1");
        guideExists.setGrade(5d);
        guideExists.setOutletCode("1212");
        List<VcategoryRelInfoDto> vcategoryInfos = new ArrayList<VcategoryRelInfoDto>();
        VcategoryRelInfoDto vcategoryRelInfoDto = new VcategoryRelInfoDto();
        vcategoryRelInfoDto.setCategoryCode("1");
        vcategoryRelInfoDto.setCategoryName("放松的方式的");
        vcategoryInfos.add(vcategoryRelInfoDto);
        BDDMockito.when(categoryDao.queryGuideCategory(BDDMockito.anyList())).thenReturn(vcategoryInfos);
        BDDMockito.when(guideInfoAdminDao.getGuideInfo(BDDMockito.anyString())).thenReturn(guideExists);
        auditService.auditGuide(guideAuditReqDto, "12061818");
    }
    @Test
    public void testBatchImportCloseGuide() {
        MultipartFile file = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return "testImportCloseGuide.xlsx";
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                InputStream inputStream = null;
                try {
                    Class<?> clazz = Class.forName("vgo.AuditServiceImplTest");
                    String path = clazz.getResource("/").getPath() + "doc/testImportCloseGuide.xlsx";
                    inputStream = new FileInputStream(new File(path));
                } catch (ClassNotFoundException e) {
                    log.error("testBatchImportCloseGuide.getInputStream error", e);
                }
                return inputStream;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {
                //empty
            }
        };
        try {
            auditService.batchImportCloseGuide(file, STAFF_ID_O2O);
        } catch (IOException e) {
            log.error("testBatchImportCloseGuide error", e);
        }
    }

    @Test
    public void testBatchImportCloseGuide2() {
        MultipartFile file = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return "testImportCloseGuide.xls";
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                InputStream inputStream = null;
                try {
                    Class<?> clazz = Class.forName("vgo.AuditServiceImplTest");
                    String path = clazz.getResource("/").getPath() + "doc/testImportCloseGuide.xls";
                    inputStream = new FileInputStream(new File(path));
                } catch (ClassNotFoundException e) {
                    log.error("testBatchImportCloseGuide.getInputStream error", e);
                }
                return inputStream;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {
                //empty
            }
        };
        try {
            auditService.batchImportCloseGuide(file, STAFF_ID_O2O);
        } catch (IOException e) {
            log.error("testBatchImportCloseGuide error", e);
        }
    }

    @Test
    public void testBatchImportCloseGuide3() {
        MultipartFile file = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return "testImportClos.xls";
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                InputStream inputStream = null;
                try {
                    Class<?> clazz = Class.forName("vgo.AuditServiceImplTest");
                    String path = clazz.getResource("/").getPath() + "vgo/AuditServiceImplTest.class";
                    inputStream = new FileInputStream(new File(path));
                } catch (ClassNotFoundException e) {
                    log.error("testBatchImportCloseGuide.getInputStream error", e);
                }
                return inputStream;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {
                //empty
            }
        };
        try {
            auditService.batchImportCloseGuide(file, STAFF_ID_O2O);
        } catch (Exception e) {
            log.error("testBatchImportCloseGuide error", e);
        }
    }

    @Test
    public void testCountByBusinessTypeAndStatus() {
        GuideInfoReqDto guideInfoReqDto = new GuideInfoReqDto();
        guideInfoReqDto.setAuditFlag(0);
        guideInfoReqDto.setBusinessType("1");
        BDDMockito.when(guideAuditDao.countByBusinessTypeAndStatus(BDDMockito.any(GuideInfoReqDto.class))).thenReturn(1L);
        auditService.countByBusinessTypeAndStatus(guideInfoReqDto);
    }

}