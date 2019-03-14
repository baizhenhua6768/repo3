/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: SexEnum
 * Author:   17061157_王薛
 * Date:     2018/7/7 16:14
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.enums;

/**
 * 功能描述：性别枚举
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public enum SexEnum {
    UNKNOW("0", "未知"), MAN("1", "男"), WOMAN("2", "女");

    String code;
    String value;

    SexEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
