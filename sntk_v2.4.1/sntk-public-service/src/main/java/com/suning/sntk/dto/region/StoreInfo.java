/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreInfo
 * Author:   88396455_白振华
 * Date:     2018-7-2 11:47
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.region;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：商店信息
 *
 * @author 88396455_白振华
 * @since 2018-7-2
 */
public class StoreInfo implements Serializable {

    private static final long serialVersionUID = 4771216475652364555L;
    /**
     * 文档id
     */
    private String id;

    /**
     * 8位门店编码
     */
    private String storeId;

    /**
     * 门店编码
     */
    private String storeCode;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 城市编码 4位
     */
    private String cityComCode;
    /**
     * 城市名称
     */
    private String cityComName;
    /**
     * 分公司编码
     */
    private String branchCode;
    /**
     * 分公司名称
     */
    private String branchName;
    /**
     * 大区编码 8位
     */
    private String regionCode;
    /**
     * 大区编码4位
     */
    private String saleRegionCode;
    /**
     * 大区名称
     */
    private String regionName;
    /**
     * 分公司财务编码 4位
     */
    private String saleBranchCode;
    /**
     * 门店级别
     */
    private String storeLevel;
    /**
     * 门店类型名称
     */
    private String storeTypeName;
    /**
     * 门店类型编码
     */
    private String storeTypeCode;

    /**
     * 门店地址
     */
    private String storeAddress;

    /**
     * 记录更新时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
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

    public String getCityComCode() {
        return cityComCode;
    }

    public void setCityComCode(String cityComCode) {
        this.cityComCode = cityComCode;
    }

    public String getCityComName() {
        return cityComName;
    }

    public void setCityComName(String cityComName) {
        this.cityComName = cityComName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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

    public String getSaleBranchCode() {
        return saleBranchCode;
    }

    public void setSaleBranchCode(String saleBranchCode) {
        this.saleBranchCode = saleBranchCode;
    }

    public String getStoreLevel() {
        return storeLevel;
    }

    public void setStoreLevel(String storeLevel) {
        this.storeLevel = storeLevel;
    }

    public String getStoreTypeName() {
        return storeTypeName;
    }

    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    public String getStoreTypeCode() {
        return storeTypeCode;
    }

    public void setStoreTypeCode(String storeTypeCode) {
        this.storeTypeCode = storeTypeCode;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSaleRegionCode() {
        return saleRegionCode;
    }

    public void setSaleRegionCode(String saleRegionCode) {
        this.saleRegionCode = saleRegionCode;
    }

    @Override
    public String toString() {
        return "StoreInfo [id=" + id + ", storeId=" + storeId + ", storeCode=" + storeCode + ", storeName=" + storeName
                + ", cityComCode=" + cityComCode + ", cityComName=" + cityComName + ", branchCode=" + branchCode
                + ", branchName=" + branchName + ", regionCode=" + regionCode + ", saleRegionCode=" + saleRegionCode
                + ", regionName=" + regionName + ", saleBranchCode=" + saleBranchCode + ", storeLevel=" + storeLevel
                + ", storeTypeName=" + storeTypeName + ", storeTypeCode=" + storeTypeCode + ", storeAddress="
                + storeAddress + ", updateTime=" + updateTime + "]";
    }

    public interface StoreAttributes {
        String STORE_ID = "storeId";
        String STORE_CODE = "storeCode";
        String STORE_NAME = "storeName";
        String CITY_COM_CODE = "cityComCode";
        String CITY_COM_NAME = "cityComName";
        String BRANCH_CODE = "branchCode";
        String BRANCH_NAME = "branchName";
        String REGION_CODE = "regionCode";
        String REGION_NAME = "regionName";
        String BRANCH_SALE_CODE = "saleBranchCode";
        String STORE_LEVEL = "storeLevel";
        String STORE_TYPE_CODE = "storeTypeCode";
        String STORE_TYPE_NAME = "storeTypeName";
        String UPDATE_TIME = "updateTime";
    }

    public interface ORG_TYPE {
        int SALE_TYPE = 1;
        int HR_TYPE = 0;
    }
}
