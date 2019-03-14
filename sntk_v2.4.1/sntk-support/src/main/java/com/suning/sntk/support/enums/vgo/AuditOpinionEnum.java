/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AuditOpinionEnum
 * Author:   88396455_白振华
 * Date:     2018-9-4 15:19
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums.vgo;

/**
 * 功能描述：审核意见
 *
 * @author 88396455_白振华
 * @since 2018-9-4
 */
public enum AuditOpinionEnum {
    HEAD_PHOTO_NOT_STANDARD("1", "头像不符合标准"),
    INTRODUCTION_NOT_STANDARD("2", "个性签名不符合标准"),
    OTHER_REASONS("3", "其他原因");

    private String code;
    private String desc;

    AuditOpinionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

