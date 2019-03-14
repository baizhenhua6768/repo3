/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ExportFileServiceImpl
 * Author:   88396455_白振华
 * Date:     2018-8-24 16:58
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.common.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suning.sntk.admin.dao.common.ExportFileDao;
import com.suning.sntk.admin.service.common.ExportFileService;
import com.suning.sntk.admin.service.common.ExportProvider;
import com.suning.sntk.dto.system.OssFileInfo;
import com.suning.sntk.entity.ExportFile;
import com.suning.sntk.enums.FileType;
import com.suning.sntk.service.system.OssService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.enums.ExportStatus;
import com.suning.sntk.support.enums.SysErrorEnum;
import com.suning.store.commons.lang.validator.Validators;

/**
 * 功能描述：文件导出服务
 *
 * @author 88396455_白振华
 * @since 2018-8-24
 */
@Service
public class ExportFileServiceImpl implements ExportFileService {

    private Logger LOGGER = LoggerFactory.getLogger(ExportFileServiceImpl.class);

    @Autowired
    private ExportFileDao exportFileDao;

    @Autowired
    @Qualifier("exportExecutor")
    private ExecutorService exportExecutor;

    @Autowired
    private OssService ossService;

    @Override
    public <T> ExportFile exportFile(String custNo, ExportProvider<T> provider) throws IOException {
        long count = provider.count();
        if (count <= 0) {
            LOGGER.info("没有需要导出的数据");
            Validators.throwAnyway(SysErrorEnum.NO_DATA_EXPORT);
        }

        FileType fileType = provider.getFileType();
        String filename = getFilename(fileType);
        ExportFile file = createExportFile(custNo, filename, fileType);

        //校验是否超过最大导出数量
        String maxCount = CommonConstants.DEFAULT_MAX_EXPORT_DATA_COUNT;
        Validators.ifInValid(count > Integer.parseInt(maxCount)).thenThrow(SysErrorEnum.EXCEED_MAX_EXPORT_COUNT);
        LOGGER.info("需要导出的数据总量：count = {}", count);
        //创建一个导出任务
        try {
            Future<?> future = exportExecutor.submit(new ExportTask<>(file, provider));
            //判断是否异步
            if (!provider.isAsync()) {
                future.get();
            }
        } catch (RejectedExecutionException e) {
            LOGGER.warn("导出任务执行队列已满，请稍后重试", e);
            Validators.throwAnyway(SysErrorEnum.EXCEED_MAX_EXPORT_COUNT);
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("同步导出文件失败", e);
        }
        return file;
    }


    private String getFilename(FileType fileType) {
        return String.format("%s-%s", fileType.getDescription(), new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()));
    }

    private ExportFile createExportFile(String custNo, String filename, FileType fileType) {
        ExportFile file = new ExportFile();
        file.setFilename(filename);
        file.setUpdateUser(custNo);
        file.setCreateUser(custNo);
        file.setStatus(ExportStatus.EXPORTING.getValue());
        file.setFileType(fileType.getValue());
        return exportFileDao.insert(file);
    }

    private class ExportTask<T> implements Runnable {

        private final ExportFile file;
        private final ExportProvider<T> provider;

        private ExportTask(ExportFile file, ExportProvider<T> provider) {
            this.file = file;
            this.provider = provider;
        }

        @Override
        public void run() {
            String filename = file.getFilename();
            //1.生成Excel
            File tmpFile;
            try {
                tmpFile = doExportFile(filename, provider);
            } catch (IOException e) {
                LOGGER.error("创建导出Excel文件异常：", e);
                file.setStatus(ExportStatus.FAIL.getValue());
                updateExportFile(file, null);
                return;
            }
            //3.读取这个临时文件，并将其上传至OSS
            OssFileInfo info = null;
            try {
                info = ossService.uploadMultiPartFile(CommonConstants.FILE_BUCKEN_NAME, filename, tmpFile.getAbsolutePath());
                file.setStatus(ExportStatus.SUCCESS.getValue());
            } catch (Exception e) {
                LOGGER.error("上传导出文件至OSS异常:", e);
                file.setStatus(ExportStatus.FAIL.getValue());
            } finally {
                //删除临时文件
                updateExportFile(file, info);
                try {
                    Files.deleteIfExists(tmpFile.toPath());
                } catch (IOException e) {
                    LOGGER.error("删除导出临时Excel文件异常:", e);
                }
            }
        }

        private void updateExportFile(ExportFile file, OssFileInfo info) {
            if (null != info) {
                file.setObjectId(info.getObjectId());
                file.setDownloadUrl(info.getDownloadUrl());
            }
            exportFileDao.update(file);
        }

        private File doExportFile(String filename, ExportProvider<T> provider) throws IOException {
            try (SXSSFWorkbook book = new SXSSFWorkbook(provider.getRowAccessWindowSize())) {
                SXSSFSheet sheet = book.createSheet();
                String[] columnHeaders = provider.getColumnHeaders();
                createHeader(sheet, columnHeaders);
                int rowNum = 1;
                while (provider.hasNext()) {
                    T data = provider.next();
                    SXSSFRow row = sheet.createRow(rowNum++);
                    for (int i = 0; i < columnHeaders.length; ++i) {
                        SXSSFCell cell = row.createCell(i);
                        Object cellValue = provider.getCellValue(data, rowNum, i);
                        setCellValue(cell, cellValue);
                    }
                }

                //2.将Excel写入另外一个临时文件中
                String folder = System.getProperty("java.io.tmpdir");
                LOGGER.debug("tmpDir = {}", folder);

                Path out = Files.createFile(Paths.get(folder, String.format("%s.%s", filename, "xlsx")));
                File tmpFile = out.toFile();
                FileOutputStream o = new FileOutputStream(tmpFile);
                book.write(o);
                o.close();
                book.dispose();

                return tmpFile;
            }
        }

        private void setCellValue(SXSSFCell cell, Object cellValue) {
            if (cellValue instanceof Boolean) {
                cell.setCellValue((Boolean) cellValue);
            } else if (cellValue instanceof Double) {
                cell.setCellValue((Double) cellValue);
            } else if (cellValue instanceof Date) {
                cell.setCellValue((Date) cellValue);
            } else if (cellValue instanceof Calendar) {
                cell.setCellValue((Calendar) cellValue);
            } else {
                cell.setCellValue(null == cellValue ? StringUtils.EMPTY : Objects.toString(cellValue));
            }
        }

        private void createHeader(SXSSFSheet sheet, String[] columnHeaders) {
            SXSSFRow header = sheet.createRow(0);
            for (int i = 0; i < columnHeaders.length; ++i) {
                SXSSFCell cell = header.createCell(i);
                cell.setCellValue(columnHeaders[i]);
            }
        }
    }
}
