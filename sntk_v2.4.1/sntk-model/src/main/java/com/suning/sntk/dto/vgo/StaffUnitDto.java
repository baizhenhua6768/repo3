/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffUnitDto
 * Author:   88396455_白振华
 * Date:     2018-8-31 15:01
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
 * 功能描述：员工组织信息
 *
 * @author 88396455_白振华
 * @since 2018-8-31
 */
public class StaffUnitDto implements Serializable {

    private static final long serialVersionUID = 4204191108671590685L;

    /**
     * 大区编码
     */
    private String regionId;

    /**
     * 分公司编码
     */
    private String orgId;

    /**
     * 门店编码
     */
    private String storeCode;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
