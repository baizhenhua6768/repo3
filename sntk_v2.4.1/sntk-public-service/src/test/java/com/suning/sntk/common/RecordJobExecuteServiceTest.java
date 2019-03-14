/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: RecordJobExecuteServiceTest
 * Author:   88397670_张辉
 * Date:     2018-9-20 16:04
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.service.common.RecordJobExecuteService;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-9-20
 */
public class RecordJobExecuteServiceTest extends BaseTest {

    @Autowired
    private RecordJobExecuteService recordJobExecuteService;

    @Test
    public void addRecord(){
        String jobDesc = "testng";
        recordJobExecuteService.addRecord(jobDesc);
    }

    @Test
    public void updateRecord(){
        Long id = new Long(1);
        Boolean isSuccess = true;
        String errorMessage = "testng";
        recordJobExecuteService.updateRecord(id,isSuccess,errorMessage);
    }
}
