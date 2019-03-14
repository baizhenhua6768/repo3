/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CommentService.java
 * Author:   15050536
 * Date:     2018年3月29日 上午11:12:47
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.web.service.o2o;

import com.suning.sntk.rsf.dto.o2o.CommentReqDto;
import com.suning.sntk.rsf.dto.o2o.CommentRspDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 评价导购相关的service
 *
 * @author 15050536 石键平
 */
public interface CommentService {

	/**
	 * 评价导购
	 * 
	 * @param commentReq
	 *            包含会员编码，店员工号，星级，评价的标签codes
	 */
	public RsfResponseDto<String> commentGuide(CommentReqDto commentReq);

	/**
	 * 查询导购的最近一次评价信息
	 * 
	 * @param commentReq
	 *            包含会员编码，店员工号
	 */
	public RsfResponseDto<CommentRspDto> findLastGuideComment(String custNo,String staffId);

}
