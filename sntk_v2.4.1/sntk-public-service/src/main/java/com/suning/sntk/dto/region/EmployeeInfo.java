/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: EmployeeInfo
 * Author:   88396455_白振华
 * Date:     2018-7-2 11:40
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.region;

import java.io.Serializable;
import java.security.Principal;

/**
 * 功能描述：人员信息
 *
 * @author 88396455_白振华
 * @since 2018-7-2
 */
public class EmployeeInfo implements Principal, Serializable {

    private static final long serialVersionUID = -4448948084400816871L;
    /**
     * 工号
     */
    private String staffId;

    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 生成菜单的时间
     */
    private String timestamp;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 大区编码
     */
    private String regionCode;

    /**
     * 大区5位编码
     */
    private String region5Code;

    /**
     * 大区名称
     */
    private String regionName;

    /**
     * 岗位编码
     */
    private String positionId;

    /**
     * 岗位编码
     */
    private String positionName;

    /**
     * 组织编码
     */
    private String orgCode;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 组织级别 0 总部 1分公司 4 门店
     */
    private String orgLevel;

    /**
     * 分公司编码(4位)
     */
    private String branchCode;

    /**
     * 分公司名称
     */
    private String branchName;

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String getName() {
        return getStaffId();
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getRegion5Code() {
        return region5Code;
    }

    public void setRegion5Code(String region5Code) {
        this.region5Code = region5Code;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EmployeeInfo [staffId=");
        builder.append(staffId);
        builder.append(", staffName=");
        builder.append(staffName);
        builder.append(", timestamp=");
        builder.append(timestamp);
        builder.append(", storeCode=");
        builder.append(storeCode);
        builder.append(", storeName=");
        builder.append(storeName);
        builder.append(", regionCode=");
        builder.append(regionCode);
        builder.append(", region5Code=");
        builder.append(region5Code);
        builder.append(", regionName=");
        builder.append(regionName);
        builder.append(", positionId=");
        builder.append(positionId);
        builder.append(", positionName=");
        builder.append(positionName);
        builder.append(", orgCode=");
        builder.append(orgCode);
        builder.append(", orgName=");
        builder.append(orgName);
        builder.append(", orgLevel=");
        builder.append(orgLevel);
        builder.append(", branchCode=");
        builder.append(branchCode);
        builder.append(", branchName=");
        builder.append(branchName);
        builder.append("]");
        return builder.toString();
    }
}
