/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffTypeEnum
 * Author:   18032490_赵亚奇
 * Date:     2018/7/4 11:14
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums;

/**
 * 发送人员信息到NSFDAS 对应的状态
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/4
 */
public enum StaffTypeEnum {

    // staffStatus:I.新增 D.删除 S.启用 H.禁用 L.离职
    ADD_STAFF("I", "新增人员"),
    DELETE_STAFF("D", "删除人员"),
    START_UP_STAFF("S", "启用人员"),
    PROHIBIT_STAFF("H", "禁用人员"),
    LEAVE_STAFF("L", "离职人员");

    private String staffStatus;
    private String description;

    public String getStaffStatus() {
        return staffStatus;
    }

    public String getDescription() {
        return description;
    }

    StaffTypeEnum(String staffStatus, String description) {
        this.staffStatus = staffStatus;
        this.description = description;
    }
}
