/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: FileType
 * Author:   88396455_白振华
 * Date:     2018-8-24 15:18
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.enums;

/**
 * 功能描述：文件类型
 *
 * @author 88396455_白振华
 * @since 2018-8-24
 */
public enum FileType {
    SHOPPING_GUIDE(1, "导购数据信息表"),
    UNKNOWN(99, "未知文件类型");

    private final int value;
    private final String description;

    FileType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static FileType valueOf(int value) {
        for (FileType fileType : values()) {
            if (fileType.value == value) {
                return fileType;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}

