/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookingRsfServiceImpl
 * Author:   18010645_黄成
 * Date:     2018/8/19 11:12
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.impl.vgo;

import com.suning.rsf.provider.annotation.Implement;
import org.apache.commons.lang3.StringUtils;
import com.suning.sntk.dto.vgo.AppointInitQueryRespDto;
import com.suning.sntk.dto.vgo.AppointPromptDto;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.StatisticsInfoDto;
import com.suning.sntk.rsf.vgo.BookingRsfService;
import com.suning.sntk.service.vgo.BookingPageService;
import com.suning.sntk.service.vgo.VgoGuideService;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.enums.VgoErrorEnum;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 功能描述：预约导购RSF实现类
 *
 * @author 18010645_黄成
 * @since 2018/8/19
 */
@Implement(contract = BookingRsfService.class, implCode = "BookingRsfServiceImpl")
@Service
public class BookingRsfServiceImpl implements BookingRsfService {

    @Autowired
    private VgoGuideService vgoGuideService;

    @Autowired
    private BookingPageService bookingPageService;

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
     * @param bookingInfo    预约入参
     * @param statisticsInfo 统计入参
     * @author 18010645_黄成
     * @since 15:28 2018/9/17
     */
    @Override
    public RsfResponseDto<String> bookingGuide(BookingInfoDto bookingInfo, StatisticsInfoDto statisticsInfo) {
        //参数判空校验
        Validators.ifInValid(StringUtils.isEmpty(bookingInfo.getGuideId())
                || StringUtils.isEmpty(bookingInfo.getCust())
                || StringUtils.isEmpty(bookingInfo.getTelephone())).thenThrow(VgoErrorEnum.PARAM_ERROR);
        //校验预约时间
        checkBookingTime(bookingInfo.getBookingTime());
        String bookCode = vgoGuideService.addBooking(bookingInfo, statisticsInfo);
        Validators.ifInValid(StringUtils.isEmpty(bookCode)).thenThrow(VgoErrorEnum.BOOKING_FAIL);
        return RsfResponseDto.of(bookCode);
    }

    /**
     * 功能：校验预约时间（12点之前：算当天开始7天；12点之后次日开始7天）
     *
     * @param bookingTime 预约时间
     * @author 18010645_黄成
     * @since 11:57 2018/9/1
     */
    private void checkBookingTime(String bookingTime) {
        //当前时间yyyy-MM-dd
        String currentDate = DateUtils.format(new Date(), DateUtils.PATTEN_10);
        //当前时间>=12点(预约日期从次日开始后的7天)
        if (DateUtils.twelveAndAfterTwelve()) {
            //预约日期<=当前日期
            Validators.ifInValid(bookingTime.compareTo(currentDate) <= 0).thenThrow(VgoErrorEnum.BOOKING_TIME_ERROR);
        } else {
            //预约日期<当前日期
            Validators.ifInValid(bookingTime.compareTo(currentDate) < 0).thenThrow(VgoErrorEnum.BOOKING_TIME_ERROR);
        }
    }

    /**
     * 功能：查询会员手机号码
     *
     * @param customerNum 会员编码
     * @author 18010645_黄成
     * @since 16:17 2018/9/4
     */
    @Override
    public RsfResponseDto<String> queryMemberPhone(String customerNum) {
        return RsfResponseDto.of(bookingPageService.queryMemberPhone(customerNum));
    }

    /**
     * 功能：查询预约页初始化信息
     *
     * @param customerNum 会员编码
     * @param guideId     导购工号
     * @author 18010645_黄成
     * @since 16:16 2018/9/4
     */
    @Override
    public RsfResponseDto<AppointInitQueryRespDto> queryAppointInit(String customerNum, String guideId) {
        AppointInitQueryRespDto appointInitQueryRespDto = bookingPageService.queryBookingPageInitInfo(customerNum, guideId);
        //手机号脱敏
        if (appointInitQueryRespDto != null && StringUtils.isNotBlank(appointInitQueryRespDto.getCustomerMobile())){
            appointInitQueryRespDto.setCustomerMobile(appointInitQueryRespDto.getCustomerMobile().replaceAll(PHONE_REGEX,PHONE_FORM));
        }
        return RsfResponseDto.of(appointInitQueryRespDto);
    }

    /**
     * 功能：查询会员当天预约信息
     *
     * @param customerNum 会员编码
     * @author 18010645_黄成
     * @since 14:04 2018/9/7
     */
    @Override
    public RsfResponseDto<AppointPromptDto> queryAppointment(String customerNum, String bookingTime) {
        return RsfResponseDto.of(bookingPageService.queryBookingPrompDialogue(customerNum, bookingTime));
    }
}
