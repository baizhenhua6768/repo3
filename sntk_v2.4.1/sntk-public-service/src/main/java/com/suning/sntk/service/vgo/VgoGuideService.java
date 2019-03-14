/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideService
 * Author:   88395115_史小配
 * Date:     2018/8/16 14:33
 * Description: 店+App导购和预约销单模块相关接口
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 * shixiaopei             2018/9/13
 */

package com.suning.sntk.service.vgo;

import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.ClerkCheckResDto;
import com.suning.sntk.dto.vgo.GuideAppointmentDto;
import com.suning.sntk.dto.vgo.GuideDto;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.OrderCompletionDto;
import com.suning.sntk.dto.vgo.QueryAppointmentReqDto;
import com.suning.sntk.dto.vgo.StatisticsInfoDto;
import com.suning.sntk.entity.vgo.GuideOrderLog;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 功能描述：店+App导购和预约销单模块相关接口
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
public interface VgoGuideService {

    /**
     * 添加接待记录
     *
     * @param bookingDto    预约单信息
     * @param statisticsDto 预约渠道信息
     * @author 88395115_史小配
     * @since 11:25 2018/8/17
     */
    String addBooking(BookingInfoDto bookingDto, StatisticsInfoDto statisticsDto);

    /**
     * 获取导购信息
     *
     * @param guideId 导购id
     * @author 88395115_史小配
     * @since 17:51 2018/8/17
     */
    GuideDto getGuideInfo(String guideId);

    /**
     * 查询预约列表
     *
     * @param queryAppointmentReqDto 查询条件
     * @param pageable               分页
     * @author 88395115_史小配
     * @since 15:40 2018/8/18
     */
    Page<GuideAppointmentDto> queryAppointmentList(QueryAppointmentReqDto queryAppointmentReqDto, Pageable pageable);

    /**
     * 添加预约单日志记录
     *
     * @param guideOrderLog 预约单日志记录
     * @author 88395115_史小配
     * @since 11:04 2018/8/20
     */
    void addGuideOrderLog(GuideOrderLog guideOrderLog);

    /**
     * 添加修改备注
     *
     * @param bookId 预约单id
     * @param remark 备注
     * @author 88395115_史小配
     * @since 14:40 2018/8/20
     */
    void updateRemark(Long bookId, String remark, String guideId);

    /**
     * 预约单回访操作
     *
     * @param bookId      预约单id
     * @param visitStatus 回访状态 1-拨打未接听 2-拨打已接听
     * @author 88395115_史小配
     * @since 9:54 2018/8/21
     */
    void returnVisit(Long bookId, String visitStatus, String guideId);

    /**
     * 预约单销单操作
     *
     * @param orderCompletionDto 预约单销单信息
     * @author 88395115_史小配
     * @since 10:58 2018/8/21
     */
    void orderCompletion(OrderCompletionDto orderCompletionDto);

    /**
     * 查询导购详情
     *
     * @param guideId 导购id
     * @param type    业态
     * @author 88395115_史小配
     * @since 11:05 2018/9/3
     */
    GuideInfoDto queryGuideDetail(String guideId, String type);

    /**
     * 更新(无则新增)待审核导购信息
     *
     * @param guideInfoDto 导购待审核信息
     * @author 88397670_张辉
     * @since 16:08 2018-8-31
     */
    void modifyAuditGuideInfo(GuideInfoDto guideInfoDto);

    /**
     * 校验店员信息完整性
     *
     * @param guideId 店员工号
     * @author 88397670_张辉
     * @since 16:00 2018-9-4
     */
    ClerkCheckResDto checkClerkInfo(String guideId);

    /**
     * 查询店员个签与是否V购认证标识信息
     *
     * @param guideId 导购工号
     * @author 88397670_张辉
     * @since 9:50 2018-10-10
     */
    GuideInfoDto queryClerkMarkInfo(String guideId);
}
