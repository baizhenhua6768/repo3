/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreInfo
 * Author:   18032490_赵亚奇
 * Date:     2018/7/9 16:16
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.staffwhitelist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 门店信息
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/9
 */
@Entity(name = "store_info")
public class StoreInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键，8位门店编码
     */
    private String storeId;
    /**
     * 4位门店编码
     */
    private String storeCode;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 门店地址
     */
    private String storeAddress;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 业态(1：电器, 2:母婴, 3:超市 , 4:广场 , 5:体育 , 6:极物, 99:其他)
     */
    private String businessType;

    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新人
     */
    private String updater;
    /**
     * 更新时间
     */
    private String updateTime;

    public StoreInfo() {

    }

    public StoreInfo(String storeCode, String latitude, String longitude, String businessType) {
        this.storeCode = storeCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.businessType = businessType;
    }

    public StoreInfo(String storeCode, String latitude, String longitude, String storeName, String businessType) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.businessType = businessType;
    }

    @Id
    @Column(name = "store_id")
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Column(name = "store_code")
    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    @Column(name = "store_name")
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Column(name = "store_address")
    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    @Column(name = "CREATER")
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Column(name = "CREATE_TIME")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Column(name = "UPDATER")
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Column(name = "UPDATE_TIME")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "LONGITUDE")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Column(name = "LATITUDE")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Column(name = "BUSINESS_TYPE")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
