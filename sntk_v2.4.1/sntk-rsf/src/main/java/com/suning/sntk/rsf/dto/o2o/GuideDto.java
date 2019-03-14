/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideDto.java
 * Author:   15050536
 * Date:     2018年3月28日 上午11:28:48
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.dto.o2o;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 导购基础信息dto
 *
 * @author 15050536 石键平
 */
public class GuideDto implements Serializable {

	private static final long serialVersionUID = -608650363814005632L;

	/**
	 * 店员工号
	 */
	private String staffId;

	/**
	 * 店员姓名
	 */
	private String staffName;

	/**
	 * 门店名称
	 */
	private String storeName;

	/**
	 * 门店编码
	 */
	private String storeCode;

	/**
	 * 门店地址
	 */
	private String storeAddress;

	/**
	 * 在职，离职 （1：在职，0：离职）
	 */
	private String status;

	/**
	 * 星级
	 */
	private String star;

	/**
	 * 城市编码
	 */
	private String cityCode;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
