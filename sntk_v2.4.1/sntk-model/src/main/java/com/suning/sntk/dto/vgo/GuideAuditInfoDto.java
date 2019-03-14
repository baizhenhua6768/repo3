/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideAuditInfoDto
 * Author:   88396455_白振华
 * Date:     2018-9-1 14:13
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
 * 功能描述：导购审核信息
 *
 * @author 88396455_白振华
 * @since 2018-9-1
 */
public class GuideAuditInfoDto implements Serializable {

    private static final long serialVersionUID = -2305632352743496179L;

    /**
     * 导购工号
     */
    private String guideId;

    /**
     * 导购姓名
     */
    private String guideName;

    /**
     * 大区名称
     */
    private String regionName;

    /**
     * 分公司名称
     */
    private String orgName;

    /**
     * 分公司编码
     */
    private String orgId;

    /**
     * 商店名称
     */
    private String storeName;

    /**
     * 层级
     */
    private String hierarchy;

    /**
     * 所属
     */
    private String attach;

    /**
     * 驳回原因
     */
    private String auditReason;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
