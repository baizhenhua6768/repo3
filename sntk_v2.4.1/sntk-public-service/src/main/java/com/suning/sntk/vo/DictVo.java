/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: DictVo
 * Author:   17092777 李明
 * Date:     2017/10/20 16:17
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.suning.sntk.entity.BaseEntity;

/**
 * 〈字典值vo〉<br>
 *
 * @author 17092777 李明
 */
public class DictVo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4212814803660854016L;

	/**
	 * 主键
	 */
	private Long dictId;

	/**
	 * 字典类型
	 */
	@NotBlank
	private String dictType;

	/**
	 * 字典code
	 */
	@NotBlank
	private String dictCode;

	/**
	 * 字典值
	 */
	@NotBlank
	private String dictValue;
	/**
	 * 字典描述
	 */
	private String description;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

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
