package com.suning.sntk.test.rsf.vgo;

import java.util.Collections;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.rsf.impl.vgo.ManagerRsfServiceImpl;
import com.suning.sntk.service.vgo.ManagerPublicService;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * @author 18032490_赵亚奇
 * @since 2018/9/18
 */
public class ManagerRsfServiceImplTest {
    @InjectMocks
    private ManagerRsfServiceImpl managerRsfService;

    @Mock
    private ManagerPublicService managerPublicService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testQueryManagerList() throws Exception {
        String custNo = "123456";
        BDDMockito.when(managerPublicService.queryManagerList(BDDMockito.anyString())).thenReturn(Collections.<ManagerInfoDto>emptyList());
        managerRsfService.queryManagerList(custNo);
    }

    @Test
    public void testBuildManagerRelation() throws Exception {
        RsfResponseDto dto = RsfResponseDto.empty();
        String custNo = "123456";
        String staffId = "100001";
        String channel = "100001";
        String storeCode = "100001";
        BDDMockito.when(managerPublicService
                .buildManagerRelation(BDDMockito.anyString(), BDDMockito.anyString(), BDDMockito.anyString(), BDDMockito.anyString()))
                .thenReturn(dto);
        managerRsfService.buildManagerRelation(custNo, staffId, storeCode, channel);
    }

    @Test
    public void testQueryManagerInfo() throws Exception {
        String custNo = "123456";
        String storeCode = "100001";
        BDDMockito.when(managerPublicService.queryManagerInfo(BDDMockito.anyString(), BDDMockito.anyString()))
                .thenReturn(new ManagerInfoDto());
        managerRsfService.queryManagerInfo(custNo, storeCode);
    }

    @Test
    public void testQueryOldManager() throws Exception {
        String custNo = "123456";
        String storeCode = "100001";
        String staffId = "100001";
        BDDMockito.when(managerPublicService.queryOldManager(BDDMockito.anyString(), BDDMockito.anyString(), BDDMockito.anyString()))
                .thenReturn(Collections.<GuideInfoDto>emptyList());
        managerRsfService.queryOldManager(custNo, staffId, storeCode);
    }
}