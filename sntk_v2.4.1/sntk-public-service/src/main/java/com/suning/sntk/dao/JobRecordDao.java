/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: JobRecordDao
 * Author:   88397670_张辉
 * Date:     2018-9-20 10:56
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao;

import com.suning.sntk.entity.JobRecordEntity;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：job执行记录持久层
 *
 * @author 88397670_张辉
 * @since 2018-9-20
 */
@DalMapper(namespace = "o2oJobRecord")
public interface JobRecordDao extends DalBaseDao<JobRecordEntity,Long> {
}
