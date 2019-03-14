/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: AppointmentCompleteStatus
 * Author:   88395115_史小配
 * Date:     2018/8/16 14:59
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums.vgo;

/**
 * 功能描述：导购预约单完成情况状态枚举
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
public enum CompleteStatusEnum {
    NOT_TO_STORE("0","未到店"),
    TO_STORE_BUY("1","到店购买"),
    TO_STORE_NOBUY("2","到店未购买"),
    UNCOMPLETE_ORDER("3","未销单"),
    COMMON("4","通用"),
    USER_CANCEL("5","用户取消"),
    SYSTEM_CANCEL("6","系统取消");

    private String status;
    private String desc;

    CompleteStatusEnum(String status, String desc) {
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
