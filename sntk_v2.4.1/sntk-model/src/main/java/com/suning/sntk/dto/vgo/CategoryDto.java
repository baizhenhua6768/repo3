/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CategoryDto
 * Author:   88397670_张辉
 * Date:     2018-8-31 11:48
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
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-8-31
 */
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 5603288617488319565L;
    /**
     * 主键
     */
    private long categoryId;

    /**
     * 品类编码
     */
    private String categoryCode;

    /**
     * 品类名称
     */
    private String categoryName;

    /**
     * 后台品类名称
     */
    private String categoryOutName;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryOutName() {
        return categoryOutName;
    }

    public void setCategoryOutName(String categoryOutName) {
        this.categoryOutName = categoryOutName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
