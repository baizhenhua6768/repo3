/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoCommonServiceImplMockTest
 * Author:   18010645_黄成
 * Date:     2018/10/7 10:03
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.vgo;

import com.suning.sntk.BaseTest;
import com.suning.sntk.service.vgo.VgoCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：vgoCommonService单元测试
 *
 * @author 18010645_黄成
 * @since 2018/10/7
 */
public class VgoCommonServiceTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VgoCommonServiceTest.class);

    @Autowired
    private VgoCommonService vgoCommonService;

    @Test
    public void tesQueryBatchOrderNumAndReceivePraise() {
        List<String> guideIds = new ArrayList<>();
        guideIds.add("01050044");
        guideIds.add("01110013");
        guideIds.add("10071578");
        guideIds.add("14080292");
        Map<String, Map<String, String>> mapList = vgoCommonService.queryBatchOrderNumAndReceivePraise(guideIds);
        LOGGER.info("mapList-------->", mapList);

    }


}
