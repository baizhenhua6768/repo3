/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideAuditInfo
 * Author:   88397670_张辉
 * Date:     2018-8-31 16:23
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.vgo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：导购审核表
 *
 * @author 88397670_张辉
 * @since 2018-8-31
 */
@Entity(name = "o2ob_v_guide_audit")
public class GuideAuditInfo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 导购工号
     */
    private String guideId;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 导购照片
     */
    private String guidePhoto;

    /**
     * 导购姓名
     */
    private String guideName;

    /**
     * 简介/个签
     */
    private String introduction;

    /**
     * 销售年限
     */
    private Integer saleAge;

    /**
     * v购标识 0不是，1是
     */
    private Integer isVgo;

    /**
     * 审核状态 0待审核，1通过，2驳回
     */
    private Integer auditFlag;

    /**
     * 驳回原因
     */
    private String auditReason;

    /**
     * 品类id，用逗号隔开
     */
    private String categoryIds;

    /**
     * 业态
     */
    private String businessType;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 审核时间
     */
    private Timestamp auditTime;

    /**
     * 审核人
     */
    private String auditPerson;

    /**
     * 导购员层级
     */
    private String hierarchy;

    /**
     * 导购员所属
     */
    private String attach;

    /**
     * 服务项目
     */
    private String serverItems;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "GUIDE_ID")
    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    @Column(name = "STORE_CODE")
    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    @Column(name = "GUIDE_PHOTO")
    public String getGuidePhoto() {
        return guidePhoto;
    }

    public void setGuidePhoto(String guidePhoto) {
        this.guidePhoto = guidePhoto;
    }

    @Column(name = "GUIDE_NAME")
    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    @Column(name = "INTRODUCTION")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Column(name = "SALE_AGE")
    public Integer getSaleAge() {
        return saleAge;
    }

    public void setSaleAge(Integer saleAge) {
        this.saleAge = saleAge;
    }

    @Column(name = "is_Vgo")
    public Integer getIsVgo() {
        return isVgo;
    }

    public void setIsVgo(Integer isVgo) {
        this.isVgo = isVgo;
    }


    @Column(name = "audit_flag")
    public Integer getAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(Integer auditFlag) {
        this.auditFlag = auditFlag;
    }

    @Column(name = "audit_reason")
    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    @Column(name = "category_ids")
    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    @Column(name = "business_type")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Column(name = "CREATE_TIME")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column(name = "CREATE_PERSON")
    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Column(name = "UPDATE_TIME")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "UPDATE_PERSON")
    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    @Column(name = "audit_TIME")
    public Timestamp getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
    }

    @Column(name = "audit_PERSON")
    public String getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }

    @Column(name = "hierarchy")
    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    @Column(name = "attach")
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @Column(name = "server_items")
    public String getServerItems() {
        return serverItems;
    }

    public void setServerItems(String serverItems) {
        this.serverItems = serverItems;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
