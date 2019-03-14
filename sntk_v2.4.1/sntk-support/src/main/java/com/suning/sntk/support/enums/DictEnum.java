/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: DictEnum.java
 * Author:   15050536
 * Date:     2018年4月2日 下午5:33:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.support.enums;

/**
 * 数据字典的枚举
 *
 * @author 15050536 石键平
 */
public enum DictEnum {

	CITY_WHILE_LIST("city_white_list", "citys", "o2o导购一期，白名单城市列表"),

	DEFAULE_CATEGORY_LIST("categories", "default", "o2o导购一期，白名单城市列表");

	private String type;

	private String code;

	private String desc;

	DictEnum(String type, String code, String desc) {
		this.type = type;
		this.code = code;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
