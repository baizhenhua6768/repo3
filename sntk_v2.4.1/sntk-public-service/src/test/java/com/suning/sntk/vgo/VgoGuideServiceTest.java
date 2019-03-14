/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoGuideServiceTest
 * Author:   88397670_张辉
 * Date:     2018-9-6 14:50
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.vgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.service.vgo.VgoGuideService;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-9-6
 */
public class VgoGuideServiceTest extends BaseTest {

    @Autowired
    VgoGuideService vgoGuideService;

    @Test
    public void modifyAuditGuideInfo(){
        GuideInfoDto guideInfoDto = new GuideInfoDto();
        guideInfoDto.setGuideId("12061818");
        guideInfoDto.setGuideName("刘俊");
        guideInfoDto.setIntroduction("for testng!");
        guideInfoDto.setSaleAge(10);
        guideInfoDto.setCategoryIds("1,2,3,4");
        vgoGuideService.modifyAuditGuideInfo(guideInfoDto);
        guideInfoDto = new GuideInfoDto();
        guideInfoDto.setGuideId("17071235");
        guideInfoDto.setGuideName("Test");
        guideInfoDto.setIntroduction("for testng!");
        guideInfoDto.setSaleAge(10);
        guideInfoDto.setCategoryIds("1,2,3,4");
        vgoGuideService.modifyAuditGuideInfo(guideInfoDto);

    }

    @Test
    public void checkClerkInfo(){
        String guideId = "testng";
         vgoGuideService.checkClerkInfo(guideId);
        logger.info("测试检查testng店员信息完整：");
    }

    @Test
    public void queryClerkMarkInfo(){
        String guideId = "testng";
        vgoGuideService.queryClerkMarkInfo(guideId);
    }

    @Test
    public void queryGuideDetailTest(){
        System.out.println( vgoGuideService.queryGuideDetail("08060791","60"));
    }


}
