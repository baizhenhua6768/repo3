/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideAuditDao
 * Author:   88397670_张辉
 * Date:     2018-8-31 17:16
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import com.suning.sntk.dto.vgo.GuideAuditDetailDto;
import com.suning.sntk.dto.vgo.GuideAuditInfoDto;
import com.suning.sntk.dto.vgo.GuideInfoReqDto;
import com.suning.sntk.entity.vgo.GuideAuditInfo;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalMethod;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-8-31
 */
@DalMapper(namespace = "guideAudit")
public interface GuideAuditDao extends DalBaseDao<GuideAuditInfo, Long> {

    /**
     * 根据导购工号查询导购待审核信息
     *
     * @param guideId 导购工号
     * @author 88397670_张辉
     * @since 10:04 2018-9-4
     */
    GuideAuditInfo queryAuditGuideInfo(@DalParam("guideId") String guideId);

    /**
     * 查询待审核、驳回导购信息
     *
     * @param guideInfoReqDto 　查询导购信息条件
     * @param pageable        　分页信息
     * @author 88396455_白振华
     * @since 14:23  2018-9-1
     */
    @DalParams({ "guideReqDto" })
    Page<GuideAuditInfoDto> queryAuditGuideForPage(GuideInfoReqDto guideInfoReqDto, Pageable pageable);

    /**
     * 根据工号查询待审核信息
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 14:23  2018-9-1
     */
    @DalParams({ "guideId" })
    GuideAuditInfo queryToBeAuditedGuide(String guideId);

    /**
     * 根据工号查询待审核详细信息
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 14:23  2018-9-1
     */
    @DalParams({ "guideId" })
    GuideAuditDetailDto queryToBeAuditedDetail(String guideId);

    /**
     * 根据业态和审核状态查询导购数量
     *
     * @param guideInfoReqDto 　查询导购信息条件
     * @author 88396455_白振华
     * @since 9:11  2018-9-6
     */
    @DalMethod(name = "queryAuditGuideForPage#count", params = { "guideReqDto" })
    Long countByBusinessTypeAndStatus(GuideInfoReqDto guideInfoReqDto);

    /**
     * 查询待审核、驳回导购信息
     *
     * @param guideInfoReqDto 　查询导购信息条件
     * @param pageable        　分页信息
     * @author 88396455_白振华
     * @since 14:23  2018-9-1
     */
    @DalParams({ "guideReqDto" })
    Page<GuideAuditInfoDto> queryAuditGuidePage(GuideInfoReqDto guideInfoReqDto, Pageable pageable);
}
