/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerAdvisor
 * Author:   17061157_王薛
 * Date:     2018/7/7 12:12
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.suning.sntk.entity.BaseEntity2;

/**
 * 功能描述：客户专业顾问信息
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@Entity(name = "mb_customer_advisor")
public class CustomerAdvisor extends BaseEntity2 {

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

    // 客户经理标识 1:是,0:否
    private int managerFlag;

    // 关系建立时间
    private String relationTime;

    // 最后扫码时间
    private String lastScanTime;

    //会员名称
    private String memberName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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

    @Column(name = "union_id")
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Column(name = "mobile_flag")
    public int getMobileFlag() {
        return mobileFlag;
    }

    public void setMobileFlag(int mobileFlag) {
        this.mobileFlag = mobileFlag;
    }

    @Column(name = "customer_no")
    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    @Column(name = "remark_name")
    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "remark_phone")
    public String getRemarkPhone() {
        return remarkPhone;
    }

    public void setRemarkPhone(String remarkPhone) {
        this.remarkPhone = remarkPhone;
    }

    @Column(name = "channel_type")
    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    @Column(name = "relation_time")
    public String getRelationTime() {
        return relationTime;
    }

    public void setRelationTime(String relationTime) {
        this.relationTime = relationTime;
    }

    @Column(name = "manager_flag")
    public int getManagerFlag() {
        return managerFlag;
    }

    public void setManagerFlag(int managerFlag) {
        this.managerFlag = managerFlag;
    }

    @Column(name = "last_scan_time")
    public String getLastScanTime() {
        return lastScanTime;
    }

    public void setLastScanTime(String lastScanTime) {
        this.lastScanTime = lastScanTime;
    }

    @Column(name = "member_name")
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
