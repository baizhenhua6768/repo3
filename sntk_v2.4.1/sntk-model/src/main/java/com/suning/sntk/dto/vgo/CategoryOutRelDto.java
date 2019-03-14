/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CategoryOutRelDto
 * Author:   18041004_余长杰
 * Date:     2018/9/3 9:08
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
 * 功能描述：品类关联外部编码Dto
 *
 * @author 18041004_余长杰
 * @since 2018/9/3
 */
public class CategoryOutRelDto implements Serializable {

    private static final long serialVersionUID = 362616769048268422L;

    /**
     * 品类编码
     */
    private String categoryCode;

    /**
     * 外部编码（三级目录编码）
     */
    private String outCode;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
