/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideRsfServiceImpl
 * Author:   88395115_史小配
 * Date:     2018/8/16 14:25
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.impl.vgo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.rsf.provider.annotation.Implement;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.BookingReqDto;
import com.suning.sntk.dto.vgo.GuideAppointmentDto;
import com.suning.sntk.dto.vgo.OrderCompletionDto;
import com.suning.sntk.dto.vgo.QueryAppointmentReqDto;
import com.suning.sntk.dto.vgo.StatisticsInfoDto;
import com.suning.sntk.rsf.vgo.VgoGuideRsfService;
import com.suning.sntk.service.vgo.VgoGuideService;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.enums.vgo.BookingStatusEnum;
import com.suning.sntk.support.enums.vgo.BusinessTypesEnum;
import com.suning.sntk.support.enums.vgo.CompleteStatusEnum;
import com.suning.sntk.support.enums.vgo.VisitStatusEnum;
import com.suning.sntk.util.ConversionBusinessTypeUtil;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：店家app导购员服务实现类
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
@Implement(contract = VgoGuideRsfService.class, implCode = "1.0.0")
@Service
@Trace
public class VgoGuideRsfServiceImpl implements VgoGuideRsfService {

    @Autowired
    private VgoGuideService guideService;

    @Override
    public RsfResponseDto<Void> addBooking(BookingReqDto bookingReqDto) {
        BookingInfoDto bookingDto = new BookingInfoDto();
        bookingDto.setGuideId(bookingReqDto.getGuideId());
        bookingDto.setTelephone(bookingReqDto.getCustomerPhone());
        bookingDto.setStoreCode(bookingReqDto.getStoreCode());
        //店家业态转换
        String storeType = ConversionBusinessTypeUtil.getBusinessType(bookingReqDto.getStoreType());
        bookingDto.setBusinessCode(BusinessTypesEnum.MOM_INFANT.getCode().equals(storeType) ?
                BusinessTypesEnum.MOM_INFANT.getCode() : BusinessTypesEnum.ELECTRIC.getCode());
        bookingDto.setBookingStatus(BookingStatusEnum.UNFINISH.getStatus());
        bookingDto.setComplete(CompleteStatusEnum.UNCOMPLETE_ORDER.getStatus());
        bookingDto.setBookingTime(DateUtils.formatPatten10(new Date()));
        bookingDto.setJudgeFlag(VgoConstants.SNSAWP_JUDGE_FLAG_NO);
        bookingDto.setVtype(VgoConstants.OFFLINE);
        StatisticsInfoDto statisticsDto = new StatisticsInfoDto();
        statisticsDto.setChannel(VgoConstants.SNSAWP_CHANNEL);
        statisticsDto.setResourceType(VgoConstants.SNSAWP_RESOURCE_TYPE);
        statisticsDto.setCreateTime(DateUtils.format(new Date()));
        guideService.addBooking(bookingDto, statisticsDto);
        return RsfResponseDto.success();
    }

    @Override
    public RsfResponseDto<Page<GuideAppointmentDto>> queryAppointmentList(QueryAppointmentReqDto reqDto,
            Pageable pageable) {
        //未完成条件转换
        if (StringUtils.isNotBlank(reqDto.getUnComplete())) {
            reqDto.setBookingStatus(BookingStatusEnum.UNFINISH.getStatus());
            if (VgoConstants.UN_CANCEL_ORDER.equals(reqDto.getUnComplete())) {
                List<String> visitList = new ArrayList<>();
                visitList.add(VisitStatusEnum.CALL_UNANSWER.getStatus());
                visitList.add(VisitStatusEnum.CALL_ANSWERED.getStatus());
                reqDto.setVisitList(visitList);
            }
            if (VisitStatusEnum.UNVISIT.getStatus().equals(reqDto.getUnComplete()) || VisitStatusEnum.CALL_UNANSWER.getStatus().equals(
                    reqDto.getUnComplete()) || VisitStatusEnum.CALL_ANSWERED.getStatus().equals(reqDto.getUnComplete())) {
                reqDto.setVisit(reqDto.getUnComplete());
            }
        }
        //已完成条件转换
        if (StringUtils.isNotBlank(reqDto.getComplete())) {
            reqDto.setBookingStatus(BookingStatusEnum.FINISH.getStatus());
            if (reqDto.getComplete().equals(VgoConstants.ALL_COMPLETE)) {
                reqDto.setComplete(null);
            }
        }
        //预约单创建时间条件转换
        if (StringUtils.isNotBlank(reqDto.getCreateTime())) {
            int month = NumberUtils.toInt(reqDto.getCreateTime());
            if (month != VgoConstants.LONGAGO) {
                reqDto.setStartTime(DateUtils.getMonthFirstDay(month));
            }
            reqDto.setEndTime(DateUtils.getMonthFirstDay(month + 1));
        }
        return RsfResponseDto.of(guideService.queryAppointmentList(reqDto, pageable));
    }

    @Override
    public RsfResponseDto<Void> returnVisit(Long bookId, String visitStatus, String guideId) {
        guideService.returnVisit(bookId, visitStatus, guideId);
        return RsfResponseDto.success();
    }

    @Override
    public RsfResponseDto<Void> orderCompletion(OrderCompletionDto orderCompletionDto) {
        guideService.orderCompletion(orderCompletionDto);
        return RsfResponseDto.success();
    }

    @Override
    public RsfResponseDto<Void> updateRemark(Long bookId, String remark, String guideId) {
        guideService.updateRemark(bookId, remark, guideId);
        return RsfResponseDto.success();
    }
}
