package com.suning.sntk.admin.vo;

import java.io.Serializable;

public class StoreVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String storeCode;

    private String storeName;

    public StoreVo() {
        // Default constructor
    }

    public StoreVo(String storeCode, String storeName) {
        this.storeCode = storeCode;
        this.storeName = storeName;
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

    @Override
    public String toString() {
        return "StoreVo [storeCode=" + storeCode + ", storeName=" + storeName + "]";
    }

}
