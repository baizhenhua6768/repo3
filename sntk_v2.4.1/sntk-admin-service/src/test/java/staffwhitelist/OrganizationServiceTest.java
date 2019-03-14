/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: OrganizationServiceTest
 * Author:   18032490_赵亚奇
 * Date:     2018/7/10 17:01
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package staffwhitelist;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.suning.sntk.admin.service.staffwhitelist.OrganizationService;
import com.suning.sntk.admin.vo.CompanyVo;
import com.suning.sntk.admin.vo.RegionVo;
import com.suning.sntk.admin.vo.StoreVo;

/**
 * @author 18032490_赵亚奇
 * @since 2018/7/10
 */
@ContextConfiguration(locations = { "classpath:spring/spring-admin-test.xml" })
public class OrganizationServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private OrganizationService organizationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceTest.class);

    @Test
    public void queryRegionList() {
        List<RegionVo> regionVos = organizationService.queryRegionList();
        LOGGER.info("查询所有大区结果 regionVos:{}", regionVos);
    }

    @Test
    public void queryBranchList() {
        String regionCode = "10001956";
        List<CompanyVo> companyVos = organizationService.queryBranchList(regionCode);
        LOGGER.info("查询所有分公司 companyVos:{}", companyVos);
    }

    @Test
    public void queryStoreList() {
        String compCode = "1001";
        List<StoreVo> storeVos = organizationService.queryStoreList(compCode);
        LOGGER.info("查询所有门店 storeVos:{}", storeVos);
    }

}
