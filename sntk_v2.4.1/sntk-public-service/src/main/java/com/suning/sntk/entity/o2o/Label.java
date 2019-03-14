/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: Label.java
 * Author:   15050536
 * Date:     2018年3月24日 上午11:33:47
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.entity.o2o;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 评价标签
 *
 * @author 15050536 石键平
 */
@Entity(name = "o2o_labels")
public class Label implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -8630244110507250280L;

	/**
	 * 标签编码
	 */
	@NotBlank
	private String labelCode;

	/**
	 * 标签名称
	 */
	@NotBlank
	private String labelName;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 所属星级 1.2.3.4.5星
	 */
	@NotNull
	@Min(1)
	@Max(5)
	private Integer starLevel;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "label_code")
	public String getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	@Column(name = "label_name")
	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "star_level")
	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
