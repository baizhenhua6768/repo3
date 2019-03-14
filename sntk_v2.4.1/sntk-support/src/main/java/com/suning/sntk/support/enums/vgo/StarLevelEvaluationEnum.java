/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StarLevelEvaluationEnum
 * Author:   88396455_白振华
 * Date:     2018-8-18 16:54
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums.vgo;

/**
 * 功能描述: 获取转换后的评分<br>
 * 评价星级=“1颗”，“单个评分”=3.0<br>
 * 评价星级=“2颗”，“单个评分”=3.5<br>
 * 评价星级=“3颗”，“单个评分”=4.0<br>
 * 评价星级=“4颗”，“单个评分”=4.5<br>
 * 评价星级=“5颗”，“单个评分”=5.0<br>
 *
 * @author 88396455_白振华
 * @since 2018-8-18
 */
public enum StarLevelEvaluationEnum {
    ONE("1", 3.0), TWO("2", 3.5), THREE("3", 4.0), FOUR("4", 4.5), FIVE("5", 5.0);
    // 成员变量
    private String name;
    private double value;

    // 构造方法
    private StarLevelEvaluationEnum(String name, double value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

}

