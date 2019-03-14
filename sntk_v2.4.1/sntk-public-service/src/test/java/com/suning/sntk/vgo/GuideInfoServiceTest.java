/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoServiceTest
 * Author:   88397670_张辉
 * Date:     2018-9-10 15:48
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.vgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.service.vgo.GuideInfoService;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-9-10
 */
public class GuideInfoServiceTest extends BaseTest {
    @Autowired
    private GuideInfoService guideInfoService;

    @Test
    public void queryAppletCustManager() {
        String storeCode = "7891";
        String custNo = "7013708919";
        guideInfoService.queryAppletCustManager(storeCode, custNo);
        String custNo1 = "12312323123123123";
        guideInfoService.queryAppletCustManager(storeCode, custNo1);
    }

    @Test
    public void queryGuideListForSmallRoutine() {
        String storeCode = "7891";
        String custNo = "7013708919";
        String preGuideId = "18041004";
        guideInfoService.queryGuideListForSmallRoutine(storeCode, custNo, preGuideId);
        String storeCode1 = "1234";
        String custNo1 = "2314122312321321";
        guideInfoService.queryGuideListForSmallRoutine(storeCode1, custNo1, preGuideId);
    }

    @Test
    public void queryGuideInfoAndVgoVideo() {
        String customerNo = "76110007895";
        String guideId = "22334455";
        guideInfoService.queryGuideInfoAndVgoVideo(customerNo, guideId, 4, 1, 20);
    }

}
