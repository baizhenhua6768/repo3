/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideDto
 * Author:   88395115_史小配
 * Date:     2018/8/18 11:25
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
 * 功能描述：
 *
 * @author 88395115_史小配
 * @since 2018/8/18
 */
public class GuideDto implements Serializable{
    private static final long serialVersionUID = -1181314747308516526L;
    private String guideId="";

    private String guideName="";

    private String starLevel="";

    private String guidePhoto="";

    private String introduction;

    private String categoryName="";

    private String orderNum="";

    private String tele;

    private String storeCode="";

    private String storeName;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 销售年限
     */
    private String saleAge="";

    private String certificate;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;


    /**
     * 导购开关  是否开启 0：开启，1：不开启
     */
    private String guideOpenFlag ;

    /**
     * V购是否开启 0：关闭  1：开启
     */
    private String storeOpenFlag ;

    /**
     * 门店类型
     */
    private String stationType;

    private String businessCode;

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

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getGuidePhoto() {
        return guidePhoto;
    }

    public void setGuidePhoto(String guidePhoto) {
        this.guidePhoto = guidePhoto;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSaleAge() {
        return saleAge;
    }

    public void setSaleAge(String saleAge) {
        this.saleAge = saleAge;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
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

    public String getGuideOpenFlag() {
        return guideOpenFlag;
    }

    public void setGuideOpenFlag(String guideOpenFlag) {
        this.guideOpenFlag = guideOpenFlag;
    }

    public String getStoreOpenFlag() {
        return storeOpenFlag;
    }

    public void setStoreOpenFlag(String storeOpenFlag) {
        this.storeOpenFlag = storeOpenFlag;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
