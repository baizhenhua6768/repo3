/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoReqDto
 * Author:   88396455_白振华
 * Date:     2018-8-16 10:42
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
 * 功能描述：查询导购信息请求
 *
 * @author 88396455_白振华
 * @since 2018-8-16
 */
public class GuideInfoReqDto implements Serializable {

    private static final long serialVersionUID = -7722453643833202463L;

    /**
     * 大区id
     */
    private String regionId;

    /**
     * 分公司id
     */
    private String orgId;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 是否开启 0 不开启，1开启
     */
    private String openFlag;

    /**
     * 是否是V购 0不是，1是
     */
    private Integer isVgo;

    /**
     * 导购员层级
     */
    private String hierarchy;

    /**
     * 导购员所属
     */
    private String attach;

    /**
     * 导购人员工号
     */
    private String guideId;

    /**
     * 导购名字
     */
    private String guideName;

    /**
     * 业态
     */
    private String businessType;

    /**
     * 审核标识
     */
    private Integer auditFlag;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(Integer auditFlag) {
        this.auditFlag = auditFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
