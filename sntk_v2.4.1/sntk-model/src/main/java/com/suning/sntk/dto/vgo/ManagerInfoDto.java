/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ManagerInfoDto
 * Author:   18032490_赵亚奇
 * Date:     2018/8/17 10:35
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * @author 18032490_赵亚奇
 * @since 2018/8/17
 */
public class ManagerInfoDto implements Serializable {
    private static final long serialVersionUID = 8539496577587513721L;

    private Long id;
    /**
     * 导购工号
     */
    private String staffId;
    /**
     * 导购名称
     */
    private String staffName;
    /**
     * 会员编号
     */
    private String custNo;

    /**
     * 门店编码
     */
    private String storeCode;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 解除关系状态 1：表示关系解除 0：表示关系没解除
     */
    private String deleteFlag;

    /**
     * 解除时间
     */
    private String deleteTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Override
    public String toString() {
        return "ManagerInfoDto{" +
                "id=" + id +
                ", staffId='" + staffId + '\'' +
                ", staffName='" + staffName + '\'' +
                ", custNo='" + custNo + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", channel='" + channel + '\'' +
                ", createTime='" + createTime + '\'' +
                ", businessType='" + businessType + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", deleteTime='" + deleteTime + '\'' +
                '}';
    }
}
