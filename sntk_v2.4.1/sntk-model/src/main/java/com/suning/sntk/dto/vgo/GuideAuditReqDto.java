/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideAuditReqDto
 * Author:   88396455_白振华
 * Date:     2018-9-3 16:51
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
 * 功能描述：导购审批请求值
 *
 * @author 88396455_白振华
 * @since 2018-9-3
 */
public class GuideAuditReqDto implements Serializable {

    private static final long serialVersionUID = -300314352738345947L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 导购工号
     */
    private String guideId;

    /**
     * 审核标识 1通过，2驳回
     */
    private Integer auditFlag;

    /**
     * V购标识 0不是，1是
     */
    private Integer isVgo;

    /**
     * 驳回原因
     */
    private String auditReason;

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public Integer getAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(Integer auditFlag) {
        this.auditFlag = auditFlag;
    }

    public Integer getIsVgo() {
        return isVgo;
    }

    public void setIsVgo(Integer isVgo) {
        this.isVgo = isVgo;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
