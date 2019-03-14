package vgo;

import java.util.ArrayList;
import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.suning.sntk.admin.dao.vgo.O2oStoreDetailDao;
import com.suning.sntk.admin.service.vgo.impl.StoreDetailServiceImpl;
import com.suning.sntk.consumer.impl.NsfbusConsumerServiceImpl;
import com.suning.sntk.dto.region.EmployeeInfo;
import com.suning.sntk.dto.region.OrgInfo;
import com.suning.sntk.dto.vgo.OrgInfoDto;
import com.suning.sntk.dto.vgo.StaffUnitDto;

/**
 * 功能描述：门店详情服务测试类
 *
 * @author 88396455_白振华
 * @since 2018-9-11
 */
@ContextConfiguration(locations = { "classpath:spring/spring-admin-test.xml" })
public class StoreDetailServiceImplTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private StoreDetailServiceImpl storeDetailService;

    @Mock
    private O2oStoreDetailDao storeDetailDao;

    @Mock
    private NsfbusConsumerServiceImpl nsfbusConsumerService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testQueryRegionInfoList() {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setStaffId("12080127");
        employeeInfo.setOrgLevel("4");
        employeeInfo.setOrgCode("1100");
        StaffUnitDto staffUnitDto = new StaffUnitDto();
        obtailParam(staffUnitDto, employeeInfo.getOrgLevel(), employeeInfo.getOrgCode());
        BDDMockito.when(storeDetailDao.queryRegionInfoByStaffUnit(staffUnitDto)).thenReturn(new ArrayList<OrgInfoDto>());
        storeDetailService.queryRegionInfoList(employeeInfo);
        employeeInfo.setOrgLevel("1");
        employeeInfo.setOrgCode("10000701");
        StaffUnitDto staffUnitDto1 = new StaffUnitDto();
        obtailParam(staffUnitDto1, employeeInfo.getOrgLevel(), employeeInfo.getOrgCode());
        BDDMockito.when(storeDetailDao.queryRegionInfoByStaffUnit(staffUnitDto1)).thenReturn(new ArrayList<OrgInfoDto>());
        storeDetailService.queryRegionInfoList(employeeInfo);
        employeeInfo.setOrgLevel("2");
        employeeInfo.setOrgCode("10000701");
        StaffUnitDto staffUnitDto2 = new StaffUnitDto();
        obtailParam(staffUnitDto2, employeeInfo.getOrgLevel(), employeeInfo.getOrgCode());
        BDDMockito.when(storeDetailDao.queryRegionInfoByStaffUnit(staffUnitDto2)).thenReturn(new ArrayList<OrgInfoDto>());
        storeDetailService.queryRegionInfoList(employeeInfo);
    }

    private void obtailParam(StaffUnitDto staffUnitDto, String level, String code) {
        if (OrgInfo.ORG_LEVEL.REGION_LEVEL.equals(level)) {
            staffUnitDto.setRegionId(code);
            return;
        } else if (OrgInfo.ORG_LEVEL.COMPANY_LEVEL.equals(level)) {
            staffUnitDto.setOrgId(code);
        } else if (OrgInfo.ORG_LEVEL.STORE_LEVEL.equals(level)) {
            staffUnitDto.setStoreCode(code);
        }
    }

    @Test
    public void testQueryCompanyInfoList() {
        List<OrgInfoDto> list = new ArrayList<OrgInfoDto>();
        OrgInfoDto orgInfoDto = new OrgInfoDto();
        orgInfoDto.setOrgId("1100");
        list.add(orgInfoDto);
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setStaffId("14074464");
        employeeInfo.setOrgLevel("1");
        employeeInfo.setOrgCode("1100");
        StaffUnitDto staffUnitDto = new StaffUnitDto();
        obtailParam(staffUnitDto, employeeInfo.getOrgLevel(), employeeInfo.getOrgCode());
        BDDMockito.when(storeDetailDao.queryCompanyInfoList(BDDMockito.any(StaffUnitDto.class))).thenReturn(list);
        storeDetailService.queryCompanyInfoList("1100", employeeInfo);
        employeeInfo.setOrgLevel("4");
        employeeInfo.setOrgCode("703U");
        storeDetailService.queryCompanyInfoList("10001956", employeeInfo);
    }

    @Test
    public void testQueryStoreInfoList() {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setStaffId("15040021");
        employeeInfo.setOrgLevel("4");
        employeeInfo.setOrgCode("700W");
        StaffUnitDto staffUnitDto1 = new StaffUnitDto();
        obtailParam(staffUnitDto1, employeeInfo.getOrgLevel(), employeeInfo.getOrgCode());
        BDDMockito.when(storeDetailDao.queryRegionInfoByStaffUnit(staffUnitDto1)).thenReturn(new ArrayList<OrgInfoDto>());
        storeDetailService.queryStoreInfoList("10001957", employeeInfo);
        employeeInfo.setStaffId("15040021");
        employeeInfo.setOrgLevel("1");
        employeeInfo.setOrgCode("10001956");
        StaffUnitDto staffUnitDto2 = new StaffUnitDto();
        obtailParam(staffUnitDto2, employeeInfo.getOrgLevel(), employeeInfo.getOrgCode());
        BDDMockito.when(storeDetailDao.queryRegionInfoByStaffUnit(staffUnitDto2)).thenReturn(new ArrayList<OrgInfoDto>());
        storeDetailService.queryStoreInfoList("10001957", employeeInfo);
    }
}