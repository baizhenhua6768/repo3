/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: LabelServiceImpl.java
 * Author:   15050536
 * Date:     2018年3月24日 上午11:54:47
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.web.service.o2o.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suning.sntk.rsf.dto.o2o.LabelDto;
import com.suning.sntk.web.service.o2o.LabelService;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 *
 * @author 15050536 石键平
 */
@Service
public class LabelServiceImpl implements LabelService {

	@Override
	public RsfResponseDto<List<LabelDto>> queryLabelsByStar(int star) {
		return null;
	}

}
