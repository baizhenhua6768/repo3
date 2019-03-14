/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StarLevelEnum
 * Author:   88396455_白振华
 * Date:     2018-9-4 11:27
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums.vgo;

/**
 * 功能描述：星级
 *
 * @author 88396455_白振华
 * @since 2018-9-4
 */
public enum StarLevelEnum {
    ONE_STAR("1", "一星级"),
    TWO_STAR("2", "二星级"),
    THREE_STAR("3", "三星级"),
    FOUR_STAR("4", "四星级"),
    FIVE_STAR("5", "五星级");

    private String code;
    private String name;

    StarLevelEnum(String code, String name) {
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

