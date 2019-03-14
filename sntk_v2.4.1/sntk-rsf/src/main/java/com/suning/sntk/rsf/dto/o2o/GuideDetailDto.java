/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideDetailDto.java
 * Author:   15050536
 * Date:     2018年4月3日 上午10:11:51
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

/**
 * 导购详细信息dto
 *
 * @author 15050536 石键平
 */
public class GuideDetailDto implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 7008088980939951119L;

	/**
	 * 导购基础信息
	 */
	private GuideDto guideDto;

	/**
	 * 评价的概览
	 */
	private List<CommentProfileDto> profile;

	/**
	 * 是否是我的专属导购，1：是；0：否
	 */
	private String ifMyGuide;

	/**
	 * ifMyGuide=0时 有值
	 */
	private String oldStaffId;

	public GuideDto getGuideDto() {
		return guideDto;
	}

	public void setGuideDto(GuideDto guideDto) {
		this.guideDto = guideDto;
	}

	public List<CommentProfileDto> getProfile() {
		return profile;
	}

	public void setProfile(List<CommentProfileDto> profile) {
		this.profile = profile;
	}

	public String getIfMyGuide() {
		return ifMyGuide;
	}

	public void setIfMyGuide(String ifMyGuide) {
		this.ifMyGuide = ifMyGuide;
	}

	public String getOldStaffId() {
		return oldStaffId;
	}

	public void setOldStaffId(String oldStaffId) {
		this.oldStaffId = oldStaffId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
