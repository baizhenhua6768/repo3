/*
 * Copyright (C), 2002-2016, 苏宁易购电子商务有限公司
 * FileName: OrikaMapperFactory.java
 * Author:   11072910
 * Date:     2016年11月9日 下午7:39:21
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.support.copy;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * 基于orika实现的bean copy工厂 属性名需保持一致 get/set方法逻辑一致 若不一致需单独指定对应关系
 *
 * @author 11072910
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class OrikaMapperFactory {

	public static final MapperFactory mapperFactory;

	public static final MapperFacade mapper;

	static {
		mapperFactory = new DefaultMapperFactory.Builder().build();
		mapper = mapperFactory.getMapperFacade();
	}

	private OrikaMapperFactory() {
		// 私有构造方法
	}

	/**
	 * 通用bean copy方法 传入源对象和拷贝目标对象
	 *
	 * @param source
	 * @param destination
	 * @param <A>
	 * @param <B>
	 * @return
	 */
	public static <A, B> B map(A source, B destination) {
		if (source != null) {
			mapper.map(source, destination);
		}
		return destination;
	}

	/**
	 * 通用bean copy方法 传入源对象和拷贝目标对象Class
	 *
	 * @param source
	 * @param destination
	 * @param <A>
	 * @param <B>
	 * @return
	 */
	public static <A, B> B map(A source, Class<B> destination) {
		if(source == null){
			return null;
		}
		return mapper.map(source, destination);
	}
}
