/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ExcelParseUtils
 * Author:   88396455_白振华
 * Date:     2018-9-5 10:12
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述：解析excel工具
 *
 * @author 88396455_白振华
 * @since 2018-9-5
 */
public class ExcelParseUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelParseUtils.class);
    /**
     * excel后缀 .xls
     */
    private static final String SUFFIX_XLS = ".xls";
    /**
     * excel后缀 .xlsx
     */
    private static final String SUFFIX_XLSX = ".xlsx";

    private ExcelParseUtils() {
    }

    public static Workbook initWorkbook(String fileName, InputStream in) {
        Workbook workbook = null;
        try {
            if (fileName.endsWith(SUFFIX_XLSX)) {
                workbook = new XSSFWorkbook(in);
            } else if (fileName.endsWith(SUFFIX_XLS)) {
                workbook = new HSSFWorkbook(in);
            }
        } catch (IOException e) {
            LOGGER.error("ExcelParseUtils.initWorkbook init error :[fileName:{}],exception:{}", fileName, e);
        }
        return workbook;
    }
}
