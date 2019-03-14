/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookingReqDto
 * Author:   18010645_黄成
 * Date:     2018/8/19 9:32
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述：预约导购入参
 *
 * @author 18010645_黄成
 * @since 2018/8/19
 */
public class BookingReqDto implements Serializable {
    private static final long serialVersionUID = -1537360134581283359L;
    //导购工号
    private String guideId;
    //会员电话号码
    private String customerPhone;
    //预约时间
    private String bookingTime;
    //门店编码
    private String storeCode;
    //渠道
    private String channel;
    //业态
    private String storeType;

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
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

    @Override
    public String toString() {
        return "BookingReqDto{" +
                "guideId='" + guideId + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}
