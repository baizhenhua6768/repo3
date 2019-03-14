package vgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.suning.sntk.admin.dao.vgo.GuideInfoAdminDao;
import com.suning.sntk.admin.job.vgo.SynMossPicToSntkJob;
import com.suning.sntk.dao.vgo.O2oGuideInfoDao;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.entity.vgo.GuideInfo;
import com.suning.sntk.entity.vgo.O2oGuideInfo;
import com.suning.store.commons.lang.BeanUtils;

/**
 * 功能描述：同步moss的图片至sntk测试类
 *
 * @author 88396455_白振华
 * @since 2018-9-13
 */
@ContextConfiguration(locations = { "classpath:spring/spring-admin-test.xml" })
public class SynMossPicToSntkJobTest extends AbstractTestNGSpringContextTests {

    public static final String GUIDE_ID = "87679087";
    @Autowired
    private SynMossPicToSntkJob synMossPicToSntkJob;

    @Autowired
    private GuideInfoAdminDao guideInfoAdminDao;

    @Autowired
    private O2oGuideInfoDao o2oGuideInfoDao;

    @BeforeMethod
    public void setUp() {
        GuideInfo guideInfo = new GuideInfo();
        guideInfo.setGuideId(GUIDE_ID);
        guideInfo.setGuidePhoto("http://image2.suning.cn/uimg/moss/activeImg/148698914507147454.png");
        guideInfo.setIsFromMoss(1);
        guideInfo.setBusinessType("1");
        guideInfo.setBusinessCode("1");
        guideInfo.setGrade(4d);
        guideInfo.setIsCompeletePicChange(0);
        guideInfoAdminDao.insert(guideInfo);
        O2oGuideInfo o2oGuideInfo = new O2oGuideInfo();
        BeanUtils.copy(guideInfo, o2oGuideInfo);
        o2oGuideInfoDao.insert(o2oGuideInfo);
    }

    @AfterMethod
    public void tearDown() {
        GuideInfoDto guideInfoDto = guideInfoAdminDao.queryByGuideId(GUIDE_ID);
        guideInfoAdminDao.deleteById(guideInfoDto.getId());
        Long aLong = o2oGuideInfoDao.selectIdByGuideId(GUIDE_ID);
        o2oGuideInfoDao.deleteById(aLong);
    }

    @Test
    public void testSynPicFromMossTosntk() {
        synMossPicToSntkJob.synPicFromMossTosntk();
        synMossPicToSntkJob.synPicFromMossTosntk();
    }
}