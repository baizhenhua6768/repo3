/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AppointmentConstant
 * Author:   18032490_赵亚奇
 * Date:     2018/8/16 16:10
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.common;

/**
 * 预约模块常量类
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/16
 */
public class AppointmentConstant {

    /**
     * 我的预约列表每页数量-默认
     */
    public static final String VGO_APPOINTMENT_LIST_PAGE_SIZE_DEFAULT = "20";
    /**
     * 我的预约数据回源默认数量
     */
    public static final String VGO_APPOINTMENT_LIST_MAX_COUNT_DEFAULT = "100";

    /**
     * 导购评价列表的每页大小-SCM
     */
    public static final String CUST_BOOKING_LIST_MAX_COUNT_DEFAULT_SCM = "CUST_BOOKING_LIST_PAGESIZE";

    /**
     * 默认的订单数
     */
    public static final String ORDER_NUM_DEFAULT = "20";
    /**
     * 降级关闭标记
     */
    public static final String SWITCH_CLOSED = "1";

    /**
     * 打开标记
     */
    public static final String SWITCH_OPEN = "0";

    /**
     * '预约完成状态 ：0未完成 1 完成，'
     */
    public static final String BOOKING_STATUS_ZERO = "0";

    public static final String BOOKING_STATUS_ONE = "1";

    /**
     * 回访状态 1拨打未接听，2 拨打已接听',
     */
    public static final String VIST_STATUS_ONE = "1";

    public static final String VIST_STATUS_TWO = "2";

    /**
     * 销单状态  0未到店、1到店购买、2到店未购买' 3 未销单  5 用户取消  6 系统自动取消,
     */
    public static final String COMPLETE_STATUS_ZERO = "0";

    public static final String COMPLETE_STATUS_ONE = "1";

    public static final String COMPLETE_STATUS_TWO = "2";

    public static final String COMPLETE_STATUS_THREE = "3";

    public static final String COMPLETE_STATUS_FIVE = "5";

    public static final String COMPLETE_STATUS_SIX = "6";

    /**
     * 组装用户看到的订单状态：0: 等待回访  1:恭候到店  2:待评价  3:已评价  4:已关闭
     */
    public static final String ORDER_STATUS_OF_CUST_ZERO = "0";

    public static final String ORDER_STATUS_OF_CUST_ONE = "1";

    public static final String ORDER_STATUS_OF_CUST_TWO = "2";

    public static final String ORDER_STATUS_OF_CUST_THREE = "3";

    public static final String ORDER_STATUS_OF_CUST_FOUR = "4";

    /**
     * 当前版本号
     */
    public static final String VERSION = "2.2.1";

    /**
     * 一天最大取消次数
     */
    public final static int CANCEL_NUM_MAX = 5;

    //预约单日志状态码：等待回访
    public static final String ORDER_LOG_STATUS_1 = "1";
    //预约单日志状态码：恭候到店(回访拨打已接听)
    public static final String ORDER_LOG_STATUS_2 = "2";
    //预约单日志状态码：已完成（到店已购买）
    public static final String ORDER_LOG_STATUS_3 = "3";
    //预约单日志状态码：已完成（到店未购买）
    public static final String ORDER_LOG_STATUS_4 = "4";
    //预约单日志状态码：待评价
    public static final String ORDER_LOG_STATUS_5 = "5";
    //预约单日志状态码：已评价
    public static final String ORDER_LOG_STATUS_6 = "6";
    //预约单日志状态码：取消）
    public static final String ORDER_LOG_STATUS_7 = "7";
    //预约单日志状态码：回访拨打未接听
    public static final String ORDER_LOG_STATUS_8 = "8";

    //预约单操作人类型0:系统,1:导购包含后台,2:用户
    public static final String ORDER_LOG_WHO_SYSTEM = "0";
    public static final String ORDER_LOG_WHO_GUIDE = "1";
    public static final String ORDER_LOG_WHO_CUST = "2";

    /**
     * 用户短信：店员主动发起
     */
    public static final String VBUY_CANCEL_SMS = "手机号为 {0} 的用户已取消 {1} 的预约";

    private AppointmentConstant() {
    }
}
