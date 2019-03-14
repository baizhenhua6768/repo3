/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: DictDao
 * Author:   17092777 李明
 * Date:     2017/10/20 16:13
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.dao;

import java.util.List;

import com.suning.sntk.entity.DictEntity;
import com.suning.sntk.vo.DictVo;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalMethod;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 〈字典表增删改查〉<br>
 *
 * @author 17092777 李明
 */
@DalMapper("dict")
public interface DictDao extends DalBaseDao<DictEntity, Long> {

	/**
	 * 根据类型和code查询字典表数据
	 *
	 * @param type
	 *            类型，如sign(验签)
	 * @param code
	 *            编码，如spos
	 * @return
	 */
	@DalMethod(name = "findDictByTypeAndCode", params = { "type", "code" })
	DictVo findDictByTypeAndCode(String type, String code);

	/**
	 * 根据类型查询字典列表
	 * 
	 * @param type
	 * @return
	 */
	@DalMethod(name = "selectDictListByType", params = { "type" })
	List<DictVo> selectDictListByType(String type);

	@DalMethod(name = "queryPageDictList")
	Page<DictVo> queryPageDictList(Pageable pageable);

}
