/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoDialogueServiceImplTest
 * Author:   18010645_黄成
 * Date:     2018/9/21 15:22
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.vgo;

import com.suning.sntk.dao.DictDao;
import com.suning.sntk.dao.vgo.GuideAppointmentDao;
import com.suning.sntk.dao.vgo.StaffBeStoreDao;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.dto.vgo.StaffBeStoreRespDto;
import com.suning.sntk.dto.vgo.TipsReqDto;
import com.suning.sntk.service.vgo.ManagerPublicService;
import com.suning.sntk.service.vgo.impl.VgoDialogueServiceImpl;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.vo.DictVo;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * 功能描述：
 *
 * @author 18010645_黄成
 * @since 2018/9/21
 */
public class VgoDialogueServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VgoDialogueServiceImplTest.class);

    @InjectMocks
    private VgoDialogueServiceImpl vgoDialogueServiceImpl;

    @Mock
    private RedisCacheUtils redisCacheUtils;

    @Mock
    private ManagerPublicService managerPublicService;

    @Mock
    private GuideAppointmentDao guideAppointmentDao;

    @Mock
    private StaffBeStoreDao staffBeStoreDao;

    @Mock
    private DictDao dictDao;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryTipsInfo1() {
        TipsReqDto reqDto = new TipsReqDto();
        reqDto.setType("1");
        reqDto.setStoreName("");
        reqDto.setStaffName("李四");
        String value = "您好，我是{0}{1}，请问有什么可以帮助到您的?";
        DictVo dictVo = new DictVo();
        dictVo.setDictValue(value);
        BDDMockito.when(dictDao.findDictByTypeAndCode(VgoConstants.YUN_XIN_TEMPLATE, reqDto.getType())).thenReturn(dictVo);
        String message = vgoDialogueServiceImpl.queryTipsInfo(reqDto);
        LOGGER.info("message", message);
    }

    @Test
    public void queryTipsInfo2() {
        TipsReqDto reqDto = new TipsReqDto();
        reqDto.setType("1");
        reqDto.setStoreName("新街口");
        reqDto.setStaffName("李四");
        String value = "您好，我是{0}{1}，请问有什么可以帮助到您的?";
        DictVo dictVo = new DictVo();
        dictVo.setDictValue(value);
        vgoDialogueServiceImpl.queryTipsInfo(reqDto);
    }

    @Test
    public void queryTipsInfo3() {
        TipsReqDto reqDto = new TipsReqDto();
        reqDto.setType("2");
        reqDto.setStoreName("新街口");
        reqDto.setStaffName("张三");
        String value = "店员当前正在忙碌，TA将会在看到消息第一时间恢复您，您也可以选择预约到店咨询，点击";
        DictVo dictVo = new DictVo();
        dictVo.setDictValue(value);
        vgoDialogueServiceImpl.queryTipsInfo(reqDto);
    }

    @Test
    public void getStoreInfo1() {
        String storeCode = "7611";
        StaffBeStoreRespDto staffBeStoreRespDto = new StaffBeStoreRespDto();
        staffBeStoreRespDto.setShortName("新街口淮海路店");
        staffBeStoreRespDto.setStoreAddress("新街口88号");
        String value = "8568";
        BDDMockito.when(redisCacheUtils.get(storeCode)).thenReturn(value);
        BDDMockito.when(staffBeStoreDao.queryStaffBeStoreAddressInfo(storeCode)).thenReturn(staffBeStoreRespDto);
        StaffBeStoreRespDto respDto = vgoDialogueServiceImpl.getStoreInfo(storeCode);
        LOGGER.info("respDto", respDto);

    }

    @Test
    public void getStoreInfo2() {
        String storeCode = "7611";
        StaffBeStoreRespDto staffBeStoreRespDto = new StaffBeStoreRespDto();
        staffBeStoreRespDto.setShortName("新街口淮海路店");
        staffBeStoreRespDto.setStoreAddress("新街口88号");
        String value = "";
        BDDMockito.when(redisCacheUtils.get(BDDMockito.anyString())).thenReturn(value);
        BDDMockito.when(staffBeStoreDao.queryStaffBeStoreAddressInfo(storeCode)).thenReturn(staffBeStoreRespDto);
        vgoDialogueServiceImpl.getStoreInfo(storeCode);
    }

    @Test
    public void setCustomerManager1() {
        String customerNo = "880956824";
        String staffId = "18050696";
        ManagerInfoDto guideCustInfo = new ManagerInfoDto();
        guideCustInfo.setBusinessType("1");
        BDDMockito.when(managerPublicService.queryManagerInfo(customerNo, staffId)).thenReturn(guideCustInfo);
        vgoDialogueServiceImpl.setCustomerManagerNew(customerNo, staffId);
    }

    @Test
    public void setCustomerManager2() {
        String customerNo = "880956824";
        String staffId = "18050696";
        String storeCode = "8727";
        DictVo yxDictVo = new DictVo();
        DictVo djDictVo = new DictVo();
        BDDMockito.when(managerPublicService.queryManagerInfo(customerNo, staffId)).thenReturn(null);
        BDDMockito.when(staffBeStoreDao.queryStoreCodeByStaffId(staffId)).thenReturn(storeCode);
        BDDMockito.when(managerPublicService.buildManagerRelation(customerNo, staffId, storeCode, O2OConstants.YUN_XIN_LINE)).thenReturn(RsfResponseDto.success());
        BDDMockito.when(dictDao.findDictByTypeAndCode(VgoConstants.SET_MANAGER_TIPS, VgoConstants.SUCCESS_YX)).thenReturn(yxDictVo);
        BDDMockito.when(dictDao.findDictByTypeAndCode(VgoConstants.SET_MANAGER_TIPS, VgoConstants.SUCCESS_DJ)).thenReturn(djDictVo);
        vgoDialogueServiceImpl.setCustomerManagerNew(customerNo, staffId);
    }


    @Test
    public void setCustomerManager3() {
        String customerNo = "880956824";
        String staffId = "18050696";
        String storeCode = "8727";
        DictVo failDictVo = new DictVo();
        BDDMockito.when(managerPublicService.queryManagerInfo(customerNo, staffId)).thenReturn(null);
        BDDMockito.when(staffBeStoreDao.queryStoreCodeByStaffId(staffId)).thenReturn(storeCode);
        BDDMockito.when(managerPublicService.buildManagerRelation(customerNo, staffId, storeCode, O2OConstants.YUN_XIN_LINE)).thenReturn(RsfResponseDto.error(null));
        BDDMockito.when(dictDao.findDictByTypeAndCode(VgoConstants.SET_MANAGER_TIPS, VgoConstants.FAIL_YX)).thenReturn(failDictVo);
        vgoDialogueServiceImpl.setCustomerManagerNew(customerNo, staffId);
    }

    @Test
    public void isCustomerManager1() {
        String customerNo = "880956824";
        String staffId = "18050696";
        BDDMockito.when(managerPublicService.queryManagerInfo(customerNo, staffId)).thenReturn(new ManagerInfoDto());
        vgoDialogueServiceImpl.isCustomerManagerNew(customerNo, staffId);
    }

    @Test
    public void isCustomerManager2() {
        String customerNo = "880956824";
        String staffId = "18050696";
        BDDMockito.when(managerPublicService.queryManagerInfo(customerNo, staffId)).thenReturn(null);
        BDDMockito.when(dictDao.findDictByTypeAndCode(VgoConstants.YX_DIALOGUE_TIPS, VgoConstants.NO_MANAGER_TEMPALTE)).thenReturn(new DictVo());
        vgoDialogueServiceImpl.isCustomerManagerNew(customerNo, staffId);
    }

    @Test
    public void queryBusyTemplate1() {
        String customerNo = "880956824";
        BDDMockito.when(guideAppointmentDao.existNoCompleteBookingOrder(customerNo, DateUtils.formatPatten10(new Date()))).thenReturn(true);
        vgoDialogueServiceImpl.queryBusyTemplate(customerNo);
    }

    @Test
    public void queryBusyTemplate2() {
        String customerNo = "880956824";
        BDDMockito.when(guideAppointmentDao.existNoCompleteBookingOrder(customerNo, DateUtils.formatPatten10(new Date()))).thenReturn(false);
        BDDMockito.when(dictDao.findDictByTypeAndCode(VgoConstants.YX_DIALOGUE_TIPS, VgoConstants.SHOPPER_BUSY_TEMPLAT)).thenReturn(new DictVo());
        vgoDialogueServiceImpl.queryBusyTemplate(customerNo);
    }

}
