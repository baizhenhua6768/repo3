/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: VgoExceptionEnum
 * Author:   88395115_史小配
 * Date:     2018/8/17 10:14
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.exception.enums;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * 功能描述：V购异常枚举类
 *
 * @author 88395115_史小配
 * @since 2018/8/17
 */
public enum VgoExceptionEnum implements ServiceExceptionNameProvider {
    CUST_HAS_APPOINTMENT_TODAY("sntk.vgo.custHasAppointmentToday"),
    APPOINTMENT_IS_NOT_EXIST("sntk.vgo.appointmentIsNotExist"),
    REPEAT_COMPLETE_ORDER("sntk.vgo.repeatCompleteOrder"),
    UNRETURN_VISIT("sntk.vgo.unreturnVisit");
    private String name;

    VgoExceptionEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
