/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ConversionStoreTypeUtil
 * Author:   88397670_张辉
 * Date:     2018-9-5 10:56
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：店+门店类型转换拓客业态
 *
 * @author 88397670_张辉
 * @since 2018-9-5
 */
public class ConversionBusinessTypeUtil {

    private ConversionBusinessTypeUtil(){
    }
    /**
     * 默认门店业态：电器
     */
    private static final  String DEFAULT_STORE_TYPE = "1";

    private static final  Map<String, String> STORE_TYPE_MAP = new HashMap<>();

    static {
        STORE_TYPE_MAP.put("20", "2");// 红孩子-> 母婴
        STORE_TYPE_MAP.put("28", "2");// 红孩子乐园-> 母婴
        STORE_TYPE_MAP.put("21", "3");// 超市
        STORE_TYPE_MAP.put("38", "4");// 广场
        STORE_TYPE_MAP.put("29", "5");// 体育
        STORE_TYPE_MAP.put("36", "6");// 极物
        // 其他默认为电器 1
    }

    public static String getBusinessType(String storeType) {
        return STORE_TYPE_MAP.get(storeType) == null ? DEFAULT_STORE_TYPE : STORE_TYPE_MAP.get(storeType);
    }
}
