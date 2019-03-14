/*
 * Copyright (C), 2002-2015, 苏宁易购电子商务有限公司
 * FileName: QueryResult.java
 * Author:   13040569
 * Date:     2015年6月19日 下午7:26:43
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 查询返回结果
 *
 */
public class QueryResult<T> implements Serializable {

	/**
     */
	private static final long serialVersionUID = -118155599922538173L;

	// 第几页,从1开始
	private int pageNumber = 1;
	// 每页大小,默认是10
	private int pageSize = 10;

	// 总记录数
	private long totalCount;
	// 总页数
	private long totalPageCount;

	// 业务的具体类型
	private List<T> resultObject;

	// 当前页
	private int currentPage;

	public List<T> getResultObject() {
		return resultObject;
	}

	public void setResultObject(List<T> resultObject) {
		this.resultObject = resultObject;
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		QueryResult<?> that = (QueryResult<?>) o;

		if (totalCount != that.totalCount) {
			return false;
		}
		if (totalPageCount != that.totalPageCount) {
			return false;
		}
		if (currentPage != that.currentPage) {
			return false;
		}
		return resultObject != null ? resultObject.equals(that.resultObject) : that.resultObject == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (int) (totalCount ^ (totalCount >>> 32));
		result = 31 * result + (int) (totalPageCount ^ (totalPageCount >>> 32));
		result = 31 * result + (resultObject != null ? resultObject.hashCode() : 0);
		result = 31 * result + currentPage;
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryResult [totalCount=");
		builder.append(totalCount);
		builder.append(", totalPageCount=");
		builder.append(totalPageCount);
		builder.append(", resultObject=");
		builder.append(resultObject);
		builder.append(", currentPage=");
		builder.append(currentPage);
		builder.append("]");
		return builder.toString();
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

}
