/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoHierarchyEnum
 * Author:   88396455_白振华
 * Date:     2018-9-10 17:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums.vgo;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：V购层级
 *
 * @author 88396455_白振华
 * @since 2018-9-10
 */
public enum VgoHierarchyEnum {

    STORE_MANAGER("00000290", "店长"),
    SALE_SUPERVISOR("00000300", "销售督导"),
    SALE_CLERK("00010001", "营业员"),
    PROMOTERS("00010002", "促销员");

    private String code;
    private String name;

    private VgoHierarchyEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static List<String> obtainCodeList() {
        VgoHierarchyEnum[] values = VgoHierarchyEnum.values();
        List<String> codes = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            codes.add(values[i].getCode());
        }
        return codes;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

