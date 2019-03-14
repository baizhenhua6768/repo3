/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookingSendMsg
 * Author:   18032490_赵亚奇
 * Date:     2018/8/31 9:54
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.vgo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 18032490_赵亚奇
 * @since 2018/8/31
 */
@Entity(name = "o2o_v_booking_send_message")
public class BookingSendMsg {
    /**
     * 主键
     */
    private Long id;
    /**
     * 预约单号
     */
    private String bookingCode;
    /**
     * 预约时间 yyyy-MM-dd HH:mm
     */
    private String appointmentTime;
    /**
     * 会员电话
     */
    private String custPhone;
    /**
     * 导购电话
     */
    private String guidePhone;
    /**
     * 种类 1.创建预约  ；2.取消预约
     */
    private String type;
    /**
     * 发送信息时间
     */
    private String sendMsgTime;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态(1.未发送 ，2 已发送)
     *
     * @return
     */
    private String status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "BOOKING_CODE")
    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    @Column(name = "APPOINTMENT_TIME")
    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Column(name = "CUST_PHONE")
    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Column(name = "GUIDE_PHONE")
    public String getGuidePhone() {
        return guidePhone;
    }

    public void setGuidePhone(String guidePhone) {
        this.guidePhone = guidePhone;
    }

    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "SEND_MSG_TIME")
    public String getSendMsgTime() {
        return sendMsgTime;
    }

    public void setSendMsgTime(String sendMsgTime) {
        this.sendMsgTime = sendMsgTime;
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookingSendMsg{" +
                "id=" + id +
                ", bookingCode='" + bookingCode + '\'' +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", guidePhone='" + guidePhone + '\'' +
                ", type='" + type + '\'' +
                ", sendMsgTime=" + sendMsgTime +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                '}';
    }
}
