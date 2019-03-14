/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ImportFileServiceImpl
 * Author:   88397670_张辉
 * Date:     2018-7-6 16:01
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.sntk.admin.dao.common.ImportFileDao;
import com.suning.sntk.admin.service.common.ImportFileService;
import com.suning.sntk.entity.ImportFile;

/**
 * 功能描述：导入文件服务
 *
 * @author 88397670_张辉
 * @since 2018-7-6
 */
@Service
public class ImportFileServiceImpl implements ImportFileService {

    @Autowired
    private ImportFileDao importFileDao;
    @Override
    public List<ImportFile> queryProcessedFile() {
        return importFileDao.queryProcessedFile();
    }

    @Override
    public void updateImportFile(ImportFile importFile) {
        importFileDao.update(importFile);
    }

    @Override
    public void updateImportFileList(List<ImportFile> importFileList) {
        importFileDao.batchUpdate(importFileList);
    }
}
