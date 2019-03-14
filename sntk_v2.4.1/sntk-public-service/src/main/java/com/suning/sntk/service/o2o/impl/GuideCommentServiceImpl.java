/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CommentServiceImpl.java
 * Author:   15050536
 * Date:     2018年3月29日 下午2:57:49
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.service.o2o.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.suning.sntk.dao.o2o.GuideCommentDao;
import com.suning.sntk.entity.o2o.GuideComment;
import com.suning.sntk.entity.o2o.Label;
import com.suning.sntk.service.o2o.GuideCommentService;
import com.suning.sntk.service.o2o.LabelService;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.sntk.support.common.redis.CacheKeys;
import com.suning.sntk.support.common.redis.RedisCache;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.store.dal.base.DalBaseDao;
import com.suning.store.dal.service.AbstractDalService;

/**
 * 中台导购评价 service 实现类
 *
 * @author 15050536 石键平
 */
@Service
public class GuideCommentServiceImpl extends AbstractDalService<GuideComment, Long> implements GuideCommentService {

	@Autowired
	private GuideCommentDao guideCommentDao;

	@Autowired
	private LabelService labelService;

	@Autowired
	private RedisCacheUtils redisClient;

	@Override
	public void saveGuideComment(GuideComment guideComment) {
		cleanProfileKey(guideComment.getStaffId());
		cleanLastCommentKey(guideComment.getCustNo(), guideComment.getStaffId());
		super.create(guideComment);
	}

	@Override
	public boolean updateGuideComment(GuideComment guideComment) {
		cleanProfileKey(guideComment.getStaffId());
		cleanLastCommentKey(guideComment.getCustNo(), guideComment.getStaffId());
		return super.update(guideComment);
	}

	@Override
	@RedisCache(key = CacheKeys.FIND_COMMENT_BY_CUSTNO_AND_STAFFID, fieldIndex = { 0, 1 })
	public GuideComment findCommentByCustNoAndStaffId(String custNo, String staffId) {
		return guideCommentDao.findCommentsByCustNoAndStaffId(custNo, staffId);
	}

	@Override
	protected DalBaseDao<GuideComment, Long> getDalDao() {
		return guideCommentDao;
	}

	@Override
	public void saveGuideCommentH(GuideComment guideComment) {
		guideCommentDao.saveGuideCommentH(guideComment);
	}

	@Override
	@RedisCache(key = CacheKeys.FIND_AVG_STAR_BY_STAFFID, fieldIndex = { 0 })
	public String findAvgStarByStaffId(String staffId) {
		String avgStarNum = guideCommentDao.findAvgStarByStaffId(staffId);
		// 如果没查到， 默认5星
		if (StringUtils.isEmpty(avgStarNum)) {
			avgStarNum = O2OConstants.DEFAULT_AVG_STAR;
		}
		return avgStarNum;
	}

	/**
	 * 根据导购工号，查询其对应的评价概况。如："服务周到":"12" "态度好":"3"
	 *
	 * @param staffId
	 * @return
	 */
	@Override
	@RedisCache(key = CacheKeys.PROFILE_COMMENT_BY_STAFFID, fieldIndex = { 0 })
	public Map<String, Integer> findProfileCommentByStaffId(String staffId) {

		String labelCodes = guideCommentDao.findProfileCommentByStaffId(staffId);

		// 查询所有的标签
		List<Label> allLabels = labelService.queryAllLabel();

		String[] labelCodeArray = StringUtils.isEmpty(labelCodes) ? new String[] {} : labelCodes.split(O2OConstants.SP_DH);
		// 库中没有查到，则返null，之所以不返回空对象是因为会存入缓存
		if (labelCodeArray.length == 0 || CollectionUtils.isEmpty(allLabels)) {
			return null;
		}

		// 将List label转换成 Map结构，key为labelCode
		Map<String, Label> labelMap = Maps.uniqueIndex(allLabels, new Function<Label, String>() {
			@Override
			public String apply(Label label) {
				return label.getLabelCode();
			}
		});

		Map<String, Integer> map = new HashMap<>();
		for (String labelCode : labelCodeArray) {
			Label label = labelMap.get(labelCode);
			if (label == null) {
				continue;
			}
			String labelName = label.getLabelName();
			if (map.containsKey(labelName)) {
				int value = map.get(labelName) + 1;
				map.put(labelName, value);
			} else {
				map.put(labelName, 1);
			}
		}
		return map;
	}

	/**
	 * 清除某导购的评价概览
	 * 
	 * @param staffId
	 */
	private void cleanProfileKey(String staffId) {
		String key = String.format(CacheKeys.PROFILE_COMMENT_BY_STAFFID.key(), staffId);
		String avgKey = String.format(CacheKeys.FIND_AVG_STAR_BY_STAFFID.key(), staffId);
		redisClient.del(key);
		redisClient.del(avgKey);
	}

	/**
	 * 清除会员对导购最后一次评论的缓存key
	 * 
	 * @param custNo
	 * @param staffId
	 */
	private void cleanLastCommentKey(String custNo, String staffId) {
		String key = String.format(CacheKeys.FIND_COMMENT_BY_CUSTNO_AND_STAFFID.key(), custNo, staffId);
		redisClient.del(key);
	}
}
