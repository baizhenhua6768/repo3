/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: requiredLabelTypeDto
 * Author:   18041004_余长杰
 * Date:     2018/7/12 20:21
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.advisor;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：必选标签类型
 *
 * @author 18041004_余长杰
 * @since 2018/7/12
 */
public class RequiredLabelTypeDto implements Serializable {

    private static final long serialVersionUID = 714673440110928752L;

    /**
     * 必选标签类型
     */
    private String labelGroupId;

    /**
     * 必选标签名称
     */
    private String labelGroupName;

    private List<CustomerLabelDto> mustLabel;

    public String getLabelGroupId() {
        return labelGroupId;
    }

    public void setLabelGroupId(String labelGroupId) {
        this.labelGroupId = labelGroupId;
    }

    public String getLabelGroupName() {
        return labelGroupName;
    }

    public void setLabelGroupName(String labelGroupName) {
        this.labelGroupName = labelGroupName;
    }

    public List<CustomerLabelDto> getMustLabel() {
        return mustLabel;
    }

    public void setMustLabel(List<CustomerLabelDto> mustLabel) {
        this.mustLabel = mustLabel;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
