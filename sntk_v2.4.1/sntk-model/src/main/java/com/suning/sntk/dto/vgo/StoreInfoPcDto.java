/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreInfoPcDto
 * Author:   18010645_黄成
 * Date:     2018/9/4 11:13
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述：pc端预约页展示：门店信息
 *
 * @author 18010645_黄成
 * @since 2018/9/4
 */
public class StoreInfoPcDto implements Serializable {
    private static final long serialVersionUID = 7044603538375201690L;

    /**
     * 门店编码
     */
    private String storeCode;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 门店名称
     */
    private String name;
    /**
     * 门店地址
     */
    private String address;

    /**
     * 周中营业起始时间
     */
    private String workdayBeginTime;

    /**
     * 周中营业截止时间
     */
    private String workdayEndTime;

    /**
     * 周末营业起始时间
     */
    private String weekendBeginTime;

    /**
     * 周末营业截止时间
     */
    private String weekendEndTime;

    /**
     * 门店经度
     */
    private String longitude;

    /**
     * 门店纬度
     */
    private String latitude;

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkdayBeginTime() {
        return workdayBeginTime;
    }

    public void setWorkdayBeginTime(String workdayBeginTime) {
        this.workdayBeginTime = workdayBeginTime;
    }

    public String getWorkdayEndTime() {
        return workdayEndTime;
    }

    public void setWorkdayEndTime(String workdayEndTime) {
        this.workdayEndTime = workdayEndTime;
    }

    public String getWeekendBeginTime() {
        return weekendBeginTime;
    }

    public void setWeekendBeginTime(String weekendBeginTime) {
        this.weekendBeginTime = weekendBeginTime;
    }

    public String getWeekendEndTime() {
        return weekendEndTime;
    }

    public void setWeekendEndTime(String weekendEndTime) {
        this.weekendEndTime = weekendEndTime;
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

    @Override
    public String toString() {
        return "StoreInfoPcDto{" +
                "storeCode='" + storeCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", workdayBeginTime='" + workdayBeginTime + '\'' +
                ", workdayEndTime='" + workdayEndTime + '\'' +
                ", weekendBeginTime='" + weekendBeginTime + '\'' +
                ", weekendEndTime='" + weekendEndTime + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
