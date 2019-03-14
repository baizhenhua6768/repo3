/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookController
 * Author:   18010645_黄成
 * Date:     2018/8/19 9:24
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.web.controller.vgo;

import com.suning.sntk.dto.vgo.*;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.web.service.vgo.BookingService;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 功能描述：预约页相关接口
 *
 * @author 18010645_黄成
 * @since 2018/8/19
 */
@Controller
@Trace
@RequestMapping("/bookingPage")
public class BookingGuideController {

    @Autowired
    private BookingService bookingService;
    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingGuideController.class);

    /**
     * 手机号匹配正则表达式
     */
    private static final String PHONE_REGEX = "(\\d{3})\\d{4}(\\d{4})";

    /**
     * 手机脱敏格式
     */
    private static final String PHONE_FORM = "$1****$2";

    /**
     * 功能：预约导购
     *
     * @param request       request
     * @param bookingReqDto 预约请求
     * @author 18010645_黄成
     * @since 16:06 2018/9/7
     */
    @RequestMapping(value = "/private/booking.do")
    @ResponseBody
    public RsfResponseDto<String> booking(HttpServletRequest request, BookingReqDto bookingReqDto) {
        BookingInfoDto bookingInfoDto = new BookingInfoDto();
        StatisticsInfoDto statisticsInfoDto = new StatisticsInfoDto();
        //组装入参
        packagingParam(request, bookingReqDto, bookingInfoDto, statisticsInfoDto);
        LOGGER.info("预约导购,入参：{}", bookingReqDto);
        return bookingService.bookingGuide(bookingInfoDto, statisticsInfoDto);
    }

    /**
     * 功能：组装参数
     *
     * @param request           request
     * @param bookingReqDto     预约请求
     * @param bookingInfoDto    预约信息
     * @param statisticsInfoDto 统计信息
     * @author 18010645_黄成
     * @since 16:08 2018/9/7
     */
    private void packagingParam(HttpServletRequest request, BookingReqDto bookingReqDto, BookingInfoDto bookingInfoDto, StatisticsInfoDto statisticsInfoDto) {
        // 会员编码
        bookingInfoDto.setCust(request.getRemoteUser());
        // 导购工号
        bookingInfoDto.setGuideId(bookingReqDto.getGuideId());
        // 会员电话号码
        bookingInfoDto.setTelephone(bookingReqDto.getCustomerPhone());
        // 预约时间
        bookingInfoDto.setBookingTime(bookingReqDto.getBookingTime());
        // 门店编码
        bookingInfoDto.setStoreCode(bookingReqDto.getStoreCode());
        // 完成状态：0:未到店,1:到店购买,2:到店未购买,3:未销单,4:通用,5:用户取消,6:系统取消
        bookingInfoDto.setComplete(VgoConstants.COMPLETE_STATUS_NO_ORDER);
        // 预约状态：0：未完成，1：已完成
        bookingInfoDto.setBookingStatus(VgoConstants.BOOKING_STATUS_NO_COMPLETE);
        // 是否评价：0:未到店,1：已到店（可评价）,2:已评价
        bookingInfoDto.setJudgeFlag(VgoConstants.EVALUATE_NO_STORE);
        // 业态(1:v购;2:母婴)
        bookingInfoDto.setBusinessCode(bookingReqDto.getStoreType());
        // 渠道
        if (StringUtils.isNotBlank(bookingReqDto.getChannel())) {
            statisticsInfoDto.setChannel(bookingReqDto.getChannel());
        } else {
            // 不传默认无渠道:0
            statisticsInfoDto.setChannel(VgoConstants.CHANEL_NO);
        }
        // 预约与否
        statisticsInfoDto.setResourceType(VgoConstants.RESOURCE_TYPE);
        // 预约创建时间
        statisticsInfoDto.setCreateTime(DateUtils.format(new Date()));
    }

    /**
     * 功能：查询会员手机号码
     *
     * @param request request
     * @author 18010645_黄成
     * @since 15:52 2018/8/31
     */
    @RequestMapping("/memberPhone.do")
    @ResponseBody
    public RsfResponseDto<String> queryMemberPhone(HttpServletRequest request) {
        LOGGER.info("查询会员手机号码");
        RsfResponseDto<String> mobileResult = bookingService.getMemberTelephone(request.getRemoteUser());
        String mobile = StringUtils.EMPTY;
        if (null != mobileResult && StringUtils.isNotBlank(mobileResult.getData())) {
            mobile = mobileResult.getData().replaceAll(PHONE_REGEX, PHONE_FORM);
        }
        return RsfResponseDto.of(mobile);
    }

    /**
     * 功能：预约页面初始化查询接口
     *
     * @param guideId 导购工号
     * @param request request域
     * @author 18010645_黄成
     * @since 15:54 2018/8/31
     */
    @RequestMapping("/queryAppointInit.do")
    @ResponseBody
    public RsfResponseDto<AppointInitQueryRespDto> queryAppointPageInit(HttpServletRequest request, String guideId) {
        LOGGER.info("查询预约页面初始化，导购工号:{}", guideId);
        return bookingService.getAppointInitInfo(request.getRemoteUser(), guideId);
    }

    /**
     * 功能：查询当天预约弹窗语
     *
     * @param request request域
     * @author 18010645_黄成
     * @since 16:00 2018/9/7
     */
    @RequestMapping("/queryAppointment.do")
    @ResponseBody
    public RsfResponseDto<AppointPromptDto> queryAppointmentInfo(HttpServletRequest request, String bookingTime) {
        LOGGER.info("查询预约弹窗语信息");
        return bookingService.getBookPrompInfo(request.getRemoteUser(), bookingTime);
    }


}
