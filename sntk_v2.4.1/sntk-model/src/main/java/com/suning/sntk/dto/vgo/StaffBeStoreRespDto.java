package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述：查询门店地址
 *
 * @author 18010645_黄成
 * @since 2018-8-18
 */
public class StaffBeStoreRespDto implements Serializable {

    private static final long serialVersionUID = -7488790893743440336L;
    //门店名称
    private String storeName;
    //门店短名称
    private String shortName;
    //门店地址
    private String storeAddress;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    @Override
    public String toString() {
        return "StaffBeStoreRespDto{" +
                "storeName='" + storeName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                '}';
    }
}
