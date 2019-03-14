/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MemberLevel
 * Author:   18041004_余长杰
 * Date:     2018/7/17 15:31
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.enums;

/**
 * 功能描述：会员等级
 *
 * @author 18041004_余长杰
 * @since 2018/7/17
 */
public enum MemberLevel {

    LEVEL_ZERO("161000000100", "新人"),
    LEVEL_ONE("161000000110", "V1"),
    LEVEL_TWO("161000000120", "V2"),
    LEVEL_THREE("161000000130", "V3"),
    LEVEL_FOUR("161000000140", "V4");

    String code;
    String value;

    MemberLevel(String code, String value) {
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
