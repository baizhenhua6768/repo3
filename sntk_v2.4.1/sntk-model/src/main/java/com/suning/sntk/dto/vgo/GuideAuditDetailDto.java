/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideAuditDetailDto
 * Author:   88396455_白振华
 * Date:     2018-9-3 9:14
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
 * 功能描述：审核导购详细信息
 *
 * @author 88396455_白振华
 * @since 2018-9-3
 */
public class GuideAuditDetailDto implements Serializable {

    private static final long serialVersionUID = 6361036235620735836L;

    /**
     * 审核记录id
     */
    private Long id;

    /**
     * 导购工号
     */
    private String guideId;

    /**
     * 导购姓名
     */
    private String guideName;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 分公司名称
     */
    private String orgName;

    /**
     * 分公司编码
     */
    private String orgId;

    /**
     * 大区名称
     */
    private String regionName;

    /**
     * 联系方式
     */
    private String tele;

    /**
     * 导购头像
     */
    private String guidePhoto;

    /**
     * 层级
     */
    private String hierarchy;

    /**
     * 所属
     */
    private String attach;

    /**
     * 擅长品类
     */
    private List<VcategoryRelInfoDto> vcategoryInfos = new ArrayList<VcategoryRelInfoDto>();

    /**
     * 服务项目
     */
    private List<String> serviceItems = new ArrayList<String>();

    /**
     * 业态
     */
    private String businessType;

    /**
     * 销售年限
     */
    private Integer saleAge;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 是否是V购 0不是，1是
     */
    private Integer isVgo;

    /**
     * 擅长品类名称
     */
    private String categoryNames;

    /**
     * 服务项目名称
     */
    private String serverItemNames;

    /**
     * 擅长品类编码
     */
    private String categoryIds;

    /**
     * 服务项目
     */
    private String serverItems;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getGuidePhoto() {
        return guidePhoto;
    }

    public void setGuidePhoto(String guidePhoto) {
        this.guidePhoto = guidePhoto;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getSaleAge() {
        return saleAge;
    }

    public void setSaleAge(Integer saleAge) {
        this.saleAge = saleAge;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getIsVgo() {
        return isVgo;
    }

    public void setIsVgo(Integer isVgo) {
        this.isVgo = isVgo;
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

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

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
