/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideServiceImpl
 * Author:   88395115_史小配
 * Date:     2018/8/16 14:33
 * Description: 店+App导购和预约销单模块相关接口
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 * shixiaopei             2018/9/13
 */

package com.suning.sntk.service.vgo.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suning.aimp.intf.resp.social.QuerySocialInfoResp;
import com.suning.sntk.constant.CustomerConstants;
import com.suning.sntk.consumer.HrConsumerService;
import com.suning.sntk.consumer.WechatConsumerService;
import com.suning.sntk.dao.vgo.GuideAppointmentDao;
import com.suning.sntk.dao.vgo.GuideAuditDao;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dao.vgo.GuideOrderLogDao;
import com.suning.sntk.dao.vgo.GuideStatisticsDao;
import com.suning.sntk.dao.vgo.ServerItemDao;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.ClerkCheckResDto;
import com.suning.sntk.dto.vgo.ClerkIntegrityDto;
import com.suning.sntk.dto.vgo.GuideAppointmentDto;
import com.suning.sntk.dto.vgo.GuideDto;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.OrderCompletionDto;
import com.suning.sntk.dto.vgo.QueryAppointmentReqDto;
import com.suning.sntk.dto.vgo.StatisticsInfoDto;
import com.suning.sntk.entity.vgo.AppointmentInfo;
import com.suning.sntk.entity.vgo.GuideAuditInfo;
import com.suning.sntk.entity.vgo.GuideOrderLog;
import com.suning.sntk.enums.SexEnum;
import com.suning.sntk.service.message.SendMsgService;
import com.suning.sntk.service.vgo.BookingSendMsgService;
import com.suning.sntk.service.vgo.CategoryService;
import com.suning.sntk.service.vgo.VgoGuideService;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.enums.vgo.AuditOpinionEnum;
import com.suning.sntk.support.enums.vgo.BookingStatusEnum;
import com.suning.sntk.support.enums.vgo.BusinessTypesEnum;
import com.suning.sntk.support.enums.vgo.CompleteStatusEnum;
import com.suning.sntk.support.enums.vgo.OrderLogStatusEnum;
import com.suning.sntk.support.enums.vgo.VisitStatusEnum;
import com.suning.sntk.support.exception.enums.CommonErrorEnum;
import com.suning.sntk.support.exception.enums.VgoExceptionEnum;
import com.suning.sntk.support.util.CommonUtils;
import com.suning.sntk.support.util.scm.ScmPropertiesUtil;
import com.suning.sntk.support.util.scm.ScmPropertyFileEnum;
import com.suning.sntk.util.ConversionBusinessTypeUtil;
import com.suning.sntk.util.VgoAdminUtils;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 功能描述：店+App导购和预约销单模块相关接口
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
@Service
@Trace
public class VgoGuideServiceImpl implements VgoGuideService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VgoGuideServiceImpl.class);

    @Autowired
    private GuideAppointmentDao guideAppointmentDao;

    @Autowired
    private GuideStatisticsDao guideStatisticsDao;

    @Autowired
    private GuideOrderLogDao guideOrderLogDao;

    @Autowired
    private GuideInfoDao guideInfoDao;

    @Autowired
    private SendMsgService sendMsgService;

    @Autowired
    private AppointmentPublicServiceImpl appointmentPublicService;

    @Autowired
    private WechatConsumerService wechatConsumerService;

    @Autowired
    private BookingSendMsgService bookingSendMsgService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GuideAuditDao guideAuditDao;

    @Autowired
    private ServerItemDao serverItemDao;

    @Autowired
    private HrConsumerService hrConsumerService;

    private static final String HTTP_PROTOCOL = "http:";
    private static final String HTTPS_PROTOCOL = "https:";

    /**
     * 添加接待记录
     *
     * @param bookingDto    预约单信息
     * @param statisticsDto 预约渠道信息
     * @author 88395115_史小配
     * @since 11:25 2018/8/17
     */
    @Override
    @Transactional
    public String addBooking(BookingInfoDto bookingDto, StatisticsInfoDto statisticsDto) {
        // 校验参数
        checkParams(bookingDto);
        // 生成预约单号
        String bookCode = generateBookCode();
        // 添加预约
        bookingDto.setBookCode(bookCode);
        bookingDto.setCreateTime(DateUtils.format(new Date()));
        // 店家新增导购预约 0：线上 1：线下
        if (StringUtils.isEmpty(bookingDto.getVtype())) {
            bookingDto.setVtype(VgoConstants.ONLINE);
            bookingDto.setVisit(VisitStatusEnum.UNVISIT.getStatus());
        } else {
            bookingDto.setVisit(VisitStatusEnum.CALL_ANSWERED.getStatus());
        }
        guideAppointmentDao.addBookGuide(bookingDto);
        // 预约后统计预约渠道
        statisticsDto.setBookCode(bookCode);
        guideStatisticsDao.addGuideStatistics(statisticsDto);
        // 添加预约单记录
        GuideOrderLog guideOrderLog = new GuideOrderLog();
        guideOrderLog.setBookCode(bookCode);
        guideOrderLog.setGuideId(bookingDto.getGuideId());
        guideOrderLog.setCustNumber(bookingDto.getCust());
        guideOrderLog.setOrderStatus(OrderLogStatusEnum.WAIT_VISIT.getStatus());
        guideOrderLog.setOrderStatusFlag(VgoConstants.ORDER_VALID);
        guideOrderLog.setCreateFlag(VgoConstants.ORDER_LOG_WHO_CUST);
        this.addGuideOrderLog(guideOrderLog);
        // 发送短信
        sendMsg(bookingDto);
        // 预约信息写入redis 供我的预约列表查询
        addBookingToRedis(bookingDto);
        return bookCode;
    }

    /**
     * 查询店员信息，将店员信息和预约单信息存入redis缓存
     *
     * @param bookingDto 预约单信息
     * @author 88395115_史小配
     * @since 19:06 2018/9/19
     */
    private void addBookingToRedis(BookingInfoDto bookingDto) {
        if (StringUtils.isNotBlank(bookingDto.getCust())) {
            BookingInfoDto bookingInfoDto = guideAppointmentDao.queryBookGuide(bookingDto.getGuideId());
            LOGGER.info("guideAppointmentDao.queryBookGuide result:{}", bookingInfoDto);
            if (null != bookingInfoDto) {
                bookingInfoDto.setCreateTime(bookingDto.getCreateTime());
                bookingInfoDto.setCust(bookingDto.getCust());
                bookingInfoDto.setJudgeFlag(bookingDto.getJudgeFlag());
                bookingInfoDto.setBusinessCode(bookingDto.getBusinessCode());
                bookingInfoDto.setComplete(bookingDto.getComplete());
                bookingInfoDto.setBookCode(bookingDto.getBookCode());
                bookingInfoDto.setCustTele(bookingDto.getTelephone());
                bookingInfoDto.setVisit(bookingDto.getVisit());
                bookingInfoDto.setBookingStatus(bookingDto.getBookingStatus());
                bookingInfoDto.setBookingTime(bookingDto.getBookingTime());
                bookingInfoDto.setDimissionFlag(VgoConstants.ON_THE_JOB);
                appointmentPublicService.syncRedisOfBookList(bookingInfoDto);
            }
        }
    }

    /**
     * 发送短信
     *
     * @param bookingDto 预约单信息
     * @author 88395115_史小配
     * @since 9:59 2018/9/13
     */
    private void sendMsg(BookingInfoDto bookingDto) {
        GuideDto guideDto = getGuideInfo(bookingDto.getGuideId());
        // 线上预约发送短信，线下不发送
        if (VgoConstants.ONLINE.equals(bookingDto.getVtype()) && null != guideDto && StringUtils.isNotBlank(guideDto.getTele())) {
            // 夜间20点到次日8点的短信延迟发送，持久化到数据库中
            if (DateUtils.isDelaySendMsg()) {
                bookingSendMsgService.delaySendMsg(bookingDto.getBookingTime(), bookingDto.getTelephone(), guideDto.getTele(),
                        VgoConstants.ADD_BOOKING_TYPE, bookingDto.getBookCode());
            } else {
                sendMsgService.sendVgoAppointMsg(bookingDto.getBookingTime(), bookingDto.getTelephone(), guideDto.getTele());
                // 更新预约单已发送短信
                guideAppointmentDao.updateSendSms(bookingDto.getBookCode());
            }
        }
    }

    /**
     * 添加预约单日志记录
     *
     * @param guideOrderLog 预约单日志记录
     * @author 88395115_史小配
     * @since 11:04 2018/8/20
     */
    @Override
    public void addGuideOrderLog(GuideOrderLog guideOrderLog) {
        guideOrderLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if (StringUtils.isNotEmpty(guideOrderLog.getBookCode())
                && StringUtils.isNotEmpty(guideOrderLog.getOrderStatus())
                && StringUtils.isNotEmpty(guideOrderLog.getCreateFlag())) {
            // 执行回访拨打电话未接听、已接听操作，则需要把之前回访状态关闭
            if (OrderLogStatusEnum.VISIT_ANSWERED.getStatus().equals(guideOrderLog.getOrderStatus())
                    || OrderLogStatusEnum.VISIT_UNANSWER.getStatus().equals(guideOrderLog.getOrderStatus())) {
                guideOrderLogDao.updateOrderStatusFlag(guideOrderLog);
            }
            // 除回访拨打未接听外 再添加记录
            if (!OrderLogStatusEnum.VISIT_UNANSWER.getStatus().equals(guideOrderLog.getOrderStatus())) {
                // 添加预约单记录
                guideOrderLogDao.insert(guideOrderLog);
            }
            // 如果导购销单以后，到店未购、到店已购 增加待评价记录
            if (OrderLogStatusEnum.TO_STORE_BUY.getStatus().equals(guideOrderLog.getOrderStatus())
                    || OrderLogStatusEnum.TO_STORE_NO_BUY.getStatus().equals(guideOrderLog.getOrderStatus())) {
                guideOrderLog.setOrderStatus(OrderLogStatusEnum.WAIT_JUDGE.getStatus());
                guideOrderLogDao.insert(guideOrderLog);
            }
        }
    }

    /**
     * 获取导购信息
     *
     * @param guideId 导购id
     * @author 88395115_史小配
     * @since 17:51 2018/8/17
     */
    @Override
    public GuideDto getGuideInfo(String guideId) {
        return guideAppointmentDao.getGuideInfo(guideId);
    }

    /**
     * 生成预约单号
     *
     * @author 88395115_史小配
     * @since 15:20 2018/8/17
     */
    private String generateBookCode() {
        String bookCode = VgoConstants.BOOK_CODE_HEAD + CommonUtils.randomNumber(VgoConstants.RANDOM_NUM);
        if (guideAppointmentDao.isExistBookCode(bookCode)) {
            return generateBookCode();
        } else {
            return bookCode;
        }
    }

    /**
     * 添加预约单校验参数
     *
     * @param bookingDto 预约单信息
     * @author 88395115_史小配
     * @since 10:00 2018/9/13
     */
    private void checkParams(BookingInfoDto bookingDto) {
        // 判断手机号是否是11位数字
        Validators.ifInValid(StringUtils.isBlank(bookingDto.getTelephone()) || !bookingDto.getTelephone().matches(
                VgoConstants.TELEPHONE_MATCHES)).thenThrow(CommonErrorEnum.MOBILE_FORMAT_ERR);
        // 校验当前客户今天是否已经预约过
        Validators.ifInValid(StringUtils.isNotBlank(bookingDto.getCust()) && doCustDayFlag(bookingDto.getCust(), bookingDto
                .getBookingTime())).thenThrow(VgoExceptionEnum.CUST_HAS_APPOINTMENT_TODAY);
    }

    /**
     * 判断客户今天是否已经预约过
     *
     * @param custNo   会员号
     * @param bookTime 预约时间
     * @author 88395115_史小配
     * @since 10:04 2018/9/13
     */
    private boolean doCustDayFlag(String custNo, String bookTime) {
        // 从数据库中查询判断客户今天是否预约过了
        return guideAppointmentDao.isExistCustBooking(custNo, bookTime, DateUtils.formatPatten10(new Date()));
    }

    /**
     * 查询预约列表
     *
     * @param queryAppointmentReqDto 查询条件
     * @param pageable               分页
     * @author 88395115_史小配
     * @since 15:40 2018/8/18
     */
    @Override
    public Page<GuideAppointmentDto> queryAppointmentList(QueryAppointmentReqDto queryAppointmentReqDto, Pageable pageable) {
        Page<GuideAppointmentDto> page = guideAppointmentDao.queryAppointmentList(queryAppointmentReqDto, pageable);
        if (CollectionUtils.isNotEmpty(page.getContent())) {
            // 设置客户姓名和性别
            for (GuideAppointmentDto guideAppointmentDto : page.getContent()) {
                queryMemberMessage(guideAppointmentDto);
            }
        }
        return page;
    }

    /**
     * 设置预约单客户性别和姓名
     *
     * @param guideAppointmentDto 预约单信息
     * @author 88395115_史小配
     * @since 10:06 2018/9/13
     */
    private void queryMemberMessage(GuideAppointmentDto guideAppointmentDto) {
        QuerySocialInfoResp querySocialInfoResp = null;
        if (StringUtils.isNotBlank(guideAppointmentDto.getCust())) {
            querySocialInfoResp = wechatConsumerService.queryCustomerSocialInfo(guideAppointmentDto.getCust());
        }
        if (null == querySocialInfoResp) {
            querySocialInfoResp = new QuerySocialInfoResp();
        }
        // 转换会员接口的性别,为空则设置默认性别，名称
        guideAppointmentDto.setGender(CustomerConstants.MEMBER_SEX_MAN.equals(querySocialInfoResp.getGender()) ?
                SexEnum.MAN.getCode() : SexEnum.WOMAN.getCode());
        guideAppointmentDto.setPartyName(StringUtils.isNotBlank(querySocialInfoResp.getPartyName()) ?
                querySocialInfoResp.getPartyName() : VgoConstants.DEFAULT_NAME);
    }

    /**
     * 添加修改备注
     *
     * @param bookId 预约单id
     * @param remark 备注
     * @author 88395115_史小配
     * @since 14:40 2018/8/20
     */
    @Override
    @Transactional
    public void updateRemark(Long bookId, String remark, String guideId) {
        queryAppointment(bookId, guideId);
        guideAppointmentDao.updateRemark(bookId, remark);
    }

    /**
     * 预约单回访操作
     *
     * @param bookId      预约单id
     * @param visitStatus 回访状态 1-拨打未接听 2-拨打已接听
     * @author 88395115_史小配
     * @since 9:54 2018/8/21
     */
    @Override
    @Transactional
    public void returnVisit(Long bookId, String visitStatus, String guideId) {
        AppointmentInfo appointmentInfo = queryAppointment(bookId, guideId);
        appointmentInfo.setVisit(visitStatus);
        // 回访更新预约单visit状态
        guideAppointmentDao.updateSkipNull(appointmentInfo);
        // 回访添加预约单日志记录
        GuideOrderLog guideOrderLog = new GuideOrderLog();
        guideOrderLog.setBookCode(appointmentInfo.getBookCode());
        guideOrderLog.setGuideId(appointmentInfo.getGuideId());
        guideOrderLog.setCustNumber(appointmentInfo.getCust());
        guideOrderLog.setCreateFlag(VgoConstants.ORDER_LOG_WHO_GUIDE);
        if (VisitStatusEnum.CALL_ANSWERED.getStatus().equals(visitStatus)) {
            guideOrderLog.setOrderStatus(OrderLogStatusEnum.VISIT_ANSWERED.getStatus());
        } else {
            guideOrderLog.setOrderStatus(OrderLogStatusEnum.VISIT_UNANSWER.getStatus());
        }
        this.addGuideOrderLog(guideOrderLog);
        // 更新redis缓存 供我的预约列表查询
        if (StringUtils.isNotBlank(appointmentInfo.getCust())) {
            BookingInfoDto bookingInfoDto = guideAppointmentDao.getAppointmentByBookCode(appointmentInfo.getBookCode());
            if (null != bookingInfoDto) {
                bookingInfoDto.setVisit(appointmentInfo.getVisit());
                appointmentPublicService.syncRedisOfBookList(bookingInfoDto);
            }
        }
    }

    /**
     * 预约单销单操作
     *
     * @param orderCompletionDto 预约单销单信息
     * @author 88395115_史小配
     * @since 10:58 2018/8/21
     */
    @Override
    @Transactional
    public void orderCompletion(OrderCompletionDto orderCompletionDto) {
        AppointmentInfo appointmentInfo = queryAppointment(orderCompletionDto.getBookId(), orderCompletionDto.getGuideId());
        // 预约单已完成的不能重复销单
        Validators.ifInValid(BookingStatusEnum.FINISH.getStatus().equals(appointmentInfo.getBookingStatus()))
                .thenThrow(VgoExceptionEnum.REPEAT_COMPLETE_ORDER);
        // 未回访的预约单不能销单
        Validators.ifInValid(VisitStatusEnum.UNVISIT.getStatus().equals(appointmentInfo.getVisit()))
                .thenThrow(VgoExceptionEnum.UNRETURN_VISIT);
        appointmentInfo.setBookingStatus(BookingStatusEnum.FINISH.getStatus());
        appointmentInfo.setComplete(orderCompletionDto.getComplete().toString());
        appointmentInfo.setReason(orderCompletionDto.getReason());
        // 设置评价状态和接待时间
        if (CompleteStatusEnum.NOT_TO_STORE.getStatus().equals(appointmentInfo.getComplete())) {
            appointmentInfo.setJudgeFlag(VgoConstants.SNSAWP_JUDGE_FLAG_NO);
        } else {
            appointmentInfo.setJudgeFlag(VgoConstants.SNSAWP_JUDGE_FLAG_WAIT);
            appointmentInfo.setReceiveTime(new Timestamp(System.currentTimeMillis()));
        }
        // 销单更新预约单已完成
        guideAppointmentDao.updateSkipNull(appointmentInfo);
        // 销单添加日志记录
        GuideOrderLog guideOrderLog = new GuideOrderLog();
        guideOrderLog.setBookCode(appointmentInfo.getBookCode());
        guideOrderLog.setGuideId(appointmentInfo.getGuideId());
        guideOrderLog.setCustNumber(appointmentInfo.getCust());
        guideOrderLog.setCreateFlag(VgoConstants.ORDER_LOG_WHO_GUIDE);
        if (CompleteStatusEnum.TO_STORE_BUY.getStatus().equals(orderCompletionDto.getComplete().toString())) {
            // 到店已购买
            guideOrderLog.setOrderStatus(OrderLogStatusEnum.TO_STORE_BUY.getStatus());
        } else if (CompleteStatusEnum.TO_STORE_NOBUY.getStatus().equals(orderCompletionDto.getComplete().toString())) {
            // 到店未购买
            guideOrderLog.setOrderStatus(OrderLogStatusEnum.TO_STORE_NO_BUY.getStatus());
        } else {
            // 未到店 日志记录为取消
            guideOrderLog.setOrderStatus(OrderLogStatusEnum.CANCEL.getStatus());
        }
        this.addGuideOrderLog(guideOrderLog);
        // 销单数加1
        guideInfoDao.updateOrderNum(appointmentInfo.getGuideId());
        guideInfoDao.updateOrderNumB(appointmentInfo.getGuideId());
        // 更新redis缓存 供我的预约列表查询
        if (StringUtils.isNotBlank(appointmentInfo.getCust())) {
            BookingInfoDto bookingInfoDto = guideAppointmentDao.getAppointmentByBookCode(appointmentInfo.getBookCode());
            if (null != bookingInfoDto) {
                bookingInfoDto.setBookingStatus(appointmentInfo.getBookingStatus());
                bookingInfoDto.setComplete(appointmentInfo.getComplete());
                bookingInfoDto.setJudgeFlag(appointmentInfo.getJudgeFlag());
                bookingInfoDto.setReason(appointmentInfo.getReason());
                bookingInfoDto.setReceiveTime(appointmentInfo.getReceiveTime());
                appointmentPublicService.syncRedisOfBookList(bookingInfoDto);
            }
        }
    }

    /**
     * 根据预约单id查询预约单信息，并判断是否是当前导购的预约单
     *
     * @param id      预约单id
     * @param guideId 导购id
     * @author 88395115_史小配
     * @since 10:16 2018/9/13
     */
    private AppointmentInfo queryAppointment(Long id, String guideId) {
        AppointmentInfo appointmentInfo = guideAppointmentDao.findById(id);
        Validators.ifInValid(null == appointmentInfo || !guideId.equals(appointmentInfo.getGuideId())).
                thenThrow(VgoExceptionEnum.APPOINTMENT_IS_NOT_EXIST);
        return appointmentInfo;
    }

    /**
     * 查询导购详情
     *
     * @param guideId 导购id
     * @param type    业态
     * @author 88395115_史小配
     * @since 11:05 2018/9/3
     */
    @Override
    public GuideInfoDto queryGuideDetail(String guideId, String type) {
        // 将sawp系统的业态转换为sntk系统业态
        String storeType = ConversionBusinessTypeUtil.getBusinessType(type);
        // 查询导购临时表导购信息
        GuideInfoDto guideInfoDto = guideInfoDao.queryGuideDetaiAudit(guideId);
        if (null != guideInfoDto) {
            // 查询审核表中的服务项目或擅长品类
            queryServiceItemOrCategoryAudit(guideInfoDto);
            // 设置驳回原因，其他原因不返回
            guideInfoDto.setAuditReasonList(castAuditReason(guideInfoDto.getAuditReason()));
        } else {
            // 查询导购表导购信息
            guideInfoDto = guideInfoDao.queryGuideDetai(guideId);
            // 根据业态查询服务项目或者擅长品类
            queryServiceItemOrCategory(storeType, guideId, guideInfoDto);
        }
        // 将导购头像url http 转换成https
        if (null != guideInfoDto && StringUtils.isNotBlank(guideInfoDto.getGuidePhoto())) {
            guideInfoDto.setGuidePhoto(guideInfoDto.getGuidePhoto().replaceFirst(HTTP_PROTOCOL, HTTPS_PROTOCOL));
        }
        return guideInfoDto;
    }

    /**
     * 将字符串形式的驳回原因转换成list，其他原因不返回
     *
     * @param auditReason 驳回原因字符串形式
     * @author 88395115_史小配
     * @since 9:27 2018/10/9
     */
    private List<String> castAuditReason(String auditReason) {
        List<String> auditReasonList = new ArrayList<>();
        if(StringUtils.isNotBlank(auditReason)){
            String auditReasonSub = auditReason;
            int otherIndex =  auditReason.indexOf(AuditOpinionEnum.OTHER_REASONS.getCode());
            if(otherIndex > 0){
                auditReasonSub = auditReason.substring(0,otherIndex);
            }
            if(auditReasonSub.contains(AuditOpinionEnum.HEAD_PHOTO_NOT_STANDARD.getCode())){
                auditReasonList.add(AuditOpinionEnum.HEAD_PHOTO_NOT_STANDARD.getCode());
            }
            if(auditReasonSub.contains(AuditOpinionEnum.INTRODUCTION_NOT_STANDARD.getCode())){
                auditReasonList.add(AuditOpinionEnum.INTRODUCTION_NOT_STANDARD.getCode());
            }
        }
       return auditReasonList;
    }

    /**
     * 查询审核表中的服务项目或擅长品类
     *
     * @param guideInfoDto 导购信息
     * @author 88395115_史小配
     * @since 10:32 2018/9/28
     */
    private void queryServiceItemOrCategoryAudit(GuideInfoDto guideInfoDto) {
        // 查询设置店员擅长品类 数据库中存的是以逗号分隔的品类编号，需要再用编号查到品类名称
        String categoryIds = guideInfoDto.getCategoryIds();
        if (StringUtils.isNotBlank(categoryIds)) {
            List<String> ids = Arrays.asList(categoryIds.split(VgoConstants.SPLIT_FLAG));
            if (CollectionUtils.isNotEmpty(ids)) {
                guideInfoDto.setVcategoryInfos(categoryService.queryGuideCategory(ids));
            }
        }
        // 设置服务项目 数据库中服务项目是以逗号分隔的服务项目名称
        if (StringUtils.isNotBlank(guideInfoDto.getServerName())) {
            guideInfoDto.setServiceItems(Arrays.asList(guideInfoDto.getServerName().split(VgoConstants.SPLIT_FLAG)));
        }
    }

    /**
     * @param storeType    业态
     * @param guideId      导购id
     * @param guideInfoDto 导购信息
     * @author 88395115_史小配
     * @since 10:28 2018/9/28
     */
    private void queryServiceItemOrCategory(String storeType, String guideId, GuideInfoDto guideInfoDto) {
        if (null != guideInfoDto) {
            // 母婴业态查询服务项目
            if (BusinessTypesEnum.MOM_INFANT.getCode().equals(storeType)) {
                // 设置服务项目 数据库中有部分老数据 存的是：图片,服务项目。需要对查询结果进行切割
                guideInfoDto.setServiceItems(VgoAdminUtils.cutOffPicUrl(serverItemDao.queryByGuideId(guideId)));
            } else {
                // 电器业态查询品类
                guideInfoDto.setVcategoryInfos(categoryService.queryGuideCategoryRel(guideId));
            }
        }
    }

    @Override
    @Transactional
    public void modifyAuditGuideInfo(GuideInfoDto guideInfoDto) {
        LOGGER.info("VgoGuideService.modifyAuditGuideInfo param guideInfoDto:{}", guideInfoDto);
        if (null == guideInfoDto){
            return;
        }
        GuideAuditInfo guideAuditInfo = guideAuditDao.queryAuditGuideInfo(guideInfoDto.getGuideId());
        // 临时方案，导购头像入库时，将http强转成https,解决https页面http请求不发的问题 add by 12061818
        if (StringUtils.isNotBlank(guideInfoDto.getGuidePhoto())) {
            String guidePhoteTemp = guideInfoDto.getGuidePhoto().replaceFirst(HTTP_PROTOCOL, HTTPS_PROTOCOL);
            guideInfoDto.setGuidePhoto(guidePhoteTemp);
        }
        // 若未查到当前员工的待审核信息，则新增该员工的审核信息
        if (null == guideAuditInfo) {
            GuideAuditInfo addGuideAuditInfo = buildAuditInfo(guideInfoDto);
            LOGGER.info("modifyAuditGuideInfo.insert param addGuideAuditInfo:{}", addGuideAuditInfo);
            guideAuditDao.insert(addGuideAuditInfo);
        } else {
            GuideAuditInfo updateGuideAuditInfo = buildUpdateAuditInfo(guideAuditInfo, guideInfoDto);
            LOGGER.info("modifyAuditGuideInfo.update param addGuideAuditInfo:{}", updateGuideAuditInfo);
            guideAuditDao.updateSkipNull(updateGuideAuditInfo);
        }
    }

    @Override
    public ClerkCheckResDto checkClerkInfo(String guideId) {
        ClerkCheckResDto clerkCheckResDto = new ClerkCheckResDto();
        ClerkIntegrityDto clerkIntegrityDto = guideInfoDao.checkClerkInfo(guideId);
        if (null != clerkIntegrityDto) {
            clerkCheckResDto.setFlag(true);
            //校验接口添加导购头像返回 2018-10-20版本新增需求
            clerkCheckResDto.setGuidePhoto(clerkIntegrityDto.getGuidePhoto());
        } else {
            // 若待审核表中存在待审核的记录，则说明该导购正在审核
            GuideAuditInfo guideAuditInfo = guideAuditDao.queryAuditGuideInfo(guideId);
            if (null != guideAuditInfo) {
                clerkCheckResDto.setFlag(false);
                clerkCheckResDto.setErrorMsg(
                        ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG, VgoConstants.TO_BE_AUDIT_DATA));
                clerkCheckResDto.setCode(VgoConstants.TO_BE_AUDIT_CODE);
            } else {
                //若正式导购表无信息，且待审核表也无导购信息，则表明该导购信息不全
                clerkCheckResDto.setFlag(false);
                clerkCheckResDto.setErrorMsg(
                        ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG, VgoConstants.IMPERFECT_DATA_MENTION));
                clerkCheckResDto.setCode(VgoConstants.IMPERFECT_MENTION_CODE);
            }
        }
        return clerkCheckResDto;
    }

    @Override
    public GuideInfoDto queryClerkMarkInfo(String guideId) {
        return guideInfoDao.queryGuideDetai(guideId);
    }

    /**
     * 构建审核更新信息
     *
     * @param guideAuditInfo 待审核信息
     * @param guideInfoDto   修改的信息
     * @author 88397670_张辉
     * @since 14:42 2018-9-1
     */
    private GuideAuditInfo buildUpdateAuditInfo(GuideAuditInfo guideAuditInfo, GuideInfoDto guideInfoDto) {
        guideInfoDto.setId(guideAuditInfo.getId());
        BeanUtils.copyProperties(guideInfoDto, guideAuditInfo);
        guideAuditInfo.setBusinessType(ConversionBusinessTypeUtil.getBusinessType(guideInfoDto.getStationType()));
        guideAuditInfo.setServerItems(guideInfoDto.getServerName());
        guideAuditInfo.setUpdateTime(CommonUtils.obtainCurrentTimestamp());
        String attach = null;
        try {
            String orgId = hrConsumerService.queryPerson(guideAuditInfo.getGuideId(), "organizationunit");
            attach = hrConsumerService.queryOrganization(orgId);
        } catch (Exception e) {
            LOGGER.error("调用HR接口查询导购所属失败！", e);
        }
        guideAuditInfo.setAttach(StringUtils.isBlank(attach) ? VgoConstants.UN_KNOW_ATTACH : attach);
        return guideAuditInfo;
    }

    /**
     * 新增待审核店员信息构建
     *
     * @param guideInfoDto 店员信息
     * @author 88397670_张辉
     * @since 16:10 2018-9-1
     */
    private GuideAuditInfo buildAuditInfo(GuideInfoDto guideInfoDto) {
        GuideAuditInfo guideAuditInfo = new GuideAuditInfo();
        // 无待审核分支，可能存在已驳回或审核通过店员信息
        GuideInfoDto guideInfo = guideInfoDao.queryGuideDetaiAudit(guideInfoDto.getGuideId());
        // 若存在审核信息则表示该导购本次非首次提交信息审核
        if (null != guideInfo) {
            BeanUtils.copyProperties(guideInfo, guideAuditInfo);
            if (StringUtils.isNotBlank(guideInfoDto.getGuidePhoto())) {
                guideAuditInfo.setGuidePhoto(guideInfoDto.getGuidePhoto());
            }
            if (StringUtils.isNotBlank(guideInfoDto.getIntroduction())) {
                guideAuditInfo.setIntroduction(guideInfoDto.getIntroduction());
            }
            if (guideInfoDto.getSaleAge() != null) {
                guideAuditInfo.setSaleAge(guideInfoDto.getSaleAge());
            }
            if (StringUtils.isNotBlank(guideInfoDto.getServerName())) {
                guideAuditInfo.setServerItems(guideInfoDto.getServerName());
            }
            if (StringUtils.isNotBlank(guideInfoDto.getCategoryIds())) {
                guideAuditInfo.setCategoryIds(guideInfoDto.getCategoryIds());
            }
        } else {
            // 若不存在则为首次提交审核信息
            BeanUtils.copyProperties(guideInfoDto, guideAuditInfo);
            guideAuditInfo.setServerItems(guideInfoDto.getServerName());
            String attach = null;
            try {
                String orgId = hrConsumerService.queryPerson(guideAuditInfo.getGuideId(), "organizationunit");
                attach = hrConsumerService.queryOrganization(orgId);
            } catch (Exception e) {
                LOGGER.error("调用HR接口查询导购所属失败！", e);
            }
            guideAuditInfo.setAttach(StringUtils.isBlank(attach) ? VgoConstants.UN_KNOW_ATTACH : attach);
            if (StringUtils.isBlank(guideAuditInfo.getHierarchy())) {
                guideAuditInfo.setHierarchy(VgoConstants.UN_KNOW_HIERARCH);
            }

        }
        // 新增待审核店员的创建时间与更新时间使用同一个时间
        Timestamp time = CommonUtils.obtainCurrentTimestamp();
        guideAuditInfo.setAuditReason(null);
        guideAuditInfo.setAuditFlag(VgoConstants.TO_BE_AUDITED);
        guideAuditInfo.setCreateTime(time);
        guideAuditInfo.setCreatePerson(guideInfoDto.getGuideId());
        guideAuditInfo.setBusinessType(ConversionBusinessTypeUtil.getBusinessType(guideInfoDto.getStationType()));
        guideAuditInfo.setUpdateTime(time);
        guideAuditInfo.setUpdatePerson(guideInfoDto.getGuideId());
        return guideAuditInfo;
    }
}
