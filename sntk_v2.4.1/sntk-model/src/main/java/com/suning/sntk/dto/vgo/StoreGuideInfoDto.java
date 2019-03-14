/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreGuideInfoDto
 * Author:   18041004_余长杰
 * Date:     2018/8/16 16:36
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：门店及导购信息
 *
 * @author 18041004_余长杰
 * @since 2018/8/16
 */
public class StoreGuideInfoDto implements Serializable {

    private static final long serialVersionUID = -8270103379052061176L;

    /**
     * 路由标记,是否路由到sntk,0：否 1：是
     */
    private String isRouteToSntk;

    /**
     * 是否是客户经理标记,0：否 1：是
     */
    private String isCustManager;

    /**
     * 导购提示标题
     */
    private String tipTitle;

    /**
     * 导购提示内容
     */
    private String tipContent;
    /**
     * 门店编码
     */
    private String storeCode = "";
    /**
     * 门店名称
     */
    private String storeName = "";

    /**
     * 门店短名称
     */
    private String storeShortName;

    /**
     * 门店地址
     */
    private String storeAddress = "";

    /**
     * 门店经度
     */
    private String storeLongitude;

    /**
     * 门店纬度
     */
    private String storeLatitude;

    /**
     * 金牌导购名字
     */
    private String guideName;
    /**
     * 金牌导购工号
     */
    private String guideId;
    /**
     * 金牌导购头像
     */
    private String guidePhoto;
    /**
     * 金牌导购接单数
     */
    private String orderNum;

    /**
     * 获赞数
     */
    private String receivePraise;
    /**
     * 销售年限
     */
    private String saleAge;

    /**
     * 擅长品类
     */
    private String categoryName = "";

    /**
     * 擅长品类Id
     */
    private String categoryId;

    /**
     * 星级
     */
    private String starLevel = "";

    /**
     * 评分
     */
    private String starGrade;

    /**
     * 拓客导购跳转路径
     */
    private String sntkGuideUrl;

    /**
     * 导购图标url
     */
    private String guideLableUrl;

    /**
     * 业态
     */
    private String businessType;

    /**
     * 个性签名
     */
    private String introduction;

    /**
     * 是否在职：0:在职 1:离职
     */
    private String dimissionFlag = "0";

    public String getIsRouteToSntk() {
        return isRouteToSntk;
    }

    public void setIsRouteToSntk(String isRouteToSntk) {
        this.isRouteToSntk = isRouteToSntk;
    }

    public String getIsCustManager() {
        return isCustManager;
    }

    public void setIsCustManager(String isCustManager) {
        this.isCustManager = isCustManager;
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

    public String getStoreShortName() {
        return storeShortName;
    }

    public void setStoreShortName(String storeShortName) {
        this.storeShortName = storeShortName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(String storeLongitude) {
        this.storeLongitude = storeLongitude;
    }

    public String getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(String storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getGuidePhoto() {
        return guidePhoto;
    }

    public void setGuidePhoto(String guidePhoto) {
        this.guidePhoto = guidePhoto;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getReceivePraise() {
        return receivePraise;
    }

    public void setReceivePraise(String receivePraise) {
        this.receivePraise = receivePraise;
    }

    public String getSaleAge() {
        return saleAge;
    }

    public void setSaleAge(String saleAge) {
        this.saleAge = saleAge;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getStarGrade() {
        return starGrade;
    }

    public void setStarGrade(String starGrade) {
        this.starGrade = starGrade;
    }

    public String getSntkGuideUrl() {
        return sntkGuideUrl;
    }

    public void setSntkGuideUrl(String sntkGuideUrl) {
        this.sntkGuideUrl = sntkGuideUrl;
    }

    public String getGuideLableUrl() {
        return guideLableUrl;
    }

    public void setGuideLableUrl(String guideLableUrl) {
        this.guideLableUrl = guideLableUrl;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
