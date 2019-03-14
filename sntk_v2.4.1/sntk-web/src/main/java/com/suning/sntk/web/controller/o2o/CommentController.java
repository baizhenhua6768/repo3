/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CommentController.java
 * Author:   15050536
 * Date:     2018年3月28日 上午11:56:09
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.web.controller.o2o;

import java.security.Principal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suning.sntk.rsf.dto.o2o.CommentReqDto;
import com.suning.sntk.rsf.dto.o2o.CommentRspDto;
import com.suning.sntk.web.service.o2o.CommentService;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 评价controller
 *
 * @author 15050536 石键平
 */
@RestController
@Validated
@RequestMapping("/o2o/comment/")
public class CommentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentService commentService;

	@RequestMapping("commentguide")
	public RsfResponseDto<String> commentGuide(@Valid @NotNull CommentReqDto commentReq, Principal principal) {
		String custNo = principal.getName();
		commentReq.setCustNo(custNo);
		LOGGER.debug("评价导购接口，入参为：评价入参= {}", commentReq);
		return commentService.commentGuide(commentReq);
	}

	@RequestMapping("findlastguidecomment")
	public RsfResponseDto<CommentRspDto> findLastGuideComment(@NotBlank String staffId, Principal principal) {
		String custNo = principal.getName();
		LOGGER.debug("查询会员对某导购最近的评价接口，入参为：{},{}", custNo, staffId);
		return commentService.findLastGuideComment(custNo, staffId);
	}
}
