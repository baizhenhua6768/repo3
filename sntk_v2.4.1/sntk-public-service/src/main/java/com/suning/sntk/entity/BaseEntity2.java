/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BaseEntity
 * Author:   14060886
 * Date:     2017/10/20 10:25
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 〈基础实体〉<br>
 *
 * @author 14060886
 */
public class BaseEntity2 implements Serializable {

	private static final long serialVersionUID = -2035975566262138159L;

	/**
	 * 创建人
	 */
	private String creater;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新人
	 */
	private String updater;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	@Column(name = "creater")
	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "updater")
	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
