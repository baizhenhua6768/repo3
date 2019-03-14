/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoTest
 * Author:   18010645_黄成
 * Date:     2018/9/3 17:23
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.vgo;

import com.suning.mzss.rsf.video.dto.VFrontContentDto;
import com.suning.sntk.BaseTest;
import com.suning.sntk.consumer.MemberConsumerService;
import com.suning.sntk.consumer.MzssConsumerService;
import com.suning.sntk.consumer.SuisConsumerService;
import com.suning.sntk.dto.vgo.*;
import com.suning.sntk.service.vgo.BookingPageService;
import com.suning.sntk.service.vgo.GuideInfoService;
import com.suning.sntk.service.vgo.VgoCommonService;
import com.suning.sntk.service.vgo.VgoDialogueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 *
 * @author 18010645_黄成
 * @since 2018/9/3
 */
public class VgoDialogueServiceTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VgoDialogueServiceTest.class);

    @Autowired
    private BookingPageService bookingPageService;

    @Autowired
    private VgoDialogueService vgoDialogueService;

    @Autowired
    private GuideInfoService guideInfoService;

    @Autowired
    private VgoCommonService vgoCommonService;

    @Autowired
    private SuisConsumerService suisConsumerService;

    @Autowired
    private MzssConsumerService mzssConsumerService;

    @Autowired
    private MemberConsumerService memberConsumerService;

    /**
     * 功能：查询预约页初始化信息
     *
     * @author 18010645_黄成
     * @since 15:06 2018/9/10
     */
    @Test
    public void queryBookingPageInitInfo() {
        AppointInitQueryRespDto respDto = null;
        try {
            respDto = bookingPageService.queryBookingPageInitInfo("6114710766", "01040056");
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.queryVgoDialogue,result[{}]" + respDto);
        }
    }

    /**
     * 功能：查询预约提示弹窗语信息
     *
     * @author 18010645_黄成
     * @since 15:05 2018/9/10
     */
    @Test
    public void queryAppointPromptDto() {
        AppointPromptDto respDto = null;
        try {
            respDto = bookingPageService.queryBookingPrompDialogue("6114710766", "2018-09-13");
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.queryVgoDialogue,result[{}]" + respDto);
        }
    }

    /**
     * 功能：vgo会话模板
     *
     * @author 18010645_黄成
     * @since 15:05 2018/9/10
     */
    @Test
    public void queryVgoDialogueTemplate() {
        TipsReqDto tipsReqDto = new TipsReqDto();
        tipsReqDto.setType("1");
        tipsReqDto.setStoreName("新街口店");
        tipsReqDto.setStaffName("张三");
        String val = null;
        try {
            val = vgoDialogueService.queryTipsInfo(tipsReqDto);
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.queryVgoDialogue,result[{}]" + val);
        }
    }

    /**
     * 功能：查询店员所属门店信息
     *
     * @author 18010645_黄成
     * @since 15:04 2018/9/10
     */
    @Test
    public void queryStaffBeStoreInfo() {
        String storeCode = "9709";
        StaffBeStoreRespDto respDto = null;
        try {
            respDto = vgoDialogueService.getStoreInfo(storeCode);
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.queryStaffBeStoreInfo,result[{}]" + respDto);
        }
    }

    /**
     * 功能：店员主页(导购详情、视频)
     *
     * @author 18010645_黄成
     * @since 15:04 2018/9/10
     */
    @Test
    public void queryGuideInfoAndVgoVideo() {
        String customerNum = "6114710766";
        String guideId = "11122037";
        Integer pageNo = 1;
        Integer pageSize = 20;
        Integer fromType = 30;
        StaffPageDto respDto = null;
        try {
            respDto = guideInfoService.queryGuideInfoAndVgoVideo(customerNum, guideId, pageNo, pageSize, fromType);
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.queryGuideInfoAndVgoVideo,result[{}]" + respDto);
        }
    }

    /**
     * 功能：pc端门店基本信息和v购人员列表
     *
     * @author 18010645_黄成
     * @since 17:16 2018/9/17
     */
    @Test
    public void getStoreVServants() {
        String storeCode = "9709";
        StoreServantRespDto respDto = null;
        try {
            respDto = guideInfoService.queryStoreGuides(storeCode, "123456");
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.getStoreVServants,result[{}]" + respDto);
        }
        LOGGER.info("VgoDialogueServiceTest.getStoreVServants,result[{}]" + respDto);
    }

    /**
     * 功能：接单数和获赞数
     *
     * @author 18010645_黄成
     * @since 17:18 2018/9/17
     */
    @Test
    public void getOrderNumAndReceivePraise() {
        String guideId = "11122037";
        Map<String, String> mapResult = null;
        try {
            mapResult = vgoCommonService.queryOrderNumAndReceivePraise(guideId);
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.getOrderNumAndReceivePraise,result[{}]" + mapResult);
        }
    }

    /**
     * 功能：查询视频编码
     *
     * @author 18010645_黄成
     * @since 17:38 2018/9/17
     */
    @Test
    public void querySuisConsumer() {
        String employed = "2432423";
        String videoNo = null;
        try {
            videoNo = suisConsumerService.queryVedioCustomerNum(employed);
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.queryVedioCustomerNum,result[{}]" + videoNo);
        }
    }

    /**
     * 功能：视频列表
     *
     * @author 18010645_黄成
     * @since 17:38 2018/9/17
     */
    @Test
    public void queryMzssConsumer() {
        List<VFrontContentDto> list = null;
        try {
            String employed = "2432423";
            List<VgoVideoDto> dtoList = new ArrayList<>();
            String videoNo = suisConsumerService.queryVedioCustomerNum(employed);
            list = mzssConsumerService.queryVgoVideoList(videoNo, 1, 1, 20);
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.queryMzssConsumer,result[{}]" + list);
        }
    }

    /**
     * 功能：查询pc端会员手机号码
     *
     * @author 18010645_黄成
     * @since 18:07 2018/9/18
     */
    @Test
    public void queryPcMemberMobile() {
        String mobile = null;
        try {
            String customerNum = "7013724275";
            mobile = memberConsumerService.queryMemberPhone(customerNum);
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.queryMemberPhone,result[{}]" + mobile);
        }
    }

    @Test
    public void setCustomerManager() {
        String customerNum = "1234566";
        String guideId = "180306987";
        VgoDialogueTipDto vgoDialogueTipDto = null;
        try {
            vgoDialogueTipDto = vgoDialogueService.setCustomerManagerNew(customerNum, guideId);
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.queryMemberPhone,result[{}]", vgoDialogueTipDto);
        }
    }

    @Test
    public void isCustomerManager() {
        String customerNum = "6115006840";
        String guideId = "13074561";
        VgoDialogueTipDto vgoDialogueTipDto = null;
        try {
            vgoDialogueTipDto = vgoDialogueService.isCustomerManagerNew(customerNum, guideId);
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.queryMemberPhone,result[{}]", vgoDialogueTipDto);
        }
    }

    @Test
    public void queryBusyTemplate() {
        String customerNum = "6115006840";
        VgoDialogueTipDto vgoDialogueTipDto = null;
        try {
            vgoDialogueTipDto = vgoDialogueService.queryBusyTemplate(customerNum);
        } catch (Exception e) {
            LOGGER.info("VgoDialogueServiceTest.queryMemberPhone,result[{}]", vgoDialogueTipDto);
        }
    }

}
