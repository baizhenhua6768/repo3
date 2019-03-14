/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CustomerInfo
 * Author:   18032490_赵亚奇
 * Date:     2018/10/30 16:28
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.vgo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import com.suning.framework.dal.parsing.support.annotation.TableRoute;

/**
 * 顾客资料实体类(分表)
 *
 * @author 18032490_赵亚奇
 * @since 2018/10/30
 */
@TableRoute(tableName = "${hash('cr_customer_info_',storeCode,5)}")
public class CustomerInfo {

    private Long id;// 主键id
    private String unionId;//公众平台客户标识
    private String custNo;//'会员编码',
    private String staffId;// '关系店员工号',
    private String storeCode;// '门店编码',
    private String sex;//性别 1:男, 2:女',
    private String memberName;// '会员姓名',
    private String remarkName;// '备注姓名',
    private Integer mobileFlag;//手机标识 1:会员手机,0:非会员手机号',
    private String contactPhone;// '联系电话',
    private String remarkPhone;// '备注手机号',
    private String contactEncrption;// '联系电话加密',
    private String customizedLabel;//'自定义标签(多个以#分割)',
    private String purchaseTime;//'预计购买时间',
    private String purchaseRemark;//'预计购买备注',
    private Integer infoComplete;//信息完善标志 0:不完善, 1:已完善',
    private Date createTime;// '创建时间',
    private String creater;//'创建人',
    private Date updateTime;// '更新时间',
    private String updater;//'更新人',

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "union_id")
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Column(name = "cust_no")
    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    @Column(name = "staff_id")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "store_code")
    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "member_name")
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Column(name = "remark_name")
    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    @Column(name = "mobile_flag")
    public Integer getMobileFlag() {
        return mobileFlag;
    }

    public void setMobileFlag(Integer mobileFlag) {
        this.mobileFlag = mobileFlag;
    }

    @Column(name = "contact_phone")
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Column(name = "remark_phone")
    public String getRemarkPhone() {
        return remarkPhone;
    }

    public void setRemarkPhone(String remarkPhone) {
        this.remarkPhone = remarkPhone;
    }

    @Column(name = "contact_encrption")
    public String getContactEncrption() {
        return contactEncrption;
    }

    public void setContactEncrption(String contactEncrption) {
        this.contactEncrption = contactEncrption;
    }

    @Column(name = "customized_label")
    public String getCustomizedLabel() {
        return customizedLabel;
    }

    public void setCustomizedLabel(String customizedLabel) {
        this.customizedLabel = customizedLabel;
    }

    @Column(name = "purchase_time")
    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    @Column(name = "purchase_remark")
    public String getPurchaseRemark() {
        return purchaseRemark;
    }

    public void setPurchaseRemark(String purchaseRemark) {
        this.purchaseRemark = purchaseRemark;
    }

    @Column(name = "info_complete")
    public Integer getInfoComplete() {
        return infoComplete;
    }

    public void setInfoComplete(Integer infoComplete) {
        this.infoComplete = infoComplete;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "creater")
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "updater")
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Override
    public String toString() {
        return "CustomerInfo{" +
                "id=" + id +
                ", unionId='" + unionId + '\'' +
                ", custNo='" + custNo + '\'' +
                ", staffId='" + staffId + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", sex='" + sex + '\'' +
                ", memberName='" + memberName + '\'' +
                ", remarkName='" + remarkName + '\'' +
                ", mobileFlag=" + mobileFlag +
                ", contactPhone='" + contactPhone + '\'' +
                ", remarkPhone='" + remarkPhone + '\'' +
                ", contactEncrption='" + contactEncrption + '\'' +
                ", customizedLabel='" + customizedLabel + '\'' +
                ", purchaseTime='" + purchaseTime + '\'' +
                ", purchaseRemark='" + purchaseRemark + '\'' +
                ", infoComplete=" + infoComplete +
                ", createTime=" + createTime +
                ", creater='" + creater + '\'' +
                ", updateTime=" + updateTime +
                ", updater='" + updater + '\'' +
                '}';
    }
}
