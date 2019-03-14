/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AppointmentException
 * Author:   18032490_赵亚奇
 * Date:     2018/8/17 10:45
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.exception.vgo;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * @author 18032490_赵亚奇
 * @since 2018/8/17
 */
public enum AppointmentErrorEnum implements ServiceExceptionNameProvider {
    PARAM_IS_NULL("sntk.appointment.custIsNull"),
    LOW_GRADE_DEAL("sntk.appointment.lowGradeDeal"),
    PARAM_ERROR_FROM_REDIS("sntk.appointment.paramErrorFromRedis"),
    NOT_FIND_DATA("sntk.appointment.notFindData"),
    UP_TO_CANCEL_MAX_NUM("sntk.appointment.upToCancelMaxNum"),
    STORE_CODE_NOT_EXIST("sntk.manager.storeCodeNotExist");

    private String name;

    AppointmentErrorEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
