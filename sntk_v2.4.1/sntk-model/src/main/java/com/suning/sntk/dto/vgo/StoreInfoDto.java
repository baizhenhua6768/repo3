/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreInfo
 * Author:   18041004_余长杰
 * Date:     2018/8/17 11:36
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述：门店信息
 *
 * @author 18041004_余长杰
 * @since 2018/8/17
 */
public class StoreInfoDto implements Serializable{

    private static final long serialVersionUID = -294565701645951098L;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        StoreInfoDto other = (StoreInfoDto) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (storeId == null) {
            if (other.storeId != null) {
                return false;
            }
        } else if (!storeId.equals(other.storeId)) {
            return false;
        }
        return true;
    }

    /**
     * 门店编码
     */
    private String storeId;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 门店简称
     */
    private String shortName;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 省市名称
     */
    private String provinceName;
    /**
     * 省市id
     */
    private String provinceId;
    /**
     * 门店地址
     */
    private String address;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 门店距离
     */
    private String storeFar = "";

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
     * 停车场信息
     */
    private String parkDetail;

    /**
     * 门店电话
     */
    private String telephone;

    /**
     * 公交路线
     */
    private String busLine;

    /**
     * 地铁路线
     */
    private String subwayDetail;

    /**
     * 用户是否收藏 1：收藏，0：未收藏
     */
    private String isFavo;

    /**
     * 自提点
     */
    private String takeSelfDetail;

    /**
     * 门店类型：0代表普通门店，1代表云店
     */

    private String storeType;

    private String isTopStore;

    /**
     * 兼容老版本 3.3以前客户端<br>
     * 楼层导航，
     */
    private String floorInfo;

    /**
     * 门店v购标识，0未开启，1开启
     */
    private String vflag;

    // 3位的城市编码
    private String cityCode;

    // 门店的店长或加盟服务站的管理员
    private String storeManagerName;

    // 加盟服务站经营范围
    private String mainCategoryName;

    // 加盟服务站的邮箱
    private String userEmail;

    private String districtId;

    private String districtName;

    // 业态
    private String multiFormatType;

    // 跳转链接
    private String jumpUrl;

    // 门店标签
    private String storeLabel;

    //业态
    private String businessType;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getStoreFar() {
        return storeFar;
    }

    public void setStoreFar(String storeFar) {
        this.storeFar = storeFar;
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

    public String getParkDetail() {
        return parkDetail;
    }

    public void setParkDetail(String parkDetail) {
        this.parkDetail = parkDetail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBusLine() {
        return busLine;
    }

    public void setBusLine(String busLine) {
        this.busLine = busLine;
    }

    public String getSubwayDetail() {
        return subwayDetail;
    }

    public void setSubwayDetail(String subwayDetail) {
        this.subwayDetail = subwayDetail;
    }

    public String getIsFavo() {
        return isFavo;
    }

    public void setIsFavo(String isFavo) {
        this.isFavo = isFavo;
    }

    public String getTakeSelfDetail() {
        return takeSelfDetail;
    }

    public void setTakeSelfDetail(String takeSelfDetail) {
        this.takeSelfDetail = takeSelfDetail;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getIsTopStore() {
        return isTopStore;
    }

    public void setIsTopStore(String isTopStore) {
        this.isTopStore = isTopStore;
    }

    public String getFloorInfo() {
        return floorInfo;
    }

    public void setFloorInfo(String floorInfo) {
        this.floorInfo = floorInfo;
    }

    public String getVflag() {
        return vflag;
    }

    public void setVflag(String vflag) {
        this.vflag = vflag;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getStoreManagerName() {
        return storeManagerName;
    }

    public void setStoreManagerName(String storeManagerName) {
        this.storeManagerName = storeManagerName;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getMultiFormatType() {
        return multiFormatType;
    }

    public void setMultiFormatType(String multiFormatType) {
        this.multiFormatType = multiFormatType;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getStoreLabel() {
        return storeLabel;
    }

    public void setStoreLabel(String storeLabel) {
        this.storeLabel = storeLabel;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
