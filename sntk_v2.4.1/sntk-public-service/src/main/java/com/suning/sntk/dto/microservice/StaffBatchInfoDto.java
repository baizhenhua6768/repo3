/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffBatchInfo
 * Author:   88397670_张辉
 * Date:     2018-7-9 16:25
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.microservice;

import java.util.List;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-7-9
 */
public class StaffBatchInfoDto {

    private List<StaffInfoDto> staffInfoList;

    /**
     * 数据返回标志位 1-未查到员工号的门店信息 2-人员组织信息不在操作人员管辖范围内
     */
    private String flag;

    public List<StaffInfoDto> getStaffInfoList() {
        return staffInfoList;
    }

    public void setStaffInfoList(List<StaffInfoDto> staffInfoList) {
        this.staffInfoList = staffInfoList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
