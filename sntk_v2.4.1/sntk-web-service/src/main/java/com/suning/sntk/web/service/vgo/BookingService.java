/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookingService
 * Author:   18010645_黄成
 * Date:     2018/8/19 10:48
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.web.service.vgo;

import com.suning.sntk.dto.vgo.AppointInitQueryRespDto;
import com.suning.sntk.dto.vgo.AppointPromptDto;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.StatisticsInfoDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：
 *
 * @author 18010645_黄成
 * @since 2018/8/19
 */
public interface BookingService {

    /**
     * 预约导购
     *
     * @param bookingInfo    预约信息dto
     * @param statisticsInfo 统计信息dto
     * @return RsfResponseDto<Void>
     * @author 18010645_黄成
     * @since 10:31 2018/8/19
     */
    RsfResponseDto<String> bookingGuide(BookingInfoDto bookingInfo, StatisticsInfoDto statisticsInfo);

    /**
     * 功能描述：获取会员基本信息（电话号码）
     *
     * @param custNo 会员编码
     * @author 18010645_黄成
     * @since 11:46  2018-8-22
     */
    RsfResponseDto<String> getMemberTelephone(String custNo);

    /**
     * 功能：预约页初始化查询接口
     *
     * @param customerNum 会员编码
     * @param guideId     导购Id
     * @author 18010645_黄成
     * @since 16:07 2018/8/31
     */
    RsfResponseDto<AppointInitQueryRespDto> getAppointInitInfo(String customerNum, String guideId);

    /**
     * 功能：获取预约提示框信息
     *
     * @param customerNum 会员编码
     * @author 18010645_黄成
     * @since 10:20 2018/9/7
     */
    RsfResponseDto<AppointPromptDto> getBookPrompInfo(String customerNum, String bookingTime);

}
