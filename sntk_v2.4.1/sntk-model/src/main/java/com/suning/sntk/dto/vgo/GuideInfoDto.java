/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoDto
 * Author:   88396455_白振华
 * Date:     2018-8-16 14:33
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：导购信息
 *
 * @author 88396455_白振华
 * @since 2018-8-16
 */
public class GuideInfoDto implements Serializable {

    private static final long serialVersionUID = -9201648080498134260L;

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
     * 门店名称
     */
    private String storeName;

    /**
     * 大区名称
     */
    private String regionName;

    /**
     * 分公司名称
     */
    private String orgName;

    /**
     * 分公司名称
     */
    private String orgId;

    /**
     * 预约业务类型
     */
    private String businessCode;

    /**
     * 品类
     */
    private String categoryName;

    /**
     * 品类id以逗号分隔
     */
    private String categoryIds;

    /**
     * 服务项目以逗号分隔
     */
    private String serverName;

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
     * 擅长品类
     */
    private List<VcategoryRelInfoDto> vcategoryInfos = new ArrayList<VcategoryRelInfoDto>();

    /**
     * 服务项目列表形式
     */
    private List<String> serviceItems = new ArrayList<String>();

    /**
     * 是否开启 0：开启，1：不开启
     */
    private String guideOpenFlag;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 城市名称
     */
    private String cityName;

    /**
     * V购是否开启 0：关闭  1：开启
     */
    private String storeOpenFlag;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 门店类型
     */
    private String stationType;

    /**
     * 是否是V购 0-不是 1-是
     */
    private Integer isVgo;

    /**
     * 驳回原因
     */
    private String auditReason;

    /**
     * 驳回原因列表形式
     */
    private List<String> auditReasonList;

    /**
     * 擅长品类名称
     */
    private String categoryNames;

    /**
     * 服务项目名称
     */
    private String serverItemNames;

    /**
     * sntk业务类型
     */
    private String businessType;

    /**
     * 在职标记 ： 0:在职 1:离职
     */
    private String dimissionFlag;

    public List<String> getAuditReasonList() {
        return auditReasonList;
    }

    public void setAuditReasonList(List<String> auditReasonList) {
        this.auditReasonList = auditReasonList;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getGuidePhoto() {
        return guidePhoto;
    }

    public void setGuidePhoto(String guidePhoto) {
        this.guidePhoto = guidePhoto;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Long getOrderBasenum() {
        return orderBasenum;
    }

    public void setOrderBasenum(Long orderBasenum) {
        this.orderBasenum = orderBasenum;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    public Long getEvlaNum() {
        return evlaNum;
    }

    public void setEvlaNum(Long evlaNum) {
        this.evlaNum = evlaNum;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Integer getSaleAge() {
        return saleAge;
    }

    public void setSaleAge(Integer saleAge) {
        this.saleAge = saleAge;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getOutletCode() {
        return outletCode;
    }

    public void setOutletCode(String outletCode) {
        this.outletCode = outletCode;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public List<VcategoryRelInfoDto> getVcategoryInfos() {
        return vcategoryInfos;
    }

    public void setVcategoryInfos(List<VcategoryRelInfoDto> vcategoryInfos) {
        this.vcategoryInfos = vcategoryInfos;
    }

    public List<String> getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(List<String> serviceItems) {
        this.serviceItems = serviceItems;
    }

    public String getGuideOpenFlag() {
        return guideOpenFlag;
    }

    public void setGuideOpenFlag(String guideOpenFlag) {
        this.guideOpenFlag = guideOpenFlag;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStoreOpenFlag() {
        return storeOpenFlag;
    }

    public void setStoreOpenFlag(String storeOpenFlag) {
        this.storeOpenFlag = storeOpenFlag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public Integer getIsVgo() {
        return isVgo;
    }

    public void setIsVgo(Integer isVgo) {
        this.isVgo = isVgo;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public String getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(String categoryNames) {
        this.categoryNames = categoryNames;
    }

    public String getServerItemNames() {
        return serverItemNames;
    }

    public void setServerItemNames(String serverItemNames) {
        this.serverItemNames = serverItemNames;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getDimissionFlag() {
        return dimissionFlag;
    }

    public void setDimissionFlag(String dimissionFlag) {
        this.dimissionFlag = dimissionFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
