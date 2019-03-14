package com.suning.sntk.admin.vo;

import java.io.Serializable;

public class RegionVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String regionCode; //五位大区编码

    private String regionName;

    public RegionVo() {
        // Default constructor
    }

    public RegionVo(String regionCode, String regionName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "RegionVo [regionCode=" + regionCode + ", regionName=" + regionName + "]";
    }

}
