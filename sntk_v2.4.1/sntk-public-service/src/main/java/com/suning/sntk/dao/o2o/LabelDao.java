/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: LabelDao.java
 * Author:   15050536
 * Date:     2018年3月24日 上午11:58:22
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.dao.o2o;

import java.util.List;

import com.suning.sntk.entity.o2o.Label;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalMethod;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 标签业务涉及的dao
 *
 * @author 15050536 石键平
 */
@DalMapper("label")
public interface LabelDao extends DalBaseDao<Label, String> {

	/**
	 * 
	 * 根据星级查询标签列表信息
	 *
	 * @param starLevel
	 *            星级：1；2；3；4；5
	 * @return
	 */
	@DalMethod(name = "queryLabelsByStar", params = { "starLevel" })
	public List<Label> queryLabelsByStar(Integer starLevel);

	/**
	 * 通过标签编码查询标签条数
	 * 
	 * @param labelCode
	 *            标签编码
	 * @return
	 */
	@DalMethod(name = "countByCode", params = { "labelCode" })
	public long countByCode(String labelCode);

	/**
	 * 
	 * @param starLevel
	 *            星级数
	 * @param labelName
	 *            标签名字
	 * @return
	 */
	@DalMethod(name = "countByName", params = { "starLevel", "labelName" })
	public long countByName(Integer starLevel, String labelName);

	/**
	 * 查询当前星级下最大的labelCode
	 */
	@DalMethod(name = "findLabelCodeByStar", params = { "starLevel" })
	public String findLabelCodeByStar(Integer starLevel);

}
