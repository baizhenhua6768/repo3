/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CommentRspDto.java
 * Author:   15050536
 * Date:     2018年3月29日 上午11:38:44
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
 * 导购评价的返回dto
 *
 * @author 15050536 石键平
 */
public class CommentRspDto implements Serializable {

    /**
     */
    private static final long serialVersionUID = -1202127742659974059L;

    /**
     * 导购工号
     */
    private String staffId;

    /**
     * 星级
     */
    private String starLevel;

    /**
     * 用户所选的标签code，以逗号分隔
     */
    private String chooseLabelCodes;

    /**
     * 标签列表
     */
    private List<SubCommentRspDto> allLabelList;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getChooseLabelCodes() {
        return chooseLabelCodes;
    }

    public void setChooseLabelCodes(String chooseLabelCodes) {
        this.chooseLabelCodes = chooseLabelCodes;
    }

    
    public List<SubCommentRspDto> getAllLabelList() {
		return allLabelList;
	}

	public void setAllLabelList(List<SubCommentRspDto> allLabelList) {
		this.allLabelList = allLabelList;
	}

	@Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
