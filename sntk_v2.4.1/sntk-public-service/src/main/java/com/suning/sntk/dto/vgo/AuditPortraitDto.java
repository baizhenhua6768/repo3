/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AuditPortraitDto
 * Author:   88397670_张辉
 * Date:     2018-8-20 10:22
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

import com.suning.sntk.entity.vgo.GuideInfo;

/**
 * 功能描述：导购头像审核列表对象
 *
 * @author 88397670_张辉
 * @since 2018-8-20
 */
public class AuditPortraitDto implements Serializable {

    private static final long serialVersionUID = 6946286956537507435L;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 导购信息列表
     */
    private List<GuideInfoDto> guideInfoList;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<GuideInfoDto> getGuideInfoList() {
        return guideInfoList;
    }

    public void setGuideInfoList(List<GuideInfoDto> guideInfoList) {
        this.guideInfoList = guideInfoList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
