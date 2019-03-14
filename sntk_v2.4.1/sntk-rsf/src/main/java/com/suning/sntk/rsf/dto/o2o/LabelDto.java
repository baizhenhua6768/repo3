/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: LabelDto.java
 * Author:   15050536
 * Date:     2018年3月28日 上午11:57:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.dto.o2o;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 标签表dto
 *
 * @author 15050536 石键平
 */
public class LabelDto implements Serializable {

    /**
     */
    private static final long serialVersionUID = 2121730538965815154L;

    /**
     * 标签编码
     */
    private String labelCode;

    /**
     * 标签名称
     */
    private String labelName;

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
