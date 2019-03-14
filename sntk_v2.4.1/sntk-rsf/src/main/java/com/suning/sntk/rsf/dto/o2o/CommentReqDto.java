/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CommentGuideReqDto.java
 * Author:   15050536
 * Date:     2018年3月29日 上午11:21:15
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.dto.o2o;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 评价导购的请求入参dto
 *
 * @author 15050536 石键平
 */
public class CommentReqDto implements Serializable {

    /**
     */
    private static final long serialVersionUID = 8649523529000491216L;

    /**
     * 星级
     */
    @NotNull
    @Min(1)
    @Max(5)
    private Integer starNum;

    /**
     * 标签编码，以逗号分隔
     */
    private String labelCodes;

    /**
     * 店员工号
     */
    @NotBlank
    private String staffId;

    /**
     * 会员编码
     */
    private String custNo;

    public Integer getStarNum() {
		return starNum;
	}

	public void setStarNum(Integer starNum) {
		this.starNum = starNum;
	}

	public String getLabelCodes() {
        return labelCodes;
    }

    public void setLabelCodes(String labelCodes) {
        this.labelCodes = labelCodes;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
