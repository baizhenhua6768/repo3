/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: OrderCompletionDto
 * Author:   88395115_史小配
 * Date:     2018/8/16 11:43
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：预约单销单信息
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
public class OrderCompletionDto implements Serializable{
    private static final long serialVersionUID = -8067402444154198198L;

    /**
     * 预约单主键
     */
    @NotNull
    private Long bookId;
    /**
     *导购id
     */
    private String guideId;
    /**
     *预约单编号
     */
    private String bookCode;
    /**
     *完成情况： 0-未到店、1-到店购买、2-到店未购买
     */
    @NotNull
    private Integer complete;
    /**
     *未到店、未购买原因
     */
    private String reason;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}