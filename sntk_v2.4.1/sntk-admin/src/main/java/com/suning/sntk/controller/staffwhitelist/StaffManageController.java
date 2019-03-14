/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: UserManagerController
 * Author:   88397670_张辉
 * Date:     2018-7-3 14:48
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.controller.staffwhitelist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.suning.sntk.admin.service.staffwhitelist.StaffManageService;
import com.suning.sntk.admin.vo.StaffInfoVo;
import com.suning.sntk.admin.vo.StationVo;
import com.suning.sntk.admin.vo.UploadImportFileVo;
import com.suning.sntk.dto.microservice.StaffBatchInfoDto;
import com.suning.sntk.dto.microservice.StaffInfoDto;
import com.suning.sntk.dto.region.EmployeeInfo;
import com.suning.sntk.interceptor.RequestUserHolder;
import com.suning.sntk.support.enums.StationEnum;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 功能描述：人员管理后台
 *
 * @author 88397670_张辉
 * @since 2018-7-3
 */
@RestController
@RequestMapping("/admin/staffManage")
@Trace
@Validated
public class StaffManageController {

    @Autowired
    private StaffManageService staffManageService;

    /**
     * 分页查询人员信息
     *
     * @param staffInfoDTO 查询入参
     * @param pageable     分页入参
     * @author 88397670_张辉
     * @since 19:59 2018-7-3
     */
    @PostMapping(value = "/queryStaffInfoPage")
    public Page<StaffInfoVo> queryStaffInfoPage(@RequestBody StaffInfoDto staffInfoDTO, Pageable pageable) {
        return staffManageService.queryStaffInfo(staffInfoDTO, pageable);
    }

    /**
     * 修改人员状态
     *
     * @param ids       人员记录Id集合
     * @param validFlag 状态(1-启用 2-禁用)
     * @author 88397670_张辉
     * @since 20:00 2018-7-3
     */
    @RequestMapping(value = "/modifyStaffStatus")
    public String modifyStaffStatus(@NotBlank String ids, Integer validFlag) {
        EmployeeInfo updateUser = RequestUserHolder.getRequestUser();
        return staffManageService.modifyStaffStatus(ids, validFlag, updateUser.getStaffId());
    }

    /**
     * 删除人员信息
     *
     * @param ids 人员记录Id集合
     * @author 88397670_张辉
     * @since 20:00 2018-7-3
     */
    @RequestMapping(value = "/deleteStaffInfo")
    public String deleteStaffInfo(@NotBlank String ids) {
        return staffManageService.deleteStaffInfo(ids);
    }

    /**
     * 通过员工号查询所在的组织信息
     *
     * @param staffId 员工号
     * @author 88397670_张辉
     * @since 20:33 2018-7-3
     */
    @RequestMapping(value = "/queryEmployExt")
    public StaffBatchInfoDto queryExtByStaffId(@NotBlank String staffId) {
        return staffManageService.queryExtByStaffId(staffId, RequestUserHolder.getRequestUser());
    }

    /**
     * 通过组织信息查询人员信息并新增
     *
     * @param staffInfoDTO 组织信息
     * @author 88397670_张辉
     * @since 20:46 2018-7-3
     */
    @PostMapping(value = "/addStaffInfoByOrgPos")
    public String addStaffInfoByOrgPos(@RequestBody StaffInfoDto staffInfoDTO) {
        EmployeeInfo updateUser = RequestUserHolder.getRequestUser();
        return staffManageService.addStaffInfoByOrgPos(staffInfoDTO, updateUser);
    }

    /**
     * 新增人员信息
     *
     * @param staffBatchInfoDto 人员信息
     * @author 88397670_张辉
     * @since 20:48 2018-7-3
     */
    @PostMapping(value = "/addStaffInfo")
    public String addStaffInfo(@RequestBody StaffBatchInfoDto staffBatchInfoDto) {
        return staffManageService.addStaffInfo(staffBatchInfoDto.getStaffInfoList(), RequestUserHolder.getRequestUser());
    }

    @RequestMapping(value = "/uploadFile")
    public void uploadFile(@RequestParam MultipartFile file) throws IOException {
        UploadImportFileVo fileVo = new UploadImportFileVo();
        fileVo.setFileName(file.getOriginalFilename());
        fileVo.setContentType(file.getContentType());
        fileVo.setSize(file.getSize());
        fileVo.setType(1);
        fileVo.setInputStream(file.getInputStream());
        fileVo.setCustNo(RequestUserHolder.getRequestUser().getStaffId());
        staffManageService.createImportFile(fileVo);
    }

    @RequestMapping(value = "/queryStation")
    public List<StationVo> queryStation() {
        List<StationVo> stationVoList = new ArrayList<>();
        for (StationEnum stationEnum : EnumSet.allOf(StationEnum.class)) {
            StationVo vo = new StationVo();
            vo.setStationCode(stationEnum.getStation());
            vo.setStationName(stationEnum.getDescription());
            stationVoList.add(vo);
        }
        return stationVoList;
    }

}
