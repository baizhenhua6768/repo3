/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreGuideReqDto
 * Author:   18041004_余长杰
 * Date:     2018/8/16 16:39
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：查询门店导购请求dto
 *
 * @author 18041004_余长杰
 * @since 2018/8/16
 */
public class StoreGuideReqDto implements Serializable{

    private static final long serialVersionUID = -3798163848368606636L;

    /**
     * 导购Id
     */
    private String guideId;

    /**
     * 市编码
     */
    private String cityId;

    /**
     * 区编码
     */
    private String districtId;

    /**
     * 目录编码
     */
    private String categoryId;

    /**
     * 商品编码
     */
    private String partNumber;

    /**
     * 会员编号
     */
    private String custNo;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 麦琪降级开关
     */
    private String matchSwitch;

    /**
     * scm中试点城市
     */
    private String testCity;

    /**
     * 导购提示标题
     */
    private String tipTitle;

    /**
     * 导购提示内容
     */
    private String tipContent;

    /**
     * 电器导购图标url
     */
    private String vgoGuideLableUrl;

    /**
     * 母婴导购图标url
     */
    private String infantGuideLabelUrl;

    /**
     * 业态
     */
    private String businessType;

    /**
     * 是否计算经纬度
     */
    private String caculateLonAndLati;

    /**
     * 最近的门店编码
     */
    private String nearestStoreCode;

    /**
     * 最近的门店编码列表
     */
    private List<String> nearestStoreCodeList;

    /**
     * 四级页进入卡片页标识
     */
    private boolean fourthPageMark;

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getMatchSwitch() {
        return matchSwitch;
    }

    public void setMatchSwitch(String matchSwitch) {
        this.matchSwitch = matchSwitch;
    }

    public String getTestCity() {
        return testCity;
    }

    public void setTestCity(String testCity) {
        this.testCity = testCity;
    }

    public String getNearestStoreCode() {
        return nearestStoreCode;
    }

    public void setNearestStoreCode(String nearestStoreCode) {
        this.nearestStoreCode = nearestStoreCode;
    }

    public List<String> getNearestStoreCodeList() {
        return nearestStoreCodeList;
    }

    public void setNearestStoreCodeList(List<String> nearestStoreCodeList) {
        this.nearestStoreCodeList = nearestStoreCodeList;
    }

    public boolean isFourthPageMark() {
        return fourthPageMark;
    }

    public void setFourthPageMark(boolean fourthPageMark) {
        this.fourthPageMark = fourthPageMark;
    }

    public String getTipTitle() {
        return tipTitle;
    }

    public void setTipTitle(String tipTitle) {
        this.tipTitle = tipTitle;
    }

    public String getTipContent() {
        return tipContent;
    }

    public void setTipContent(String tipContent) {
        this.tipContent = tipContent;
    }

    public String getVgoGuideLableUrl() {
        return vgoGuideLableUrl;
    }

    public void setVgoGuideLableUrl(String vgoGuideLableUrl) {
        this.vgoGuideLableUrl = vgoGuideLableUrl;
    }

    public String getInfantGuideLabelUrl() {
        return infantGuideLabelUrl;
    }

    public void setInfantGuideLabelUrl(String infantGuideLabelUrl) {
        this.infantGuideLabelUrl = infantGuideLabelUrl;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCaculateLonAndLati() {
        return caculateLonAndLati;
    }

    public void setCaculateLonAndLati(String caculateLonAndLati) {
        this.caculateLonAndLati = caculateLonAndLati;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
