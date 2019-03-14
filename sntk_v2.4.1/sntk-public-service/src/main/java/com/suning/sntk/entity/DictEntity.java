/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: DictEntity.java
 * Author:   15050536
 * Date:     2017年7月10日 上午10:33:37
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 字典表
 *
 * @author 15050536 石键平
 */
@Entity(name = "dicts")
public class DictEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4652190920183033599L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 字典类型
	 */
	private String type;

	/**
	 * 字典code
	 */
	private String code;

	/**
	 * 字典code对应的描述
	 */
	private String value;

	/**
	 * 字典描述
	 */
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
