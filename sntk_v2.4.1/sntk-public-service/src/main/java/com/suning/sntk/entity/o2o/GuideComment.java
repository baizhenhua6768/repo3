/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideComment.java
 * Author:   15050536
 * Date:     2018年3月29日 下午2:55:36
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.entity.o2o;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 导购评价表关联的实体对象
 *
 * @author 15050536 石键平
 */
@Entity(name = "o2o_guide_comments")
public class GuideComment implements Serializable {

	/**
     */
	private static final long serialVersionUID = 3646990592585177604L;
	/**
	 * 唯一id
	 */
	private Long id;
	/**
	 * 店员id
	 */
	private String staffId;
	/**
	 * 会员编号
	 */
	private String custNo;
	/**
	 * 星级（1-5颗星）
	 */
	private int starLevel;
	/**
	 * 店员对导购的评价（使用","分隔的字符串）
	 */
	private String labelCodes;

	private String createTime;

	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "staff_id")
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "cust_no")
	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "star_level")
	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	@Column(name = "label_codes")
	public String getLabelCodes() {
		return labelCodes;
	}

	public void setLabelCodes(String labelCodes) {
		this.labelCodes = labelCodes;
	}

	@Column(name = "create_time")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
