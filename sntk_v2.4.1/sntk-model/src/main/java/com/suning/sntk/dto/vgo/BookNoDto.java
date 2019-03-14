/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookNoDto
 * Author:   18010645_黄成
 * Date:     2018/10/7 17:45
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述：批量查询预约数响应类
 *
 * @author 18010645_黄成
 * @since 2018/10/7
 */
public class BookNoDto implements Serializable {
    private static final long serialVersionUID = 3370544582954501880L;

    //导购工号
    private String guideId;
    //该导购预约count数量
    private Long num;

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "BookNoDto{" +
                "guideId='" + guideId + '\'' +
                ", num=" + num +
                '}';
    }
}
