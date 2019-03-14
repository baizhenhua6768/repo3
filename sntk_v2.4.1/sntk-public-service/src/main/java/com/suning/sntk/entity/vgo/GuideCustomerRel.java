/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideCustomerRel
 * Author:   18032490_赵亚奇
 * Date:     2018/10/30 16:54
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.vgo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import com.suning.framework.dal.parsing.support.annotation.TableRoute;

/**
 * 店员顾客关系实体类
 *
 * @author 18032490_赵亚奇
 * @since 2018/10/30
 */
@TableRoute(tableName = "${hash('cr_guide_customer_rel_',storeCode,5)}")
public class GuideCustomerRel {

    private Long id;// 主键id
    private String unionId;//公众平台客户标识
    private String custNo;//'会员编码'
    private String staffId;// '关系店员工号'
    private String storeCode;// '门店编码'
    private String staffName;// '店员名称'
    private String relationType;//'关系类型, 1:客户经理, 2:专业顾问'
    private String channel;// '渠道: 0:微信面对面,1:微信线上,2:微信会话(预留),3:小程序面对面,4:小程序线上,5:小程序会话,6:易购微服务,7:预存, 8:预售, 9:易购店员主页, 10:易购会话'
    private String businessType;// '业态'
    private Integer staffStatus;//'店员状态, 1:正常, 0:离店(离职或调岗)'
    private String deleteFlag;// 关系状态, 0:关系正常, 1:关系解除 '
    private String deleteTime;// '解除关系时间'
    private Date createTime;// '创建时间',
    private String creater;//'创建人',
    private Date updateTime;// '更新时间',
    private String updater;//'更新人',

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "union_id")
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Column(name = "cust_no")
    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    @Column(name = "staff_id")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "store_code")
    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    @Column(name = "staff_name")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Column(name = "relation_type")
    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    @Column(name = "channel")
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Column(name = "business_type")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Column(name = "staff_status")
    public Integer getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(Integer staffStatus) {
        this.staffStatus = staffStatus;
    }

    @Column(name = "delete_flag")
    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Column(name = "delete_time")
    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "creater")
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "updater")
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Override
    public String toString() {
        return "GuideCustomerRel{" +
                "id=" + id +
                ", unionId='" + unionId + '\'' +
                ", custNo='" + custNo + '\'' +
                ", staffId='" + staffId + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", staffName='" + staffName + '\'' +
                ", relationType='" + relationType + '\'' +
                ", channel='" + channel + '\'' +
                ", businessType='" + businessType + '\'' +
                ", staffStatus=" + staffStatus +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", deleteTime='" + deleteTime + '\'' +
                ", createTime=" + createTime +
                ", creater='" + creater + '\'' +
                ", updateTime=" + updateTime +
                ", updater='" + updater + '\'' +
                '}';
    }
}
