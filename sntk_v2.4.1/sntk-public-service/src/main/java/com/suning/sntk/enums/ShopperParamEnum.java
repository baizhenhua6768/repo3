/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ShopperParamEnum
 * Author:   88402362 欧小冬
 * Date:     2018/7/16 9:14
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.enums;

/**
 * 功能描述： 店员详情擅长品类参数
 *
 * @author 88402362_欧小冬
 * @since 2018/7/16
 */
public enum ShopperParamEnum {

    BRAND_TYPE("01"),
    FRIDGE_WASHER("冰洗"),
    FRIDGE("冰箱"),
    WASHER("洗衣机");

    String paramCode;

    ShopperParamEnum(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamCode() {
        return paramCode;
    }

}
