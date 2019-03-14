package com.suning.sntk.admin.mq.listener.staffwhitelist.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class StoreBaseInfoDto implements Serializable {

    /**
     */
    private static final long serialVersionUID = 6472045748622702352L;

    @XStreamAlias("storeId")
    private String storeId;

    @XStreamAlias("storeSaleCode")
    private String storeSaleCode;

    @XStreamAlias("storeName")
    private String storeName;
    
    @XStreamAlias("storeAddress")
    private String storeAddress;

    @XStreamAlias("storeLevel")
    private String storeLevel;

    @XStreamAlias("regionCode")
    private String regionCode;

    @XStreamAlias("regionName")
    private String regionName;

    @XStreamAlias("companyCode")
    private String companyCode;

    @XStreamAlias("companySaleCode")
    private String companySaleCode;
    
    @XStreamAlias("companyName")
    private String companyName;
    
    @XStreamAlias("provinceCode")
    private String provinceCode;
    
    @XStreamAlias("provinceName")
    private String provinceName;
    
    @XStreamAlias("cityCode")
    private String cityCode;
    
    @XStreamAlias("cityName")
    private String cityName;
    
    @XStreamAlias("districtCode")
    private String districtCode;
    
    @XStreamAlias("districtName")
    private String districtName;
    
    @XStreamAlias("storeTypeId")
    private String storeTypeId;
    
    @XStreamAlias("storeTypeName")
    private String storeTypeName;
    
    @XStreamAlias("longitude")
    private String longitude;
    
    @XStreamAlias("latitude")
    private String latitude;
    
    @XStreamAlias("storeManagerCode")
    private String storeManagerCode;
    
    @XStreamAlias("storeManagerName")
    private String storeManagerName;
    
    @XStreamAlias("businessDistrict")
    private String businessDistrict;
    
    @XStreamAlias("surroundBuildings")
    private String surroundBuildings;
    
    @XStreamAlias("telephone")
    private String telephone;
    
    @XStreamAlias("postCode")
    private String postCode;
    
    @XStreamAlias("isPark")
    private String isPark;
    
    @XStreamAlias("parkDetail")
    private String parkDetail;
    
    @XStreamAlias("isSubway")
    private String isSubway;
    
    @XStreamAlias("subwayDetail")
    private String subwayDetail;
    
    @XStreamAlias("busLine")
    private String busLine;
    
    @XStreamAlias("isTakeSelf")
    private String isTakeSelf;
    
    @XStreamAlias("takeSelfDetail")
    private String takeSelfDetail;
    
    @XStreamAlias("isSuningNet")
    private String isSuningNet;
    
    @XStreamAlias("suningNetDetail")
    private String suningNetDetail;
    
    @XStreamAlias("workdayBeginTime")
    private String workdayBeginTime;
    
    @XStreamAlias("workdayEndTime")
    private String workdayEndTime;
    
    @XStreamAlias("weekendBeginTime")
    private String weekendBeginTime;
    
    @XStreamAlias("weekendEndTime")
    private String weekendEndTime;
    
    @XStreamAlias("storePicUrl")
    private String storePicUrl;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreSaleCode() {
        return storeSaleCode;
    }

    public void setStoreSaleCode(String storeSaleCode) {
        this.storeSaleCode = storeSaleCode;
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

    public String getStoreLevel() {
        return storeLevel;
    }

    public void setStoreLevel(String storeLevel) {
        this.storeLevel = storeLevel;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanySaleCode() {
        return companySaleCode;
    }

    public void setCompanySaleCode(String companySaleCode) {
        this.companySaleCode = companySaleCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getStoreTypeId() {
        return storeTypeId;
    }

    public void setStoreTypeId(String storeTypeId) {
        this.storeTypeId = storeTypeId;
    }

    public String getStoreTypeName() {
        return storeTypeName;
    }

    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
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

    public String getStoreManagerCode() {
        return storeManagerCode;
    }

    public void setStoreManagerCode(String storeManagerCode) {
        this.storeManagerCode = storeManagerCode;
    }

    public String getStoreManagerName() {
        return storeManagerName;
    }

    public void setStoreManagerName(String storeManagerName) {
        this.storeManagerName = storeManagerName;
    }

    public String getBusinessDistrict() {
        return businessDistrict;
    }

    public void setBusinessDistrict(String businessDistrict) {
        this.businessDistrict = businessDistrict;
    }

    public String getSurroundBuildings() {
        return surroundBuildings;
    }

    public void setSurroundBuildings(String surroundBuildings) {
        this.surroundBuildings = surroundBuildings;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getIsPark() {
        return isPark;
    }

    public void setIsPark(String isPark) {
        this.isPark = isPark;
    }

    public String getParkDetail() {
        return parkDetail;
    }

    public void setParkDetail(String parkDetail) {
        this.parkDetail = parkDetail;
    }

    public String getIsSubway() {
        return isSubway;
    }

    public void setIsSubway(String isSubway) {
        this.isSubway = isSubway;
    }

    public String getSubwayDetail() {
        return subwayDetail;
    }

    public void setSubwayDetail(String subwayDetail) {
        this.subwayDetail = subwayDetail;
    }

    public String getBusLine() {
        return busLine;
    }

    public void setBusLine(String busLine) {
        this.busLine = busLine;
    }

    public String getIsTakeSelf() {
        return isTakeSelf;
    }

    public void setIsTakeSelf(String isTakeSelf) {
        this.isTakeSelf = isTakeSelf;
    }

    public String getTakeSelfDetail() {
        return takeSelfDetail;
    }

    public void setTakeSelfDetail(String takeSelfDetail) {
        this.takeSelfDetail = takeSelfDetail;
    }

    public String getIsSuningNet() {
        return isSuningNet;
    }

    public void setIsSuningNet(String isSuningNet) {
        this.isSuningNet = isSuningNet;
    }

    public String getSuningNetDetail() {
        return suningNetDetail;
    }

    public void setSuningNetDetail(String suningNetDetail) {
        this.suningNetDetail = suningNetDetail;
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

    public String getStorePicUrl() {
        return storePicUrl;
    }

    public void setStorePicUrl(String storePicUrl) {
        this.storePicUrl = storePicUrl;
    }

    @Override
    public String toString() {
        return "StoreBaseInfoDto [storeId=" + storeId + ", storeSaleCode=" + storeSaleCode + ", storeName=" + storeName
                + ", storeAddress=" + storeAddress + ", storeLevel=" + storeLevel + ", regionCode=" + regionCode
                + ", regionName=" + regionName + ", companyCode=" + companyCode + ", companySaleCode=" + companySaleCode
                + ", companyName=" + companyName + ", provinceCode=" + provinceCode + ", provinceName=" + provinceName
                + ", cityCode=" + cityCode + ", cityName=" + cityName + ", districtCode=" + districtCode
                + ", districtName=" + districtName + ", storeTypeId=" + storeTypeId + ", storeTypeName=" + storeTypeName
                + ", longitude=" + longitude + ", latitude=" + latitude + ", storeManagerCode=" + storeManagerCode
                + ", storeManagerName=" + storeManagerName + ", businessDistrict=" + businessDistrict
                + ", surroundBuildings=" + surroundBuildings + ", telephone=" + telephone + ", postCode=" + postCode
                + ", isPark=" + isPark + ", parkDetail=" + parkDetail + ", isSubway=" + isSubway + ", subwayDetail="
                + subwayDetail + ", busLine=" + busLine + ", isTakeSelf=" + isTakeSelf + ", takeSelfDetail="
                + takeSelfDetail + ", isSuningNet=" + isSuningNet + ", suningNetDetail=" + suningNetDetail
                + ", workdayBeginTime=" + workdayBeginTime + ", workdayEndTime=" + workdayEndTime
                + ", weekendBeginTime=" + weekendBeginTime + ", weekendEndTime=" + weekendEndTime + ", storePicUrl="
                + storePicUrl + "]";
    }
    
}
