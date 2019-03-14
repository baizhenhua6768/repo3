/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoAttachEnum
 * Author:   88396455_白振华
 * Date:     2018-9-10 17:16
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums.vgo;

/**
 * 功能描述：V购所属
 *
 * @author 88396455_白振华
 * @since 2018-9-10
 */
public enum VgoAttachEnum {
    STRAIGHT_SHOP("07", "直营店");

    private String code;
    private String name;

    private VgoAttachEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

