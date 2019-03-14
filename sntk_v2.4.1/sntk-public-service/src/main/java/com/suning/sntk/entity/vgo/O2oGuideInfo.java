/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: O2oGuideInfo
 * Author:   88397670_张辉
 * Date:     2018-8-18 15:06
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
 * 功能描述：中台导购人员信息表
 *
 * @author 88397670_张辉
 * @since 2018-8-18
 */
@Entity(name = "o2o_v_guide_info")
public class O2oGuideInfo {

    /**
     * 表主键id
     */
    private Long id;

    /**
     * 工号
     */
    private String guideId;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 头像
     */
    private String guidePhoto;

    /**
     * 名字
     */
    private String guideName;

    /**
     * 星级
     */
    private String starLevel;

    /**
     * 电话
     */
    private String tele;

    /**
     * 介绍
     */
    private String introduction;

    /**
     * 是否开启
     */
    private String openFlag;

    /**
     * 品类
     */
    private String categoryName;

    /**
     * 接单数
     */
    private Long orderNum;
    /**
     * 销售年限-4.2版本
     */
    private Integer saleAge;

    private String businessCode;

    private String certificate;

    /**
     * 评分-4.7版本
     */
    private Double grade;

    /**
     * 维修点编码
     */
    private String outletCode;

    /**
     * V购标识 0不是，1是
     */
    private Integer isVgo;

    /**
     * 删除标识 0正常，1删除
     */
    private Integer deleteFlag;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 业态
     */
    private String businessType;


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

    @Column(name = "STAR_LEVEL")
    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    @Column(name = "TELE")
    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    @Column(name = "INTRODUCTION")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Column(name = "OPEN_FLAG")
    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    @Column(name = "CATEGORY_NAME")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Column(name = "ORDER_NUM")
    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    @Column(name = "SALE_AGE")
    public Integer getSaleAge() {
        return saleAge;
    }

    public void setSaleAge(Integer saleAge) {
        this.saleAge = saleAge;
    }

    @Column(name = "business_code")
    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    @Column(name = "certificate")
    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    /**
     * @return the grade
     */
    @Column(name = "grade")
    public Double getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Column(name = "outlet_code")
    public String getOutletCode() {
        return outletCode;
    }

    @Column(name = "IS_VGO")
    public Integer getIsVgo() {
        return isVgo;
    }

    public void setIsVgo(Integer isVgo) {
        this.isVgo = isVgo;
    }

    @Column(name = "DELETE_FLAG")
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    @Column(name = "BUSINESS_TYPE")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public void setOutletCode(String outletCode) {
        this.outletCode = outletCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
