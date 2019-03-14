/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffManagerService
 * Author:   88397670_张辉
 * Date:     2018-7-3 16:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.staffwhitelist;

import java.util.List;

import com.suning.sntk.admin.mq.sender.staffwhitelist.dto.StaffSenderDto;
import com.suning.sntk.admin.vo.StaffInfoVo;
import com.suning.sntk.admin.vo.UploadImportFileVo;
import com.suning.sntk.dto.microservice.StaffBatchInfoDto;
import com.suning.sntk.dto.microservice.StaffInfoDto;
import com.suning.sntk.dto.region.EmployeeInfo;
import com.suning.sntk.entity.staffwhitelist.Staff;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 功能描述：人员管理后台服务
 *
 * @author 88397670_张辉
 * @since 2018-7-3
 */
public interface StaffManageService {

    /**
     * 分页查询人员信息
     *
     * @param staffInfoDTO 查询参数
     * @param pageable    分页参数
     * @author 88397670_张辉
     * @since 20:01 2018-7-3
     */
    Page<StaffInfoVo> queryStaffInfo(StaffInfoDto staffInfoDTO, Pageable pageable);

    /**
     * 修改人员状态
     *
     * @param ids     人员记录Id集合
     * @param validFlag  状态(1-启用 2-禁用)
     * @param updateUser 更新用户
     * @author 88397670_张辉
     * @since 20:01 2018-7-3
     */
    String modifyStaffStatus(String ids, Integer validFlag, String updateUser);

    /**
     * 删除人员信息
     *
     * @param ids 人员记录Id集合
     * @author 88397670_张辉
     * @since 20:01 2018-7-3
     */
    String deleteStaffInfo(String ids);

    /**
     * 通过员工号查询员工所有的组织信息
     *
     * @param staffId 员工号
     * @param opreateUser 操作人
     * @author 88397670_张辉
     * @since 20:17 2018-7-3
     */
    StaffBatchInfoDto queryExtByStaffId(String staffId, EmployeeInfo opreateUser);

    /**
     * 通过组织信息查询人员并添加人员信息
     *
     * @param staffInfoDTO 组织信息
     * @param opreateUser    当前登录人员
     * @author 88397670_张辉
     * @since 20:43 2018-7-3
     */
    String addStaffInfoByOrgPos(StaffInfoDto staffInfoDTO, EmployeeInfo opreateUser);

    /**
     * 新增人员信息
     *
     * @param staffInfoList 人员信息
     * @param opreateUser    操作人
     * @author 88397670_张辉
     * @since 9:26 2018-7-4
     */
    String addStaffInfo(List<StaffInfoDto> staffInfoList, EmployeeInfo opreateUser);

    /**
     * 上传文件
     *
     * @param fileVo 文件对象
     * @author 88397670_张辉
     * @since 15:22 2018-7-6
     */
    void createImportFile(UploadImportFileVo fileVo);

    /**
     * 判断当前人员信息是否已存在，并组装通知消息体
     *
     * @param staff 人员信息
     * @author 88397670_张辉
     * @since 9:38 2018-7-16
     */
    StaffSenderDto judgeAndConversionSender(Staff staff);
}
