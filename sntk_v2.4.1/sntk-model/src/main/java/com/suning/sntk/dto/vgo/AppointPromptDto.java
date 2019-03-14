/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AppointPromptDto
 * Author:   18010645_黄成
 * Date:     2018/9/7 10:16
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述：预约提示框信息
 *
 * @author 18010645_黄成
 * @since 2018/9/7
 */
public class AppointPromptDto implements Serializable {
    private static final long serialVersionUID = -5475383649731839877L;
    //预约编码
    private String bookCode;
    //预约时间
    private String bookTime;
    //门店名称
    private String storeName;
    //门店短名称
    private String shortName;

    //导购名称
    private String guideName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    @Override
    public String toString() {
        return "AppointPromptDto{" +
                "bookCode='" + bookCode + '\'' +
                ", bookTime='" + bookTime + '\'' +
                ", storeName='" + storeName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", guideName='" + guideName + '\'' +
                '}';
    }
}
