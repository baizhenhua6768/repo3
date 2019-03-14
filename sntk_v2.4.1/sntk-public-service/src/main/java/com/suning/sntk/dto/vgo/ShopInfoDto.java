/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ShopInfoDto
 * Author:   88397670_张辉
 * Date:     2018-8-18 16:33
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-8-18
 */
public class ShopInfoDto implements Serializable {

    private static final long serialVersionUID = 5262291134913945611L;
    // 主键
    private Long storeId;
    // 门店编号
    private String storeCode;
    // 门店名称
    private String name;
    // 门店类型
    private String type;
    // 门店等级
    private String level;
    // 店长名称
    private String storeManagerName;
    // 店长工号
    private String storeManagerWorkNum;
    // 分公司代号
    private String orgId;
    // 分公司名称
    private String orgName;
    // 门店财务编码
    private String financeId;
    // 分公司财务编码
    private String financeOrgId;
    // 门店所在区县
    private String districtId;
    // 门店所区县名称
    private String districtName;
    // 城市名称
    private String cityName;
    // 城市编号
    private String cityId;
    // 门店所在省名称
    private String provinceName;
    // 门店所在省编号
    private String provinceId;
    // 门店所属大区ID
    private String regionId;
    // 门店所属大区名称
    private String regionName;
    // 门店地址
    private String address;
    // 门店所在商圈
    private String businessDistrict;
    // 周边建筑
    private String surroundBuildings;
    // 门店电话
    private String telephone;
    // 门店邮编
    private String postCode;
    //'是否有停车场  0，是；1，不是'
    private String isPark;
    // 停车场信息
    private String parkDetail;
    // 是否临近地铁
    private String isSubway;
    // 地铁路线
    private String subwayDetail;
    // 公交路线
    private String busLine;
    // 自提信息
    private String takeSelfDetail;
    // 周中营业起始时间
    private String workdayBeginTime;
    // 周中营业截止时间
    private String workdayEndTime;
    // 周末营业起始时间
    private String weekendBeginTime;
    // 周末营业截止时间
    private String weekendEndTime;
    // 门店经度
    private String longitude;
    // 门店纬度
    private String latitude;
    // 门店图片
    private String picUrl;

    // 是否云店
    private Integer isCloudStore;

    // 是否支持v购
    private String vFlag;

    //门店8位组织编码（门店id）
    private String storeNewId;

    //是否支持自提 ,0 否  1 是
    private String isTakeSelf;

    //isSuningNet 是否支持苏宁互联 ,0 否  1 是
    private String isSnWlan;

    private String wLanDetail;

    //店铺经营范围
    private String mainCategoryName;

    //店铺邮箱信息
    private String userEmail;

    //店铺加盟角色 1:服务站 2:代理点
    private String userType;

    //身份证正面照片及反面照片，中间以";"隔开
    private String idPhoto;

    //搜索的门店名称
    private String toSearchStoreName;

    //门店简称
    private String shortName;

    //门店关注数
    private String attentionCount;

    //商圈
    private List<String> cbdId;

    //业务编码
    private List<String> businessCode;

    //业态
    private String multiFormatType;

    //跳转链接
    private String jumpUrl;

    //是否是红孩子店
    private String redBabyOrNot;

    //门店标签
    private String storeLabel;

    /**
     * 门店状态{1:开启,0:关闭},默认是1开启状态
     */
    private String storeStatus = "1";

    //父icon主键
    private List<String> parentIconId;

    //icon主键
    private List<String> iconId;

    //icon名称
    private List<String> iconName;

    //是否置顶
    private int orderNum;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStoreManagerName() {
        return storeManagerName;
    }

    public void setStoreManagerName(String storeManagerName) {
        this.storeManagerName = storeManagerName;
    }

    public String getStoreManagerWorkNum() {
        return storeManagerWorkNum;
    }

    public void setStoreManagerWorkNum(String storeManagerWorkNum) {
        this.storeManagerWorkNum = storeManagerWorkNum;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getFinanceId() {
        return financeId;
    }

    public void setFinanceId(String financeId) {
        this.financeId = financeId;
    }

    public String getFinanceOrgId() {
        return financeOrgId;
    }

    public void setFinanceOrgId(String financeOrgId) {
        this.financeOrgId = financeOrgId;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getTakeSelfDetail() {
        return takeSelfDetail;
    }

    public void setTakeSelfDetail(String takeSelfDetail) {
        this.takeSelfDetail = takeSelfDetail;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getIsCloudStore() {
        return isCloudStore;
    }

    public void setIsCloudStore(Integer isCloudStore) {
        this.isCloudStore = isCloudStore;
    }

    public String getvFlag() {
        return vFlag;
    }

    public void setvFlag(String vFlag) {
        this.vFlag = vFlag;
    }

    public String getStoreNewId() {
        return storeNewId;
    }

    public void setStoreNewId(String storeNewId) {
        this.storeNewId = storeNewId;
    }

    public String getIsTakeSelf() {
        return isTakeSelf;
    }

    public void setIsTakeSelf(String isTakeSelf) {
        this.isTakeSelf = isTakeSelf;
    }

    public String getIsSnWlan() {
        return isSnWlan;
    }

    public void setIsSnWlan(String isSnWlan) {
        this.isSnWlan = isSnWlan;
    }

    public String getwLanDetail() {
        return wLanDetail;
    }

    public void setwLanDetail(String wLanDetail) {
        this.wLanDetail = wLanDetail;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getToSearchStoreName() {
        return toSearchStoreName;
    }

    public void setToSearchStoreName(String toSearchStoreName) {
        this.toSearchStoreName = toSearchStoreName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(String attentionCount) {
        this.attentionCount = attentionCount;
    }

    public List<String> getCbdId() {
        return cbdId;
    }

    public void setCbdId(List<String> cbdId) {
        this.cbdId = cbdId;
    }

    public List<String> getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(List<String> businessCode) {
        this.businessCode = businessCode;
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

    public String getRedBabyOrNot() {
        return redBabyOrNot;
    }

    public void setRedBabyOrNot(String redBabyOrNot) {
        this.redBabyOrNot = redBabyOrNot;
    }

    public String getStoreLabel() {
        return storeLabel;
    }

    public void setStoreLabel(String storeLabel) {
        this.storeLabel = storeLabel;
    }

    public String getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(String storeStatus) {
        this.storeStatus = storeStatus;
    }

    public List<String> getParentIconId() {
        return parentIconId;
    }

    public void setParentIconId(List<String> parentIconId) {
        this.parentIconId = parentIconId;
    }

    public List<String> getIconId() {
        return iconId;
    }

    public void setIconId(List<String> iconId) {
        this.iconId = iconId;
    }

    public List<String> getIconName() {
        return iconName;
    }

    public void setIconName(List<String> iconName) {
        this.iconName = iconName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
