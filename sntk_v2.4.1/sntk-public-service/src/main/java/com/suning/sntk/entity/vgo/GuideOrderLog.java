/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideOrderLog
 * Author:   88395115_史小配
 * Date:     2018/8/17 16:15
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.vgo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：预约单日志记录
 *
 * @author 88395115_史小配
 * @since 2018/8/17
 */
@Entity(name = "o2o_guide_order_log")
public class GuideOrderLog {
    /**
     * 预约单日志id
     */
    private Long orderLogId;

    /**
     * 预约单编号
     */
    private String bookCode;

    /**
     * 会员编号
     */
    private String custNumber;

    /**
     * 导购员id
     */
    private String guideId;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 订单状态标志
     */
    private String orderStatusFlag;

    /**
     * 操作人类型{0:导购(包含后台),1:用户}
     */
    private String createFlag;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_LOG_ID")
    public Long getOrderLogId() {
        return orderLogId;
    }

    public void setOrderLogId(Long orderLogId) {
        this.orderLogId = orderLogId;
    }

    @Column(name = "BOOKCODE")
    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    @Column(name = "CUST_NUMBER")
    public String getCustNumber() {
        return custNumber;
    }

    public void setCustNumber(String custNumber) {
        this.custNumber = custNumber;
    }

    @Column(name = "GUIDE_ID")
    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    @Column(name = "ORDER_STATUS")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(name = "ORDER_STATUS_FLAG")
    public String getOrderStatusFlag() {
        return orderStatusFlag;
    }

    public void setOrderStatusFlag(String orderStatusFlag) {
        this.orderStatusFlag = orderStatusFlag;
    }

    @Column(name = "CREATE_FLAG")
    public String getCreateFlag() {
        return createFlag;
    }

    public void setCreateFlag(String createFlag) {
        this.createFlag = createFlag;
    }

    @Column(name = "CREATE_TIME")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
