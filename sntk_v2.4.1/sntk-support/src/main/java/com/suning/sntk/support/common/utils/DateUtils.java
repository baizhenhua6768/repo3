/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: DateUtils.java
 * Author:   15050536
 * Date:     2018年4月5日 上午10:42:24
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.support.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 *
 * @author 15050536 石键平
 */
public class DateUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * yyyy-MM-dd
     */
    public static final String PATTEN_10 = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String PATTEN_19 = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {
        // 私有构造器
    }

    /**
     * 获取格式为"yyyy-MM-dd HH:mm:ss"当前日期字符串
     *
     * @return
     */
    public static String getNowFullStr() {
        return format(new Date(), PATTEN_19);
    }

    /**
     * 将字符串转换成日期对象， 仅支持格式：yyyy-MM-dd HH:mm:ss<br/>
     * <p>
     * 如果字符串格式不正确<br/>
     * 则返回<b style="color:FF2200">当前时间</b><br/>
     *
     * @param date 待转变的字符串
     * @return
     */
    public static Date parseByPatten19(String date) {
        return parse(date, PATTEN_19);
    }

    /**
     * 将字符串转换成日期对象，如果字符串格式不正确<br/>
     * 则返回<b style="color:FF2200">当前时间</b>
     * <p>
     * 内置的格式有：<br/>
     * PATTEN_10="yyyy-MM-dd"<br/>
     * PATTEN_19="yyyy-MM-dd HH:mm:ss"
     * </p>
     *
     * @param date
     * @return
     */
    public static Date parse(String date, String patten) {
        try {
            DateTimeFormatter format = DateTimeFormat.forPattern(patten);
            // 时间解析
            DateTime dateTime = DateTime.parse(date, format);
            return dateTime.toDate();
        } catch (Exception e) {
            LOGGER.error("待解析的日期字符串格式不正确:{}", date, e);
        }
        return new Date();
    }

    /**
     * <p>
     * 将日期对象转换成字符串。
     * </P>
     * <p>
     * 内置的格式有：<br/>
     * PATTEN_10="yyyy-MM-dd"<br/>
     * PATTEN_19="yyyy-MM-dd HH:mm:ss"
     * </p>
     *
     * @return
     */
    public static String format(Date date, String patten) {
        DateTime datetime = new DateTime(date);
        if (StringUtils.isEmpty(patten)) {
            return datetime.toString(PATTEN_19);
        }
        return datetime.toString(patten);
    }

    /**
     * <p>
     * 将日期对象转换成字符串。格式为"yyyy-MM-dd HH:mm:ss"
     * </P>
     *
     * @return
     */
    public static String format(Date date) {
        return format(date, PATTEN_19);
    }

    /**
     * 将日期对象转换成字符串。格式为"yyyy-MM-dd"
     *
     * @param date
     * @author 88395115_史小配
     * @since 11:52 2018/9/5
     */
    public static String formatPatten10(Date date) {
        return format(date, PATTEN_10);
    }

    /**
     * 判断两个日期是否是同一天
     */
    public static boolean isSameDay(Date a, Date b) {
        return getDiffDay(a, b) == 0;
    }

    /**
     * 获取2个日期的相差天数
     */
    public static int getDiffDay(Date a, Date b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("请传入合法入参");
        }
        return Days.daysBetween(new DateTime(a), new DateTime(b)).getDays();// 相差天数
    }

    /**
     * 获取2个日期的相差毫秒数
     */
    public static long getDiffMills(Date a, Date b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("请传入合法入参");
        }
        Duration d = new Duration(new DateTime(a), new DateTime(b));
        return d.getMillis(); // 毫秒
    }

    /**
     * 将日期字符串转为其他格式的字符串
     */
    public static String formatNewStringPattern(String date, String oldPattern, String newPattern) {
        if (date == null || oldPattern == null || newPattern == null)
            return "";
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern);        // 实例化模板对象
        SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern);        // 实例化模板对象
        Date d = null;
        try {
            d = sdf1.parse(date);   // 将给定的字符串中的日期提取出来
        } catch (Exception e) {            // 如果提供的字符串格式有错误，则进行异常处理
            LOGGER.info(e + "");    // 打印异常信息
        }
        return sdf2.format(d);
    }

    /**
     * 获取与当前月相比过去或之后的月份的第一天(包括时分秒)
     *
     * @author 88395115_史小配
     * @since 17:44 2018/8/18
     */
    public static String getMonthFirstDay(int add) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, add);
        return format(calendar.getTime(), PATTEN_19);
    }

    /**
     * 20点-8点时延迟发送短息
     *
     * @author 18032490_赵亚奇
     * @since 10:58  2018/8/31
     */

    public static boolean isDelaySendMsg() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        return hour < 8 || hour >= 20;
    }

    /**
     * 功能：当前时间>=12点
     *
     * @author 18010645_黄成
     * @since 10:58 2018/9/1
     */
    public static boolean twelveAndAfterTwelve() {
        int hour = new Date().getHours();
        return hour >= 12;
    }

    /**
     * 计算两个日期间的秒数时间差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @author 88397670_张辉
     * @since 11:23 2018-9-20
     */
    public static Long estimatedSeconds(Date startDate, Date endDate) {
        long a = endDate.getTime();
        long b = startDate.getTime();
        return (a - b) / 1000;
    }
}
