/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideOrderLogDao
 * Author:   88395115_史小配
 * Date:     2018/8/18 9:35
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import java.util.List;

import com.suning.sntk.dto.vgo.GuideOrderLogDto;
import com.suning.sntk.entity.vgo.GuideOrderLog;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：导购预约记录接口
 *
 * @author 88395115_史小配
 * @since 2018/8/18
 */
@DalMapper("guideOrderLog")
@Trace
public interface GuideOrderLogDao extends DalBaseDao<GuideOrderLog, Long> {

    /**
     * 根据导购或会员更新预约记录
     *
     * @param guideOrderLog
     * @author 88395115_史小配
     * @since 9:48 2018/8/18
     */
    void updateOrderStatusFlag(@DalParam("guideOrderLog") GuideOrderLog guideOrderLog);

    /**
     * 查询预约操作记录
     *
     * @param bookCode
     * @author 18032490_赵亚奇
     */
    @DalParams({ "bookCode" })
    List<GuideOrderLogDto> queryGuideOrderLog(String bookCode);
}