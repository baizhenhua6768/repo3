/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: O2oStoreDetail
 * Author:   88397670_张辉
 * Date:     2018-8-22 15:49
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.vgo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-8-22
 */
@Entity(name = "o2o_store_detail")
public class O2oStoreDetail {

    /**
     * 主键
     */
    private Long storeId;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 短名称
     */
    private String shortName;

    /**
     * 门店类型
     */
    private String type;

    /**
     * 店长姓名
     */
    private String storeManagerName;

    /**
     * 店长工号
     */
    private String storeManagerWorkNum;

    /**
     * 分公司代码
     */
    private String orgId;

    /**
     * 分公司名称
     */
    private String orgName;

    /**
     * 门店财务编码
     */
    private String financeId;

    /**
     * 分公司财务编码
     */
    private String financeOrgId;

    /**
     * 门店所区县名称
     */
    private String districtName;

    /**
     * 门店所在区县
     */
    private String districtId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 城市编号
     */
    private String cityId;

    /**
     * 门店所在省名称
     */
    private String provinceName;

    /**
     * 门店所在省编号
     */
    private String provinceId;

    /**
     * 门店所属大区ID
     */
    private String regionId;

    /**
     * 门店所属大区名称
     */
    private String regionName;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 门店所在商圈
     */
    private String businessDistrict;

    /**
     * 周边建筑
     */
    private String surroundBuildings;

    /**
     * 门店电话
     */
    private String telephone;

    /**
     * 门店邮编
     */
    private String postCode;

    /**
     * 停车场信息
     */
    private String parkDetail;

    /**
     * 是否临近地铁
     */
    private String isSubway;

    /**
     * 地铁路线
     */
    private String subwayDetail;

    /**
     * 公交路线
     */
    private String busLine;

    /**
     * 自提信息
     */
    private String takeSelfDetail;

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

    /**
     * 门店图片
     */
    private String picUrl;

    /**
     * 是否有自提信息 0，支持；1，不支持
     */
    private String isTakeSelf;

    /**
     *是否有停车场  0，是；1，不是
     */
    private String isPark;

    /**
     * 是否有苏宁互联  0,是；1，不是
     */
    private String isSnWlan;

    /**
     * 苏宁互联详情
     */
    private String wlanDetail;

    /**
     * 门店级别
     */
    private String level;

    /**
     * V购是否开启 0关闭，1开启
     */
    private String vFlag;

    /**
     * 新门店Id
     */
    private String storeNewId;

    /**
     * 主营品类
     */
    private String mainCategoryName;

    /**
     * 店铺邮箱信息
     */
    private String userEmail;

    /**
     * 店铺加盟角色 1:服务站 2:代理点
     */
    private String userType;

    /**
     * 身份证正面照片及反面照片，中间以";"隔开
     */
    private String idPhoto;

    /**
     * 业态
     */
    private String multiFormatType;

    /**
     * 跳转链接
     */
    private String jumpUrl;

    /**
     * 是否是红孩子店
     */
    private String redBabyOrNot;

    /**
     * 门店标签
     */
    private String storeLabel;

    /**
     * 门店状态{1:开启,0:关闭},默认是1开启状态
     */
    private String storeStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Column(name = "STORE_CODE")
    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SHORT_NAME")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "STORE_MANAGER_NAME")
    public String getStoreManagerName() {
        return storeManagerName;
    }

    public void setStoreManagerName(String storeManagerName) {
        this.storeManagerName = storeManagerName;
    }

    @Column(name = "STORE_MANAGER_WORK_NUM")
    public String getStoreManagerWorkNum() {
        return storeManagerWorkNum;
    }

    public void setStoreManagerWorkNum(String storeManagerWorkNum) {
        this.storeManagerWorkNum = storeManagerWorkNum;
    }

    @Column(name = "ORG_ID")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Column(name = "ORG_NAME")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Column(name = "FINANCE_ID")
    public String getFinanceId() {
        return financeId;
    }

    public void setFinanceId(String financeId) {
        this.financeId = financeId;
    }

    @Column(name = "FINANCE_ORG_ID")
    public String getFinanceOrgId() {
        return financeOrgId;
    }

    public void setFinanceOrgId(String financeOrgId) {
        this.financeOrgId = financeOrgId;
    }

    @Column(name = "DISTRICT_NAME")
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Column(name = "DISTRICT_ID")
    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    @Column(name = "CITY_NAME")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Column(name = "CITY_ID")
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Column(name = "PROVINCE_NAME")
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Column(name = "PROVINCE_ID")
    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    @Column(name = "REGION_ID")
    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @Column(name = "REGION_NAME")
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "BUSINESS_DISTRICT")
    public String getBusinessDistrict() {
        return businessDistrict;
    }

    public void setBusinessDistrict(String businessDistrict) {
        this.businessDistrict = businessDistrict;
    }

    @Column(name = "SURROUND_BUILDINGS")
    public String getSurroundBuildings() {
        return surroundBuildings;
    }

    public void setSurroundBuildings(String surroundBuildings) {
        this.surroundBuildings = surroundBuildings;
    }

    @Column(name = "TELEPHONE")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(name = "POSTCODE")
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Column(name = "PARK_DETAIL")
    public String getParkDetail() {
        return parkDetail;
    }

    public void setParkDetail(String parkDetail) {
        this.parkDetail = parkDetail;
    }

    @Column(name = "IS_SUBWAY")
    public String getIsSubway() {
        return isSubway;
    }

    public void setIsSubway(String isSubway) {
        this.isSubway = isSubway;
    }

    @Column(name = "SUBWAY_DETAIL")
    public String getSubwayDetail() {
        return subwayDetail;
    }

    public void setSubwayDetail(String subwayDetail) {
        this.subwayDetail = subwayDetail;
    }

    @Column(name = "BUS_LINE")
    public String getBusLine() {
        return busLine;
    }

    public void setBusLine(String busLine) {
        this.busLine = busLine;
    }

    @Column(name = "TAKE_SELF_DETAIL")
    public String getTakeSelfDetail() {
        return takeSelfDetail;
    }

    public void setTakeSelfDetail(String takeSelfDetail) {
        this.takeSelfDetail = takeSelfDetail;
    }

    @Column(name = "WORKDAY_BEGIN_TIME")
    public String getWorkdayBeginTime() {
        return workdayBeginTime;
    }

    public void setWorkdayBeginTime(String workdayBeginTime) {
        this.workdayBeginTime = workdayBeginTime;
    }

    @Column(name = "WORKDAY_END_TIME")
    public String getWorkdayEndTime() {
        return workdayEndTime;
    }

    public void setWorkdayEndTime(String workdayEndTime) {
        this.workdayEndTime = workdayEndTime;
    }

    @Column(name = "WEEKEND_BEGIN_TIME")
    public String getWeekendBeginTime() {
        return weekendBeginTime;
    }

    public void setWeekendBeginTime(String weekendBeginTime) {
        this.weekendBeginTime = weekendBeginTime;
    }

    @Column(name = "WEEKEND_END_TIME")
    public String getWeekendEndTime() {
        return weekendEndTime;
    }

    public void setWeekendEndTime(String weekendEndTime) {
        this.weekendEndTime = weekendEndTime;
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

    @Column(name = "PIC_URL")
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Column(name = "IS_TAKE_SELF")
    public String getIsTakeSelf() {
        return isTakeSelf;
    }

    public void setIsTakeSelf(String isTakeSelf) {
        this.isTakeSelf = isTakeSelf;
    }

    @Column(name = "IS_PARK")
    public String getIsPark() {
        return isPark;
    }

    public void setIsPark(String isPark) {
        this.isPark = isPark;
    }

    @Column(name = "IS_SN_WLAN")
    public String getIsSnWlan() {
        return isSnWlan;
    }

    public void setIsSnWlan(String isSnWlan) {
        this.isSnWlan = isSnWlan;
    }

    @Column(name = "WLAN_DETAIL")
    public String getWlanDetail() {
        return wlanDetail;
    }

    public void setWlanDetail(String wlanDetail) {
        this.wlanDetail = wlanDetail;
    }

    @Column(name = "LEVEL")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Column(name = "V_FLAG")
    public String getvFlag() {
        return vFlag;
    }

    public void setvFlag(String vFlag) {
        this.vFlag = vFlag;
    }

    @Column(name = "STORE_NEW_ID")
    public String getStoreNewId() {
        return storeNewId;
    }

    public void setStoreNewId(String storeNewId) {
        this.storeNewId = storeNewId;
    }

    @Column(name = "MAIN_CATEGORY_NAME")
    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    @Column(name = "USER_EMAIL")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Column(name = "USER_TYPE")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Column(name = "ID_PHOTO")
    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    @Column(name = "MULTI_FORMAT_TYPE")
    public String getMultiFormatType() {
        return multiFormatType;
    }

    public void setMultiFormatType(String multiFormatType) {
        this.multiFormatType = multiFormatType;
    }

    @Column(name = "JUMP_URL")
    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    @Column(name = "RED_BABY_OR_NOT")
    public String getRedBabyOrNot() {
        return redBabyOrNot;
    }

    public void setRedBabyOrNot(String redBabyOrNot) {
        this.redBabyOrNot = redBabyOrNot;
    }

    @Column(name = "STORE_LABEL")
    public String getStoreLabel() {
        return storeLabel;
    }

    public void setStoreLabel(String storeLabel) {
        this.storeLabel = storeLabel;
    }

    @Column(name = "STORE_STATUS")
    public String getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(String storeStatus) {
        this.storeStatus = storeStatus;
    }
}
