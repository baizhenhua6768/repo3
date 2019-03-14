/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: RegionInfoDto
 * Author:   18041004_余长杰
 * Date:     2018/9/11 11:12
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
 * 功能描述：区域信息
 *
 * @author 18041004_余长杰
 * @since 2018/9/11
 */
public class GuideRegionInfoDto implements Serializable {

    private static final long serialVersionUID = -3270452030177498720L;

    /**
     * 区域编码
     */
    private String regionalCode;

    /**
     * 区域名称
     */
    private String regionalName;

    /**
     * 区域层级
     */
    private String regionalLevel;

    /**
     * 区域父层级
     */
    private String parentRegionalCode;

    private String extension;

    public String getRegionalCode() {
        return regionalCode;
    }

    public void setRegionalCode(String regionalCode) {
        this.regionalCode = regionalCode;
    }

    public String getRegionalName() {
        return regionalName;
    }

    public void setRegionalName(String regionalName) {
        this.regionalName = regionalName;
    }

    public String getRegionalLevel() {
        return regionalLevel;
    }

    public void setRegionalLevel(String regionalLevel) {
        this.regionalLevel = regionalLevel;
    }

    public String getParentRegionalCode() {
        return parentRegionalCode;
    }

    public void setParentRegionalCode(String parentRegionalCode) {
        this.parentRegionalCode = parentRegionalCode;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
