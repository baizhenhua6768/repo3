/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: DictService.java
 * Author:   15050536
 * Date:     2017年7月10日 上午10:47:38
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.service.configuration;

import java.util.List;

import com.suning.sntk.entity.DictEntity;
import com.suning.sntk.support.enums.DictEnum;
import com.suning.sntk.vo.DictListVo;
import com.suning.sntk.vo.DictVo;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.service.DalService;

/**
 * 数据字典service
 *
 * @author 15050536 石键平
 */
public interface DictService extends DalService<DictEntity, Long> {

	/**
	 * 字典表的添加
	 *
	 * @param dictVo
	 *            添加字典
	 * @return
	 */
	boolean addDict(DictVo dictVo);

	/**
	 * 字典表的删除
	 *
	 * @param id
	 * @return
	 */
	boolean deleteDict(Long id, String user);

	/**
	 * 根据类型和code查询字典表数据
	 *
	 * @param type
	 *            类型，如sign(验签)
	 * @param code
	 *            编码，如spos
	 * @return
	 */
	DictVo findDictByTypeAndCode(String type, String code);

	/**
	 * 根据字典表枚举值，查询字典表的数据
	 *
	 * @param dictEnum
	 * @return
	 */
	DictVo findDictByDictEnum(DictEnum dictEnum);

	/**
	 * 编辑字典
	 * 
	 * @param dictVo
	 * @param user
	 * @return
	 */
	boolean editDictVo(DictVo dictVo, String user);

	/**
	 * 根据类型查询字典信息
	 * 
	 * @param type
	 * @return
	 */
	List<DictVo> queryDictListByType(String type);

	/**
	 * 分页查询字典表数据
	 *
	 * @param pageable
	 * @return
	 */
	DictListVo queryPageDictList(Pageable pageable);

}
