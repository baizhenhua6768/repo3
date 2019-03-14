/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerQueryDto
 * Author:   17061157_王薛
 * Date:     2018/7/7 20:24
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.advisor;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：客户列表查询参数
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public class CustomerQueryDto implements Serializable {

    private static final long serialVersionUID = -2035975566262138159L;

    // 店员工号
    private String staffId;

    // 客户in工程搜索
    private String customerName;

    // 查询类型 最近几个月
    private int buyDateType;

    // 查询当天跟进标识
    private String isToday;

    // 起始页码
    private Integer startIndex;

    // 查询条数
    private Integer pageSize;

    // 购买预期截止时间
    private Date expectEndTime;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getBuyDateType() {
        return buyDateType;
    }

    public void setBuyDateType(int buyDateType) {
        this.buyDateType = buyDateType;
    }

    public String getIsToday() {
        return isToday;
    }

    public void setIsToday(String isToday) {
        this.isToday = isToday;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getExpectEndTime() {
        return expectEndTime;
    }

    public void setExpectEndTime(Date expectEndTime) {
        this.expectEndTime = expectEndTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
