/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: BookingStatus
 * Author:   88395115_史小配
 * Date:     2018/8/16 15:14
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums.vgo;

/**
 * 功能描述：预约完成状态枚举
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
public enum BookingStatusEnum {
    UNFINISH("0","未完成"),
    FINISH("1","已完成")
    ;

    private String status;
    private String desc;

    BookingStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
