/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MotherStatus
 * Author:   88396455_白振华
 * Date:     2018-7-3 11:25
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums;

/**
 * 功能描述：妈妈状态
 *
 * @author 88396455_白振华
 * @since 2018-7-3
 */
public enum MotherStatus {
    UNPLANNED("无计划", "212000000000"),
    HAVA_BABY("有宝宝", "212000000010"),
    PREPARING("备孕中", "212000000020"),
    IN_PRE4GNANCY("怀孕中", "212000000030");

    private String name;
    private String value;

    private MotherStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}

