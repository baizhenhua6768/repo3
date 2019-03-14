/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: StoreGeoDto
 * Author:   17061157_王薛
 * Date:     2018/9/22 11:32
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.common;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 功能描述：门店经纬度 DTO
 *
 * @author 17061157_王薛
 * @since 2018/9/22
 */
public class StoreGeoDto implements Serializable {

    private static final long serialVersionUID = -1;

    private String storeCode;

    private String storeName;

    // 门店纬度
    private double latitude;

    // 门店经度
    private double longitude;

    // 业态
    private String businessType;

    // 6位geohash值
    private String geoHash6Code;

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getGeoHash6Code() {
        return geoHash6Code;
    }

    public void setGeoHash6Code(String geoHash6Code) {
        this.geoHash6Code = geoHash6Code;
    }
    
    public void copy(StoreGeoDto newObj) {
        this.storeCode = newObj.getStoreCode();
        this.storeName = newObj.getStoreName();
        this.latitude = newObj.getLatitude();
        this.longitude = newObj.getLongitude();
        this.businessType = newObj.getBusinessType();
        this.geoHash6Code = newObj.getGeoHash6Code();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("storeCode", storeCode)
                .append("storeName", storeName)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("businessType", businessType)
                .append("geoHash6Code", geoHash6Code)
                .toString();
    }
}
