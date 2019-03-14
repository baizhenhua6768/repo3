/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoGuideInfoServiceTest
 * Author:   18041004_余长杰
 * Date:     2018/9/8 15:23
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
 * 功能描述：导购相关信息
 *
 * @author 18041004_余长杰
 * @since 2018/9/8
 */
public class VgoGuideInfoServiceTest extends BaseTest {

    @Autowired
    private GuideInfoService guideInfoService;



    @Test
    public void queryRegionListByParentCode() {
        String parentRegionCode = "CN";
        String regionalLevel = "1";
        logger.info(guideInfoService.queryRegionListByParentCode(parentRegionCode, regionalLevel));
    }

    @Test
    public void queryRegionInfoByRegionalCodeTest() {
        String regionCode = "025";
        String regionalLevel = "3";
        logger.info(guideInfoService.queryRegionInfoByRegionalCode(regionCode, regionalLevel));
    }

}
