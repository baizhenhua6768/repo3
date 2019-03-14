/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: UploadImportFileVo
 * Author:   88397670_张辉
 * Date:     2018-7-6 15:15
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.vo;

import java.io.InputStream;
import java.io.Serializable;

/**
 * 功能描述：上传文件传输对象
 *
 * @author 88397670_张辉
 * @since 2018-7-6
 */
public class UploadImportFileVo implements Serializable {

    private static final long serialVersionUID = -1444481916909898948L;

    private String fileName;
    private long size;
    private int type;
    private String ossUrl;
    private String contentType;
    private transient InputStream inputStream;
    private String custNo;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return String.format("UploadImportFileVo{filename='%s', size=%d, type=%d, ossUrl='%s'}", fileName, size, type, ossUrl);
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustNo() {
        return custNo;
    }
}
