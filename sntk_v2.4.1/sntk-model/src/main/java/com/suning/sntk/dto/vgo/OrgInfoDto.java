/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: OrgInfoDto
 * Author:   88396455_白振华
 * Date:     2018-8-31 14:15
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
 * @author 88396455_白振华
 * @since 2018-8-31
 */
public class OrgInfoDto implements Serializable {

    private static final long serialVersionUID = -3270452030177498720L;

    /**
     * 编码
     */
    private String orgId;

    /**
     * 名称
     */
    private String orgName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
