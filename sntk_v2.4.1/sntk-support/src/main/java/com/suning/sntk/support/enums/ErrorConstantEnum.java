/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ErrorConstant
 * Author:   18032490_赵亚奇
 * Date:     2018/8/16 15:06
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums;

/**
 * vgo错误码与错误信息
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/16
 */
public enum ErrorConstantEnum {
    ERROR_0001("0001", "para error!"),
    ERROR_0013("0013", "switch open!");

    private String errorCode;
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    ErrorConstantEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
