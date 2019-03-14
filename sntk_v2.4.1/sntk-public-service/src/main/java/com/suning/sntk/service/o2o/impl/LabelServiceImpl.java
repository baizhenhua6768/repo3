/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: LabelServiceImpl.java
 * Author:   15050536
 * Date:     2018年3月24日 上午11:54:47
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.service.o2o.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Multimaps;
import com.suning.sntk.dao.o2o.LabelDao;
import com.suning.sntk.entity.o2o.Label;
import com.suning.sntk.service.o2o.LabelService;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.sntk.support.common.redis.CacheKeys;
import com.suning.sntk.support.common.redis.RedisCache;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.exception.enums.CommentErrorEnum;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.base.DalBaseDao;
import com.suning.store.dal.service.AbstractDalService;

/**
 *
 * @author 15050536 石键平
 */
@Service
public class LabelServiceImpl extends AbstractDalService<Label, String> implements LabelService {

	@Autowired
	private LabelDao labelDao;

	@Autowired
	private RedisCacheUtils redisClient;

	@Override
	public List<Label> queryLabelsByStar(int star) {
		return labelDao.queryLabelsByStar(star);
	}

	@Override
	public Page<Label> queryPageLabels(Pageable pageable) {
		return labelDao.findAll(pageable);
	}

	@Override
	@RedisCache(key = CacheKeys.QUERY_ALL_LABEL_MAP)
	public Map<String, Collection<Label>> queryAllLabelMap() {
		List<Label> allLabels = labelDao.findAll();
		if (CollectionUtils.isEmpty(allLabels)) {
			return null;
		}
		return Multimaps.index(allLabels, new Function<Label, String>() {
			@Override
			public String apply(Label label) {
				return String.valueOf(label.getStarLevel());
			}
		}).asMap();
	}

	@Override
	public List<Label> queryAllLabel() {
		return labelDao.findAll();
	}

	@Override
	public boolean updateLabel(Label label) {
		// 清除缓存key
		cleanAllLabelKey();
		return super.updateSkipNull(label);
	}

	@Override
	public boolean deleteLabel(String labelCode) {
		// 清除缓存key
		cleanAllLabelKey();
		return super.deleteById(labelCode);
	}

	@Override
	public void saveLabel(Label label) {
		Integer starLevel = label.getStarLevel();
		String labelCode = label.getLabelCode();
		String labelName = label.getLabelName();

		Validators.ifInValid(this.isRepeatCode(labelCode)).thenThrow(CommentErrorEnum.LABEL_CODE_EXIST);
		Validators.ifInValid(this.isRepeatName(starLevel, labelName)).thenThrow(CommentErrorEnum.LABEL_NAME_REPEAT);
		label.setCreateTime(new Date());

		super.create(label);
		// 清除缓存key
		cleanAllLabelKey();
	}

	@Override
	protected DalBaseDao<Label, String> getDalDao() {
		return labelDao;
	}

	@Override
	public boolean isRepeatCode(String labelCode) {
		long count = labelDao.countByCode(labelCode);
		return count > 0;
	}

	@Override
	public boolean isRepeatName(Integer starLevel, String labelName) {
		long count = labelDao.countByName(starLevel, labelName);
		return count > 0;
	}

	/**
	 * 清除获取全部标签缓存key
	 *
	 */
	private void cleanAllLabelKey() {
		String key = String.valueOf(CacheKeys.QUERY_ALL_LABEL_MAP);
		redisClient.del(key);
	}

	/**
	 * 查询当前星级的下一个labelCode
	 */
	@Override
	public String findNextLabelCodeByStar(Integer star) {
		String labelCode = labelDao.findLabelCodeByStar(star);
		if (StringUtils.isEmpty(labelCode)) {
			return O2OConstants.DEFAULT_LABLE_CODE_MAP.get(star);
		}
		Integer next = Integer.parseInt(labelCode) + 1;
		return String.valueOf(next);
	}
}
