package com.suning.sntk.support.common.redis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述： key 表示 redis中的key，表示一个缓存区域，如 用户信息，货币类型，店铺信息等。 fieldIndex : 缓存 的标识是第几个参数
 * 
 * @author 作者 88300355@cnsuning.com
 * @version 1.0.0
 * @created Apr 16, 2014 11:25:19 PM
 * @date Apr 16, 2014 11:25:19 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {
	/**
	 * redis 中的key
	 */
	CacheKeys key();

	/**
	 * redis field 的组
	 * 
	 * @return
	 */
	int[] fieldIndex() default {};

	/**
	 * redis 数据结构
	 */
	RedisStruct struct() default RedisStruct.STRINGS;
}
