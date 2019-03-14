/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: Staff
 * Author:   18032490_赵亚奇
 * Date:     2018/7/3 17:17
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.staffwhitelist;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author 18032490_赵亚奇
 * @since 2018/7/3
 */
@Entity(name = "staff_white_list")
public class Staff implements Serializable {
    
    private static final long serialVersionUID = 1L;
    /**
     * 自增id
     */
    private Long id;
    /**
     * 员工工号
     */
    private String staffId;
    /**
     * '员工姓名'
     */
    private String staffName;
    /**
     * '大区编码'
     */
    private String areaCode;
    /**
     * '大区名称'
     */
    private String areaName;
    /**
     * '分公司编码'
     */
    private String companyCode;
    /**
     * '分公司名称'
     */
    private String companyName;
    /**
     * '门店编码'
     */
    private String storeCode;
    /**
     * '门店名称'
     */
    private String storeName;
    /**
     * 岗位
     */
    private String station;
    /**
     * 启用标志 1:启用,2:禁用
     */
    private Integer validFlag;
    /**
     * 删除标志 1:未删除,2:离职删除
     */
    private Integer deleteFalg1;
    /**
     * 校验标志 1:导入数据错误,0：数据正确
     */
    private Integer errorFalg;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updater;
    /**
     * 更新时间
     */
    private Date updateTime;

    @Column(name = "id")
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "staff_id")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "staff_name")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Column(name = "area_code")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Column(name = "area_name")
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Column(name = "company_code")
    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "store_code")
    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    @Column(name = "store_name")
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Column(name = "station")
    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Column(name = "valid_flag")
    public Integer getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    @Column(name = "delete_falg")
    public Integer getDeleteFalg1() {
        return deleteFalg1;
    }

    public void setDeleteFalg1(Integer deleteFalg1) {
        this.deleteFalg1 = deleteFalg1;
    }

    @Column(name = "error_falg")
    public Integer getErrorFalg() {
        return errorFalg;
    }

    public void setErrorFalg(Integer errorFalg) {
        this.errorFalg = errorFalg;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "CREATER")
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "UPDATER")
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Column(name = "UPDATE_TIME")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", staffId='" + staffId + '\'' +
                ", staffName='" + staffName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", storeName='" + storeName + '\'' +
                ", station='" + station + '\'' +
                ", validFlag=" + validFlag +
                ", deleteFalg1=" + deleteFalg1 +
                ", errorFalg=" + errorFalg +
                ", remark='" + remark + '\'' +
                ", creater='" + creater + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updater='" + updater + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
