/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: LabelDao
 * Author:   88402362 欧小冬
 * Date:    2018/7/9
 * Description: //模块目的、功能描述
 * History: //修改记录
 * &lt;author&gt;      &lt;time&gt;      &lt;version&gt;    &lt;desc&gt;
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.relation;

import com.suning.sntk.entity.relation.Label;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：
 *
 * @author 88402362_欧小冬
 * @since 2018/7/9
 */
@DalMapper(namespace = "relationLabel")
public interface LabelDao extends DalBaseDao<Label,Long> {
}
