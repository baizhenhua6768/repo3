/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: RegionInfoListDto
 * Author:   18041004_余长杰
 * Date:     2018/9/11 11:11
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：区域列表
 *
 * @author 18041004_余长杰
 * @since 2018/9/11
 */
public class GuideRegionInfoListDto implements Serializable {

    private static final long serialVersionUID = -3785236883872030517L;

    private List<GuideRegionInfoDto> regionInfoDtoList;

    public List<GuideRegionInfoDto> getRegionInfoDtoList() {
        return regionInfoDtoList;
    }

    public void setRegionInfoDtoList(List<GuideRegionInfoDto> regionInfoDtoList) {
        this.regionInfoDtoList = regionInfoDtoList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
