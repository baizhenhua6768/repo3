/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookingServiceImpl
 * Author:   18010645_黄成
 * Date:     2018/8/19 11:02
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.web.service.vgo.impl;

import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.dto.vgo.AppointInitQueryRespDto;
import com.suning.sntk.dto.vgo.AppointPromptDto;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.StatisticsInfoDto;
import com.suning.sntk.rsf.vgo.BookingRsfService;
import com.suning.sntk.support.enums.VgoErrorEnum;
import com.suning.sntk.web.service.vgo.BookingService;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 功能描述：预约导购实现类
 *
 * @author 18010645_黄成
 * @since 2018/8/19
 */
@Service
public class BookingServiceImpl implements BookingService {
    //预约RSF
    private BookingRsfService bookingRsfService = ServiceLocator
            .getService(BookingRsfService.class, "BookingRsfServiceImpl");

    @Override
    public RsfResponseDto<String> bookingGuide(BookingInfoDto bookingInfo, StatisticsInfoDto statisticsInfo) {
        //判断当前预约手机号传值是否为脱敏后的手机号，是则重新查询赋值
        if (bookingInfo.getTelephone().contains("*")){
            bookingInfo.setTelephone(getMemberTelephone(bookingInfo.getCust()).getData());
        }
        return bookingRsfService.bookingGuide(bookingInfo, statisticsInfo);
    }

    @Override
    public RsfResponseDto<String> getMemberTelephone(String custNo) {

        return bookingRsfService.queryMemberPhone(custNo);
    }

    @Override
    public RsfResponseDto<AppointInitQueryRespDto> getAppointInitInfo(String customerNum, String guideId) {
        Validators.ifInValid(StringUtils.isBlank(customerNum) || StringUtils.isBlank(guideId))
                .thenThrow(VgoErrorEnum.PARAM_ERROR);
        return bookingRsfService.queryAppointInit(customerNum, guideId);
    }

    @Override
    public RsfResponseDto<AppointPromptDto> getBookPrompInfo(String customerNum, String bookingTime) {
        Validators.ifInValid(StringUtils.isBlank(customerNum)).thenThrow(VgoErrorEnum.PARAM_ERROR);
        return bookingRsfService.queryAppointment(customerNum, bookingTime);
    }
}
