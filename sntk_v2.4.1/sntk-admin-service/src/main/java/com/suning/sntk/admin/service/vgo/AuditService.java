/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AuditService
 * Author:   88397670_张辉
 * Date:     2018-8-20 10:51
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.vgo;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.suning.sntk.dto.vgo.AuditGuideDto;
import com.suning.sntk.dto.vgo.BatchCloseGuideRespDto;
import com.suning.sntk.dto.vgo.GuideAuditDetailDto;
import com.suning.sntk.dto.vgo.GuideAuditInfoDto;
import com.suning.sntk.dto.vgo.GuideAuditReqDto;
import com.suning.sntk.dto.vgo.GuideInfoReqDto;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 审核导购服务
 *
 * @author 88396455_白振华
 * @since 10:48  2018-8-31
 */
public interface AuditService {

    /**
     * 批量开启、关闭V购
     *
     * @param auditGuideDto 包含导购工号集合
     * @param staffId       操作人工号
     * @param openFlag      开关标识
     * @author 88396455_白振华
     * @since 10:54  2018-8-31
     */
    Boolean batchOperateOpenFlag(AuditGuideDto auditGuideDto, String staffId, String openFlag);

    /**
     * 批量添加、删除V购标识
     *
     * @param auditGuideDto 包含导购工号集合
     * @param staffId       操作人工号
     * @param isVgo         V购标识
     * @author 88396455_白振华
     * @since 10:54  2018-8-31
     */
    Boolean batchOperateVgoFlag(AuditGuideDto auditGuideDto, String staffId, Integer isVgo);

    /**
     * 查询待审核、驳回导购信息
     *
     * @param guideInfoReqDto 　查询导购信息条件
     * @param pageable        　分页信息
     * @author 88396455_白振华
     * @since 14:23  2018-9-1
     */
    Page<GuideAuditInfoDto> queryAuditGuideForPage(GuideInfoReqDto guideInfoReqDto, Pageable pageable);

    /**
     * 查询待审核导购详细信息
     *
     * @param guideId      导购工号
     * @param businessType 业态
     * @author 88396455_白振华
     * @since 9:31  2018-9-3
     */
    GuideAuditDetailDto queryGuideAuditDetail(String guideId, String businessType);

    /**
     * 审批员工信息
     *
     * @param staffId          员工工号
     * @param guideAuditReqDto 审核信息
     * @author 88396455_白振华
     * @since 9:10  2018-9-3
     */
    Boolean auditGuide(GuideAuditReqDto guideAuditReqDto, String staffId);

    /**
     * 批量导入关闭导购
     *
     * @param staffId 员工工号
     * @param file    文件对象
     * @author 88396455_白振华
     * @since 9:10  2018-9-3
     */
    BatchCloseGuideRespDto batchImportCloseGuide(MultipartFile file, String staffId) throws IOException;

    /**
     * 根据业态和审核状态查询导购数量
     *
     * @param guideInfoReqDto 　查询导购信息条件
     * @author 88396455_白振华
     * @since 9:11  2018-9-6
     */
    Long countByBusinessTypeAndStatus(GuideInfoReqDto guideInfoReqDto);
}
