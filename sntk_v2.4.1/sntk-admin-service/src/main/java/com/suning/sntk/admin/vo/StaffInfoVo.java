/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: UserVo
 * Author:   88397670_张辉
 * Date:     2018-7-3 15:30
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.vo;

/**
 * 功能描述：人员列表显示信息
 *
 * @author 88397670_张辉
 * @since 2018-7-3
 */
public class StaffInfoVo {

    /**
     * 唯一标识
     */
    private Long id;

    /**
     * 员工号
     */
    private String staffId;

    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 大区名
     */
    private String areaName;

    /**
     * 分公司名
     */
    private String companyName;

    /**
     * 门店名
     */
    private String storeName;

    /**
     * 岗位
     */
    private String station;

    /**
     * 启用标志 1-启用 2-禁用 3-错误
     */
    private Integer validFlag;

    /**
     * 导入错误标志
     */
    private Integer errorFlag;

    public StaffInfoVo() {
        //默认构造器
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    public Integer getErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(Integer errorFlag) {
        this.errorFlag = errorFlag;
    }
}
