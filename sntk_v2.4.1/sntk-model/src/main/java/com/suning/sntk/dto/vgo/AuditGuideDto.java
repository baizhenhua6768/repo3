/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AuditGuideDto
 * Author:   88396455_白振华
 * Date:     2018-8-31 10:51
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：审批导购入参
 *
 * @author 88396455_白振华
 * @since 2018-8-31
 */
public class AuditGuideDto implements Serializable {

    private static final long serialVersionUID = 3184235327798983688L;

    private String openFlag;

    private Integer isVgo;

    /**
     * 导购人员集合
     */
    private List<String> guideIds = new ArrayList<String>();

    public AuditGuideDto() {
        //empty constructor
    }

    public AuditGuideDto(String openFlag, Integer isVgo, List<String> guideIds) {
        this.openFlag = openFlag;
        this.isVgo = isVgo;
        this.guideIds = guideIds;
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    public Integer getIsVgo() {
        return isVgo;
    }

    public void setIsVgo(Integer isVgo) {
        this.isVgo = isVgo;
    }

    public List<String> getGuideIds() {
        return guideIds;
    }

    public void setGuideIds(List<String> guideIds) {
        this.guideIds = guideIds;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
