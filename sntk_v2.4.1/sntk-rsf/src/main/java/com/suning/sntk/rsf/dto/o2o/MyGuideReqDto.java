/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: MyGuideReqDto.java
 * Author:   15050536
 * Date:     2018年3月27日 下午4:03:28
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.dto.o2o;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.suning.rsf.util.StringUtils;

/**
 * 查询我的导购 入参dto
 *
 * @author 15050536 石键平
 */
public class MyGuideReqDto implements Serializable {

	/**
     */
	private static final long serialVersionUID = 1797157838378553555L;

	/**
	 * 会员编码
	 */
	private String custNo;

	/**
	 * 城市编码
	 */
	@NotBlank
	private String cityCode;

	/**
	 * 业态类型，目前默认传1，表示电器
	 */
	private String businessType;

	/**
	 * 品类编码 (非必填)
	 */
	private String categoryCode;

	/**
	 * 门店列表，用逗号分隔 (非必填)
	 */
	private String storeList = StringUtils.EMPTY;

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getStoreList() {
		return storeList;
	}

	public void setStoreList(String storeList) {
		this.storeList = storeList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
