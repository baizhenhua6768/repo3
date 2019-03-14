/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AuditQueryDto
 * Author:   88397670_张辉
 * Date:     2018-8-21 14:41
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
 * 功能描述：导购头像审核查询参数
 *
 * @author 88397670_张辉
 * @since 2018-8-21
 */
public class AuditQueryDto implements Serializable {

    private static final long serialVersionUID = 5626945337329767845L;

    /**
     *大区编码
     */
    private String regionCode;

    /**
     *分公司编码
     */
    private String companyCode;

    /**
     *门店编码
     */
    private String storeCode;

    /**
     *导购姓名
     */
    private String guideName;

    /**
     *导购工号
     */
    private String guideId;

    /**
     * 审核标记
     */
    private String openFlag;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
