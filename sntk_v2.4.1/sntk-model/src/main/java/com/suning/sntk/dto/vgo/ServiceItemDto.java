/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ServiceItemDto
 * Author:   88397670_张辉
 * Date:     2018-9-3 15:23
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
 * 功能描述：母婴服务项目
 *
 * @author 88397670_张辉
 * @since 2018-9-3
 */
public class ServiceItemDto implements Serializable {

    private static final long serialVersionUID = 4587084471887498543L;

    /**
     * 服务项目Id
     */
    private Long itemId;

    /**
     * 服务类型 1：服务项目，2：服务特色及保障，3：厂家授权品牌
     */
    private Integer itemType;

    /**
     * 服务项目名
     */
    private String itemVal;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getItemVal() {
        return itemVal;
    }

    public void setItemVal(String itemVal) {
        this.itemVal = itemVal;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
