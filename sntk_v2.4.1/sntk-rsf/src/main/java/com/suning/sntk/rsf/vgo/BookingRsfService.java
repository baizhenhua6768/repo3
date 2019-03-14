/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookingRsfService
 * Author:   18010645_黄成
 * Date:     2018/8/19 11:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.vgo;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.dto.vgo.AppointInitQueryRespDto;
import com.suning.sntk.dto.vgo.AppointPromptDto;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.StatisticsInfoDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：预约接口
 *
 * @author 18010645_黄成
 * @since 2018/8/19
 */
@Contract(name = "com.suning.sntk.rsf.vgo.BookingRsfService", description = "bookingRsfService")
public interface BookingRsfService {
    /**
     * 功能：预约导购
     *
     * @param bookingInfo    预约信息
     * @param statisticsInfo 统计信息
     * @author 18010645_黄成
     * @since 16:13 2018/9/7
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "预约导购")
    RsfResponseDto<String> bookingGuide(BookingInfoDto bookingInfo, StatisticsInfoDto statisticsInfo);

    /**
     * 功能：查询会员电话号码
     *
     * @param customerNum 会员编码
     * @author 18010645_黄成
     * @since 16:14 2018/9/7
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询会员电话号码")
    RsfResponseDto<String> queryMemberPhone(String customerNum);

    /**
     * 功能：预约页初始化查询接口
     *
     * @param customerNum 会员编码
     * @param guideId     导购工号
     * @author 18010645_黄成
     * @since 16:14 2018/9/7
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "预约页初始化查询接口")
    RsfResponseDto<AppointInitQueryRespDto> queryAppointInit(String customerNum, String guideId);

    /**
     * 功能：预约弹窗语
     *
     * @param customerNum 会员编码
     * @author 18010645_黄成
     * @since 16:14 2018/9/7
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "预约弹窗语")
    RsfResponseDto<AppointPromptDto> queryAppointment(String customerNum, String bookingTime);
}
