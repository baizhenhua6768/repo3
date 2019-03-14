/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AppointPageInitService
 * Author:   18010645_黄成
 * Date:     2018/8/31 16:19
 * Description: //预约页、功能描述：预约页相关接口
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo;

import com.suning.sntk.dto.vgo.AppointInitQueryRespDto;
import com.suning.sntk.dto.vgo.AppointPromptDto;

/**
 * 功能描述：预约页相关接口
 *
 * @author 18010645_黄成
 * @since 2018/8/31
 */
public interface BookingPageService {

    /**
     * 功能：查询预约页初始信息
     *
     * @param customerNum 会员编码
     * @param guideId     导购Id
     * @author 18010645_黄成
     * @since 16:22 2018/8/31
     */
    AppointInitQueryRespDto queryBookingPageInitInfo(String customerNum, String guideId);

    /**
     * 功能：查询预约弹窗语
     *
     * @param customerNum 会员编码
     * @param bookingTime 预约时间
     * @author 18010645_黄成
     * @since 10:34 2018/9/7
     */
    AppointPromptDto queryBookingPrompDialogue(String customerNum, String bookingTime);

    /**
     * 功能：获取会员手机号
     *
     * @param customerNum 会员编号
     * @author 18010645_黄成
     * @since 19:06 2018/9/4
     */
    String queryMemberPhone(String customerNum);
}
