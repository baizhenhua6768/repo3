/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: LabelService.java
 * Author:   15050536
 * Date:     2018年3月24日 上午11:49:16
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.service.o2o;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.suning.sntk.entity.o2o.Label;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.service.DalService;

/**
 * 标签service；顾客评价的时候需要使用
 * 
 * @author 15050536 石键平
 */
public interface LabelService extends DalService<Label, String> {

	/**
	 * 
	 * 根据星级查询标签列表信息
	 *
	 * @param star
	 *            星级：1；2；3；4；5
	 * @return
	 */
	public List<Label> queryLabelsByStar(int star);

	/**
	 * 分页查询评价标签
	 */
	public Page<Label> queryPageLabels(Pageable pageable);

	/**
	 * 查询所有的标签
	 *
	 * @return
	 */
	public List<Label> queryAllLabel();

	/**
	 * 查询全部星级及对应标签
	 * 
	 * @return
	 */
	public Map<String, Collection<Label>> queryAllLabelMap();

	/**
	 * 更新标签
	 * 
	 * @param label
	 *            标签
	 */
	public boolean updateLabel(Label label);

	/**
	 * 删除标签
	 * 
	 * @param labelCode
	 *            标签编码
	 * @return
	 */
	public boolean deleteLabel(String labelCode);

	/**
	 * 保存标签
	 * 
	 * @param label
	 * @return
	 */
	public void saveLabel(Label label);

	/**
	 * 判断编码是否重复
	 * 
	 * @param labelCode
	 * @return (true：重复；false：不重复)
	 */
	public boolean isRepeatCode(String labelCode);

	/**
	 * 通过星级判断编码名称是否重复(同星级情况)
	 * 
	 * 
	 * @param starLevel
	 * @param labelName
	 * @return (true：重复；false：不重复)
	 */
	public boolean isRepeatName(Integer starLevel, String labelName);

	/**
	 * 获取当前星级的可入库的标签code
	 *
	 * @param star
	 *            当前星级
	 * @return
	 */
	public String findNextLabelCodeByStar(Integer star);

}
