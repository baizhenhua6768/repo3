/*
 * Copyright (C), 2002-2016, 苏宁易购电子商务有限公司
 * FileName: QueryResult.java
 * Author:   14041582
 * Date:     2016年11月2日 上午10:41:03
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.dto.vgo;

import java.util.Objects;

/**
 * 查询返回结果<br>
 * 18032490
 */
public class QueryResult<T> extends QueryParam {

    /**
     */
    private static final long serialVersionUID = -6394963286315609351L;

    //保存的数据
    private T data;

    // 总记录数
    private long totalCount;
    // 总页数
    private long totalPageCount;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "data=" + data +
                ", totalCount=" + totalCount +
                ", totalPageCount=" + totalPageCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        QueryResult<?> result = (QueryResult<?>) o;
        return totalCount == result.totalCount &&
                totalPageCount == result.totalPageCount &&
                Objects.equals(data, result.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), data, totalCount, totalPageCount);
    }
}
