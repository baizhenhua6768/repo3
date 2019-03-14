/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoRsfServiceTest
 * Author:   18010645_黄成
 * Date:     2018/9/10 10:30
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.test.rsf.vgo;

import com.suning.sntk.dao.vgo.StaffBeStoreDao;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.dto.vgo.TipsReqDto;
import com.suning.sntk.dto.vgo.VgoDialogueTipDto;
import com.suning.sntk.rsf.impl.vgo.VgoDialogueRsfServiceImpl;
import com.suning.sntk.service.vgo.ManagerPublicService;
import com.suning.sntk.service.vgo.VgoDialogueService;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 功能描述：vgo会话RSF单测
 *
 * @author 18010645_黄成
 * @since 2018/9/18
 */
public class VgoDialogueRsfServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VgoDialogueRsfServiceTest.class);

    @InjectMocks
    private VgoDialogueRsfServiceImpl vgoDialogueRsfServiceImpl;

    @Mock
    private ManagerPublicService managerPublicService;

    @Mock
    private VgoDialogueService vgoDialogueService;

    @Mock
    private StaffBeStoreDao staffBeStoreDao;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testQueryExistsCustomerManagerNew() {
        String customerNum = "6114710766";
        String staffId = "11122037";
        String storeCode = "8438";
        BDDMockito.when(staffBeStoreDao.queryStoreCodeByStaffId(staffId)).thenReturn(storeCode);
        BDDMockito.when(managerPublicService.queryManagerInfo(customerNum, storeCode)).thenReturn(new ManagerInfoDto());
        RsfResponseDto<VgoDialogueTipDto> message = vgoDialogueRsfServiceImpl.existsCustomerManagerNew(customerNum, staffId);
        LOGGER.info("VgoDialogueRsfServiceTest.testQueryExistsCustomerManager,message={}", message);
    }

    @Test
    public void testQueryExistsCustomerManager() {
        String customerNum = "6114710766";
        String staffId = "11122037";
        String storeCode = "8438";
        BDDMockito.when(staffBeStoreDao.queryStoreCodeByStaffId(staffId)).thenReturn(storeCode);
        BDDMockito.when(managerPublicService.queryManagerInfo(customerNum, storeCode)).thenReturn(new ManagerInfoDto());
        RsfResponseDto<String> message = vgoDialogueRsfServiceImpl.existsCustomerManager(customerNum, staffId);
        LOGGER.info("VgoDialogueRsfServiceTest.testQueryExistsCustomerManager,message={}", message);
    }

    @Test
    public void testQueryExistsCustomerManager1() {
        String customerNum = "6114710766";
        String staffId = "11122037";
        String storeCode = "8438";
        ManagerInfoDto managerInfoDto = new ManagerInfoDto();
        managerInfoDto.setBusinessType("1");
        BDDMockito.when(staffBeStoreDao.queryStoreCodeByStaffId(staffId)).thenReturn(storeCode);
        BDDMockito.when(managerPublicService.queryManagerInfo(customerNum, storeCode)).thenReturn(managerInfoDto);
        RsfResponseDto<String> message = vgoDialogueRsfServiceImpl.existsCustomerManager(customerNum, staffId);
        LOGGER.info("VgoDialogueRsfServiceTest.testQueryExistsCustomerManager,message={}", message);
    }

    @Test
    public void testbindCustomerManager3() {
        String customerNum = "6114710766";
        String staffId = "11122037";
        String storeCode = "8438";
        ManagerInfoDto managerInfoDto = new ManagerInfoDto();
        managerInfoDto.setBusinessType("1");
        BDDMockito.when(staffBeStoreDao.queryStoreCodeByStaffId(staffId)).thenReturn(storeCode);
        BDDMockito.when(managerPublicService.queryManagerInfo(customerNum, staffId)).thenReturn(managerInfoDto);
        RsfResponseDto<String> message = vgoDialogueRsfServiceImpl.bindCustomerManager(customerNum, staffId);
        LOGGER.info("VgoDialogueRsfServiceTest.testQueryExistsCustomerManager,message={}", message);
    }

    @Test
    public void testbindCustomerManager1() {
        String customerNum = "6114710766";
        String staffId = "11122037";
        String storeCode = "8438";
        BDDMockito.when(staffBeStoreDao.queryStoreCodeByStaffId(staffId)).thenReturn(storeCode);
        BDDMockito.when(managerPublicService.queryManagerInfo(customerNum, storeCode)).thenReturn(null);
        BDDMockito.when(managerPublicService.buildManagerRelation(customerNum, staffId, storeCode, O2OConstants.YUN_XIN_LINE)).thenReturn(RsfResponseDto.success());
        RsfResponseDto<String> message = vgoDialogueRsfServiceImpl.bindCustomerManager(customerNum, staffId);
        LOGGER.info("VgoDialogueRsfServiceTest.testQueryExistsCustomerManager,message={}", message);
    }

    @Test
    public void testbindCustomerManager2() {
        String customerNum = "6114710766";
        String staffId = "11122037";
        String storeCode = "8438";
        BDDMockito.when(staffBeStoreDao.queryStoreCodeByStaffId(staffId)).thenReturn(storeCode);
        BDDMockito.when(managerPublicService.queryManagerInfo(customerNum, storeCode)).thenReturn(null);
        BDDMockito.when(managerPublicService.buildManagerRelation(customerNum, staffId, storeCode, O2OConstants.YUN_XIN_LINE)).thenReturn(RsfResponseDto.error(null));
        RsfResponseDto<String> message = vgoDialogueRsfServiceImpl.bindCustomerManager(customerNum, staffId);
        LOGGER.info("VgoDialogueRsfServiceTest.testQueryExistsCustomerManager,message={}", message);
    }

    @Test
    public void testVgoDialogueRsfService1() {
        String storeCode = "7611";
        RsfResponseDto dto = vgoDialogueRsfServiceImpl.queryStoreAddr(storeCode);
        LOGGER.info("VgoDialogueRsfServiceTest.existsCustomerManager,message={}", dto);
    }

    @Test
    public void testVgoDialogueRsfService2() {
        String customerNum = "6114710766";
        String staffId = "11122037";
        String storeCode = "8438";
        BDDMockito.when(staffBeStoreDao.queryStoreCodeByStaffId(staffId)).thenReturn(storeCode);
        RsfResponseDto response = managerPublicService.buildManagerRelation(customerNum, staffId, storeCode, O2OConstants.CHANNEL_ONLINE);
        LOGGER.info("VgoDialogueRsfServiceTest.bindCustomerManager,message={}", response);
    }

    @Test
    public void testVgoDialogueRsfService4() {
        TipsReqDto tipsReqDto = new TipsReqDto();
        tipsReqDto.setType("1");
        tipsReqDto.setStoreName("新街口店");
        tipsReqDto.setStaffName("张三");
        RsfResponseDto dto = vgoDialogueRsfServiceImpl.queryDialogueTemplate(tipsReqDto);
        LOGGER.info("VgoDialogueRsfServiceTest.queryDialogueTemplate,message={}", dto);
    }


}
