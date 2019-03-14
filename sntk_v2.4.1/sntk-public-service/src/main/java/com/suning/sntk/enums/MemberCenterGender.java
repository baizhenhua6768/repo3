/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MemberCenterGender
 * Author:   88396455_白振华
 * Date:     2018-7-11 16:21
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.enums;

/**
 * 功能描述：会员中心性别
 *
 * @author 88396455_白振华
 * @since 2018-7-11
 */
public enum MemberCenterGender {

    MAN("124000000010"),
    WOMAN("124000000020");

    String code;

    private MemberCenterGender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

