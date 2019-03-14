/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: O2oUnitDetailDto
 * Author:   88396455_白振华
 * Date:     2018-8-17 14:28
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
 * 功能描述：区域详情
 *
 * @author 88396455_白振华
 * @since 2018-8-17
 */
public class O2oUnitDetailDto implements Serializable {

    private static final long serialVersionUID = -1511218905822297959L;

    /**
     * 商店编码
     */
    private String storeCode;

    /**
     * 商店名称
     */
    private String storeName;

    /**
     * 城市id
     */
    private String cityId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 大区id
     */
    private String regionId;

    /**
     * 大区名称
     */
    private String regionName;

    /**
     * 门店V购标识 0关闭，1开启
     */
    private String vFlag;

    /**
     * 业态
     */
    private String multiFormatType;

    public String getMultiFormatType() {
        return multiFormatType;
    }

    public void setMultiFormatType(String multiFormatType) {
        this.multiFormatType = multiFormatType;
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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getvFlag() {
        return vFlag;
    }

    public void setvFlag(String vFlag) {
        this.vFlag = vFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
