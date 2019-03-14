/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BabyInfoDto
 * Author:   88396455_白振华
 * Date:     2018-7-12 12:27
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.dto.mombaby;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-7-12
 */
public class BabyInfoDto implements Serializable {

    private static final long serialVersionUID = 1691175394348275547L;
    private Integer babyNum;
    private String babyName;
    private String babySex;
    private String babyBirthday;

    public Integer getBabyNum() {
        return babyNum;
    }

    public void setBabyNum(Integer babyNum) {
        this.babyNum = babyNum;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public String getBabySex() {
        return babySex;
    }

    public void setBabySex(String babySex) {
        this.babySex = babySex;
    }

    public String getBabyBirthday() {
        return babyBirthday;
    }

    public void setBabyBirthday(String babyBirthday) {
        this.babyBirthday = babyBirthday;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
