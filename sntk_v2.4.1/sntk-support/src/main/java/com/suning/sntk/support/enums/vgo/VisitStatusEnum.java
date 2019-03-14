/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: VisitStatus
 * Author:   88395115_史小配
 * Date:     2018/8/16 15:21
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums.vgo;

/**
 * 功能描述：预约回访情况
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
public enum VisitStatusEnum {
    UNVISIT("0", "未回访"),
    CALL_UNANSWER("1", "拨打未接听"),
    CALL_ANSWERED("2", "拨打已接听");
    private String status;
    private String desc;

    VisitStatusEnum(String status, String desc) {
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
