/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ExportStatus
 * Author:   88396455_白振华
 * Date:     2018-8-24 17:25
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums;

/**
 * 功能描述：导出状态
 *
 * @author 88396455_白振华
 * @since 2018-8-24
 */
public enum ExportStatus {
    FAIL(-1, "导出失败"),
    EXPORTING(0, "正在导出"),
    SUCCESS(1, "导出成功"),
    UNKNOWN(99, "未知状态");
    private final int value;
    private final String description;

    ExportStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static ExportStatus valueOf(int value) {
        for (ExportStatus exportStatus : values()) {
            if (exportStatus.getValue() == value) {
                return exportStatus;
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

