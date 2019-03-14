/*
 * Copyright (C), 2002-2015, 苏宁易购电子商务有限公司
 * FileName: JsonUtil.java
 * Author:   10070706
 * Date:     2015-4-10 下午6:22:53
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.support.common.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 〈Json转化工具类〉<br>
 * 
 * @author 10070706
 */
public class JsonUtils {

	private JsonUtils() {
	}

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** GSON构建器 */
	private static final Gson GSON = new GsonBuilder().serializeNulls().setDateFormat(DATE_FORMAT).disableHtmlEscaping().create();

	/**
	 * 对象转换成JSON字符串<br>
	 * 日期格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param obj
	 * @return
	 */
	public static String object2Json(Object obj) {
		return GSON.toJson(obj);
	}

	public static <T> T json2Object(String json, Class<T> requireType) {
		return GSON.fromJson(json, requireType);
	}

	public static <T> T json2Object(String json, Type type) {
		return GSON.fromJson(json, type);
	}
}
