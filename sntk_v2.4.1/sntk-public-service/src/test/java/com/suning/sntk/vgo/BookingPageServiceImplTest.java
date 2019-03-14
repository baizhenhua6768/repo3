/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookingPageServiceImplTest
 * Author:   18010645_黄成
 * Date:     2018/9/22 14:24
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.vgo;

import com.suning.aimp.intf.dto.CustAliasInfo;
import com.suning.aimp.intf.resp.social.QuerySocialInfoResp;
import com.suning.sntk.consumer.WechatConsumerService;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dto.vgo.AppointPromptDto;
import com.suning.sntk.dto.vgo.GuideDetailDto;
import com.suning.sntk.service.vgo.GuideInfoService;
import com.suning.sntk.service.vgo.impl.BookingPageServiceImpl;
import com.suning.sntk.support.common.utils.DateUtils;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：BookingPageServiceImplTest单元测试覆盖
 *
 * @author 18010645_黄成
 * @since 2018/9/22
 */
public class BookingPageServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingPageServiceImplTest.class);

    @InjectMocks
    private BookingPageServiceImpl bookingPageServiceImpl;

    @Mock
    private WechatConsumerService wechatConsumerService;

    @Mock
    private GuideInfoService guideInfoService;

    @Mock
    private GuideInfoDao guideInfoDao;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() {
        String customerNum = "8596256358";
        String guideId = "8596325";
        QuerySocialInfoResp socialInfo = new QuerySocialInfoResp();
        List<CustAliasInfo> list = new ArrayList<>();
        CustAliasInfo custAliasInfo = new CustAliasInfo();
        custAliasInfo.setCustAlias("18956435528");
        list.add(custAliasInfo);
        socialInfo.setAliasInfoList(list);
        BDDMockito.when(wechatConsumerService.queryCustomerSocialInfo(customerNum)).thenReturn(socialInfo);
        GuideDetailDto guideDetailDto = new GuideDetailDto();
        guideDetailDto.setStarGrade("1.0");
        guideDetailDto.setGuideName("李四");
        BDDMockito.when(guideInfoService.getGuideDetail(guideId)).thenReturn(guideDetailDto);
        bookingPageServiceImpl.queryBookingPageInitInfo(customerNum, guideId);
    }


    @Test
    public void test2() {
        String customerNum = "8596256358";
        String bookTime = "2018-09-22";
        String guideId = "5896523";
        BDDMockito.when(guideInfoDao.queryBookingGuideId(customerNum, bookTime, DateUtils.formatPatten10(new Date()))).thenReturn(guideId);
        AppointPromptDto respDto = new AppointPromptDto();
        respDto.setShortName("新街口淮海路店");
        respDto.setBookCode("V9999999999");
        respDto.setBookTime("2018-09-22");
        respDto.setGuideName("张三丰");
        BDDMockito.when(guideInfoDao.queryBookingDialogue(guideId, customerNum, bookTime, DateUtils.formatPatten10(new Date()))).thenReturn(respDto);
        AppointPromptDto response = bookingPageServiceImpl.queryBookingPrompDialogue(customerNum, bookTime);
        LOGGER.info("BookingPageServiceImplTest.test2,response:{}", response);
    }
}
