/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffBookDto
 * Author:   18010645_黄成
 * Date:     2018/9/6 19:20
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述：会员最近时间预约信息
 *
 * @author 18010645_黄成
 * @since 2018/9/6
 */
public class CustomerBookDto implements Serializable{
    private static final long serialVersionUID = 6157194024220225865L;
    //预约编码
    private String bookCode;
    //预约创建时间
    private String bookingCreateTime = "";
    //预约到店时间
    private String bookingTime = "";

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookingCreateTime() {
        return bookingCreateTime;
    }

    public void setBookingCreateTime(String bookingCreateTime) {
        this.bookingCreateTime = bookingCreateTime;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "CustomerBookDto{" +
                "bookCode='" + bookCode + '\'' +
                ", bookingCreateTime='" + bookingCreateTime + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                '}';
    }
}
