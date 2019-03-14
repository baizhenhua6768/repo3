/*
 * Copyright (C), 2002-2016, 苏宁易购电子商务有限公司
 * FileName: DataResponse.java
 * Author:   15050536
 * Date:     2016-3-9 下午3:33:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.admin.vo;

/**
 * datatables 返回的数据格式
 * 
 * @author 15050536 石键平
 */
public class DataResponse {

	/**
	 * 暂时理解为批次号，前端传过来几，就返回几，不做改变
	 * 
	 * 因为是ajax提交，如果前端传1，后端回2，会乱套的，保证在异步的情况下有个对应
	 */
	private String draw;

	/**
	 * 总记录条数
	 */
	private long recordsTotal;

	/**
	 * 过滤后的记录条数
	 */
	private long recordsFiltered;

	/**
	 * 分页数据
	 */
	private Object data;

	/**
	 * 一些额外的返回信息
	 */
	private Object other;

	public DataResponse() {
		// 默认构造器
	}

	public DataResponse(String draw, long recordsTotal, long recordsFiltered, Object data) {
		super();
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.setRecordsFiltered(recordsTotal);
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public Object getOther() {
		return other;
	}

	public void setOther(Object other) {
		this.other = other;
	}

}
