/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AppointInitQueryRespDto
 * Author:   18010645_黄成
 * Date:     2018/8/31 15:26
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述：预约页初始化查询返回dto
 *
 * @author 18010645_黄成
 * @since 2018/8/31
 */
public class AppointInitQueryRespDto implements Serializable {
    private static final long serialVersionUID = 2123730034166700254L;

    //导购ID
    private String guideId;
    //导购姓名
    private String guideName;
    //导购头像
    private String guidePhoto;
    //星级
    private String starLevel;
    //星级评分
    private String starGrade;
    //业态
    private String businessType;
    //门店code
    private String storeCode;
    //门店名称
    private String storeName;
    //门店短名称
    private String storeShortName;
    //门店地址
    private String storeAddress;
    // 服务器初始化时间(yyyy-mm-dd hh:mm:ss)
    private String initTime;
    //会员手机号码
    private String customerMobile;

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

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getInitTime() {
        return initTime;
    }

    public void setInitTime(String initTime) {
        this.initTime = initTime;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    @Override
    public String toString() {
        return "AppointInitQueryRespDto{" +
                "guideId='" + guideId + '\'' +
                ", guideName='" + guideName + '\'' +
                ", guidePhoto='" + guidePhoto + '\'' +
                ", starLevel='" + starLevel + '\'' +
                ", starGrade='" + starGrade + '\'' +
                ", businessType='" + businessType + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeShortName='" + storeShortName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", initTime='" + initTime + '\'' +
                ", customerMobile='" + customerMobile + '\'' +
                '}';
    }
}
