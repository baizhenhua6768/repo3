/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: MyGuideRspDto.java
 * Author:   15050536
 * Date:     2018年4月3日 上午9:54:24
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.dto.o2o;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.alibaba.fastjson.JSONObject;

/**
 * 专属导购的回参
 *
 * @author 15050536 石键平
 */
public class MyGuideRspDto implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -4655643983708057898L;

	/**
	 * 导购列表
	 */
	private List<GuideDto> guideList;

	/**
	 * 品类列表，当导购列表为空的时候，品类列表有值
	 */
	private List<JSONObject> categoryList;

	public List<GuideDto> getGuideList() {
		return guideList;
	}

	public void setGuideList(List<GuideDto> guideList) {
		this.guideList = guideList;
	}

	public List<JSONObject> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<JSONObject> categoryList) {
		this.categoryList = categoryList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
