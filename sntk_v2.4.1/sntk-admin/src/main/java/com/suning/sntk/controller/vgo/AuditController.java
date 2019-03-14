/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AuditController
 * Author:   88397670_张辉
 * Date:     2018-8-20 9:41
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.controller.vgo;

import java.io.IOException;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.suning.sntk.admin.service.vgo.AuditService;
import com.suning.sntk.dto.vgo.AuditGuideDto;
import com.suning.sntk.dto.vgo.BatchCloseGuideRespDto;
import com.suning.sntk.dto.vgo.GuideAuditDetailDto;
import com.suning.sntk.dto.vgo.GuideAuditInfoDto;
import com.suning.sntk.dto.vgo.GuideAuditReqDto;
import com.suning.sntk.dto.vgo.GuideInfoReqDto;
import com.suning.sntk.interceptor.RequestUserHolder;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.enums.vgo.BusinessTypesEnum;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 审核Controller
 *
 * @author 88396455_白振华
 * @since 10:48  2018-8-31
 */
@RestController
@Trace
@RequestMapping("/admin/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    /**
     * 批量操作导购开启
     *
     * @param auditGuideDto 包含导购工号集合
     * @author 88396455_白振华
     * @since 10:54  2018-8-31
     */
    @RequestMapping("/batchOpen")
    public Boolean batchOpen(@RequestBody AuditGuideDto auditGuideDto) {
        if (CollectionUtils.isEmpty(auditGuideDto.getGuideIds())) {
            return true;
        }
        return auditService.batchOperateOpenFlag(auditGuideDto, RequestUserHolder.getRequestUser().getStaffId(), VgoConstants.OPEN_FLAG_ON);
    }

    /**
     * 批量操作导购关闭
     *
     * @param auditGuideDto 包含导购工号集合
     * @author 88396455_白振华
     * @since 10:54  2018-8-31
     */
    @RequestMapping("/batchClose")
    public Boolean batchClose(@RequestBody AuditGuideDto auditGuideDto) {
        if (CollectionUtils.isEmpty(auditGuideDto.getGuideIds())) {
            return true;
        }
        return auditService
                .batchOperateOpenFlag(auditGuideDto, RequestUserHolder.getRequestUser().getStaffId(), VgoConstants.OPEN_FLAG_OFF);
    }

    /**
     * 批量添加V购标识
     *
     * @param auditGuideDto 包含导购工号集合
     * @author 88396455_白振华
     * @since 10:54  2018-8-31
     */
    @RequestMapping("/batchAddIsVgo")
    public Boolean batchAddIsVgo(@RequestBody AuditGuideDto auditGuideDto) {
        if (CollectionUtils.isEmpty(auditGuideDto.getGuideIds())) {
            return true;
        }
        return auditService.batchOperateVgoFlag(auditGuideDto, RequestUserHolder.getRequestUser().getStaffId(), VgoConstants.IS_VGO_FLAG);
    }

    /**
     * 批量删除V购标识
     *
     * @param auditGuideDto 包含导购工号集合
     * @author 88396455_白振华
     * @since 10:54  2018-8-31
     */
    @RequestMapping("/batchDeleteIsVgo")
    public Boolean batchDeleteIsVgo(@RequestBody AuditGuideDto auditGuideDto) {
        if (CollectionUtils.isEmpty(auditGuideDto.getGuideIds())) {
            return true;
        }
        return auditService.batchOperateVgoFlag(auditGuideDto, RequestUserHolder.getRequestUser().getStaffId(), VgoConstants.NOT_VGO_FLAG);
    }

    /**
     * 查询待审核、驳回导购信息
     *
     * @param guideInfoReqDto 　查询导购信息条件
     * @param pageable        　分页信息
     * @author 88396455_白振华
     * @since 14:23  2018-9-1
     */
    @RequestMapping("/queryAuditGuide")
    public Page<GuideAuditInfoDto> queryAuditGuideForPage(@RequestBody GuideInfoReqDto guideInfoReqDto, Pageable pageable) {
        return auditService.queryAuditGuideForPage(guideInfoReqDto, pageable);
    }

    /**
     * 查询待审核导购详细信息
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 10:01  2018-9-3
     */
    @RequestMapping("/queryGuideAuditDetail")
    public GuideAuditDetailDto queryGuideAuditDetail(String guideId, String businessType) {
        return auditService.queryGuideAuditDetail(guideId, businessType);
    }

    /**
     * 审核导购信息
     *
     * @param guideAuditReqDto 审核信息
     * @author 88396455_白振华
     * @since 16:56  2018-9-3
     */
    @RequestMapping("/auditGuide")
    public Boolean auditGuide(@RequestBody GuideAuditReqDto guideAuditReqDto) {
        return auditService.auditGuide(guideAuditReqDto, RequestUserHolder.getRequestUser().getStaffId());
    }

    /**
     * 批量导入关闭导购
     *
     * @param file 文件
     * @author 88396455_白振华
     * @since 14:19  2018-9-5
     */
    @RequestMapping("/batchImportCloseGuide")
    public BatchCloseGuideRespDto batchImportCloseGuide(MultipartFile file) throws IOException {
        return auditService.batchImportCloseGuide(file, RequestUserHolder.getRequestUser().getStaffId());
    }

    /**
     * 查询V购待审核导购数量
     *
     * @author 88396455_白振华
     * @since 9:03  2018-9-6
     */
    @RequestMapping("/countVGoToBeAudited")
    public Long countVgoToBeAuditedGuide(@RequestBody GuideInfoReqDto guideInfoReqDto) {
        guideInfoReqDto.setBusinessType(BusinessTypesEnum.ELECTRIC.getCode());
        guideInfoReqDto.setAuditFlag(VgoConstants.TO_BE_AUDITED);
        return auditService.countByBusinessTypeAndStatus(guideInfoReqDto);
    }

    /**
     * 查询母婴待审核导购数量
     *
     * @author 88396455_白振华
     * @since 9:03  2018-9-6
     */
    @RequestMapping("/countMomInfantToBeAudited")
    public Long countMomInfantToBeAuditedGuide(@RequestBody GuideInfoReqDto guideInfoReqDto) {
        guideInfoReqDto.setBusinessType(BusinessTypesEnum.MOM_INFANT.getCode());
        guideInfoReqDto.setAuditFlag(VgoConstants.TO_BE_AUDITED);
        return auditService.countByBusinessTypeAndStatus(guideInfoReqDto);
    }

    /**
     * 查询V购驳回导购数量
     *
     * @author 88396455_白振华
     * @since 9:03  2018-9-6
     */
    @RequestMapping("/countVGoReject")
    public Long countVGoReject(@RequestBody GuideInfoReqDto guideInfoReqDto) {
        guideInfoReqDto.setBusinessType(BusinessTypesEnum.ELECTRIC.getCode());
        guideInfoReqDto.setAuditFlag(VgoConstants.AUDIT_REJECT);
        return auditService.countByBusinessTypeAndStatus(guideInfoReqDto);
    }

    /**
     * 查询母婴驳回导购数量
     *
     * @author 88396455_白振华
     * @since 9:03  2018-9-6
     */
    @RequestMapping("/countMomInfantReject")
    public Long countMomInfantReject(@RequestBody GuideInfoReqDto guideInfoReqDto) {
        guideInfoReqDto.setBusinessType(BusinessTypesEnum.MOM_INFANT.getCode());
        guideInfoReqDto.setAuditFlag(VgoConstants.AUDIT_REJECT);
        return auditService.countByBusinessTypeAndStatus(guideInfoReqDto);
    }

}
