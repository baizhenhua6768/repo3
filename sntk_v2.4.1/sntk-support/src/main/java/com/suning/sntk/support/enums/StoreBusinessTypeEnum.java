/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: StoreBusinessTypeEnum
 * Author:   17061157_王薛
 * Date:     2018/9/4 10:29
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums;

/**
 * 功能描述：门店业态类型
 *
 * @author 17061157_王薛
 * @since 2018/9/4
 */
public enum StoreBusinessTypeEnum {

    ELEC("1", "电器"),
    BABY("2", "母婴"),
    MARKET("3", "超市"),
    SQUARE("4", "广场"),
    SPORT("5", "体育"),
    JIWU("6", "极物");

    private String code;
    private String value;

    StoreBusinessTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
