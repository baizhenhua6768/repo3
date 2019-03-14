package com.suning.sntk.rsf.dto.o2o;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 星级对应的标签code与name列表rsponseDto
 * @author Administrator
 *
 */
public class SubCommentRspDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8904621450509033554L;
	
	/**
	 * 星级
	 */
	private String star;
	
	/**
	 * 星级对应的标签列表
	 */
	private List<LabelDto> labelList;

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public List<LabelDto> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<LabelDto> labelList) {
		this.labelList = labelList;
	}
	
	@Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
