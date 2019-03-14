/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CommentRsfServiceImpl.java
 * Author:   15050536
 * Date:     2018年3月29日 下午2:51:19
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.impl.o2o;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.suning.rsf.provider.annotation.Implement;
import com.suning.rsf.util.StringUtils;
import com.suning.sntk.entity.o2o.GuideComment;
import com.suning.sntk.entity.o2o.Label;
import com.suning.sntk.rsf.dto.o2o.CommentReqDto;
import com.suning.sntk.rsf.dto.o2o.CommentRspDto;
import com.suning.sntk.rsf.dto.o2o.LabelDto;
import com.suning.sntk.rsf.dto.o2o.SubCommentRspDto;
import com.suning.sntk.rsf.o2o.CommentRsfService;
import com.suning.sntk.service.o2o.GuideCommentService;
import com.suning.sntk.service.o2o.LabelService;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.copy.OrikaMapperFactory;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 导购评价rsf service 实现类
 *
 * @author 15050536 石键平
 */
@Implement(contract = CommentRsfService.class, implCode = "1.0.0")
@Service
public class CommentRsfServiceImpl implements CommentRsfService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentRsfServiceImpl.class);

	@Autowired
	private GuideCommentService commentService;
	@Autowired
	private LabelService labelService;
	@Autowired
	private CommentRsfServiceImpl self;

	/**
	 * 保存顾客对导购的评价
	 */
	@Override
	public RsfResponseDto<String> saveComment(CommentReqDto commentReq) {
		LOGGER.debug("rsf评价导购接口入参为:{}", commentReq);
		self.doSave(commentReq);
		LOGGER.debug("rsf评价导购接口返回值为:保存成功！");
		return RsfResponseDto.success();
	}

	@Transactional
	public void doSave(CommentReqDto commentReq) {
		// 1.判断是否有评价
		GuideComment guideComment = commentService.findCommentByCustNoAndStaffId(commentReq.getCustNo(), commentReq.getStaffId());
		// 2.如果没有，则新增
		if (guideComment == null) {
			guideComment = new GuideComment();
			OrikaMapperFactory.mapperFactory.classMap(CommentReqDto.class, GuideComment.class).field("starNum", "starLevel").byDefault().register();
			OrikaMapperFactory.map(commentReq, guideComment);
			guideComment.setCreateTime(DateUtils.format(new Date()));
			// 新增
			commentService.saveGuideComment(guideComment);
		} else {
			// 3.如果有，则更新 更新时，改变会变化的属性（starLevel、labelCodes、createTime）
			guideComment.setLabelCodes(commentReq.getLabelCodes());
			guideComment.setStarLevel(commentReq.getStarNum());
			guideComment.setCreateTime(DateUtils.format(new Date()));
			commentService.updateGuideComment(guideComment);
		}
		// 同步数据到评论导购历史表
		commentService.saveGuideCommentH(guideComment);
	}

	/**
	 * 查询顾客对某导购的最近一次评价<br/>
	 * 如果没有则返回默认5星好评
	 */
	@Override
	public RsfResponseDto<CommentRspDto> findLastGuideComment(String custNo, String staffId) {
		LOGGER.debug("rsf会员对某导购最近的评价入参为:{}:{}", custNo, staffId);
		GuideComment guideComment = commentService.findCommentByCustNoAndStaffId(custNo, staffId);
		CommentRspDto commentRspDto = new CommentRspDto();
		if (guideComment == null) {
			// 1、会员未对导购进行过评价
			commentRspDto.setStarLevel(String.valueOf(O2OConstants.DEFAULT_STAR));// 默认给5星
			commentRspDto.setChooseLabelCodes(StringUtils.EMPTY);
		} else {
			// 2、会员已对导购进行过评价
			commentRspDto.setStarLevel(String.valueOf(guideComment.getStarLevel()));
			commentRspDto.setChooseLabelCodes(guideComment.getLabelCodes());
		}
		commentRspDto.setStaffId(staffId);
		// 查询所有的标签,结构为Map key为星级，value为星级对应的标签列表
		Map<String, Collection<Label>> labelMap = labelService.queryAllLabelMap();
		List<SubCommentRspDto> allLabels = changeMapToSubCommentRspDto(labelMap);
		commentRspDto.setAllLabelList(allLabels);
		return RsfResponseDto.of(commentRspDto);
	}

	/**
	 * 将数据库中查询出来的Map<star,标签list>转化成 List<SubCommentRspDto>对象
	 * 
	 * @param labelMap
	 * @return
	 */
	private List<SubCommentRspDto> changeMapToSubCommentRspDto(Map<String, Collection<Label>> labelMap) {
		List<SubCommentRspDto> allLabels = new ArrayList<>();
		if (!CollectionUtils.isEmpty(labelMap)) {
			// 解析map对象
			for (Entry<String, Collection<Label>> entry : labelMap.entrySet()) {
				SubCommentRspDto subCommentRspDto = new SubCommentRspDto();
				subCommentRspDto.setStar(entry.getKey());
				Collection<Label> labels = entry.getValue();
				subCommentRspDto.setLabelList(changeCollectionToList(labels));
				allLabels.add(subCommentRspDto);
			}
		}
		return allLabels;
	}

	/**
	 * 将Collection<Label> 转化成List<LabelDto>
	 * 
	 * @param labels
	 * @return
	 */
	private List<LabelDto> changeCollectionToList(Collection<Label> labels) {
		List<LabelDto> labelDtos = new ArrayList<>();
		for (Label label : labels) {
			LabelDto labelDto = createLabelDto(label);
			labelDtos.add(labelDto);
		}
		return labelDtos;
	}

	/**
	 * 将label转成labelDto
	 */
	private LabelDto createLabelDto(Label label) {
		LabelDto labelDto = new LabelDto();
		labelDto.setLabelCode(label.getLabelCode());
		labelDto.setLabelName(label.getLabelName());
		return labelDto;
	}

}
