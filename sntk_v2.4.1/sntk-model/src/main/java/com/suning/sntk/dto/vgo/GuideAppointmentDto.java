/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: QueryAppointmentRespDto
 * Author:   88395115_史小配
 * Date:     2018/8/16 11:06
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：预约单信息
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
public class GuideAppointmentDto implements Serializable{

    private static final long serialVersionUID = 8742989488723998492L;

    private Long bookId;

    /**
     * 预约编码
     */
    private String bookCode;
    /**
     *  预约时间
     */
    private String bookingTime;
    /**
     * 类别
     */
    private String categoryName;

    /**
     * 导购ID
     */
    private String guideId;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 完成情况
     */
    private String complete;
    /**
     * 联系电话
     */
    private String telephone;
    /**
     * 备注
     */
    private String remark;
    /**
     * 会员账号
     */
    private String cust;
    /**
     * 是否回访
     */
    private String visit;
    /**
     * 预约状态
     */
    private String bookingStatus;

    /**
     * 是否评价
     */
    private String judgeFlag;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 接待时间
     */
    private String receiveTime;

    /**
     * 未到店、到店未购买原因
     */
    private String reason;

    /**
     * 到店已购买金额
     */
    private String price;

    /**
     * 是否已发送短信，0：未发送，1：已发送
     */
    private int isSendSms;

    /**
     * 业务code：0为V购;
     */
    private String businessCode;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 头像
     */
    private String headPic;

    /**
     * 性别
     */
    private String gender;

    /**
     * 姓名
     */
    private String partyName;

    /**
     * 0：线上 1：线下
     */
    private String vtype;

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCust() {
        return cust;
    }

    public void setCust(String cust) {
        this.cust = cust;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getJudgeFlag() {
        return judgeFlag;
    }

    public void setJudgeFlag(String judgeFlag) {
        this.judgeFlag = judgeFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String reviceTime) {
        this.receiveTime = reviceTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getIsSendSms() {
        return isSendSms;
    }

    public void setIsSendSms(int isSendSms) {
        this.isSendSms = isSendSms;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
