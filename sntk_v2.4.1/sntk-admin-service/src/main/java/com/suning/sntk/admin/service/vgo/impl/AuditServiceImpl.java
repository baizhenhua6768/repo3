/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AuditServiceImpl
 * Author:   88397670_张辉
 * Date:     2018-8-20 10:51
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.vgo.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.suning.baozi.rsfservice.dto.O2oVGuideInfoDto;
import com.suning.nsfuaa.employee.dto.EmployeeExt;
import com.suning.sntk.admin.dao.vgo.CategoryRelAdminDao;
import com.suning.sntk.admin.dao.vgo.GuideInfoAdminDao;
import com.suning.sntk.admin.service.vgo.AuditService;
import com.suning.sntk.admin.service.vgo.StoreInfoService;
import com.suning.sntk.admin.util.ExcelParseUtils;
import com.suning.sntk.consumer.BaoziConsumerService;
import com.suning.sntk.consumer.NsfbusConsumerService;
import com.suning.sntk.consumer.NsfuaaConsumerService;
import com.suning.sntk.dao.vgo.CategoryDao;
import com.suning.sntk.dao.vgo.GuideAuditDao;
import com.suning.sntk.dao.vgo.O2oGuideInfoDao;
import com.suning.sntk.dao.vgo.ServerItemDao;
import com.suning.sntk.dto.vgo.AuditGuideDto;
import com.suning.sntk.dto.vgo.BatchCloseGuideRespDto;
import com.suning.sntk.dto.vgo.FailParseGuideExcelDto;
import com.suning.sntk.dto.vgo.GuideAuditDetailDto;
import com.suning.sntk.dto.vgo.GuideAuditInfoDto;
import com.suning.sntk.dto.vgo.GuideAuditReqDto;
import com.suning.sntk.dto.vgo.GuideInfoReqDto;
import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;
import com.suning.sntk.entity.vgo.CategoryRelInfo;
import com.suning.sntk.entity.vgo.GuideAuditInfo;
import com.suning.sntk.entity.vgo.GuideInfo;
import com.suning.sntk.entity.vgo.O2oGuideInfo;
import com.suning.sntk.entity.vgo.ReservationGuide;
import com.suning.sntk.service.vgo.VgoModifyRedisService;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.enums.VgoErrorEnum;
import com.suning.sntk.support.enums.vgo.AuditOpinionEnum;
import com.suning.sntk.support.enums.vgo.BusinessTypesEnum;
import com.suning.sntk.support.enums.vgo.StarLevelEnum;
import com.suning.sntk.support.enums.vgo.StarLevelEvaluationEnum;
import com.suning.sntk.support.enums.vgo.VgoHierarchyEnum;
import com.suning.sntk.support.exception.enums.CommentErrorEnum;
import com.suning.sntk.support.util.CommonUtils;
import com.suning.sntk.util.VgoAdminUtils;
import com.suning.store.commons.lang.BeanUtils;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 审核导购服务
 *
 * @author 88396455_白振华
 * @since 10:48  2018-8-31
 */
@Service
public class AuditServiceImpl implements AuditService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditServiceImpl.class);

    /**
     * 正则表达式-8位纯数字
     */
    private static final String REGEX_NUM_EIGHT = "\\d{8}";

    @Autowired
    private GuideInfoAdminDao guideInfoAdminDao;

    @Autowired
    private O2oGuideInfoDao o2oGuideInfoDao;

    @Autowired
    private GuideAuditDao guideAuditDao;

    @Autowired
    private NsfuaaConsumerService nsfuaaConsumerService;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryRelAdminDao categoryRelDao;

    @Autowired
    private ServerItemDao serverItemDao;

    @Autowired
    private VgoModifyRedisService vgoModifyRedisService;

    @Autowired
    private NsfbusConsumerService nsfbusConsumerService;

    @Autowired
    private StoreInfoService storeInfoService;

    @Autowired
    private BaoziConsumerService baoziConsumerService;

    @Transactional
    @Override
    public Boolean batchOperateOpenFlag(AuditGuideDto auditGuideDto, String staffId, String openFlag) {
        List<String> guideIds = auditGuideDto.getGuideIds();
        List<GuideInfo> guideInfos = guideInfoAdminDao.queryGuideInfoList(guideIds);
        List<O2oGuideInfo> o2oGuideInfos = o2oGuideInfoDao.queryGuideInfoList(guideIds);
        batchOperateOpenFlag(staffId, guideInfos, o2oGuideInfos, openFlag);
        guideInfoAdminDao.batchUpdateSkipNull(guideInfos);
        o2oGuideInfoDao.batchUpdateSkipNull(o2oGuideInfos);
        //更新redis
        if (VgoConstants.OPEN_FLAG_OFF.equals(openFlag)) {
            //向麦琪推送关闭信息
            List<O2oVGuideInfoDto> o2oVGuideInfoDtos = obtainNoticeBaoziParam(guideInfos);
            if (CollectionUtils.isNotEmpty(o2oVGuideInfoDtos)) {
                baoziConsumerService.updateGuideInfoTable(o2oVGuideInfoDtos);
            }
            for (GuideInfo guideInfo : guideInfos) {
                vgoModifyRedisService.deleteGuideInfoCache(guideInfo.getGuideId(), guideInfo.getStoreCode(), guideInfo.getBusinessType());
                storeInfoService.handleStoreLocationRedisCache(guideInfo.getStoreCode(), StoreInfoService.VGO_CLOSE);
            }
        } else {
            for (GuideInfo guideInfo : guideInfos) {
                if (guideInfo != null && guideInfo.getIsVgo() == VgoConstants.IS_VGO) {
                    storeInfoService.handleStoreLocationRedisCache(guideInfo.getStoreCode(), StoreInfoService.VGO_OPEN);
                }
            }
        }
        return true;
    }

    /**
     * 获取通知麦琪参数
     */
    private List<O2oVGuideInfoDto> obtainNoticeBaoziParam(List<GuideInfo> guideInfos) {
        List<O2oVGuideInfoDto> o2oVGuideInfoDtos = new ArrayList<O2oVGuideInfoDto>();
        for (GuideInfo guideInfo : guideInfos) {
            O2oVGuideInfoDto o2oVGuideInfoDto = new O2oVGuideInfoDto();
            o2oVGuideInfoDto.setBusinessType(guideInfo.getBusinessType());
            o2oVGuideInfoDto.setDeleteFlag(guideInfo.getDeleteFlag().toString());
            o2oVGuideInfoDto.setGuideId(guideInfo.getGuideId());
            o2oVGuideInfoDto.setStoreCode(guideInfo.getStoreCode());
            o2oVGuideInfoDto.setDimissionFlag(guideInfo.getDimissionFlag());
            o2oVGuideInfoDto.setIsVgo(guideInfo.getIsVgo().toString());
            o2oVGuideInfoDto.setOpenFlag(guideInfo.getOpenFlag());
            o2oVGuideInfoDtos.add(o2oVGuideInfoDto);
        }
        return o2oVGuideInfoDtos;
    }

    @Transactional
    @Override
    public Boolean batchOperateVgoFlag(AuditGuideDto auditGuideDto, String staffId, Integer isVgo) {
        List<String> guideIds = auditGuideDto.getGuideIds();
        List<GuideInfo> guideInfos = guideInfoAdminDao.queryGuideInfoList(guideIds);
        List<O2oGuideInfo> o2oGuideInfos = o2oGuideInfoDao.queryGuideInfoList(guideIds);
        batchOperateVgoFlag(staffId, guideInfos, o2oGuideInfos, isVgo);
        guideInfoAdminDao.batchUpdateSkipNull(guideInfos);
        o2oGuideInfoDao.batchUpdateSkipNull(o2oGuideInfos);
        //更新redis
        if (VgoConstants.IS_NOT_VGO.equals(isVgo)) {
            //向麦琪推送关闭V购标识信息
            List<O2oVGuideInfoDto> o2oVGuideInfoDtos = obtainNoticeBaoziParam(guideInfos);
            if (CollectionUtils.isNotEmpty(o2oVGuideInfoDtos)) {
                baoziConsumerService.updateGuideInfoTable(o2oVGuideInfoDtos);
            }
            for (GuideInfo guideInfo : guideInfos) {
                vgoModifyRedisService.deleteGuideInfoCache(guideInfo.getGuideId(), guideInfo.getStoreCode(), guideInfo.getBusinessType());
                storeInfoService.handleStoreLocationRedisCache(guideInfo.getStoreCode(), StoreInfoService.VGO_UNSET);
            }
        } else {
            for (GuideInfo guideInfo : guideInfos) {
                if (guideInfo != null && VgoConstants.OPEN_FLAG_ON.equals(guideInfo.getOpenFlag())) {
                    storeInfoService.handleStoreLocationRedisCache(guideInfo.getStoreCode(), StoreInfoService.VGO_SET);
                }
            }
        }
        return true;
    }

    /**
     * 批量开启、关闭V购
     */
    private void batchOperateVgoFlag(String staffId, List<GuideInfo> guideInfos, List<O2oGuideInfo> o2oGuideInfos, Integer isVgo) {
        Timestamp timestamp = CommonUtils.obtainCurrentTimestamp();
        for (GuideInfo guideInfo : guideInfos) {
            guideInfo.setIsVgo(isVgo);
            guideInfo.setUpdateTime(timestamp);
            guideInfo.setUpdatePerson(staffId);
        }
        for (O2oGuideInfo o2oGuideInfo : o2oGuideInfos) {
            o2oGuideInfo.setIsVgo(isVgo);
            o2oGuideInfo.setUpdateTime(timestamp);
            o2oGuideInfo.setUpdatePerson(staffId);
        }
    }

    /**
     * 批量开启、关闭导购
     */
    private void batchOperateOpenFlag(String staffId, List<GuideInfo> guideInfos, List<O2oGuideInfo> o2oGuideInfos, String openFlag) {
        Timestamp timestamp = CommonUtils.obtainCurrentTimestamp();
        for (GuideInfo guideInfo : guideInfos) {
            guideInfo.setOpenFlag(openFlag);
            guideInfo.setUpdateTime(timestamp);
            guideInfo.setUpdatePerson(staffId);
        }
        for (O2oGuideInfo o2oGuideInfo : o2oGuideInfos) {
            o2oGuideInfo.setOpenFlag(openFlag);
            o2oGuideInfo.setUpdateTime(timestamp);
            o2oGuideInfo.setUpdatePerson(staffId);
        }
    }

    @Override
    public Page<GuideAuditInfoDto> queryAuditGuideForPage(GuideInfoReqDto guideInfoReqDto, Pageable pageable) {
        String hierarchy = guideInfoReqDto.getHierarchy();
        List<String> codes = VgoHierarchyEnum.obtainCodeList();
        Page<GuideAuditInfoDto> result = null;
        if (StringUtils.isNotBlank(hierarchy) && !codes.contains(hierarchy)) {
            result = guideAuditDao.queryAuditGuidePage(guideInfoReqDto, pageable);
            convertAuditReason(result);
        } else {
            result = guideAuditDao.queryAuditGuideForPage(guideInfoReqDto, pageable);
            convertAuditReason(result);
        }
        if (null != result && CollectionUtils.isNotEmpty(result.getContent())) {
            List<GuideAuditInfoDto> content = result.getContent();
            for (GuideAuditInfoDto guideAuditInfoDto : content) {
                String orgId = guideAuditInfoDto.getOrgId();
                if (StringUtils.isNotBlank(orgId)) {
                    guideAuditInfoDto.setOrgName(nsfbusConsumerService.querySaleOrgName(orgId));
                }
            }
        }
        return result;
    }

    /**
     * 转换驳回原因标号至文字
     */
    private void convertAuditReason(Page<GuideAuditInfoDto> guideAuditInfoDtos) {

        if (null == guideAuditInfoDtos || CollectionUtils.isEmpty(guideAuditInfoDtos.getContent())) {
            return;
        }
        for (GuideAuditInfoDto auditInfoDto : guideAuditInfoDtos.getContent()) {
            String auditReason = auditInfoDto.getAuditReason();
            if (StringUtils.isBlank(auditReason)) {
                continue;
            }
            String[] reasonArray = auditReason.split("#");
            List<String> auditReasons = Arrays.asList(reasonArray);
            StringBuilder builder = new StringBuilder();
            if (auditReasons.contains(AuditOpinionEnum.HEAD_PHOTO_NOT_STANDARD.getCode())) {
                builder.append(AuditOpinionEnum.HEAD_PHOTO_NOT_STANDARD.getDesc()).append(VgoConstants.SPLIT_FLAG);
            }
            if (auditReasons.contains(AuditOpinionEnum.INTRODUCTION_NOT_STANDARD.getCode())) {
                builder.append(AuditOpinionEnum.INTRODUCTION_NOT_STANDARD.getDesc()).append(VgoConstants.SPLIT_FLAG);
            }
            if (auditReasons.contains(AuditOpinionEnum.OTHER_REASONS.getCode())) {
                builder.append(reasonArray[reasonArray.length - 1]);
            }
            String audit = builder.toString();
            auditInfoDto.setAuditReason(audit.endsWith(VgoConstants.SPLIT_FLAG) ? audit.substring(0, audit.length() - 1) : audit);
        }
    }

    @Override
    public GuideAuditDetailDto queryGuideAuditDetail(String guideId, String businessType) {
        GuideAuditDetailDto auditDetailDto = guideAuditDao.queryToBeAuditedDetail(guideId);
        //查询人员组织扩展信息
        EmployeeExt employeeExt = nsfuaaConsumerService.queryEmployeeExt(guideId);
        if (null != employeeExt) {
            //设置手机号
            auditDetailDto.setTele(employeeExt.getCellphone());
        }
        if (StringUtils.isNotBlank(auditDetailDto.getOrgId())) {
            String orgId = auditDetailDto.getOrgId();
            if (StringUtils.isNotBlank(orgId)) {
                auditDetailDto.setOrgName(nsfbusConsumerService.querySaleOrgName(orgId));
            }
        }
        //设置擅长品类/服务项目
        if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
            String categoryIds = auditDetailDto.getCategoryIds();
            if (StringUtils.isNotBlank(categoryIds)) {
                List<String> categoryIdList = Arrays.asList(categoryIds.split(VgoConstants.SPLIT_FLAG));
                List<VcategoryRelInfoDto> vcategoryRelInfoDtos = categoryDao.queryGuideCategory(categoryIdList);
                auditDetailDto.setCategoryNames(VgoAdminUtils.obtainCategoryNames(vcategoryRelInfoDtos));
            }
        } else if (BusinessTypesEnum.MOM_INFANT.getCode().equals(businessType)) {
            String serverItems = auditDetailDto.getServerItems();
            auditDetailDto.setServerItemNames(VgoAdminUtils
                    .obtainServerItemNames(
                            StringUtils.isNotBlank(serverItems) ? Arrays.asList(serverItems.split(VgoConstants.SPLIT_FLAG)) : null));
        }
        return auditDetailDto;
    }

    @Override
    @Transactional
    public Boolean auditGuide(GuideAuditReqDto guideAuditReqDto, String staffId) {
        //审核通过操作
        if (VgoConstants.AUDIT_ADOPT == guideAuditReqDto.getAuditFlag()) {
            String guideId = guideAuditReqDto.getGuideId();
            //查询导购擅长品类，以备更新redis使用
            GuideAuditInfo auditInfo = guideAuditDao.queryToBeAuditedGuide(guideId);
            //查询导购信息是否存在于管理表中,存在更新，不存在新增
            GuideInfo guideExists = guideInfoAdminDao.getGuideInfo(guideId);
            String businessType = auditInfo.getBusinessType();
            boolean isVgo = BusinessTypesEnum.ELECTRIC.getCode().equals(businessType);
            boolean isMomInfant = BusinessTypesEnum.MOM_INFANT.getCode().equals(businessType);
            if (null != guideExists && StringUtils.isNotBlank(guideExists.getGuideId())) {
                //更新导购信息
                updateGuideInfo(guideAuditReqDto, auditInfo, guideExists, staffId);
                updateRelevantInfo(guideId, auditInfo, isVgo, isMomInfant);
            } else {
                //新增导购信息
                addGuideInfo(guideAuditReqDto, staffId, auditInfo);
                addRelevantInfo(guideId, auditInfo, isVgo, isMomInfant);
                // 新增并且是V购
                if (VgoConstants.IS_VGO == guideAuditReqDto.getIsVgo()) {
                    storeInfoService.handleStoreLocationRedisCache(auditInfo.getStoreCode(), StoreInfoService.VGO_ADD);
                }
            }
        }
        //更新审核表
        updateAuditInfo(guideAuditReqDto, staffId);

        return true;
    }

    /**
     * 根据业态增加导购相关信息
     */
    private void addRelevantInfo(String guideId, GuideAuditInfo auditInfo, boolean isVgo, boolean isMomInfant) {
        if (isVgo) {
            //新增导购-擅长品类对应关系
            addCategoryRel(guideId, auditInfo);
        } else if (isMomInfant) {
            //新增导购-服务项目对应关系
            addServerItem(guideId, auditInfo);
        }
    }

    /**
     * 根据业态更新相关信息
     */
    private void updateRelevantInfo(String guideId, GuideAuditInfo auditInfo, boolean isVgo, boolean isMomInfant) {
        if (isVgo) {
            //更新擅长品类信息
            updateCategoryRel(guideId, auditInfo);
        } else if (isMomInfant) {
            //更新服务项目
            updateServerItem(guideId, auditInfo);
        }
    }

    @Override
    public BatchCloseGuideRespDto batchImportCloseGuide(MultipartFile file, String staffId) throws IOException {
        //解析失败信息集合
        List<FailParseGuideExcelDto> failList = new ArrayList<FailParseGuideExcelDto>();
        //解析成功信息集合
        List<String> successList = new ArrayList<String>();
        try (Workbook workbook = ExcelParseUtils.initWorkbook(file.getOriginalFilename(), file.getInputStream())) {
            if (null == workbook) {
                Validators.throwAnyway(CommentErrorEnum.INIT_EXCEL_FAIL);
                return null;
            }
            Sheet sheet = workbook.getSheetAt(0);
            if (null == sheet) {
                Validators.throwAnyway(CommentErrorEnum.INIT_EXCEL_FAIL);
                return null;
            }
            int lastRowNum = sheet.getLastRowNum();
            Validators.ifInValid(lastRowNum > 1000).thenThrow(VgoErrorEnum.MORE_THAN_MAXNUM);
            //迭代sheet
            Iterator<Row> sheetIterator = sheet.iterator();
            while (sheetIterator.hasNext()) {
                Row row = sheetIterator.next();
                //标题行不需要，从第二行开始解析
                if (row.getRowNum() == 0) {
                    continue;
                }
                //只有一列，只取第一列
                Cell cell = row.getCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String guideId = cell.getStringCellValue();
                if (guideId.matches(REGEX_NUM_EIGHT)) {
                    successList.add(guideId);
                } else {
                    FailParseGuideExcelDto failExcel = new FailParseGuideExcelDto();
                    failExcel.setColumn(cell.getColumnIndex() + 1);
                    failExcel.setRow(cell.getRowIndex() + 1);
                    failExcel.setGuideiId(guideId);
                    failList.add(failExcel);
                }
            }
        }
        LOGGER.info("AuditServiceImpl.batchCloseGuide 操作人：staffId:{}上传的Excel,符合规范的导购工号信息：{}", staffId, successList);
        LOGGER.info("AuditServiceImpl.batchCloseGuide 操作人：staffId:{}上传的Excel,不符合规范的导购工号信息：{}", staffId, failList);
        //批量关闭导购
        if (CollectionUtils.isNotEmpty(successList)) {
            AuditGuideDto auditGuideDto = new AuditGuideDto();
            auditGuideDto.setGuideIds(successList);
            batchOperateOpenFlag(auditGuideDto, staffId, VgoConstants.OPEN_FLAG_OFF);
        }
        return new BatchCloseGuideRespDto(failList, failList.size(), successList.size());
    }

    @Override
    public Long countByBusinessTypeAndStatus(GuideInfoReqDto guideInfoReqDto) {
        return guideAuditDao.countByBusinessTypeAndStatus(guideInfoReqDto);
    }

    /**
     * 新增 导购-服务项目对应关系
     */
    private void addServerItem(String guideId, GuideAuditInfo auditInfo) {
        String serverItemStr = auditInfo.getServerItems();
        if (StringUtils.isNotBlank(serverItemStr)) {
            List<String> serverItems = Arrays.asList(serverItemStr.split(VgoConstants.SPLIT_FLAG));
            List<ReservationGuide> reservationGuides = new ArrayList<ReservationGuide>();
            for (String serverItem : serverItems) {
                ReservationGuide reservationGuide = new ReservationGuide();
                reservationGuide.setGuideId(guideId);
                reservationGuide.setServiceItemType(VgoConstants.SERVER_ITEM);
                reservationGuide.setServiceItemVal(serverItem);
                reservationGuides.add(reservationGuide);
            }
            serverItemDao.batchInsert(reservationGuides);
        }
    }

    /**
     * 新增 导购-擅长品类对应关系
     */
    private void addCategoryRel(String guideId, GuideAuditInfo auditInfo) {
        String categoryIdStr = auditInfo.getCategoryIds();
        if (StringUtils.isNotBlank(categoryIdStr)) {
            List<String> categoryIds = Arrays.asList(categoryIdStr.split(VgoConstants.SPLIT_FLAG));
            List<CategoryRelInfo> categoryRelInfos = new ArrayList<CategoryRelInfo>();
            for (String categoryId : categoryIds) {
                CategoryRelInfo categoryRelInfo = new CategoryRelInfo();
                categoryRelInfo.setGuideId(guideId);
                categoryRelInfo.setCategoryId(NumberUtils.toLong(categoryId));
                categoryRelInfos.add(categoryRelInfo);
            }
            categoryRelDao.batchInsert(categoryRelInfos);
        }
    }

    /**
     * 新增导购信息
     */
    private void addGuideInfo(GuideAuditReqDto guideAuditReqDto, String staffId, GuideAuditInfo auditInfo) {
        O2oGuideInfo o2oGuideInfo = new O2oGuideInfo();
        GuideInfo guideInfo = new GuideInfo();
        String businessType = auditInfo.getBusinessType();
        guideInfo.setGuideId(guideAuditReqDto.getGuideId());
        guideInfo.setStoreCode(auditInfo.getStoreCode());
        guideInfo.setGuidePhoto(auditInfo.getGuidePhoto());
        guideInfo.setGuideName(auditInfo.getGuideName());
        //接单数默认90-100
        guideInfo.setOrderNum(90L + new Random().nextInt(10));
        //默认五星级
        guideInfo.setStarLevel(StarLevelEnum.FIVE_STAR.getCode());
        //设置导购联系方式
        EmployeeExt employeeExt = nsfuaaConsumerService.queryEmployeeExt(guideAuditReqDto.getGuideId());
        if (null != employeeExt) {
            guideInfo.setTele(employeeExt.getCellphone());
        }
        guideInfo.setIntroduction(auditInfo.getIntroduction());
        //默认开启
        guideInfo.setOpenFlag(VgoConstants.STORE_V_FLAG_ON);
        guideInfo.setSaleAge(auditInfo.getSaleAge());
        guideInfo.setHierarchy(auditInfo.getHierarchy());
        guideInfo.setAttach(auditInfo.getAttach());
        guideInfo.setGrade(transfStarLevelGrade(StarLevelEnum.FIVE_STAR.getCode()));
        guideInfo.setBusinessType(businessType);
        guideInfo.setCreateTime(CommonUtils.obtainCurrentTimestamp());
        guideInfo.setCreatePerson(staffId);
        guideInfo.setUpdateTime(CommonUtils.obtainCurrentTimestamp());
        guideInfo.setUpdatePerson(staffId);
        guideInfo.setIsVgo(guideAuditReqDto.getIsVgo());
        guideInfo.setDeleteFlag(VgoConstants.VGO_NORMAL_FLAG);
        guideInfo.setDimissionFlag(VgoConstants.ON_THE_JOB);
        guideInfo.setIsCompeletePicChange(VgoConstants.NOT_COMPLETE_CHANGE_PIC);
        guideInfo.setIsFromMoss(VgoConstants.NOT_FROM_MOSS);
        if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
            guideInfo.setBusinessCode(VgoConstants.BUSINESS_CODE_VGO);
        } else if (BusinessTypesEnum.MOM_INFANT.getCode().equals(businessType)) {
            guideInfo.setBusinessCode(VgoConstants.BUSINESS_CODE_MOMINFANT);
        }
        BeanUtils.copy(guideInfo, o2oGuideInfo);
        //插入后台导购管理表
        guideInfoAdminDao.insert(guideInfo);
        //根据业态不同：电器设置擅长品类名称，母婴设置服务项目名称，以顿号分割
        putRelevantNameByBusinessType(auditInfo, o2oGuideInfo);
        //插入中台导购信息表
        o2oGuideInfoDao.insert(o2oGuideInfo);
    }

    /**
     * 星级转换为评分
     */
    private Double transfStarLevelGrade(String starLevelStar) {
        for (StarLevelEvaluationEnum starLevel : StarLevelEvaluationEnum.values()) {
            if (starLevel.getName().equals(starLevelStar)) {
                // 转换评分
                return starLevel.getValue();
            }
        }
        return StarLevelEvaluationEnum.FIVE.getValue();
    }

    /**
     * 根据业态：电器设置擅长品类名称，母婴设置服务项目名称
     */
    private void putRelevantNameByBusinessType(GuideAuditInfo auditInfo, O2oGuideInfo o2oGuideInfo) {
        if (BusinessTypesEnum.ELECTRIC.getCode().equals(auditInfo.getBusinessType())) {
            String categoryIdStr = auditInfo.getCategoryIds();
            if (StringUtils.isNotBlank(categoryIdStr)) {
                List<String> categoryIds = Arrays.asList(categoryIdStr.split(VgoConstants.SPLIT_FLAG));
                List<VcategoryRelInfoDto> vcategoryInfos = categoryDao.queryGuideCategory(categoryIds);
                StringBuilder stringBuilder = new StringBuilder();
                for (VcategoryRelInfoDto vcategoryInfo : vcategoryInfos) {
                    stringBuilder.append(vcategoryInfo.getCategoryName()).append(VgoConstants.SPLIT_FLAG_BREAK);
                }
                o2oGuideInfo.setCategoryName(stringBuilder.toString().substring(0, stringBuilder.length() - 1));
            }
        }
    }

    /**
     * 更新导购信息
     */
    private void updateGuideInfo(GuideAuditReqDto guideAuditReqDto, GuideAuditInfo auditInfo, GuideInfo guideExists, String staffId) {
        //待更新字段值
        String guidePhoto = auditInfo.getGuidePhoto();
        String introduction = auditInfo.getIntroduction();
        Integer saleAge = auditInfo.getSaleAge();
        Integer isVgo = guideAuditReqDto.getIsVgo();
        //更新后台导购管理表
        GuideInfo guideInfo = new GuideInfo();
        guideInfo.setId(guideExists.getId());
        guideInfo.setGuidePhoto(guidePhoto);
        guideInfo.setIntroduction(introduction);
        guideInfo.setSaleAge(saleAge);
        guideInfo.setIsVgo(isVgo);
        guideInfo.setUpdateTime(CommonUtils.obtainCurrentTimestamp());
        guideInfo.setUpdatePerson(staffId);
        guideInfoAdminDao.updateSkipNull(guideInfo);
        //更新中台导购表
        Long id = o2oGuideInfoDao.selectIdByGuideId(guideAuditReqDto.getGuideId());
        O2oGuideInfo o2oGuideInfo = new O2oGuideInfo();
        o2oGuideInfo.setId(id);
        o2oGuideInfo.setGuidePhoto(guidePhoto);
        o2oGuideInfo.setIntroduction(introduction);
        o2oGuideInfo.setSaleAge(saleAge);
        o2oGuideInfo.setIsVgo(isVgo);
        //根据业态不同：电器设置擅长品类名称，母婴设置服务项目名称，以顿号分割
        putRelevantNameByBusinessType(auditInfo, o2oGuideInfo);
        o2oGuideInfo.setUpdateTime(CommonUtils.obtainCurrentTimestamp());
        o2oGuideInfo.setUpdatePerson(staffId);
        o2oGuideInfoDao.updateSkipNull(o2oGuideInfo);
    }

    /**
     * 更新服务项目
     *
     * @param guideId   导购工号
     * @param auditInfo 审核信息
     */
    private void updateServerItem(String guideId, GuideAuditInfo auditInfo) {
        serverItemDao.deleteByGuideId(guideId);
        addServerItem(guideId, auditInfo);
    }

    /**
     * 更新品类信息
     *
     * @param guideId   导购工号
     * @param auditInfo 审核信息
     */
    private void updateCategoryRel(String guideId, GuideAuditInfo auditInfo) {
        categoryRelDao.deleteByGuideId(guideId);
        addCategoryRel(guideId, auditInfo);
    }

    /**
     * 更新审核表
     *
     * @param staffId          员工工号
     * @param guideAuditReqDto 审核信息
     * @author 88396455_白振华
     * @since 17:23  2018-9-3
     */
    private void updateAuditInfo(GuideAuditReqDto guideAuditReqDto, String staffId) {
        GuideAuditInfo guideAuditInfo = new GuideAuditInfo();
        Integer auditFlag = guideAuditReqDto.getAuditFlag();
        guideAuditInfo.setId(guideAuditReqDto.getId());
        guideAuditInfo.setAuditFlag(auditFlag);
        guideAuditInfo.setAuditTime(CommonUtils.obtainCurrentTimestamp());
        guideAuditInfo.setAuditPerson(staffId);
        if (VgoConstants.AUDIT_ADOPT == auditFlag) {
            guideAuditInfo.setIsVgo(guideAuditReqDto.getIsVgo());
        } else {
            guideAuditInfo.setAuditReason(guideAuditReqDto.getAuditReason());
        }
        guideAuditDao.updateSkipNull(guideAuditInfo);
    }

}
