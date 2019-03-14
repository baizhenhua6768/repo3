/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffManagerServiceImpl
 * Author:   88397670_张辉
 * Date:     2018-7-3 17:01
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.staffwhitelist.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.nsfbus.organization.rsfservice.SiteRsfService;
import com.suning.nsfbus.organization.rsfservice.dto.DistrictSaleOrgDto;
import com.suning.nsfbus.organization.rsfservice.dto.SiteInfoDto;
import com.suning.nsfuaa.employee.EmployeeService;
import com.suning.nsfuaa.employee.dto.EmpListOfOrgPos;
import com.suning.nsfuaa.employee.dto.EmployeeExt;
import com.suning.nsfuaa.employee.dto.EmployeeInfo;
import com.suning.nsfuaa.employee.dto.EmployeeOrgPositionMapping;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.admin.dao.common.ImportFileDao;
import com.suning.sntk.admin.dao.staffwhitelist.StaffDao;
import com.suning.sntk.admin.dao.staffwhitelist.StoreInfoDao;
import com.suning.sntk.admin.mq.sender.staffwhitelist.StaffSender;
import com.suning.sntk.admin.mq.sender.staffwhitelist.dto.StaffSenderDto;
import com.suning.sntk.admin.service.staffwhitelist.StaffManageService;
import com.suning.sntk.admin.vo.StaffInfoVo;
import com.suning.sntk.admin.vo.UploadImportFileVo;
import com.suning.sntk.dto.microservice.StaffBatchInfoDto;
import com.suning.sntk.dto.microservice.StaffInfoDto;
import com.suning.sntk.dto.system.OssFileInfo;
import com.suning.sntk.entity.ImportFile;
import com.suning.sntk.entity.staffwhitelist.Staff;
import com.suning.sntk.entity.staffwhitelist.StoreInfo;
import com.suning.sntk.service.system.OssService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.enums.StaffTypeEnum;
import com.suning.sntk.support.util.CommonUtils;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 功能描述：人员管理后台服务
 *
 * @author 88397670_张辉
 * @since 2018-7-3
 */
@Service
@Trace
public class StaffManageServiceImpl implements StaffManageService {

    private Logger logger = LoggerFactory.getLogger(StaffManageServiceImpl.class);

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private OssService ossService;

    @Autowired
    private StaffSender staffSender;

    @Autowired
    private ImportFileDao importFileDao;

    @Autowired
    private StoreInfoDao storeInfoDao;

    private EmployeeService employeeService = ServiceLocator.getService(EmployeeService.class, null);

    private SiteRsfService siteRsfService = ServiceLocator.getService(SiteRsfService.class, null);

    private static final Integer START = 1;

    private static final String STORE_LEVEL = "4";

    private static final String HQ_LEVEL = "0";

    private static final String COMPANY_LEVLE = "1";

    private static final String AREA_LEVLE = "2";

    @Override
    public Page<StaffInfoVo> queryStaffInfo(StaffInfoDto staffInfoDTO, Pageable pageable) {
        return staffDao.queryStaffInfoPage(staffInfoDTO, pageable);
    }

    @Override
    public String modifyStaffStatus(String ids, Integer validFlag, String updateUser) {
        String message = null;
        List<Long> idList = conversionIdList(ids);
        try {
            staffDao.modifyStaffStatus(idList, validFlag, updateUser);
            List<StaffSenderDto> staffSenderList = queryStaffInfoByIdList(idList);
            if (START.equals(validFlag)) {
                staffSender.sendStaffInfo(staffSenderList, StaffTypeEnum.START_UP_STAFF.getStaffStatus());
            } else {
                staffSender.sendStaffInfo(staffSenderList, StaffTypeEnum.PROHIBIT_STAFF.getStaffStatus());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message = "禁用启用人员失败！";
        }
        return message;
    }

    /**
     * 变更人员白名单表数据时查询相关信息封装通知
     *
     * @param idList id集合
     * @author 88397670_张辉
     * @since 15:31 2018-7-11
     */
    private List<StaffSenderDto> queryStaffInfoByIdList(List<Long> idList) {
        return staffDao.queryStaffInfoList(idList);
    }

    @Override
    public String deleteStaffInfo(String ids) {
        String message = null;
        try {
            List<Long> idList = conversionIdList(ids);
            List<StaffSenderDto> staffSenderList = queryStaffInfoByIdList(idList);
            staffDao.deleteStaffInfo(idList);
            staffSender.sendStaffInfo(staffSenderList, StaffTypeEnum.DELETE_STAFF.getStaffStatus());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message = "删除人员失败！";
        }
        return message;
    }

    /**
     * 转换前台Id传参变成list
     *
     * @param ids 以","分割的id字符串
     * @author 88397670_张辉
     * @since 15:30 2018-7-11
     */
    private List<Long> conversionIdList(String ids) {
        List<Long> idList = new ArrayList<>();
        String[] arg = ids.split(",");
        for (String id : arg) {
            idList.add(Long.parseLong(id));
        }
        return idList;
    }

    @Override
    public StaffBatchInfoDto queryExtByStaffId(String staffId, com.suning.sntk.dto.region.EmployeeInfo opreateUser) {
        //封装返回人员信息，添加标志位flag，优先判断人员信息list是否为空。不为空，则正常取staffInfoVoList，flag位的值无意义；
        // 否则取flag位，为1表示人员信息列表为空是因为该工号无门店组织信息，flag位为2则表示因操作用户权限不足以添加该人员门店信息
        StaffBatchInfoDto staffBatchInfoDto = new StaffBatchInfoDto();
        //初始化默认查询人员门店信息为查不到
        staffBatchInfoDto.setFlag("1");
        List<StaffInfoDto> staffInfoVoList = new ArrayList<>();
        EmployeeExt employeeExt = employeeService.queryEmployeeExt(staffId);
        if (employeeExt == null || employeeExt.getEmployeeOrgPostionMappingList() == null) {
            staffBatchInfoDto.setStaffInfoList(staffInfoVoList);
            return staffBatchInfoDto;
        }
        List<EmployeeOrgPositionMapping> employeeOrgPostionMappingList = employeeExt.getEmployeeOrgPostionMappingList();

        for (EmployeeOrgPositionMapping dto : employeeOrgPostionMappingList) {
            if (dto.getOrgLevelCode().equals(STORE_LEVEL)) {
                DistrictSaleOrgDto districtSaleOrgDto = siteRsfService.queryDistrictCompanyRelation(dto.getCompanyCode());
                StaffInfoDto staffInfoDTO = new StaffInfoDto();
                if (districtSaleOrgDto != null) {
                    staffInfoDTO.setAreaCode(districtSaleOrgDto.getAreaCode());
                    staffInfoDTO.setAreaName(districtSaleOrgDto.getAreaName());
                    staffInfoDTO.setCompanyCode(districtSaleOrgDto.getSaleOrgCode());
                    staffInfoDTO.setCompanyName(districtSaleOrgDto.getSaleOrgName());
                }
                staffInfoDTO.setStoreCode(dto.getOrgCode());
                staffInfoDTO.setStoreName(dto.getOrgDisplayName());
                staffInfoDTO.setStation(dto.getPostionName());
                staffInfoDTO.setStationCode(dto.getPostionId());
                staffInfoDTO.setStaffId(staffId);
                staffInfoDTO.setStaffName(employeeExt.getEmployeeName());
                if (!determineUserOwner(opreateUser, staffInfoDTO)) {//若当前操作人无权限新增该人员记录则跳出本次循环
                    //鉴权不足则设置flag为查询到门店信息，但无权限添加
                    staffBatchInfoDto.setFlag("2");
                    continue;
                }
                staffInfoVoList.add(staffInfoDTO);
            }
        }
        staffBatchInfoDto.setStaffInfoList(staffInfoVoList);
        return staffBatchInfoDto;
    }

    /**
     * 判断当前操作人员是否有权限新增该人员记录（操作人与该条人员记录是否归属同一大区或分公司）
     *
     * @param opreateUser  操作人
     * @param staffInfoDTO 待新增人员信息
     * @author 88397670_张辉
     * @since 10:52 2018-7-5
     */
    private Boolean determineUserOwner(com.suning.sntk.dto.region.EmployeeInfo opreateUser, StaffInfoDto staffInfoDTO) {
        //当前操作人只存在为大区或分公司人员，所有无需判断门店信息

        switch (opreateUser.getOrgLevel()) {
            case HQ_LEVEL://操作人归属总部，可添加全部
                return true;
            case COMPANY_LEVLE://操作人归属分公司，则比较待新增人员是否在该分公司下
                return staffInfoDTO.getCompanyCode().equals(opreateUser.getBranchCode());
            case AREA_LEVLE://操作人归属大区，则比较大区是否相同
                return staffInfoDTO.getAreaCode().equals(opreateUser.getRegion5Code());
            case STORE_LEVEL://操作人归属门店，则比较待新增人员是否在该门店下
                return staffInfoDTO.getStoreCode().equals(opreateUser.getStoreCode());
            default://操作人不归属大区或分公司，则该人员并无新增权限，返回false
                return false;
        }
    }

    @Override
    public String addStaffInfoByOrgPos(StaffInfoDto staffInfoDTO, com.suning.sntk.dto.region.EmployeeInfo opreateUser) {
        StringBuilder message = new StringBuilder();
        EmpListOfOrgPos empListOfOrgPos = employeeService.getEmpListOfOrgPos(staffInfoDTO.getStoreCode(), staffInfoDTO.getStationCode());
        List<StaffSenderDto> staffInfoList = new ArrayList<>();
        if (empListOfOrgPos == null || empListOfOrgPos.getEmpList() == null) {
            message.append("未能查到员工相关信息！");
            return message.toString();
        }
        for (EmployeeInfo info : empListOfOrgPos.getEmpList()) {
            staffInfoDTO.setStaffId(info.getEmployeeId());
            staffInfoDTO.setStaffName(info.getEmployeeName());
            if (!determineUserOwner(opreateUser, staffInfoDTO)) {
                message.append("当前用户无权限新增员工号为：").append(staffInfoDTO.getStaffId()).append("的用户信息；");
                continue;
            }
            StaffSenderDto staffSenderDto = judgeAndConversionSender(conversionVoToStaff(staffInfoDTO, opreateUser.getStaffId()));
            if (staffSenderDto != null) {
                staffInfoList.add(staffSenderDto);
            }
        }
        staffSender.sendStaffInfo(staffInfoList, StaffTypeEnum.ADD_STAFF.getStaffStatus());
        return message.toString();
    }

    /**
     * 转换entity为通知封装的Dto并赋予cityCode（新增特有）
     *
     * @param staff 人员白名单entity
     * @author 88397670_张辉
     * @since 15:28 2018-7-11
     */
    private StaffSenderDto conversionToSenderDto(Staff staff) {
        StaffSenderDto staffSenderDto = new StaffSenderDto();
        staffSenderDto.setStaffId(staff.getStaffId());
        staffSenderDto.setStaffName(staff.getStaffName());
        staffSenderDto.setStoreCode(staff.getStoreCode());
        staffSenderDto.setStoreName(staff.getStoreName());
        String cityCode = null;
        SiteInfoDto siteInfoDto = siteRsfService.querySiteInfo(staff.getStoreCode());
        if (siteInfoDto != null) {
            cityCode = siteInfoDto.getCityCode();
        }
        staffSenderDto.setCityCode(cityCode);
        StoreInfo storeInfo = storeInfoDao.queryStoreInfo(staff.getStoreCode());
        if (storeInfo != null) {
            staffSenderDto.setStoreAddress(storeInfo.getStoreAddress());
        }
        return staffSenderDto;
    }

    @Override
    public String addStaffInfo(List<StaffInfoDto> staffInfoList, com.suning.sntk.dto.region.EmployeeInfo opreateUser) {
        StringBuilder message = new StringBuilder();
        List<StaffSenderDto> staffSenderList = new ArrayList<>();
        for (StaffInfoDto dto : staffInfoList) {
            if (!determineUserOwner(opreateUser, dto)) {
                message.append("当前登录用户无权限新增员工号为：").append(dto.getStaffId()).append("的人员信息；");
                continue;
            }
            StaffSenderDto staffSenderDto = judgeAndConversionSender(conversionVoToStaff(dto, opreateUser.getStaffId()));
            if (staffSenderDto != null) {
                staffSenderList.add(staffSenderDto);
            }
        }
        staffSender.sendStaffInfo(staffSenderList, StaffTypeEnum.ADD_STAFF.getStaffStatus());
        return message.toString();
    }

    /**
     * 判断当前人员信息是否已存在
     *
     * @param staff 人员信息
     * @author 88397670_张辉
     * @since 9:12 2018-7-16
     */
    private Boolean existStaffInfo(Staff staff) {
        return staffDao.existStaffInfo(staff.getStaffId(), staff.getStoreCode()) > 0;
    }

    @Override
    public void createImportFile(UploadImportFileVo fileVo) {
        // OSS不支持空格
        String objectName = fileVo.getFileName().replaceAll("\\s+", "-");
        OssFileInfo fileInfo = ossService.uploadFile(CommonConstants.FILE_BUCKEN_NAME, objectName, fileVo.getContentType(), fileVo
                .getInputStream());
        ImportFile file = buildImportFile(fileVo);
        file.setObjectId(fileInfo.getObjectId());
        file.setDownloadUrl(fileInfo.getDownloadUrl());
        if (StringUtils.isBlank(file.getObjectId()) || StringUtils.isEmpty(file.getObjectId())) {
            return;
        }
        importFileDao.insert(file);
    }

    /**
     * 将文件对象转换成表实体entity
     *
     * @param fileVo 文件对象
     * @author 88397670_张辉
     * @since 15:27 2018-7-11
     */
    private ImportFile buildImportFile(UploadImportFileVo fileVo) {
        ImportFile file = new ImportFile();
        file.setFileType(fileVo.getType());
        file.setFileName(fileVo.getFileName());
        file.setDownloadUrl(fileVo.getOssUrl());
        file.setStatus(0);
        file.setSize(fileVo.getSize());
        file.setCreateUser(fileVo.getCustNo());
        file.setUpdateUser(fileVo.getCustNo());
        return file;
    }

    /**
     * 将Vo转换成entity并给entity的其他字段赋予初始值
     *
     * @param staffInfoDTO 人员信息Dto
     * @param userName     操作人
     * @author 88397670_张辉
     * @since 9:28 2018-7-4
     */
    private Staff conversionVoToStaff(StaffInfoDto staffInfoDTO, String userName) {
        Date date = new Date();
        Staff staff = new Staff();
        staff.setAreaCode(staffInfoDTO.getAreaCode());
        staff.setAreaName(staffInfoDTO.getAreaName());
        staff.setCompanyCode(staffInfoDTO.getCompanyCode());
        staff.setCompanyName(staffInfoDTO.getCompanyName());
        staff.setStoreCode(staffInfoDTO.getStoreCode());
        staff.setStoreName(staffInfoDTO.getStoreName());
        staff.setStaffName(staffInfoDTO.getStaffName());
        staff.setStaffId(staffInfoDTO.getStaffId());
        staff.setStation(staffInfoDTO.getStation());
        staff.setValidFlag(1);//默认新增为启用状态
        staff.setDeleteFalg1(1);
        if (staffInfoDTO.getErrorFlag() != null) {
            staff.setErrorFalg(staffInfoDTO.getErrorFlag());
        } else {
            staff.setErrorFalg(0);
        }
        staff.setCreater(userName);
        staff.setCreateTime(date);
        staff.setUpdater(userName);
        staff.setUpdateTime(date);
        staff.setRemark("normal");
        return staff;
    }

    @Override
    public StaffSenderDto judgeAndConversionSender(Staff staff) {
        StaffSenderDto staffSenderDto = null;
        Staff staff1 = conversionNotNullAttribute(staff);
        if (existStaffInfo(staff1)) {
            staffDao.updateStaffInfo(staff1);
        } else {
            staffDao.insert(staff1);
            staffSenderDto = conversionToSenderDto(staff);
        }
        return staffSenderDto;
    }

    private Staff conversionNotNullAttribute(Staff staff) {
        Staff staff1 = new Staff();
        staff1.setStaffId(CommonUtils.nullToEmptyString(staff.getStaffId()));
        staff1.setStaffName(CommonUtils.nullToEmptyString(staff.getStaffName()));
        staff1.setAreaCode(CommonUtils.nullToEmptyString(staff.getAreaCode()));
        staff1.setAreaName(CommonUtils.nullToEmptyString(staff.getAreaName()));
        staff1.setCompanyCode(CommonUtils.nullToEmptyString(staff.getCompanyCode()));
        staff1.setCompanyName(CommonUtils.nullToEmptyString(staff.getCompanyName()));
        staff1.setStoreCode(CommonUtils.nullToEmptyString(staff.getStoreCode()));
        staff1.setStoreName(CommonUtils.nullToEmptyString(staff.getStoreName()));
        staff1.setStation(CommonUtils.nullToEmptyString(staff.getStation()));
        staff1.setValidFlag(staff.getValidFlag());
        staff1.setDeleteFalg1(staff.getDeleteFalg1());
        staff1.setErrorFalg(staff.getErrorFalg());
        staff1.setRemark(CommonUtils.nullToEmptyString(staff.getRemark()));
        staff1.setCreater(staff.getCreater());
        staff1.setCreateTime(staff.getCreateTime());
        staff1.setUpdater(staff.getUpdater());
        staff1.setUpdateTime(staff.getUpdateTime());
        return staff1;
    }

}
