/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ImportFileService
 * Author:   88397670_张辉
 * Date:     2018-7-6 15:59
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.common;

import java.util.List;

import com.suning.sntk.entity.ImportFile;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-7-6
 */
public interface ImportFileService {

    /**
     * 查询待处理的导入文件
     *
     * @author 88397670_张辉
     * @since 16:03 2018-7-6
     */
    List<ImportFile> queryProcessedFile();

    void updateImportFile(ImportFile importFile);

    void updateImportFileList(List<ImportFile> importFileList);
}
