/*
 * Copyright (C), 2002-2016, 苏宁易购电子商务有限公司
 * FileName: OperationParam.java
 * Author:   14041582
 * Date:     2016年11月2日 下午3:16:41
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.dto.vgo;

import java.util.Objects;

/**
 * redis操作参数<br>
 *
 * @author 14041582
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class OperationParam<T> extends QueryParam {// NOSONAR

    /**
     */
    private static final long serialVersionUID = -7479872942063750968L;
    
    /**
     * 有序
     */
    private double score;
    
    /**
     * 数据
     */
    private T dto;// NOSONAR
    
    /**
     * 能通过field找到dto
     */
    private String field;

    /**
     * 缓存失效时间
     */
    private int seconds;
    
    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public T getDto() {
        return dto;
    }

    public void setDto(T dto) {
        this.dto = dto;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        OperationParam<?> that = (OperationParam<?>) o;
        return Double.compare(that.score, score) == 0 &&
                seconds == that.seconds &&
                Objects.equals(dto, that.dto) &&
                Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), score, dto, field, seconds);
    }

    @Override
    public String toString() {
        return "OperationParam [score=" + score + ", dto=" + dto + ", field=" + field + "]";
    }
}
