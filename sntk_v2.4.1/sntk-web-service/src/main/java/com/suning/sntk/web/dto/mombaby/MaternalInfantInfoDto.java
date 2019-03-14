/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MaternalInfantInfoDto
 * Author:   88396455_白振华
 * Date:     2018-7-12 12:26
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.dto.mombaby;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 功能描述：母婴关系
 *
 * @author 88396455_白振华
 * @since 2018-7-12
 */
public class MaternalInfantInfoDto implements Serializable {
    private static final long serialVersionUID = 8758827787235131373L;

    private String momStat;
    private String childbirthDueDate;
    private List<BabyInfoDto> babyInfoList;

    public String getMomStat() {
        return momStat;
    }

    public void setMomStat(String momStat) {
        this.momStat = momStat;
    }

    public String getChildbirthDueDate() {
        return childbirthDueDate;
    }

    public void setChildbirthDueDate(String childbirthDueDate) {
        this.childbirthDueDate = childbirthDueDate;
    }

    public List<BabyInfoDto> getBabyInfoList() {
        return babyInfoList;
    }

    public void setBabyInfoList(List<BabyInfoDto> babyInfoList) {
        this.babyInfoList = babyInfoList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
