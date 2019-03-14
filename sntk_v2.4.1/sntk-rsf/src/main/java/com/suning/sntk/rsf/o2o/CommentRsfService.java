/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CommentRsfService.java
 * Author:   15050536
 * Date:     2018年3月29日 上午11:57:57
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.o2o;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.rsf.dto.o2o.CommentReqDto;
import com.suning.sntk.rsf.dto.o2o.CommentRspDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 评价的rsf接口类
 *
 * @author 15050536 石键平
 */
@Contract(name = "commentRsfService", internal = false, description = "o2o导购项目，处理评价相关的rsf接口类")
public interface CommentRsfService {

	/**
	 * 保存顾客评价
	 *
	 * @param commentReq
	 * @return
	 */
	@Method(idempotent = false, timeout = 3000, retryTimes = 0, description = "保存顾客对导购的评价")
	public RsfResponseDto<String> saveComment(CommentReqDto commentReq);

	@Method(idempotent = true, timeout = 3000, retryTimes = 0, description = "查询会员对某导购最近的评价")
	public RsfResponseDto<CommentRspDto> findLastGuideComment(String custNo, String staffId);

}
