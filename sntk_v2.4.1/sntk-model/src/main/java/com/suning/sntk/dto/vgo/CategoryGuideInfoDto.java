/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreInfoDto
 * Author:   18041004_余长杰
 * Date:     2018/8/17 11:06
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
 * 功能描述：门店基本信息
 *
 * @author 18041004_余长杰
 * @since 2018/8/17
 */
public class CategoryGuideInfoDto implements Serializable{

    private static final long serialVersionUID = -1329531623224959798L;

    /**
     * 门店编码
     */
    private String storeCode = "";
    /**
     * 门店名称
     */
    private String storeName = "";
    /**
     * 门店地址
     */
    private String storeAddress = "";
    /**
     * 门店类型
     */
    private String storeType = "";
    /**
     * 门店经度
     */
    private String longitude = "";
    /**
     * 门店纬度
     */
    private String latitude = "";
    /**
     * 门店距离
     */
    private String storeFar = "";
    /**
     * 门店关注数
     */
    private String storeAttentionNum = "";
    /**
     * 是否关注过:1、关注的
     */
    private String isAttention = "";
    /**
     * 是否最热:1、最热
     */
    private String isMaxHot = "";
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
     * 金牌导购跳转的地址
     */
    private String weeklyTopGuiderUrl;
    /**
     * 销售经验
     */
    private String saleAge;


    /**
     * 是否导购预约
     */
    private int isGuide;

    /**
     * 是否支持下单
     */
    private int isOrder;

    /**
     * 品类
     */
    private String categoryName = "";

    /**
     * 品类编码
     */
    private String categoryCode;

    /**
     * 星级
     */
    private String starLevel = "";

    /**
     * 业务code：0为V购;
     */
    private String businessCode;

    /**
     * 门店v购标识，0未开启，1开启
     */
    private String vflag;

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

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
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

    public String getStoreFar() {
        return storeFar;
    }

    public void setStoreFar(String storeFar) {
        this.storeFar = storeFar;
    }

    public String getStoreAttentionNum() {
        return storeAttentionNum;
    }

    public void setStoreAttentionNum(String storeAttentionNum) {
        this.storeAttentionNum = storeAttentionNum;
    }

    public String getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(String isAttention) {
        this.isAttention = isAttention;
    }

    public String getIsMaxHot() {
        return isMaxHot;
    }

    public void setIsMaxHot(String isMaxHot) {
        this.isMaxHot = isMaxHot;
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

    public String getWeeklyTopGuiderUrl() {
        return weeklyTopGuiderUrl;
    }

    public void setWeeklyTopGuiderUrl(String weeklyTopGuiderUrl) {
        this.weeklyTopGuiderUrl = weeklyTopGuiderUrl;
    }

    public String getSaleAge() {
        return saleAge;
    }

    public void setSaleAge(String saleAge) {
        this.saleAge = saleAge;
    }

    public int getIsGuide() {
        return isGuide;
    }

    public void setIsGuide(int isGuide) {
        this.isGuide = isGuide;
    }

    public int getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(int isOrder) {
        this.isOrder = isOrder;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getVflag() {
        return vflag;
    }

    public void setVflag(String vflag) {
        this.vflag = vflag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
