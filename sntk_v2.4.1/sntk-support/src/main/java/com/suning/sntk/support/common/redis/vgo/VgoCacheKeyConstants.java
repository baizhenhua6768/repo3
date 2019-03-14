/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: VgoCacheKeyConstants
 * Author:   88395115_史小配
 * Date:     2018/8/16 17:47
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.common.redis.vgo;

/**
 * 功能描述：V 购 redis缓存key
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
public class VgoCacheKeyConstants {

    private VgoCacheKeyConstants() {
    }

    /**
     * 缓存一小时
     */
    public static final int ONE_HOUR = 60 * 60;

    /**
     * 缓存一天
     */
    public static final int ONE_DAY_TIME = 24 * 60 * 60;

    /**
     * 缓存两小时
     */
    public static final int TWO_HOUR = 7200;

    /**
     * 客户经理信息
     */
    public static final String KEY_CUST_MANAGER = "KEY:CUST:MANAGER:{0}";

    /**
     * 导购详细信息
     */
    public static final String KEY_GUIDE_INFO = "KEY:GUIDE:INFO:{0}";

    /**
     * 店员主页导购详情
     */
    public static final String KEY_STAFF_PAGE_GUIDE_DETAIL = "KEY_STAFF_PAGE_GUIDE_DETAIL_{0}";

    /**
     * 三级目录
     */
    public static final String KEY_THREE_DIRECTORY = "KEY:THREE:DIRECTORY:{0}";


    /**
     * 城市、业态-门店信息
     */
    public static final String KEY_CITY_STORE = "KEY:CITY:STORE:{0}:{1}";

    /**
     * 城市、区、业态-门店信息
     */
    public static final String KEY_CITY_DISTRICT_BUSINESS_TYPE = "KEY:CITY:DISTRICT:BUSINESS:TYPE:{0}:{1}:{2}";


    /**
     * 门店-品类-业态-导购
     */
    public static final String KEY_STORE_GUIDE_CATEGORY = "KEY:STORE:GUIDE:CATEGORY:{0}:{1}:{2}";

    /**
     * 城市-三级类目-导购
     */
    public static final String KEY_CITY_THREE_CATEGORY = "KEY:CITY:THREE:CATEGORY:{0}:{1}";

    /**
     * 城市-业态-导购
     */
    public static final String KEY_CITY_BUSINESS_TYPE = "KEY:CITY:BUSINESS:TYPE:{0}:{1}";

    /**
     * 城市-会员编号后4位-业态-导购
     */
    public static final String KEY_CITY_CUSTNO_BUSINESS_TYPE = "KEY:CITY:CUSTNO:BUSINESS:TYPE:{0}:{1}:{2}";

    /**
     * 会员编码截取后四位
     */
    public static final int SUB_COUNT_OF_CUSTNO = 4;

    /**
     * 导购id
     */
    public static final String KEY_GUIDE_ID = "KEY:GUIDE:ID:{0}";

    /**
     * redis key 客户预约列表的key
     */
    public static final String CUST_BOOKING_LIST_PAGE_ZKEY = "CUST_BOOKING_LIST_PAGE_ZKEY_{0}";

    /**
     * redis key 客户预约列表的key
     */
    public static final String CUST_BOOKING_LIST_PAGE_HKEY = "CUST_BOOKING_LIST_PAGE_HKEY_{0}";


    /**
     * 城市编码转换,4位转3位-中台后台的KEY保持一致
     */
    public static final String KEY_CITY_DATA_F_2_T = "KEY_CITY_DATA_F_2_T_{0}";

    /**
     * 模块路由地址-需要前后台的key保持一致
     */
    public static final String KEY_ROUTER_ADDRESS_INFO = "KEY_ROUTER_ADDRESS_INFO_{0}";

    /**
     * 查询店员所属门店地址key
     */
    public static final String KEY_STAFF_BE_STORE = "KEY_STAFF_BE_STORE_{0}";

    /**
     * 保存某天用户的取消预约次数-4.9版本，DATE_CANCEL_BOOK_{custNum}_{date}
     */
    public static final String DATE_CANCEL_BOOK = "DATE_CANCEL_BOOK_{0}_{1}";

    /**
     * 一天缓存
     */
    public static final int CITY_V_STORES_REDIS_TIME = 86400;

    /**
     * 预约导购信息，TODAY_GUIDER_BOOKINGS_{custNum}_{guideId}_{createStartTime}_{createEndTime}
     */
    public static final String TODAY_GUIDER_BOOKINGS = "TODAY_GUIDER_BOOKINGS_{0}_{1}_{2}_{3}";


    /**
     * 易购会员信息
     */
    public static final String KEY_SCHOOL_USER_INFO = "KEY_SCHOOL_USER_INFO_{0}";

    /**
     * 易购会员预约记录
     */
    public static final String KEY_CUST_BOOK_RECORD = "KEY_CUST_BOOK_RECORD_{0}";

    /**
     * 门店下的V购人员及门店基本信息:UNBG_STORE_VGO_SERVANTS_门店编码
     */
    public static final String UNBG_STORE_VGO_SERVANTS = "UNBG_STORE_VG_SERVANTS_{0}_{1}";

    /**
     * 城市编码-KEY 与后台的KEY保持一致，后台定时任务放入，前台取出,4位转3位
     */
    public static final String KEY_CITY_DATA_T_2_F = "KEY_CITY_DATA_T_2_F_{0}";

}
