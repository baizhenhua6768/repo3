package com.suning.sntk.support.common.redis;

/**
 * CacheKeys
 */
public enum CacheKeys {

	/**
	 * 字典表配置
	 */
	DICT_BY_TYPE_CODE("SNTK:DICT:BY:TYPE:CODE:%s_%s", RedisKeyConstants.ONE_HOUR_S),

	/**
	 * 根据城市查询导购列表的缓存KEY
	 */
	QUERY_GUIDES_BY_CITY("SNTK:QUERY:GUIDES:BY:CITY:%s_%s_%s", RedisKeyConstants.ONE_HOUR_S),

	/**
	 * 根据会员号和导购工号查询导购信息的缓存KEY
	 */
	FIND_REL_BY_CUSTNO_STAFFID("SNTK:FIND:REL:BY:CUSTNO:STAFFID:%s_%s", RedisKeyConstants.ONE_HOUR_S),

	/**
	 * 根据会员编码，查询其对应导的购列表
	 */
	STAFFCUSTLIST_BY_CUSTNO("SNTK:STAFFCUSTLIST:BY:CUSTNO:%s", RedisKeyConstants.ONE_HOUR_S),

	/**
	 * 某导购的评价概况
	 */
	PROFILE_COMMENT_BY_STAFFID("SNTK:PROFILE:COMMENT:BY:STAFFID:%s", RedisKeyConstants.HALF_AN_HOUR),

	/**
	 * 根据会员号和导购工号查询评论信息的缓存KEY
	 */
	FIND_COMMENT_BY_CUSTNO_AND_STAFFID("SNTK:FIND:COMMENT:BY:CUSTNO:AND:STAFFID:%s_%s", RedisKeyConstants.ONE_HOUR_S),

	/**
	 * 获取到全部标签的缓存KEY
	 */
	QUERY_ALL_LABEL_MAP("SNTK:QUERY:ALL:LABEL:MAP", RedisKeyConstants.SEVEN_DAY_S),

	/**
	 * 通过导购工号获取到改导购的平均值缓存KEY
	 */
	FIND_AVG_STAR_BY_STAFFID("SNTK:FIND:AVG:STAR:BY:STAFFID:%s", RedisKeyConstants.FIVE_MINUTE);
	private String key;

	private int expire;

	CacheKeys(String key, int expire) {
		this.key = key;
		this.expire = expire;
	}

	/**
	 * key <br>
	 */
	public String key() {
		return this.key;
	}

	/**
	 * expire <br>
	 */
	public int expire() {
		return this.expire;
	}

	@Override
	public String toString() {
		return this.key;
	}

	/**
	 * getExpire <br>
	 */
	public static int getExpire(String key) {
		for (CacheKeys cacheKey : CacheKeys.values()) {
			if (key.equals(cacheKey.key)) {
				return cacheKey.expire;
			}
		}

		return RedisKeyConstants.HALF_AN_HOUR;
	}
}
