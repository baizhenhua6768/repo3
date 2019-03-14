/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CommentProfileDto.java
 * Author:   15050536
 * Date:     2018年4月3日 上午10:14:43
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
 * 评价概况dto
 *
 * @author 15050536 石键平
 */
public class CommentProfileDto implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 619652637182197922L;

	/**
	 * 评价的标签
	 */
	private String labelName;

	/**
	 * 被评价的次数
	 */
	private int num;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
