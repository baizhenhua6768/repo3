/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffSenderDto
 * Author:   18032490_赵亚奇
 * Date:     2018/7/9 18:04
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.mq.sender.staffwhitelist.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 发送消息时封装的dto(新增时需传送所有字段，其余情况只需传员工工号)
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/9
 */
@XStreamAlias("staffSenderDto")
public class StaffSenderDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 员工工号
     */
    @XStreamAlias("staffId")
    private String staffId;
    /**
     * 员工姓名
     */
    @XStreamAlias("staffName")
    private String staffName;
    /**
     * 门店编码
     */
    @XStreamAlias("storeCode")
    private String storeCode;
    /**
     * 门店名称
     */
    @XStreamAlias("storeName")
    private String storeName;
    /**
     * 门店地址
     */
    @XStreamAlias("storeAddress")
    private String storeAddress;
    /**
     * 城市编码
     */
    @XStreamAlias("cityCode")
    private String cityCode;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        return "StaffSenderDto{" +
                "staffId='" + staffId + '\'' +
                ", staffName='" + staffName + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", cityCode='" + cityCode + '\'' +
                '}';
    }
}



