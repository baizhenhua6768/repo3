/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: VpurchaseSawpRsfService
 * Author:   88395115_史小配
 * Date:     2018/8/16 10:04
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.vgo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.dto.vgo.BookingReqDto;
import com.suning.sntk.dto.vgo.GuideAppointmentDto;
import com.suning.sntk.dto.vgo.OrderCompletionDto;
import com.suning.sntk.dto.vgo.QueryAppointmentReqDto;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：店家导购员相关服务接口
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
@Contract(name = "guideRsfService", description = "店家导购员相关服务接口")
public interface VgoGuideRsfService {

    /**
     * 添加接待记录
     *
     * @param bookingReqDto 预约信息
     * @author 88395115_史小配
     * @since 10:22 2018/8/16
     */
    @Method(idempotent = false, timeout = 3000, retryTimes = 0, description = "添加接待记录")
    RsfResponseDto<Void> addBooking(@Valid @NotNull BookingReqDto bookingReqDto);

    /**
     * 分页查询预约列表
     *
     * @param queryAppointmentReqDto 查询条件
     * @param pageable               分页
     * @author 88395115_史小配
     * @since 10:33 2018/8/16
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询导购员未完成、完成的预约单列表信息")
    RsfResponseDto<Page<GuideAppointmentDto>> queryAppointmentList(@Valid @NotNull QueryAppointmentReqDto queryAppointmentReqDto,
            Pageable pageable);

    /**
     * 预约单回访操作
     *
     * @param bookId      预约单id
     * @param visitStatus 回访状态 1-拨打未接听 2-拨打已接听
     * @author 88395115_史小配
     * @since 11:39 2018/8/16
     */
    @Method(idempotent = false, timeout = 3000, retryTimes = 0, description = "预约单回访操作")
    RsfResponseDto<Void> returnVisit(@NotBlank Long bookId, @NotBlank String visitStatus, @NotBlank String guideId);

    /**
     * 预约单销单操作
     *
     * @param orderCompletionDto 销单信息
     * @author 88395115_史小配
     * @since 11:55 2018/8/16
     */
    @Method(idempotent = false, timeout = 3000, retryTimes = 0, description = "预约单销单操作")
    RsfResponseDto<Void> orderCompletion(@Valid @NotNull OrderCompletionDto orderCompletionDto);

    /**
     * 预约单备注操作
     *
     * @param bookId 预约单id
     * @param remark 备注
     * @author 88395115_史小配
     * @since 14:30 2018/8/20
     */
    @Method(idempotent = false, timeout = 3000, retryTimes = 0, description = "预约单备注操作")
    RsfResponseDto<Void> updateRemark(@NotNull Long bookId, @NotBlank String remark, @NotBlank String guideId);

}