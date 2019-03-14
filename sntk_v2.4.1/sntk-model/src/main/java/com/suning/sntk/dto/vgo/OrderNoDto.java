/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: OrderNoDto
 * Author:   18010645_黄成
 * Date:     2018/10/7 17:42
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述：批量查询接单数响应类
 *
 * @author 18010645_黄成
 * @since 2018/10/7
 */
public class OrderNoDto implements Serializable {
    private static final long serialVersionUID = 331575488046344363L;

    //导购工号
    private String guideId;
    //接单数
    private Long orderNum;

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "OrderNoDto{" +
                "guideId='" + guideId + '\'' +
                ", orderNum=" + orderNum +
                '}';
    }
}
