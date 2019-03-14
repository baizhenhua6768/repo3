/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerLabelStringDto
 * Author:   17061157_王薛
 * Date:     2018/7/12 16:13
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.relation;

/**
 * 功能描述：客户标签字符串 DTO
 *
 * @author 17061157_王薛
 * @since 2018/7/12
 */
public class CustomerLabelStringDto {

    private String unionId;

    private String labelStr;

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getLabelStr() {
        return labelStr;
    }

    public void setLabelStr(String labelStr) {
        this.labelStr = labelStr;
    }
}
