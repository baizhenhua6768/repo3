/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MyInfoRsfService
 * Author:   88397670_张辉
 * Date:     2018-8-31 10:49
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.vgo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.dto.vgo.CategoryDto;
import com.suning.sntk.dto.vgo.ClerkCheckResDto;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ServiceItemDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：店员资料
 *
 * @author 88397670_张辉
 * @since 2018-8-31
 */
@Contract(name = "ClerkRsfService", description = "我的资料服务接口")
public interface ClerkRsfService {

    /**
     * 查询擅长品类列表
     *
     * @author 88397670_张辉
     * @since 11:06 2018-8-31
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询擅长品类列表")
    RsfResponseDto<List<CategoryDto>> queryCategoryList();

    /**
     * 查询母婴服务项目列表
     *
     * @author 88397670_张辉
     * @since 15:28 2018-9-3
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询服务项目列表")
    RsfResponseDto<List<ServiceItemDto>> queryServiceItemList();

    /**
     * 查询个签模板
     *
     * @author 88397670_张辉
     * @since 11:17 2018-8-31
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询个签模板")
    RsfResponseDto<List<String>> querySignature();

    /**
     * 修改我的资料
     *
     * @param guideInfoDto 修改参数
     * @author 88397670_张辉
     * @since 11:33 2018-8-31
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "修改我的资料")
    RsfResponseDto modifyGuideAuditInfo(@NotNull GuideInfoDto guideInfoDto);

    /**
     * 查询导购详情
     *
     * @param guideId 导购工号
     * @param type 业态
     * @author 88395115_史小配
     * @since 14:13 2018/8/31
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询导购详情")
    RsfResponseDto<GuideInfoDto> queryGuideDetail(@NotBlank String guideId, String type);

    /**
     * 校验导购信息完整性
     *
     * @param guideId 导购工号
     * @author 88397670_张辉
     * @since 14:07 2018-9-4
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "校验导购信息完整性")
    RsfResponseDto<ClerkCheckResDto> checkClerkInfoIntegrity(@NotBlank String guideId);

    /**
     * 查询店员个签与是否V购认证标识信息
     *
     * @param guideId 导购工号
     * @author 88397670_张辉
     * @since 9:43 2018-10-10
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "检查店员是否V购")
    RsfResponseDto<GuideInfoDto> queryClerkMarkInfo(@NotBlank String guideId);
}
