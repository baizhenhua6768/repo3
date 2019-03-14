/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideOrderLogDto
 * Author:   18032490_赵亚奇
 * Date:     2018/8/28 19:32
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 查询预约操作日志的dto
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/28
 */
public class GuideOrderLogDto implements Serializable {
    private static final long serialVersionUID = -2296325025803232359L;

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

    public Long getOrderLogId() {
        return orderLogId;
    }

    public void setOrderLogId(Long orderLogId) {
        this.orderLogId = orderLogId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getCustNumber() {
        return custNumber;
    }

    public void setCustNumber(String custNumber) {
        this.custNumber = custNumber;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusFlag() {
        return orderStatusFlag;
    }

    public void setOrderStatusFlag(String orderStatusFlag) {
        this.orderStatusFlag = orderStatusFlag;
    }

    public String getCreateFlag() {
        return createFlag;
    }

    public void setCreateFlag(String createFlag) {
        this.createFlag = createFlag;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "GuideOrderLogDto{" +
                "orderLogId=" + orderLogId +
                ", bookCode='" + bookCode + '\'' +
                ", custNumber='" + custNumber + '\'' +
                ", guideId='" + guideId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderStatusFlag='" + orderStatusFlag + '\'' +
                ", createFlag='" + createFlag + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
