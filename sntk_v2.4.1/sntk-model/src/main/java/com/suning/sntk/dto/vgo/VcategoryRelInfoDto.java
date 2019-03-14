/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VcategoryRelInfoDto
 * Author:   88396455_白振华
 * Date:     2018-8-16 14:37
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
 * 功能描述：导购擅长品类
 *
 * @author 88396455_白振华
 * @since 2018-8-16
 */
public class VcategoryRelInfoDto implements Serializable {

    private static final long serialVersionUID = -6165572351323541533L;

    /**
     * 品类id
     */
    private long categoryId;

    /**
     * 导购id
     */
    private String guideId;

    /**
     * 品类名称
     */
    private String categoryName;

    /**
     * 品类编码
     */
    private String categoryCode;

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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
