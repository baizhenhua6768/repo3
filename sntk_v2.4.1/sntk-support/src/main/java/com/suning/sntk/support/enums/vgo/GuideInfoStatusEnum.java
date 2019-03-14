/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoStatus
 * Author:   18041004_余长杰
 * Date:     2018/8/28 10:36
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums.vgo;

/**
 * 功能描述：导购相关枚举
 *
 * @author 18041004_余长杰
 * @since 2018/8/28
 */
public enum GuideInfoStatusEnum {
    ROUTE_TO_SNTK("1", "路由到sntk"),
    ROUTE_TO_MOSS("0", "路由到moss"),
    IS_CUST_MANAGER("1", "是客户经理"),
    NOT_CUST_MANAGER("0", "不是客户经理");

    private String status;
    private String desc;

    GuideInfoStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
