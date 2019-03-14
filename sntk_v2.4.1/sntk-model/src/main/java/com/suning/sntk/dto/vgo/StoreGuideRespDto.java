/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreGuideRespDto
 * Author:   18041004_余长杰
 * Date:     2018/8/16 16:32
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：查询门店导购返回dto
 *
 * @author 18041004_余长杰
 * @since 2018/8/16
 */
public class StoreGuideRespDto implements Serializable {

    private static final long serialVersionUID = -8166185943717414062L;

    /**
     * 导购提示标题
     */
    private String tipTitle;

    /**
     * 提示标题点击事件跳转的url
     */
    private String tipUrl;

    /**
     * 专属导购
     */
    private String specialtyGuide;

    /**
     * 客户经理列表
     */
    private List<StoreGuideInfoDto> storeGuideInfoList;

    public String getSpecialtyGuide() {
        return specialtyGuide;
    }

    public void setSpecialtyGuide(String specialtyGuide) {
        this.specialtyGuide = specialtyGuide;
    }

    public String getTipTitle() {
        return tipTitle;
    }

    public void setTipTitle(String tipTitle) {
        this.tipTitle = tipTitle;
    }

    public String getTipUrl() {
        return tipUrl;
    }

    public void setTipUrl(String tipUrl) {
        this.tipUrl = tipUrl;
    }

    public List<StoreGuideInfoDto> getStoreGuideInfoList() {
        return storeGuideInfoList;
    }

    public void setStoreGuideInfoList(List<StoreGuideInfoDto> storeGuideInfoList) {
        this.storeGuideInfoList = storeGuideInfoList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
