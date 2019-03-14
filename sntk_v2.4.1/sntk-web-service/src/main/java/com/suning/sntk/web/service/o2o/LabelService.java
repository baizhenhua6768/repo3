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
package com.suning.sntk.web.service.o2o;

import java.util.List;

import com.suning.sntk.rsf.dto.o2o.LabelDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 标签service；顾客评价的时候需要使用
 * 
 * @author 15050536 石键平
 */
public interface LabelService {

	/**
	 * 
	 * 根据星级查询标签列表信息
	 *
	 * @param star
	 *            星级：1；2；3；4；5
	 * @return
	 */
	public RsfResponseDto<List<LabelDto>> queryLabelsByStar(int star);

}
