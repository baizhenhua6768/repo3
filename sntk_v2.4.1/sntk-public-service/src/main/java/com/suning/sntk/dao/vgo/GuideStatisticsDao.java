/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideStatisticsDao
 * Author:   88395115_史小配
 * Date:     2018/8/16 17:27
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import com.suning.sntk.dto.vgo.StatisticsInfoDto;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParam;

/**
 * 功能描述：V购预约统计dao
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
@Trace
@DalMapper("guideStatistics")
public interface GuideStatisticsDao{
    /**
     * 添加V购预约统计
     *
     * @author 88395115_史小配
     * @param statisticsDto
     * @since 15:50 2018/8/17
     */
    void addGuideStatistics(@DalParam("dto") StatisticsInfoDto statisticsDto);
}