package com.suning.sntk.support.common.redis;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.support.common.utils.MD5Util;
import com.suning.sntk.support.exception.SntkServiceException;
import com.suning.sntk.support.exception.enums.CommonErrorEnum;

/**
 * 处理RedisCache注解的类
 */
@Aspect
@Component
public class CacheAnnotationProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheAnnotationProcessor.class);

	@Autowired
	RedisCacheUtils redisClient;

	/**
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(com.suning.sntk.support.common.redis.RedisCache)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {// NOSONAR
		Object methodReturnValue = null;
		try {
			Object[] methodArgs = joinPoint.getArgs();
			Signature signature = joinPoint.getSignature();
			if (signature instanceof MethodSignature) {
				MethodSignature methodSignature = (MethodSignature) signature;
				// 取得aop method的方法
				Method aopCacheMethod = methodSignature.getMethod();
				// 缓存标签的参数，一些缓存的配置
				RedisCache cacheParam = aopCacheMethod.getAnnotation(RedisCache.class);
				// 取redis 缓存
				String cachedValue = this.getCachedValue(cacheParam, methodArgs);
				// 有缓存，反序列化
				if (StringUtils.isNotEmpty(cachedValue)) {
					return JsonUtils.json2Object(cachedValue, aopCacheMethod.getGenericReturnType());
				}

				// 没缓存 调用方法
				methodReturnValue = joinPoint.proceed();

				// 兼容dal annotaion (原来dal查询出来，如果没有值会返回null,新框架后会返回new
				// ArrayList()，导致进缓存，故修改)
				if (methodReturnValue instanceof Collection<?>) { // NOSONAR
					if (CollectionUtils.isEmpty((Collection<?>) methodReturnValue)) {// NOSONAR
						return methodReturnValue;
					}
				}

				if (null != methodReturnValue) {
					this.setCacheValue(cacheParam, methodArgs, methodReturnValue);
				}

			} else {
				methodReturnValue = joinPoint.proceed();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			methodReturnValue = joinPoint.proceed();
		}
		return methodReturnValue;
	}

	private void setCacheValue(RedisCache cacheParam, Object[] methodArgs, Object methodReturnValue) {
		CacheKeys cacheKeyEnum = cacheParam.key();
		String rawCacheKey = cacheKeyEnum.key();
		if (cacheParam.struct().equals(RedisStruct.STRINGS)) {
			// 取方法入参作为 key的一部分，限制为基本类型
			Object[] stringFields = this.buildField(methodArgs, cacheParam.fieldIndex());
			// abc%s + "123" -> abc123 返回的值为最终的缓存key
			String finalCacheKey = String.format(rawCacheKey, stringFields);
			this.redisClient.set(finalCacheKey, JsonUtils.object2Json(methodReturnValue));
			if (cacheKeyEnum.expire() > 0) {
				this.redisClient.expire(finalCacheKey, cacheKeyEnum.expire());
			}
		} else if (cacheParam.struct().equals(RedisStruct.HASH)) {
			// 数组变下划线连接的字符
			String hashField = this.buildFieldForHash(methodArgs, cacheParam.fieldIndex());
			this.redisClient.hset(rawCacheKey, hashField, JsonUtils.object2Json(methodReturnValue));
			// -1 if the key does not exist or if the key exist but has no
			// associated expire.
			if (this.redisClient.ttl(rawCacheKey) < 0) {
				// 有效时间不为0且key为新插入则设置业务缓存的有效时间
				this.redisClient.expire(rawCacheKey, cacheKeyEnum.expire());
			}
		} else {
			throw new SntkServiceException(CommonErrorEnum.REDIS_NOT_SUPPORT_STRUCT);
		}
	}

	private String getCachedValue(RedisCache cacheParam, Object[] methodArgs) {
		CacheKeys cacheKeyEnum = cacheParam.key();
		String rawCacheKey = cacheKeyEnum.key();
		if (cacheParam.struct().equals(RedisStruct.STRINGS)) {
			// 取方法入参作为 key的一部分，限制为基本类型
			Object[] stringFields = this.buildField(methodArgs, cacheParam.fieldIndex());
			// abc%s + "123" -> abc123 返回的值为最终的缓存key
			String finalCacheKey = String.format(rawCacheKey, stringFields);
			return this.redisClient.get(finalCacheKey);
		} else if (cacheParam.struct().equals(RedisStruct.HASH)) {
			// 数组变下划线连接的字符
			String hashField = this.buildFieldForHash(methodArgs, cacheParam.fieldIndex());
			return this.redisClient.hget(rawCacheKey, hashField);
		} else {
			throw new SntkServiceException(CommonErrorEnum.REDIS_NOT_SUPPORT_STRUCT);
		}
	}

	/**
	 * 功能描述： 输入参数：<按照参数定义顺序>
	 *
	 */
	@SuppressWarnings("unused")
	private boolean hasNullArgument(Object[] methodArgs) {
		boolean hasNullValue = false;
		if (null == methodArgs) {
			hasNullValue = true;
		} else {
			for (int i = 0; i < methodArgs.length; i++) {
				if (methodArgs[i] == null) {
					hasNullValue = true;
					break;
				}
			}
		}
		return hasNullValue;
	}

	/**
	 * 功能描述：取缓存key的值
	 *
	 */
	private Object[] buildField(Object[] args, int[] fields) {
		List<String> fieldArray = new ArrayList<>();
		Object[] arr = new Object[] {};
		if (null != fields) {
			for (int i = 0; i < fields.length; i++) {
				Object arg = args[fields[i]];
				if (arg == null) {
					arg = "null";
				}
				if (ClassUtils.isPrimitiveOrWrapper(arg.getClass()) || arg instanceof String) {
					fieldArray.add(arg.toString());
				} else {
					throw new IllegalArgumentException("非法的 redis field.Field 只能为 基本类型或String");
				}
			}
		}
		return fieldArray.toArray(arr);
	}

	/**
	 * 功能描述：将 分开的 值合成一个 值。eg: 123 ,abc,1ab->123_abc_1ab
	 *
	 */
	private String buildFieldForHash(Object[] args, int[] fields) {
		StringBuilder sb = new StringBuilder();
		if (null != fields) {
			for (int i = 0; i < fields.length; i++) {
				Object arg = args[fields[i]];
				if (arg == null) {
					arg = "null";
				}
				sb.append(arg.toString()).append("_");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		String field = sb.toString();
		if (field.length() > 32) {
			field = MD5Util.MD5(field);
		}
		return field;
	}
}
