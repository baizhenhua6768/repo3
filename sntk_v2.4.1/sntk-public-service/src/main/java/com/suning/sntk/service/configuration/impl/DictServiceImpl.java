/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: DictServiceImpl.java
 * Author:   15050536
 * Date:     2017年7月10日 上午10:48:19
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.service.configuration.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.sntk.dao.DictDao;
import com.suning.sntk.entity.DictEntity;
import com.suning.sntk.service.configuration.DictService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.common.redis.CacheKeys;
import com.suning.sntk.support.common.redis.RedisCache;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.enums.DictEnum;
import com.suning.sntk.support.exception.enums.CommonErrorEnum;
import com.suning.sntk.vo.DictListVo;
import com.suning.sntk.vo.DictVo;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.base.DalBaseDao;
import com.suning.store.dal.service.AbstractDalService;

/**
 * @author 15050536 石键平
 */
@Service
@Trace
public class DictServiceImpl extends AbstractDalService<DictEntity, Long> implements DictService {

	@Autowired
	private DictDao dictDao;

	@Autowired
	private RedisCacheUtils redisUtils;

	@Override
	public boolean addDict(DictVo dictVo) {
		// 查询该字典值是否添加过
		DictVo dictVo1 = dictDao.findDictByTypeAndCode(dictVo.getDictType(), dictVo.getDictCode());
		Validators.ifInValid(dictVo1 != null).thenThrow(CommonErrorEnum.DICT_EXIST);
		DictEntity entity = new DictEntity();
		entity.setCode(dictVo.getDictCode());
		entity.setType(String.valueOf(dictVo.getDictType()));
		entity.setValue(dictVo.getDictValue());
		entity.setDeleteFlag(CommonConstants.UNDELETE_FLAG);
		Date currentDate = new Date();
		entity.setCreateTime(currentDate);
		entity.setCreateUser(dictVo.getCreateUser());
		entity.setUpdateTime(currentDate);
		entity.setUpdateUser(dictVo.getCreateUser());
		entity.setDescription(dictVo.getDescription());
		dictDao.insert(entity);
		return true;
	}

	@Override
	public boolean deleteDict(Long id, String user) {
		DictEntity entity = dictDao.findById(id);
		if (null != entity) {
			redisUtils.del(String.format(CacheKeys.DICT_BY_TYPE_CODE.key(), entity.getType(), entity.getCode()));
			dictDao.delete(entity);
		}
		return true;
	}

	@Override
	@RedisCache(key = CacheKeys.DICT_BY_TYPE_CODE, fieldIndex = { 0, 1 })
	public DictVo findDictByTypeAndCode(String type, String code) {
		return dictDao.findDictByTypeAndCode(type, code);
	}

	@Override
	public DictVo findDictByDictEnum(DictEnum dictEnum) {
		if (dictEnum == null) {
			return null;
		}
		return this.findDictByTypeAndCode(dictEnum.getType(), dictEnum.getCode());
	}

	@Override
	public boolean editDictVo(DictVo dictVo, String user) {
		// 查询改字典值是否存在
		DictVo dictVo1 = dictDao.findDictByTypeAndCode(dictVo.getDictType(), dictVo.getDictCode());
		Validators.ifNull(dictVo1).thenThrow(CommonErrorEnum.DICT_UN_EXIST);

		redisUtils.del(String.format(CacheKeys.DICT_BY_TYPE_CODE.key(), dictVo.getDictType(), dictVo.getDictCode()));

		DictEntity entity = new DictEntity();
		entity.setId(dictVo.getDictId());
		entity.setType(dictVo.getDictType());
		entity.setValue(dictVo.getDictValue());
		entity.setCode(dictVo.getDictCode());
		entity.setDescription(dictVo.getDescription());
		entity.setUpdateTime(new Date());
		entity.setUpdateUser(user);
		return dictDao.updateSkipNull(entity);
	}

	@Override
	public List<DictVo> queryDictListByType(String type) {
		return dictDao.selectDictListByType(type);
	}

	@Override
	public DictListVo queryPageDictList(Pageable pageable) {
		Page<DictVo> page = dictDao.queryPageDictList(pageable);

		DictListVo dictList = new DictListVo();
		dictList.setDictList(page.getContent());
		dictList.setTotalRecord(page.getTotalElements());
		return dictList;
	}

	@Override
	protected DalBaseDao<DictEntity, Long> getDalDao() {
		return dictDao;
	}
}
