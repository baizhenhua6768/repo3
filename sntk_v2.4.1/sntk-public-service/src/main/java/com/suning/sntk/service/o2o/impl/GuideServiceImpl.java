package com.suning.sntk.service.o2o.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.sntk.dao.o2o.GuideDao;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.service.o2o.GuideService;
import com.suning.sntk.support.common.redis.CacheKeys;
import com.suning.sntk.support.common.redis.RedisCache;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.dal.base.DalBaseDao;
import com.suning.store.dal.service.AbstractDalService;

@Service
@Trace
public class GuideServiceImpl extends AbstractDalService<StaffCustRel, Long> implements GuideService {

	@Autowired
	private GuideDao guideDao;

	@Autowired
	private RedisCacheUtils redisClient;

	@Override
	public boolean deleteRelByStaffIdAndCustNo(String staffId, String custNo) {
		// 删除 根据工号和会员编码查询导购关系的缓存
		String key = String.format(CacheKeys.FIND_REL_BY_CUSTNO_STAFFID.key(), staffId, custNo);
		redisClient.del(key);

		// 删除绑定关系的时候，清理设置的缓存
		String staffCustListKey = String.format(CacheKeys.STAFFCUSTLIST_BY_CUSTNO.key(), custNo);
		redisClient.del(staffCustListKey);

		return guideDao.deleteRelByStaffIdAndCustNo(staffId, custNo);
	}

	@Override
	public StaffCustRel saveStaffCustRel(StaffCustRel saffCustRelationship) {
		return super.create(saffCustRelationship);
	}

	@Override
	@RedisCache(key = CacheKeys.FIND_REL_BY_CUSTNO_STAFFID, fieldIndex = { 0, 1 })
	public StaffCustRel findRelByCustNoAndStaffId(String staffId, String custNo) {
		return guideDao.findRelByCustNoAndStaffId(staffId, custNo);
	}

	@Override
	@RedisCache(key = CacheKeys.STAFFCUSTLIST_BY_CUSTNO, fieldIndex = { 0 })
	public List<StaffCustRel> queryStaffCustListByCustNo(String custNo) {
		return guideDao.queryStaffCustListByCustNo(custNo);
	}

	@Override
	public StaffCustRel queryRelByCustNo(String customerNo) {
		return guideDao.queryRelByCustNo(customerNo);
	}

	@Override
	protected DalBaseDao<StaffCustRel, Long> getDalDao() {
		return guideDao;
	}

}
