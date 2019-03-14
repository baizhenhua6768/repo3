/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: PositionDto
 * Author:   18032490_赵亚奇
 * Date:     2018/10/19 15:34
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 位置信息
 *
 * @author 18032490_赵亚奇
 * @since 2018/10/19
 */
public class PositionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 城市id
     */
    private String cityCode;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 经度
     */
    private String lng;

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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "PositionDto{" +
                "cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
