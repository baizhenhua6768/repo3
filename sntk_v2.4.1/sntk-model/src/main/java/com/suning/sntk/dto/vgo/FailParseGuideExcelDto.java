/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: FailParseGuideExcelDto
 * Author:   88396455_白振华
 * Date:     2018-9-5 10:02
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：解析导购失败信息
 *
 * @author 88396455_白振华
 * @since 2018-9-5
 */
public class FailParseGuideExcelDto implements Serializable {

    private static final long serialVersionUID = 3132631647826192279L;

    /**
     * 导购工号
     */
    private String guideiId;

    /**
     * 行数
     */
    private Integer row;

    /**
     * 列数
     */
    private Integer column;

    public String getGuideiId() {
        return guideiId;
    }

    public void setGuideiId(String guideiId) {
        this.guideiId = guideiId;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
