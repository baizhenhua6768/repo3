/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: OrganizationDao
 * Author:   18032490_赵亚奇
 * Date:     2018/7/3 11:54
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.dao.staffwhitelist;

import java.util.List;

import com.suning.sntk.admin.mq.sender.staffwhitelist.dto.StaffSenderDto;
import com.suning.sntk.admin.vo.StaffInfoVo;
import com.suning.sntk.dto.microservice.StaffInfoDto;
import com.suning.sntk.entity.staffwhitelist.Staff;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.annotation.DalPrimitiveParam;
import com.suning.store.dal.base.DalBaseDao;

/**
 * @author 18032490_赵亚奇
 * @since 2018/7/3
 */
@DalMapper(namespace = "staff")
public interface StaffDao extends DalBaseDao<Staff, Long> {

    /**
     * 查询所有岗位（去重）
     *
     * @return
     */
    List<String> queryStationList();

    /**
     * 分页查询人员信息
     *
     * @param staffInfoDTO 查询参数
     * @param pageable     分页参数
     * @author 88397670_张辉
     * @since 20:03 2018-7-3
     */
    Page<StaffInfoVo> queryStaffInfoPage(@DalParam("staffInfoDTO") StaffInfoDto staffInfoDTO, Pageable pageable);

    /**
     * 删除人员信息
     *
     * @param idList 人员记录Id集合
     * @author 88397670_张辉
     * @since 20:02 2018-7-3
     */
    void deleteStaffInfo(@DalPrimitiveParam @DalParam("idList") List<Long> idList);

    /**
     * 修改人员状态
     *
     * @param idList     人员记录Id集合
     * @param validFlag  状态(1-启用 2-禁用)
     * @param updateUser 更新用户
     * @author 88397670_张辉
     * @since 20:02 2018-7-3
     */
    @DalParams({ "idList", "validFlag", "updateUser" })
    void modifyStaffStatus(@DalPrimitiveParam List<Long> idList, Integer validFlag, String updateUser);

    /**
     * 通过门店编码及员工号删除人员记录
     *
     * @param storeCode 门店编码
     * @param staffId   员工号
     * @author 88397670_张辉
     * @since 9:09 2018-7-4
     */
    @DalParams({ "storeCode", "staffId" })
    void deleteStaff(String storeCode, String staffId);

    /**
     * 根据员工工号更改状态
     *
     * @param staffList
     * @param deleteFlagLeave
     */
    @DalParams({ "staffList", "deleteFlagLeave" })
    void updateDeleteFalgByStaff(@DalPrimitiveParam List<String> staffList, Integer deleteFlagLeave);

    /**
     * 根据Id集合查询人员信息集合
     *
     * @param idList id集合
     * @author 88397670_张辉
     * @since 15:29 2018-7-18
     */
    List<StaffSenderDto> queryStaffInfoList(@DalPrimitiveParam @DalParam("idList") List<Long> idList);

    /**
     * 查询指定门店下员工信息是否已存在
     *
     * @param staffId 员工号
	 * @param storeCode 门店编码
     * @author 88397670_张辉
     * @since 15:30 2018-7-18
     */
    @DalParams({"staffId","storeCode"})
    Long existStaffInfo(String staffId,String storeCode);

    /**
     * 更新人员信息
     *
     * @param staff 人员信息
     * @author 88397670_张辉
     * @since 15:30 2018-7-18
     */
    void updateStaffInfo(@DalParam("staff")Staff staff);
}