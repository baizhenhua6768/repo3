/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: OssServiceTest
 * Author:   88397670_张辉
 * Date:     2018-7-13 11:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.system;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.service.system.OssService;
import com.suning.sntk.support.common.CommonConstants;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-7-13
 */
public class OssServiceTest extends BaseTest {

    @Autowired
    private OssService ossService;

    @Test
    public void getFileStream(){
        String objectId = "ES-TljsBlydmCaAdhYo0VCDjy6MNESID4wyQmsyxmzOpOcz3js26SPngBg6NjGJ0.xlsx";
        ossService.getFileStream(CommonConstants.FILE_BUCKEN_NAME, objectId);
    }

    @Test
    public void uploadFile(){
        String filename = "staffImportFile.xlsx";
        String contentType = "multipart/form-data";
        InputStream in = ossService.getFileStream(CommonConstants.FILE_BUCKEN_NAME,
                "ES-TljsBlydmCaAdhYo0VCDjy6MNESID4wyQmsyxmzOpOcz3js26SPngBg6NjGJ0.xlsx");
        ossService.uploadFile(CommonConstants.FILE_BUCKEN_NAME, filename, contentType, in);
    }

    @Test
    public void testUploadMultiPartFile() {
        try {
            Class<?> clazz = Class.forName("com.suning.sntk.system.OssServiceTest");
            String path = clazz.getResource("/").getPath() + "doc/testImportCloseGuide.xlsx";
            ossService.uploadMultiPartFile(CommonConstants.FILE_BUCKEN_NAME, "testImportCloseGuide.xlsx", path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
