/*
 * Copyright (C), 2002-2016, 苏宁易购电子商务有限公司
 * FileName: QueryParam.java
 * Author:   14041582
 * Date:     2016年11月2日 上午11:03:45
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 分页信息<br>
 *
 * @author 14041582
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class QueryParam implements Serializable {

    /**
     */
    private static final long serialVersionUID = 1416074538160285776L;

    private static final int TEN = 10;

    // 第几页,从1开始
    private int pageNumber = 1;
    // 每页大小,默认是10
    private int pageSize = TEN;
    // 排序方式 asc desc 加正序倒序
    private String orderBy;

    /**
     * 开始
     */
    private int begin = 0;

    /**
     * 结束
     */
    private int end = 0;

    /**
     * 数据的key
     */
    private String zkey;

    /**
     * 数据
     */
    private String hkey;

    public String getZkey() {
        return zkey;
    }

    public void setZkey(String zkey) {
        this.zkey = zkey;
    }

    public String getHkey() {
        return hkey;
    }

    public void setHkey(String hkey) {
        this.hkey = hkey;
    }

    public int getBegin() {
        begin = (this.getPageNumber() - 1) * this.getPageSize();
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        end = this.getPageNumber() * this.getPageSize() - 1;
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "QueryParam [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", orderBy=" + orderBy + ", begin="
                + begin + ", end=" + end + ", zkey=" + zkey + ", hkey=" + hkey + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + begin;
        result = prime * result + end;
        result = prime * result + ((hkey == null) ? 0 : hkey.hashCode());
        result = prime * result + ((orderBy == null) ? 0 : orderBy.hashCode());
        result = prime * result + pageNumber;
        result = prime * result + pageSize;
        result = prime * result + ((zkey == null) ? 0 : zkey.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QueryParam other = (QueryParam) obj;
        if (begin != other.begin)
            return false;
        if (end != other.end)
            return false;
        if (hkey == null) {
            if (other.hkey != null)
                return false;
        } else if (!hkey.equals(other.hkey))
            return false;
        if (orderBy == null) {
            if (other.orderBy != null)
                return false;
        } else if (!orderBy.equals(other.orderBy))
            return false;
        if (pageNumber != other.pageNumber)
            return false;
        if (pageSize != other.pageSize)
            return false;
        if (zkey == null) {
            if (other.zkey != null)
                return false;
        } else if (!zkey.equals(other.zkey))
            return false;
        return true;
    }
}
