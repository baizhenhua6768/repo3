/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideDetailDto
 * Author:   18010645_黄成
 * Date:     2018/9/6 9:14
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述：店员主页导购详情
 *
 * @author 18010645_黄成
 * @since 2018/9/6
 */
public class GuideDetailDto implements Serializable {

    private static final long serialVersionUID = -8903833959609151668L;
    /**
     * 金牌导购工号
     */
    private String guideId;
    /**
     * 金牌导购名字
     */
    private String guideName;
    /**
     * 金牌导购头像
     */
    private String guidePhoto;
    /**
     * 金牌导购接单数
     */
    private String orderNum;
    /**
     * 星级
     */
    private String starLevel = "";
    /**
     * 星级分数
     */
    private String starGrade = "";
    /**
     * 擅长品类
     */
    private String categoryName = "";
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 销售年限
     */
    private String saleAge;
    /**
     * 获赞数
     */
    private String receivePraise;
    /**
     * 门店编码
     */
    private String storeCode = "";

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 门店短名称
     */
    private String storeShortName;
    /**
     * 当前时间
     */
    private String currentTime;
    /**
     * 业态
     */
    private String businessType;
    /**
     * 是否是客户经理:1:是；0：不是
     */
    private String customerManagerFlag;

    //门店地址
    private String storeAddress;

    /**
     * 门店经度
     */
    private String longitude;

    /**
     * 门店纬度
     */
    private String latitude;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStarGrade() {
        return starGrade;
    }

    public void setStarGrade(String starGrade) {
        this.starGrade = starGrade;
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

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSaleAge() {
        return saleAge;
    }

    public void setSaleAge(String saleAge) {
        this.saleAge = saleAge;
    }

    public String getReceivePraise() {
        return receivePraise;
    }

    public void setReceivePraise(String receivePraise) {
        this.receivePraise = receivePraise;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreShortName() {
        return storeShortName;
    }

    public void setStoreShortName(String storeShortName) {
        this.storeShortName = storeShortName;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCustomerManagerFlag() {
        return customerManagerFlag;
    }

    public void setCustomerManagerFlag(String customerManagerFlag) {
        this.customerManagerFlag = customerManagerFlag;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    @Override
    public String toString() {
        return "GuideDetailDto{" +
                "guideId='" + guideId + '\'' +
                ", guideName='" + guideName + '\'' +
                ", guidePhoto='" + guidePhoto + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", starLevel='" + starLevel + '\'' +
                ", starGrade='" + starGrade + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", saleAge='" + saleAge + '\'' +
                ", receivePraise='" + receivePraise + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeShortName='" + storeShortName + '\'' +
                ", currentTime='" + currentTime + '\'' +
                ", businessType='" + businessType + '\'' +
                ", customerManagerFlag='" + customerManagerFlag + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
