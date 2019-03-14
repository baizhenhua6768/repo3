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
 * 功能描述：预约日志状态
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
public enum OrderLogStatusEnum {
    WAIT_VISIT("1", "等待回访"),
    VISIT_ANSWERED("2", "恭候到店，回访拨打已接听"),
    TO_STORE_BUY("3", "完成，到店已购买"),
    TO_STORE_NO_BUY("4", "完成，到店未购买"),
    WAIT_JUDGE("5", "待评价"),
    ALREADY_JUDGE("6", "已评价"),
    CANCEL("7", "取消"),
    VISIT_UNANSWER("8", "回访拨打未接听");

    private String status;
    private String desc;

    OrderLogStatusEnum(String status, String desc) {
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
