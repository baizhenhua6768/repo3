/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ImportFile
 * Author:   88397670_张辉
 * Date:     2018-7-6 14:59
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 功能描述：导入文件信息表
 *
 * @author 88397670_张辉
 * @since 2018-7-6
 */
@Entity(name = "import_file")
public class ImportFile {

    /**
     * 自增主键
     */
    private Long id;
    /**
     * 导入文件名称
     */
    private String fileName;

    /**
     * 导入数据类型：1.人员管理后台导入
     */
    private Integer fileType;

    /**
     * 导入状态： -1 失败，0-待处理，1-成功
     */
    private Integer status;
    /**
     * 下载地址
     */
    private String downloadUrl;
    /**
     * OSS存储的导入文件对象Id，用于获取文件输入流
     */
    private String objectId;
    /**
     * 文件大小
     */
    private Long size;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "file_type")
    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "download_url")
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Column(name = "object_id")
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Column(name = "size")
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Column(name = "create_user")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_user")
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
