/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GeoStoreDto
 * Author:   17061157_王薛
 * Date:     2018/9/3 16:06
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.location;

/**
 * 功能描述：门店地理位置
 *
 * @author 17061157_王薛
 * @since 2018/9/3
 */
public class GeoStoreDto {

    // 门店编码
    private String storeCode;
    // 门店纬度
    private double lat;
    // 门店经度
    private double lng;
    // 门店名称
    private String storeName;
    // 6位geohash值
    private String geoHash6Code;

    public GeoStoreDto() {

    }

    public GeoStoreDto(String storeCode, double lat, double lng, String storeName
            , String geoHash6Code) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.lat = lat;
        this.lng = lng;
        this.geoHash6Code = geoHash6Code;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getGeoHash6Code() {
        return geoHash6Code;
    }

    public void setGeoHash6Code(String geoHash6Code) {
        this.geoHash6Code = geoHash6Code;
    }

    @Override
    public String toString() {
        return "GeoStore [storeCode=" + storeCode + ", lat=" + lat + ", lng=" + lng + ", storeName=" + storeName
                + ", geoHash6Code=" + geoHash6Code + "]";
    }

}
