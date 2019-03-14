/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerDto
 * Author:   17061157_王薛
 * Date:     2018/7/7 17:20
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.relation;

import java.io.Serializable;

/**
 * 功能描述：客户信息
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = -2024466335426318368L;

    private Long id;

    // 顾问工号
    private String staffId;

    // 顾问姓名
    private String staffName;

    // 大区编码
    private String areaCode;

    // 大区名称
    private String areaName;

    // 分公司编码
    private String companyCode;

    // 分公司名称
    private String companyName;

    // 门店编码
    private String storeCode;

    // 门店名称
    private String storeName;

    // 公众平台客户唯一编号
    private String unionId;

    // 会员手机标识 1:会员手机, 0:非会员手机号
    private int mobileFlag;

    // 会员编码
    private String customerNo;

    // 备注姓名
    private String remarkName;

    // 顾客性别 1:男, 2:女
    private String sex;

    // 客户备注手机号
    private String remarkPhone;

    // 渠道类型, 0:导购推广码, 1:易购APP微服务, 2:预售, 3:预存
    private String channelType;

    // 关系建立时间
    private String relationTime;

    // 最后扫码时间
    private String lastScanTime;

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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public int getMobileFlag() {
        return mobileFlag;
    }

    public void setMobileFlag(int mobileFlag) {
        this.mobileFlag = mobileFlag;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemarkPhone() {
        return remarkPhone;
    }

    public void setRemarkPhone(String remarkPhone) {
        this.remarkPhone = remarkPhone;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getRelationTime() {
        return relationTime;
    }

    public void setRelationTime(String relationTime) {
        this.relationTime = relationTime;
    }

    public String getLastScanTime() {
        return lastScanTime;
    }

    public void setLastScanTime(String lastScanTime) {
        this.lastScanTime = lastScanTime;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", staffId='" + staffId + '\'' +
                ", staffName='" + staffName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", storeName='" + storeName + '\'' +
                ", unionId='" + unionId + '\'' +
                ", mobileFlag=" + mobileFlag +
                ", customerNo='" + customerNo + '\'' +
                ", remarkName='" + remarkName + '\'' +
                ", sex='" + sex + '\'' +
                ", remarkPhone='" + remarkPhone + '\'' +
                ", channelType='" + channelType + '\'' +
                ", relationTime='" + relationTime + '\'' +
                ", lastScanTime='" + lastScanTime + '\'' +
                '}';
    }
}
