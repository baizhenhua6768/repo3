/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffUnitInfoDto
 * Author:   88396455_白振华
 * Date:     2018-8-17 10:51
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
 * 功能描述：人员所在单位信息
 *
 * @author 88396455_白振华
 * @since 2018-8-17
 */
public class StaffUnitInfoDto implements Serializable {

    private static final long serialVersionUID = 4040310175421079351L;

    /**
     * 所在行政等级  0，门店  1城市 2大区 3总部
     */
    private String unitLevel;

    /**
     * 所在单位编号
     */
    private String unitId;

    public String getUnitLevel() {
        return unitLevel;
    }

    public void setUnitLevel(String unitLevel) {
        this.unitLevel = unitLevel;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
