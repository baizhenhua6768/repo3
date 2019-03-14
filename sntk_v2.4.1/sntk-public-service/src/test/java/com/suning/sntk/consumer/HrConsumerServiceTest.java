/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: HrConsumerServiceTest
 * Author:   88397670_张辉
 * Date:     2018-9-12 10:42
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-9-12
 */
public class HrConsumerServiceTest extends BaseTest {

    @Autowired
    private HrConsumerService hrConsumerService;

    @Test
    public void queryPerson(){
        String param = "12061818";
        String resultName = "organizationunit";
        String result = hrConsumerService.queryPerson(param,resultName);
        logger.info("HR接口查询人员层级返回结果 ，result：{}"+result);
    }

    @Test
    public void queryOrganization(){
        String orgId = "testng";
        String result = hrConsumerService.queryOrganization(orgId);
        logger.info("HR接口查询人员所属结果，result:{}"+result);
    }
}
