package com.suning.sntk.vgo;

import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.suning.aimp.intf.resp.social.QuerySocialInfoResp;
import com.suning.nsfbus.organization.rsfservice.dto.DistrictSaleOrgDto;
import com.suning.nsfbus.organization.rsfservice.dto.SiteInfoDto;
import com.suning.nsfuaa.employee.dto.Employee;
import com.suning.sntk.BaseTest;
import com.suning.sntk.consumer.NsfbusConsumerService;
import com.suning.sntk.consumer.NsfuaaConsumerService;
import com.suning.sntk.consumer.WechatConsumerService;
import com.suning.sntk.dao.o2o.GuideDao;
import com.suning.sntk.dao.relation.CustomerAdvisorDao;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dao.vgo.StaffBeStoreDao;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.entity.relation.CustomerAdvisor;
import com.suning.sntk.service.vgo.VgoModifyRedisService;
import com.suning.sntk.service.vgo.impl.ManagerPublicServiceImpl;
import com.suning.sntk.support.common.redis.RedisCacheUtils;

/**
 * @author 18032490_赵亚奇
 * @since 2018/9/5
 */
public class ManagerPublicServiceTest {

    @InjectMocks
    private ManagerPublicServiceImpl managerPublicService;

    @Mock
    private GuideDao guideDao;

    @Mock
    private StaffBeStoreDao staffBeStoreDao;

    @Mock
    private GuideInfoDao guideInfoDao;

    @Mock
    private NsfuaaConsumerService employeeService;

    @Mock
    private CustomerAdvisorDao customerAdvisorDao;

    @Mock
    private NsfbusConsumerService nsfbusConsumerService;

    @Mock
    private WechatConsumerService wechatConsumerService;

    @Mock
    private RedisCacheUtils redisCacheUtils;

    @Mock
    private VgoModifyRedisService vgoModifyRedisService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuildManagerRelation1() throws Exception {
        String custNo = "100100";
        String storeCode = "70B0";
        String staffId = "BE7166F000AE7CD3F0F91321DBEE5A17";
        String channel = "30";
        String businessType = "1";
        ManagerInfoDto managerInfoDto = new ManagerInfoDto();
        Employee employee = new Employee();
        CustomerAdvisor advisor = new CustomerAdvisor();
        SiteInfoDto storeInfo = new SiteInfoDto();
        DistrictSaleOrgDto orgDto = new DistrictSaleOrgDto();
        QuerySocialInfoResp socialInfo = new QuerySocialInfoResp();

        BDDMockito.when(staffBeStoreDao.queryBusinessType(BDDMockito.anyString())).thenReturn(businessType);
        BDDMockito.when(guideDao.queryManagerByCustNoAndBusiness(BDDMockito.anyString(), BDDMockito.anyString()))
                .thenReturn(managerInfoDto);
        BDDMockito.when(employeeService.queryEmployeeBasicInfo(BDDMockito.anyString())).thenReturn(employee);
        BDDMockito.when(customerAdvisorDao.queryInfoByStaffAndCustNo(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(advisor);
        BDDMockito.when(nsfbusConsumerService.queryStoreInfo(BDDMockito.anyString())).thenReturn(storeInfo);
        BDDMockito.when(nsfbusConsumerService.queryAreaInfo(BDDMockito.anyString())).thenReturn(orgDto);
        BDDMockito.when(wechatConsumerService.queryCustomerSocialInfo(BDDMockito.anyString())).thenReturn(socialInfo);
        BDDMockito.when(redisCacheUtils.hset(BDDMockito.anyString(), BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(1L);
        managerPublicService.buildManagerRelation(custNo, staffId, storeCode, channel);
    }

    @Test
    public void testBuildManagerRelation2() throws Exception {
        String custNo = "100100";
        String storeCode = "70B0";
        String staffId = "BE7166F000AE7CD3F0F91321DBEE5A17";
        String channel = "30";
        String businessType = "1";
        ManagerInfoDto managerInfoDto = new ManagerInfoDto();
        Employee employee = new Employee();
        CustomerAdvisor advisor = null;
        SiteInfoDto storeInfo = new SiteInfoDto();
        DistrictSaleOrgDto orgDto = new DistrictSaleOrgDto();
        QuerySocialInfoResp socialInfo = new QuerySocialInfoResp();

        BDDMockito.when(staffBeStoreDao.queryBusinessType(BDDMockito.anyString())).thenReturn(businessType);
        BDDMockito.when(guideDao.queryManagerByCustNoAndBusiness(BDDMockito.anyString(), BDDMockito.anyString()))
                .thenReturn(managerInfoDto);
        BDDMockito.when(employeeService.queryEmployeeBasicInfo(BDDMockito.anyString())).thenReturn(employee);
        BDDMockito.when(customerAdvisorDao.queryInfoByStaffAndCustNo(BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(advisor);
        BDDMockito.when(nsfbusConsumerService.queryStoreInfo(BDDMockito.anyString())).thenReturn(storeInfo);
        BDDMockito.when(nsfbusConsumerService.queryAreaInfo(BDDMockito.anyString())).thenReturn(orgDto);
        BDDMockito.when(wechatConsumerService.queryCustomerSocialInfo(BDDMockito.anyString())).thenReturn(socialInfo);
        BDDMockito.when(redisCacheUtils.hset(BDDMockito.anyString(), BDDMockito.anyString(), BDDMockito.anyString())).thenReturn(1L);
        managerPublicService.buildManagerRelation(custNo, staffId, storeCode, channel);
    }

    @Test
    public void testQueryManagerInfo() throws Exception {
        String custNo = "100100";
        String storeCode = "70B0";
        BDDMockito.when(staffBeStoreDao.queryBusinessType(storeCode)).thenReturn("1");
        managerPublicService.queryManagerInfo(custNo, storeCode);
    }

    @Test
    public void testQueryOldManager1() throws Exception {
        String custNo = "100100";
        String storeCode = "70B0";
        String staffId = "18000000";
        String businessType = "1";
        ManagerInfoDto managerInfoDto = new ManagerInfoDto();
        BDDMockito.when(staffBeStoreDao.queryBusinessType(storeCode)).thenReturn(businessType);
        BDDMockito.when(guideDao.queryManagerByCustNoAndBusiness(custNo, businessType)).thenReturn(managerInfoDto);
        BDDMockito.when(guideInfoDao.queryGuideDetailList(Lists.newArrayList(staffId, managerInfoDto
                .getStaffId()))).thenReturn(Lists.<GuideInfoDto>newArrayList());
        managerPublicService.queryOldManager(custNo, staffId, storeCode);
    }

    @Test
    public void testQueryOldManager2() throws Exception {
        String custNo = "100100";
        String storeCode = "70B0";
        String staffId = "18000000";
        String businessType = "1";
        ManagerInfoDto managerInfoDto = null;
        BDDMockito.when(staffBeStoreDao.queryBusinessType(storeCode)).thenReturn(businessType);
        BDDMockito.when(guideDao.queryManagerByCustNoAndBusiness(custNo, businessType)).thenReturn(managerInfoDto);
        managerPublicService.queryOldManager(custNo, staffId, storeCode);
    }

    @Test
    public void testQueryManagerList() throws Exception {
        String custNo = "100100";
        List<ManagerInfoDto> list = Lists.newArrayList();
        BDDMockito.when(guideDao.queryManagerByCustNo(custNo)).thenReturn(list);
        managerPublicService.queryManagerList(custNo);
    }
}