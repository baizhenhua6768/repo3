package com.suning.sntk.web.dto.vgo;

import java.io.Serializable;

public class WeixinJsSdkConfig implements Serializable {

    /**
     */
    private static final long serialVersionUID = 4722839205676065807L;

    private String appId;

    private String timestamp;

    private String nonceStr;

    private String signature;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
