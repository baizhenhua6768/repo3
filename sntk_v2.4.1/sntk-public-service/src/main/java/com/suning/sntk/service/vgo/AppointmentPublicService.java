/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AppointmentPublicService
 * Author:   18032490_赵亚奇
 * Date:     2018/8/17 14:51
 * Description: 预约模块接口
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 * 赵亚奇                2018/8/23 9:25      2.2.1
 */

package com.suning.sntk.service.vgo;

import java.util.List;

import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.GuideOrderLogDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 预约模块接口
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/17
 */
public interface AppointmentPublicService {

    /**
     * 查询我的预约列表
     *
     * @param custNo 会员编码
     * @param size   查询数量
     * @author 18032490_赵亚奇
     * @since 10:17  2018/8/29
     */
    RsfResponseDto<List<BookingInfoDto>> queryMyAppointmentList(String custNo, Integer size);

    /**
     * 查询预约详情
     *
     * @param custNo   会员编码
     * @param bookCode 会员编号
     * @author 18032490_赵亚奇
     * @since 10:18  2018/8/29
     */
    RsfResponseDto<BookingInfoDto> queryAppoinmentInfo(String custNo, String bookCode);

    /**
     * 取消预约
     *
     * @param bookCode 预约编码
     * @param custNo   会员编码
     * @author 18032490_赵亚奇
     * @since 10:28  2018/8/29
     */
    RsfResponseDto cancelAppointment(String bookCode, String custNo);

    /**
     * 查询预约操作日志
     *
     * @param bookCode 预约编码
     * @author 18032490_赵亚奇
     * @since 10:29  2018/8/29
     */
    RsfResponseDto<List<GuideOrderLogDto>> queryGuideOrderLog(String bookCode);

    /**
     * 查询最近预约信息
     *
     * @param custNo 会员编码
     * @author 18032490_赵亚奇
     * @since 10:43  2018/9/14
     */
    RsfResponseDto<List<BookingInfoDto>> queryNearAppointment(String custNo);
}
