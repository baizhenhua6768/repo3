/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CommentServiceImpl.java
 * Author:   15050536
 * Date:     2018年3月29日 上午11:13:05
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.web.service.o2o.impl;

import org.springframework.stereotype.Service;

import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.rsf.dto.o2o.CommentReqDto;
import com.suning.sntk.rsf.dto.o2o.CommentRspDto;
import com.suning.sntk.rsf.o2o.CommentRsfService;
import com.suning.sntk.web.service.o2o.CommentService;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 评价业务实现类
 *
 * @author 15050536 石键平
 */
@Service
public class CommentServiceImpl implements CommentService {

	private CommentRsfService commentRsfService = ServiceLocator.getService(CommentRsfService.class, "1.0.0");

	@Override
	public RsfResponseDto<String> commentGuide(CommentReqDto commentReq) {
		return commentRsfService.saveComment(commentReq);
	}

	@Override
	public RsfResponseDto<CommentRspDto> findLastGuideComment(String custNo, String staffId) {
		return commentRsfService.findLastGuideComment(custNo, staffId);
	}

}
