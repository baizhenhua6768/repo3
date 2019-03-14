/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: JobRecordEntity
 * Author:   88397670_张辉
 * Date:     2018-9-20 10:39
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 功能描述：job执行记录表
 *
 * @author 88397670_张辉
 * @since 2018-9-20
 */
@Entity(name = "o2o_job_record")
public class JobRecordEntity {

    /**
     * 表主键
     */
    private Long id;

    /**
     * job描述
     */
    private String description;

    /**
     * 任务开始时间
     */
    private Date beginTime;

    /**
     * 任务结束时间
     */
    private Date endTime;

    /**
     * 任务总耗时（单位：秒）
     */
    private Long totalTime;

    /**
     * job执行是否成功
     */
    private Boolean isSuccess;

    /**
     * 错误
     */
    private String errorMessage;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "BEGIN_TIME")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Column(name = "END_TIME")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "TOTAL_TIME")
    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    @Column(name = "IS_SUCCESS")
    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    @Column(name = "ERROR_MESSAGE")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
