/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BusinessTypesEnum
 * Author:   88396455_白振华
 * Date:     2018-9-3 15:26
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums.vgo;

/**
 * 功能描述：业态
 *
 * @author 88396455_白振华
 * @since 2018-9-3
 */
public enum BusinessTypesEnum {

    ELECTRIC("1", "电器"),
    MOM_INFANT("2", "母婴");

    private String code;
    private String name;

    BusinessTypesEnum(String code, String name) {
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

