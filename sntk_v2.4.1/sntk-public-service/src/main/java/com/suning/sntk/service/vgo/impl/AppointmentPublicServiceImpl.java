/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AppointmentPublicServiceImpl
 * Author:   18032490_赵亚奇
 * Date:     2018/8/17 14:51
 * Description: 预约实现类
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 * 赵亚奇                2018/8/23 9:25      2.2.1
 */

package com.suning.sntk.service.vgo.impl;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.suning.sntk.dao.vgo.GuideAppointmentDao;
import com.suning.sntk.dao.vgo.GuideOrderLogDao;
import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.GuideDto;
import com.suning.sntk.dto.vgo.GuideOrderLogDto;
import com.suning.sntk.dto.vgo.OperationParam;
import com.suning.sntk.dto.vgo.QueryParam;
import com.suning.sntk.dto.vgo.QueryResult;
import com.suning.sntk.entity.vgo.GuideOrderLog;
import com.suning.sntk.service.message.SendMsgService;
import com.suning.sntk.service.vgo.AppointmentPublicService;
import com.suning.sntk.service.vgo.BookingSendMsgService;
import com.suning.sntk.service.vgo.VgoCommonService;
import com.suning.sntk.service.vgo.VgoGuideService;
import com.suning.sntk.support.common.AppointmentConstant;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.redis.vgo.VgoCacheKeyConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.exception.vgo.AppointmentErrorEnum;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 预约实现类
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/17
 */
@Service
@Trace
public class AppointmentPublicServiceImpl implements AppointmentPublicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentPublicServiceImpl.class);

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private GuideAppointmentDao appointmentDao;

    @Autowired
    private VgoGuideService guideService;

    @Autowired
    private GuideOrderLogDao guideOrderLogDao;

    @Autowired
    private SendMsgService sendMsgService;

    @Autowired
    private BookingSendMsgService bookingSendMsgService;

    @Autowired
    private VgoCommonService vgoCommonService;

    /**
     * 延迟发送类型：取消
     */
    private static final String CANCEL_TYPE = "2";

    /**
     * 手机号匹配正则表达式
     */
    private static final String PHONE_REGEX = "(\\d{3})\\d{4}(\\d{4})";

    /**
     * 手机脱敏格式
     */
    private static final String PHONE_FORM = "$1****$2";

    /**
     * 默认的取消预约数量
     */
    private static final int DEFAULT_CANCEL_NUM = 1;

    /**
     * 时间切割符
     */
    private static final String SPLIT_FLAG = ".";

    /**
     * 查询我的预约列表
     *
     * @param custNo 会员编码
     * @param size   查询数量
     * @author 18032490_赵亚奇
     * @since 10:17  2018/8/29
     */
    @Override
    public RsfResponseDto<List<BookingInfoDto>> queryMyAppointmentList(String custNo, Integer size) {
        //分页从缓存中获取数据，默认始终查询第一页
        QueryResult<List<BookingInfoDto>> result = queryVPageInfo(custNo, 1);
        LOGGER.info("从redis中查询的结果 result:{}，custNo={},pageNumber={}", result, custNo, 1);
        //验证每个对象是否为空
        if (result.getTotalCount() > 0 && CollectionUtils.isNotEmpty(result.getData())) {
            List<BookingInfoDto> list = result.getData();
            for (BookingInfoDto info : list) {
                if (null == info) {
                    // 数据有错误,需要回源
                    LOGGER.warn("数据有错误，需要回源 list:{}", list);
                    result.setTotalCount(0);
                    break;
                }
            }
        }
        if (0 == result.getTotalCount()) {
            LOGGER.info("数据回源，需要从库中查找数据");
            //数据回源,获取bookList并放置到缓存中
            List<BookingInfoDto> list = gainBookList(custNo, size);
            if (list.isEmpty()) {
                return RsfResponseDto.of(Collections.<BookingInfoDto>emptyList());
            }
            result = queryVPageInfo(custNo, 1);
        }

        List<BookingInfoDto> list = result.getData();
        if (CollectionUtils.isNotEmpty(list)) {
            for (BookingInfoDto infoDto : list) {
                if (null == infoDto) {
                    continue;
                }
                //设置返还日期
                String createTime = infoDto.getCreateTime();
                if (createTime.contains(SPLIT_FLAG)) {
                    infoDto.setCreateTimeLong(getDateFromString(createTime.substring(0, createTime.indexOf(SPLIT_FLAG))));
                } else {
                    infoDto.setCreateTimeLong(getDateFromString(createTime));
                }
            }
            //按照创建时间排序
            compareLists(list);
            LOGGER.info("排序后的集合list:{}", list);
        }

        return RsfResponseDto.of(list);
    }

    /**
     * 查询预约详情
     *
     * @param custNo   会员编码
     * @param bookCode 会员编号
     * @author 18032490_赵亚奇
     * @since 10:18  2018/8/29
     */
    @Override
    public RsfResponseDto<BookingInfoDto> queryAppoinmentInfo(String custNo, String bookCode) {
        //获取预约信息
        String hKey = MessageFormat.format(VgoCacheKeyConstants.CUST_BOOKING_LIST_PAGE_HKEY, custNo);
        String bookInfoStr = redisCacheUtils.hget(hKey, bookCode);
        LOGGER.info("从redis中获取的预约信息 bookInfoStr:{} ", bookInfoStr);
        BookingInfoDto info;
        if (StringUtils.isNotBlank(bookInfoStr)) {
            info = JSON.parseObject(bookInfoStr, BookingInfoDto.class);
        } else {
            //从数据库中查询预约信息
            info = appointmentDao.findBookingByBookCode(bookCode);
            LOGGER.info("从数据库查询的预约信息：info:{}", info);
        }
        if (null == info || !custNo.equals(info.getCust())) {
            return RsfResponseDto.empty();
        }
        //获取订单数
        info.setOderNum(vgoCommonService.queryOrderNumAndReceivePraise(info.getGuideId()).get("orderNum"));
        //手机号脱敏
        if (StringUtils.isNotBlank(info.getCustTele())) {
            info.setCustTele(info.getCustTele().replaceAll(PHONE_REGEX, PHONE_FORM));
        }

        return RsfResponseDto.of(info);
    }

    /**
     * 取消预约
     *
     * @param bookCode 预约编码
     * @param custNo   会员编码
     * @author 18032490_赵亚奇
     * @since 10:28  2018/8/29
     */
    @Override
    @Transactional
    public RsfResponseDto cancelAppointment(String bookCode, String custNo) {
        //预约单已完成验证
        BookingInfoDto infoDto = appointmentDao.getBookingInfoByBookCode(bookCode);
        LOGGER.info("根据订单号查到的预约详情 BookingInfoDto:{}", infoDto);
        Validators.ifInValid(infoDto == null || !custNo.equals(infoDto.getCust())).thenThrow(AppointmentErrorEnum.NOT_FIND_DATA);
        //如果已经取消则直接返回
        if (AppointmentConstant.COMPLETE_STATUS_FIVE.equals(infoDto.getComplete())) {//NOSONAR
            return RsfResponseDto.success();
        }
        //取消限制次数
        Validators.ifInValid(!isAllowCancel(infoDto.getCust())).thenThrow(AppointmentErrorEnum.UP_TO_CANCEL_MAX_NUM);//NOSONAR
        //到数据库执行取消操作
        infoDto.setComplete(AppointmentConstant.COMPLETE_STATUS_FIVE);
        infoDto.setBookingStatus(AppointmentConstant.BOOKING_STATUS_ONE);
        appointmentDao.cancelBook(infoDto);
        //取消成功后：添加预约单记录
        GuideOrderLog guideOrderLog = new GuideOrderLog();
        guideOrderLog.setBookCode(bookCode);
        guideOrderLog.setGuideId(infoDto.getGuideId());
        guideOrderLog.setCustNumber(infoDto.getCust());
        guideOrderLog.setOrderStatus(AppointmentConstant.ORDER_LOG_STATUS_7);
        guideOrderLog.setCreateFlag(AppointmentConstant.ORDER_LOG_WHO_CUST);
        guideService.addGuideOrderLog(guideOrderLog);
        //更改预约缓存
        syncRedisOfBookList(infoDto);
        //增加取消限制
        fitCancelBookNum(infoDto.getCust());
        // 取消预约短信发送
        cancelBookingMsg(infoDto);
        return RsfResponseDto.success();
    }

    /**
     * 查询预约操作日志
     *
     * @param bookCode 预约编码
     * @author 18032490_赵亚奇
     * @since 10:29  2018/8/29
     */
    @Override
    public RsfResponseDto<List<GuideOrderLogDto>> queryGuideOrderLog(String bookCode) {
        List<GuideOrderLogDto> list = guideOrderLogDao.queryGuideOrderLog(bookCode);
        LOGGER.info("查询预约操作日志的结果:{}", list);
        return RsfResponseDto.of(list);
    }

    /**
     * 查询会员最近一次未完成的预约记录
     *
     * @param custNo 会员编码
     * @author 18032490_赵亚奇
     * @since 10:43  2018/9/14
     */
    @Override
    public RsfResponseDto<List<BookingInfoDto>> queryNearAppointment(String custNo) {
        List<BookingInfoDto> list = appointmentDao.queryNearAppointment(custNo);
        Date nowTime = new Date();
        for (BookingInfoDto dto : list) {
            dto.setNowTime(nowTime);
        }
        return RsfResponseDto.of(list);
    }

    /**
     * 分页获取redis预约列表
     *
     * @param cust       会员编码
     * @param pageNumber 分页条数
     */
    private QueryResult<List<BookingInfoDto>> queryVPageInfo(String cust, Integer pageNumber) {
        LOGGER.info("从redis中分页获取预约列表");
        String pageSize = AppointmentConstant.VGO_APPOINTMENT_LIST_PAGE_SIZE_DEFAULT;
        String zKey = MessageFormat.format(VgoCacheKeyConstants.CUST_BOOKING_LIST_PAGE_ZKEY, cust);
        String hKey = MessageFormat.format(VgoCacheKeyConstants.CUST_BOOKING_LIST_PAGE_HKEY, cust);
        QueryParam param = new QueryParam();
        param.setZkey(zKey);
        param.setHkey(hKey);
        param.setPageNumber(pageNumber);
        param.setPageSize(Integer.parseInt(pageSize));
        return queryPageInfo(param, BookingInfoDto.class);
    }

    /**
     * 功能描述: redis分页查询<br>
     *
     * @param param  分页查询参数
     * @param tClass 转换类型
     */
    private QueryResult<List<BookingInfoDto>> queryPageInfo(QueryParam param, Class tClass) {
        List<BookingInfoDto> infoList = Lists.newArrayList();
        QueryResult<List<BookingInfoDto>> queryResult = new QueryResult<>();
        // 按时间先后顺序进行排序查询
        int start = param.getBegin();
        int end = param.getEnd();

        // 判断start和end大小
        Validators.ifInValid(start > end || end <= 0).thenThrow(AppointmentErrorEnum.PARAM_ERROR_FROM_REDIS);

        // 获取当前分类的总条数
        long totalNums = redisCacheUtils.zcard(param.getZkey());
        // 如果totalNums=0时通知消费方回源
        if (totalNums == 0) {
            LOGGER.info("redis中的数据为空,key:{}", param.getZkey());
            queryResult.setTotalCount(totalNums);
            return queryResult;
        }
        // 计算总页数
        int totalPageNumber = (int) ((totalNums / param.getPageSize()) + ((totalNums % param.getPageSize()) > 0 ? 1 : 0));
        // 当前页数超过总页数
        Validators.ifInValid(param.getPageNumber() > totalPageNumber).thenThrow(AppointmentErrorEnum.PARAM_ERROR_FROM_REDIS);

        // 从有序集合中获取指定区间的数据:预约实体的索引(hash数据的field)
        Set<String> s = redisCacheUtils.zrevrange(param.getZkey(), start, end);
        LOGGER.info("从redis中取到的有序集合set:{}", s);
        if (null != s) {
            //把数据放到string数组中
            String[] keys = new String[s.size()];
            int i = 0;
            for (String t : s) {
                if (StringUtils.isNotBlank(t)) {
                    keys[i] = t;
                    i++;
                }
            }

            if (keys.length > 0) {
                // 通过filed再从hash中获取数据
                List<String> lm = redisCacheUtils.hmget(param.getHkey(), keys);
                for (String m : lm) {
                    if (StringUtils.isNotBlank(m)) {
                        //循环数组,转换成预约实体
                        BookingInfoDto dto = (BookingInfoDto) JSON.parseObject(m, tClass);
                        infoList.add(dto);
                    }
                }
            }
            // 总记录数
            queryResult.setTotalCount(totalNums);
            // 当前页数
            queryResult.setPageNumber(param.getPageNumber());
            // 总页数
            queryResult.setTotalPageCount(totalPageNumber);
            //数据
            queryResult.setData(infoList);
        }
        return queryResult;
    }

    /**
     * 从数据库中获取数据
     *
     * @param custNo 会员编码
     * @param size   查询条数
     */
    private List<BookingInfoDto> gainBookList(String custNo, Integer size) {
        List<BookingInfoDto> list = appointmentDao.queryBookList(custNo, size);
        LOGGER.info("从数据库中查询到的预约列表list:{},随后将list放在redis中", list);
        for (BookingInfoDto info : list) {
            // 放入redis (根据会员编号进行缓存)
            syncRedisOfBookList(info);
        }
        return list;
    }

    /**
     * 数据放到redis中:
     *
     * @param bookingInfo 预约信息
     */
    public void syncRedisOfBookList(BookingInfoDto bookingInfo) {
        //拼接数据
        OperationParam param = getRedisOperationParam(bookingInfo);
        //放到redis中（将每一个cust对应的info分别放在hash和set中）
        updateQueryPageInfo(param);
    }

    /**
     * 拼接数据 <br>
     *
     * @param bookingInfo 预约信息
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private OperationParam getRedisOperationParam(BookingInfoDto bookingInfo) {
        //拼接redis中的key值
        String zKey = MessageFormat.format(VgoCacheKeyConstants.CUST_BOOKING_LIST_PAGE_ZKEY,
                bookingInfo.getCust());
        String hKey = MessageFormat.format(VgoCacheKeyConstants.CUST_BOOKING_LIST_PAGE_HKEY,
                bookingInfo.getCust());
        String createTime = bookingInfo.getCreateTime();
        Date date;
        //转换日期格式
        if (createTime.contains(".")) {
            date = DateUtils.parseByPatten19(createTime.substring(0, createTime.indexOf('.')));
        } else {
            date = DateUtils.parseByPatten19(createTime);
        }
        long score = date.getTime();
        //拼接分页查询参数
        OperationParam param = new OperationParam();
        param.setHkey(hKey);
        param.setZkey(zKey);
        param.setDto(bookingInfo);
        param.setScore(score);
        param.setField(bookingInfo.getBookCode());
        return param;
    }

    /**
     * 数据库回源，redis放入数据。 <br>
     *
     * @param param 分页查询参数
     */
    private void updateQueryPageInfo(OperationParam param) {
        LOGGER.info("将数据放到redis中 param:{}", param);
        boolean blVal = redisCacheUtils.hexists(param.getHkey(), param.getField());
        // redis 的key存在就做修改操作
        if (blVal) {
            redisCacheUtils.zrem(param.getZkey(), param.getField());
        }
        if (redisCacheUtils.exists(param.getHkey())) {
            // 记录顺序,按照时间长整型排序（score）,field为索引
            redisCacheUtils.zadd(param.getZkey(), param.getScore(), param.getField());
            // 存储哈希数据
            redisCacheUtils.hset(param.getHkey(), param.getField(), JSON.toJSONString(param.getDto()));
        } else {
            redisCacheUtils.zadd(param.getZkey(), param.getScore(), param.getField());
            // 存储数据
            redisCacheUtils.hset(param.getHkey(), param.getField(), JSON.toJSONString(param.getDto()));
            int i = (int) timeBetweenTwo();
            redisCacheUtils.expire(param.getZkey(), i);
            redisCacheUtils.expire(param.getHkey(), i);
        }
    }

    /**
     * 功能描述: 当前时间到次日凌晨4点之间的秒数<br>
     */
    private long timeBetweenTwo() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        //当前日期加1
        cal.add(Calendar.DAY_OF_YEAR, 1);
        //设置次日凌晨4点
        cal.set(Calendar.HOUR_OF_DAY, 4);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Calendar cal1 = Calendar.getInstance();
        //计算秒数
        return (cal.getTime().getTime() - cal1.getTime().getTime()) / 1000;
    }

    /**
     * 功能描述:排序，根据创建时间排序
     *
     * @param bList 待排序的集合
     */
    private void compareLists(List<BookingInfoDto> bList) {

        Collections.sort(bList, new Comparator<BookingInfoDto>() {
            @Override
            public int compare(BookingInfoDto t1, BookingInfoDto t2) {
                if (t1.getCreateTimeLong() != 0 && t2.getCreateTimeLong() != 0) {
                    if (t1.getCreateTimeLong() > t2.getCreateTimeLong()) {
                        return -1;
                    } else if (t1.getCreateTimeLong() == t2.getCreateTimeLong()) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else if (t1.getCreateTimeLong() != 0 && t2.getCreateTimeLong() == 0) {
                    return -1;
                } else if (t2.getCreateTimeLong() != 0 && t1.getCreateTimeLong() == 0) {
                    return 1;
                } else if (0 == t1.getCreateTimeLong() && 0 == t2.getCreateTimeLong()) {
                    return 0;
                }
                return 0;
            }
        });
    }

    /**
     * 日期业务处理
     *
     * @param date 待转换的日期
     */
    private long getDateFromString(String date) {
        if (StringUtils.isNotEmpty(date)) {
            Date dt = DateUtils.parseByPatten19(date);
            return dt.getTime();
        }
        return 0;
    }

    /**
     * 功能描述: 是否允许取消
     *
     * @param custNum 会员编码
     */
    private boolean isAllowCancel(String custNum) {
        String today = DateUtils.format(new Date(), DateUtils.PATTEN_10);
        Integer cancelNum = getCancelBookRedis(custNum, today);
        return (null == cancelNum ? 0 : cancelNum) < AppointmentConstant.CANCEL_NUM_MAX;
    }

    /**
     * 从缓存中获取当天取消预约数
     *
     * @param custNum 当前登录用户编码
     * @param date    当天日期
     * @return 当天已取消的预约数
     */
    private Integer getCancelBookRedis(String custNum, String date) {
        // 缓存key
        String key = MessageFormat.format(VgoCacheKeyConstants.DATE_CANCEL_BOOK, custNum, date);
        return redisCacheUtils.getBin(key, Integer.class);
    }

    /**
     * 功能描述: 设置用户取消的次数,同步删除预约缓存记录<br>
     *
     * @param custNum 会员编码
     */
    private void fitCancelBookNum(String custNum) {
        String today = DateUtils.format(new Date(), DateUtils.PATTEN_10);
        //获取用户当天已取消的预约数，没有值默认设置为1
        Integer cancelNum = getCancelBookRedis(custNum, today);
        LOGGER.info("从redis中查到用户的取消次数：custNum:{},cancelNum:{}", custNum, cancelNum);
        setCancelBookRedis(custNum, today, cancelNum == null ? DEFAULT_CANCEL_NUM : ++cancelNum);
    }

    /**
     * 功能描述: 保存某天用户的取消预约次数<br>
     *
     * @param custNum   会员编码
     * @param date      当天日期
     * @param cancelNum 取消次数
     */
    private void setCancelBookRedis(String custNum, String date, Integer cancelNum) {
        // 缓存key
        String key = MessageFormat.format(VgoCacheKeyConstants.DATE_CANCEL_BOOK, custNum, date);
        redisCacheUtils.setexBin(key, cancelNum, VgoCacheKeyConstants.CITY_V_STORES_REDIS_TIME);
    }

    /**
     * 取消预约,短信发送
     */
    private void cancelBookingMsg(BookingInfoDto bookingInfo) {
        // 预约取消成功，发送短息
        GuideDto guideInfo = guideService.getGuideInfo(bookingInfo.getGuideId());
        if (DateUtils.isDelaySendMsg()) {
            //入库操作
            bookingSendMsgService.delaySendMsg(bookingInfo.getBookingTime(), bookingInfo.getCustTele(),
                    guideInfo.getTele(), CANCEL_TYPE, bookingInfo.getBookCode());
            return;
        }
        sendMsgService.sendVgoCancelMsg(bookingInfo.getCreateTime(), bookingInfo.getCustTele(), guideInfo.getTele());
    }
}
