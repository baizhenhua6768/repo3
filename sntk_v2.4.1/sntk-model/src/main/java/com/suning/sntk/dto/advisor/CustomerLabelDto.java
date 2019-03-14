/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CustomerLabelDto
 * Author:   18041004_余长杰
 * Date:     2018/7/10 15:55
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.advisor;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：标签详情Dto
 *
 * @author 18041004_余长杰
 * @since 2018/7/10
 */
public class CustomerLabelDto implements Serializable {
    private static final long serialVersionUID = 7918502321837784960L;

    /**
     * 标签编号
     */
    private String labelNo;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 是否已选：1-已选 0-未选
     */
    private String checked;

    public String getLabelNo() {
        return labelNo;
    }

    public void setLabelNo(String labelNo) {
        this.labelNo = labelNo;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
