/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ShopperEnum
 * Author:   88402362 欧小冬
 * Date:     2018/7/16 9:18
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.enums;

/**
 * 功能描述：
 *
 * @author 88402362_欧小冬
 * @since 2018/7/16
 */
public enum ShopperEnum {

    FRIDGE_WASHER("冰洗","冰箱#洗衣机"),
    COMMUNICATION("通讯","通讯"),
    DIGITAL("数码","数码"),
    KITCHEN_TOILET("厨卫","厨卫"),
    COMPUTER("电脑","电脑"),
    AIR_CONDITIONER("空调","空调"),
    SMALL_HOME_APPLIANCES("小家电","小家电"),
    TV("黑电","电视机");
    String brandName;
    String brandOtherName;

    ShopperEnum(String brandName, String brandOtherName){
        this.brandOtherName = brandOtherName;
        this.brandName = brandName;
    }


    public String getBrandOtherName() {
        return brandOtherName;
    }


    public static ShopperEnum valueOfOtherName(String brandName){
        ShopperEnum[] values = ShopperEnum.values();
        for (ShopperEnum value : values) {
            if(value.brandName.equals(brandName)){
                return value;
            }
        }
        return null;
    }


}
