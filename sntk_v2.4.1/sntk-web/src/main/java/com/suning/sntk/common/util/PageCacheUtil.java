/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: PageCacheUtil.java
 * Author:   12074031
 * Date:     2014-4-3 下午3:49:44
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.common.util;

import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

/**
 * 〈页面缓存设置工具类〉<br>
 * 〈功能详细描述〉
 * 
 * @author 12074031
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PageCacheUtil {

    private static final String PRAGMA = "Pragma";
    private static final String LAST_MODIFIED = "Last-Modified";
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String EXPIRES = "Expires";
    private static final int MILLISECOND = 1000;

    private PageCacheUtil() {

    }

    /**
     *
     * 功能描述: 设置缓存时间
     * 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void setCacheHeader(HttpServletResponse response, Integer cacheTime) {
        setCacheHead(response, cacheTime, cacheTime);
    }

    /**
     * 
     * 功能描述:设置缓存时间 <br>
     * 
     * @param response
     * @param cacheTime CDN缓存
     * @param varnishCacheTime varnish缓存
     * @author 15050648
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void setCacheHead(HttpServletResponse response, Integer cacheTime, Integer varnishCacheTime) {
        if (cacheTime > 0 || varnishCacheTime > 0) {
            response.setHeader(CACHE_CONTROL, "max-age=" + cacheTime);
            // 设置varnish缓存时间
            response.addHeader(CACHE_CONTROL, "s-maxage=" + varnishCacheTime);
            long now = System.currentTimeMillis();
            long cacheTimeLong = (long) cacheTime * MILLISECOND;
            response.setDateHeader("Expires", now + cacheTimeLong);
            response.setHeader("Pragma", "Pragma");
            response.setDateHeader("Last-Modified", now - (now % cacheTimeLong));
        } else if (varnishCacheTime == 0) {
            response.setHeader(CACHE_CONTROL, "max-age=" + varnishCacheTime);
            response.addHeader(CACHE_CONTROL, "s-maxage=" + varnishCacheTime);
            response.setDateHeader("Expires", 0);
            response.setHeader("Pragma", "no-cache");
        } else {
            response.setHeader(CACHE_CONTROL, "no-cache,no-store");
            response.setDateHeader("Expires", 0);
            response.setHeader("Pragma", "no-cache");
        }
    }

    /**
     * 
     * 功能描述: 设置动态缓存时间
     * 
     * @param response 响应对象
     * @param cacheTime 缓存时间
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void setDynCacheHeader(HttpServletResponse response, Integer cacheTime) {
        long now = System.currentTimeMillis();
        if (cacheTime != null) {
            long cacheTimeLong = (long) cacheTime * (long) MILLISECOND;
            response.setDateHeader(EXPIRES, now + cacheTimeLong);
            response.setHeader(PRAGMA, PRAGMA);
            if (cacheTime > 0) {
                Calendar todayEnd = Calendar.getInstance();
                todayEnd.set(Calendar.HOUR_OF_DAY, 23);
                todayEnd.set(Calendar.MINUTE, 59);
                todayEnd.set(Calendar.SECOND, 59);
                todayEnd.set(Calendar.MILLISECOND, 999);
                Long secsDiff = 0L;
                if (todayEnd.getTimeInMillis() > now) {
                    secsDiff = (todayEnd.getTimeInMillis() - now) / 1000 + 60;
                }
                if (secsDiff >= cacheTime) {
                    response.setHeader(CACHE_CONTROL, "max-age" + "=" + cacheTime);
                } else {
                    response.setHeader(CACHE_CONTROL, "max-age" + "=" + secsDiff);
                }
                response.setDateHeader(LAST_MODIFIED, now - (now % cacheTimeLong));
            } else {
                response.setHeader(CACHE_CONTROL, "no-cache,no-store");
            }
        }
    }

    /**
     * 功能描述: CDN整点失效 〈功能详细描述〉add by 14041276
     * */
    public static void setDynExpireHeader(HttpServletResponse response) {
        response.setHeader(PRAGMA, PRAGMA);
        Calendar todayNow = Calendar.getInstance();
        long now = todayNow.getTimeInMillis();

        // 一天的晚上0点
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);

        long expireTimes = todayEnd.getTimeInMillis();
        long lessOneH = todayEnd.getTimeInMillis() - now;
        // from 23:59:999, more then 1h
        if (lessOneH > 3600 * MILLISECOND) {
            // add 1h
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, today.get(Calendar.HOUR_OF_DAY) + 1);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);
            expireTimes = today.getTimeInMillis();
        }
        response.setDateHeader("Expires", expireTimes);
        // cdn缓存时间 距离正点60min差的分钟数*60 单位s
        int cdnCacheTime = 3600 - todayNow.get(Calendar.MINUTE) * 60 - todayNow.get(Calendar.SECOND);
        response.setHeader(CACHE_CONTROL, "max-age=" + cdnCacheTime);
    }
}
