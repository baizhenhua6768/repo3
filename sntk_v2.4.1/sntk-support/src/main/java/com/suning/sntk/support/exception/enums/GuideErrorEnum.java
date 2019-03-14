package com.suning.sntk.support.exception.enums;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

public enum GuideErrorEnum implements ServiceExceptionNameProvider {
	/**
	 * 1002 城市校验失败，非白名单城市
	 */
	CHECK_CITY_ERROR("sntk.guide.checkCityError"),
	/**
	 * 8001 根据staffId查询nsf导购信息接口返回失败
	 */
	QUERY_NSF_GUIDE_BY_STAFF_ID_FAIL("sntk.guide.queryNsfGuideByStaffIdFail"),
	/**
	 * 8002 根据cityCode查询nsf导购列表接口返回失败
	 */
	QUERY_NSF_GUIDE_BY_CITY_CODE_FAIL("sntk.guide.queryNsfGuideByCityCodeFail"),

	/**
	 * 8003 根麦琪系统匹配异常，或者没有返回导购对象
	 */
	MATCH_FAIL("sntk.guide.matchFail"),

	/**
	 * 2001 今日更新次数已达上限
	 */
	UPDATE_LIMIT("sntk.guide.updateLimit");
	private String name;

	GuideErrorEnum(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
