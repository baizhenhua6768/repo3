/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: QueryAppointmentReqDto
 * Author:   88395115_史小配
 * Date:     2018/8/16 10:26
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：查询预约单请求条件
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
public class QueryAppointmentReqDto implements Serializable{

    private static final long serialVersionUID = -3032746716536816911L;

    private String guideId;

    /**
     * 未完成状态： 0-未回访 1-拨打未接听 2-拨打已接听  3-未销单 4-未完成所有
     */
    private String unComplete;

    private List<String> visitList;

    /**
     * 已完成状态：0-未到店、1-到店购买、2-到店未购买、5-用户取消 、6-系统取消 、7-已完成所有
     */
    private String complete;

    /**
     * 创建时间 0-当月、-1-上月、-2-前月、-3-更久
     */
    private String createTime;

    /**
     * 客户手机号
     */
    private String telephone;

    //预约单是否完成 0-未完成 1-已完成
    private String bookingStatus;
    //回访
    private String visit;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;

    public List<String> getVisitList() {
        return visitList;
    }

    public void setVisitList(List<String> visitList) {
        this.visitList = visitList;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getUnComplete() {
        return unComplete;
    }

    public void setUnComplete(String unComplete) {
        this.unComplete = unComplete;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
