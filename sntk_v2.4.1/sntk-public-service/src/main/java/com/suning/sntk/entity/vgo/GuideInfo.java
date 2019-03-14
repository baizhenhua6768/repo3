/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfo
 * Author:   88396455_白振华
 * Date:     2018-8-16 10:15
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.vgo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：后台导购信息实体
 *
 * @author 88396455_白振华
 * @since 2018-8-16
 */
@Entity(name = "o2ob_v_guide_info")
public class GuideInfo implements Serializable {

    private static final long serialVersionUID = -8500361026866298728L;
    /**
     * 表主键id
     */
    private Long id;

    /**
     * 导购人员id
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
     * 接单数
     */
    private Long orderNum;

    /**
     * 接单基数
     */
    private Long orderBasenum;

    /**
     * 星级
     */
    private String starLevel;

    /**
     * 手机号码
     */
    private String tele;

    /**
     * 所属门店地址
     */
    private String address;

    /**
     * 介绍
     */
    private String introduction;

    /**
     * 是否开启
     */
    private String openFlag;

    /**
     * 评价数
     */
    private Long evlaNum;

    /**
     * 预约业务类型
     */
    private String businessCode;

    /**
     * 证书
     */
    private String certificate;

    /**
     * 销售年限-4.2版本
     */
    private Integer saleAge;

    /**
     * 评分-4.7版本
     */
    private Double grade;

    /**
     * 导购员层级
     */
    private String hierarchy;

    /**
     * 维修点编码
     */
    private String outletCode;

    /**
     * 导购员所属
     */
    private String attach;

    /**
     * V购标识 0不是，1是
     */
    private Integer isVgo;

    /**
     * 业态
     */
    private String businessType;

    /**
     * 删除标识 0正常，1删除
     */
    private Integer deleteFlag;

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
     * 0:在职 1:离职
     */
    private String dimissionFlag;

    /**
     * 是否来自moss 0-不是，1-是
     */
    private Integer isFromMoss;

    /**
     * 图片更新完成0-否，1-是
     */
    private Integer isCompeletePicChange;

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

    @Column(name = "GUIDE_NAME")
    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    @Column(name = "GUIDE_PHOTO")
    public String getGuidePhoto() {
        return guidePhoto;
    }

    public void setGuidePhoto(String guidePhoto) {
        this.guidePhoto = guidePhoto;
    }

    @Column(name = "ORDER_NUM")
    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    @Column(name = "ORDER_BASENUM")
    public Long getOrderBasenum() {
        return orderBasenum;
    }

    public void setOrderBasenum(Long orderBasenum) {
        this.orderBasenum = orderBasenum;
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

    @Column(name = "EVLA_NUM")
    public Long getEvlaNum() {
        return evlaNum;
    }

    public void setEvlaNum(Long evlaNum) {
        this.evlaNum = evlaNum;
    }

    @Column(name = "SALE_AGE")
    public Integer getSaleAge() {
        return saleAge;
    }

    public void setSaleAge(Integer saleAge) {
        this.saleAge = saleAge;
    }

    @Column(name = "BUSINESS_CODE")
    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    @Column(name = "CERTIFICATE")
    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    @Column(name = "GRADE")
    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Column(name = "HIERARCHY")
    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    @Column(name = "ATTACH")
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @Column(name = "OUTLET_CODE")
    public String getOutletCode() {
        return outletCode;
    }

    public void setOutletCode(String outletCode) {
        this.outletCode = outletCode;
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

    @Column(name = "dimission_flag")
    public String getDimissionFlag() {
        return dimissionFlag;
    }

    public void setDimissionFlag(String dimissionFlag) {
        this.dimissionFlag = dimissionFlag;
    }

    @Column(name = "is_from_moss")
    public Integer getIsFromMoss() {
        return isFromMoss;
    }

    public void setIsFromMoss(Integer isFromMoss) {
        this.isFromMoss = isFromMoss;
    }

    @Column(name = "is_complete_pic_change")
    public Integer getIsCompeletePicChange() {
        return isCompeletePicChange;
    }

    public void setIsCompeletePicChange(Integer isCompeletePicChange) {
        this.isCompeletePicChange = isCompeletePicChange;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
