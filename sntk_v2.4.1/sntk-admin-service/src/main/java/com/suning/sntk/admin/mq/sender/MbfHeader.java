/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MbfHeader
 * Author:   18032490_赵亚奇
 * Date:     2018/7/4 15:03
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.mq.sender;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 消息头
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/4
 */
public class MbfHeader {
    @XStreamAlias("ServiceCode")
    private String serviceCode;

    @XStreamAlias("Operation")
    private String operation;

    @XStreamAlias("AppCode")
    private String appCode;

    @XStreamAlias("UId")
    private String uid;

    @XStreamAlias("AuthId")
    private String authId;

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    @Override
    public String toString() {
        return "MbfHeader{" +
                "serviceCode='" + serviceCode + '\'' +
                ", operation='" + operation + '\'' +
                ", appCode='" + appCode + '\'' +
                ", uid='" + uid + '\'' +
                ", authId='" + authId + '\'' +
                '}';
    }
}
