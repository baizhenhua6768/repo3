/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: VgoConstants
 * Author:   88395115_史小配
 * Date:     2018/8/16 16:16
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.common;

/**
 * 功能描述：
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
public class VgoConstants {

    /**
     * 未知所属 默认电器店类型
     */
    public static final String UN_KNOW_ATTACH = "99";

    /**
     * 其他岗位
     */
    public static final String UN_KNOW_HIERARCH = "99";

    private VgoConstants() {
    }

    //校验手机号的正则表达式
    public static final String TELEPHONE_MATCHES = "\\d{11}$";

    // 线上
    public static final String ONLINE = "0";
    // 线下
    public static final String OFFLINE = "1";
    //建立预约
    public static final String ADD_BOOKING_TYPE = "1";
    //预约编号以VGO开头
    public static final String BOOK_CODE_HEAD = "VGO";
    //以逗号分隔
    public static final String SPLIT_FLAG = ",";
    //顿号
    public static final String SPLIT_FLAG_BREAK = "、";
    //数字位数 10位
    public static final int RANDOM_NUM = 10;

    /**
     * 渠道 线下接待
     */
    public static final String SNSAWP_CHANNEL = "12";

    /**
     * 店+来源
     */
    public static final String SNSAWP_RESOURCE_TYPE = "1";

    /**
     * 是否评价 0:未到店，1：已到店（可评价），2：已评价
     */
    public static final String SNSAWP_JUDGE_FLAG_WAIT = "1";
    /**
     * 是否评价 0:未到店，1：已到店（可评价），2：已评价
     */
    public static final String SNSAWP_JUDGE_FLAG_NO = "0";

    /**
     * 订单完成情况 0未到店、1到店购买、2到店未购买、3初始化状态
     */
    public static final String SNSAWP_COMPLETE_0 = "0";

    public static final String SNSAWP_COMPLETE_1 = "1";

    public static final String SNSAWP_COMPLETE_2 = "2";

    /**
     * 字典类型,1:拼接门店编码和导购Id
     */
    public static final String WELCOME_TO_YOU = "1";

    /**
     * 字典类型,2:不拼接
     */
    public static final String SORRY_TO_YOU = "2";

    /**
     * 有客户经理
     */
    public static final String EXSIT_CUSTOMER_MANAGER = "0";

    /**
     * 没有客户经理
     */
    public static final String NO_CUSTOMER_MANAGER = "1";

    /**
     * 已经存在客户经理
     */
    public static final String IS_EXIST_CUSTOMER_MANAGER = "我已经是您的客户经理了，您可以通过苏宁易购APP“我的易购”找到我哦";


    /**
     * 有客户经理
     */
    public static final String IS_CUSTOMER_MANAGER_YX = "0";

    /**
     * 无客户经理
     */
    public static final String NO_CUSTOMER_MANAGER_YX = "1";

    /**
     * 无客户经理type
     */
    public static final String YX_DIALOGUE_TIPS = "YX_DIALOGUE_TIPS";

    /**
     * 无客户经理code
     */
    public static final String NO_MANAGER_TEMPALTE = "NO_MANAGER_TEMPALTE";

    /**
     * 门店层级
     */
    public static final String STORE_LEVEL = "4";

    /**
     * 总部
     */
    public static final String HQ_LEVEL = "0";
    /**
     * 分公司
     */
    public static final String COMPANY_LEVEL = "1";
    /**
     * 大区
     */
    public static final String REGION_LEVEL = "2";

    /**
     * 修改预约时间操作类型
     */
    public static final String MODIFY_APPOINTMENT_TIME = "0";

    /**
     * 销单
     */
    public static final String MODIFY_APPOINTMENT_DESTORY = "1";

    /**
     * 添加备注
     */
    public static final String MODIFY_APPOINTMENT_REMARK = "2";

    /**
     * 回访
     */
    public static final String MODIFY_APPOINTMENT_VISIT = "3";

    //预约单是否有效 0-无效 1-有效
    public static final String ORDER_VALID = "1";

    //预约单操作人类型0:系统,1:导购包含后台,2:用户
    public static final String ORDER_LOG_WHO_GUIDE = "1";
    public static final String ORDER_LOG_WHO_CUST = "2";

    /**
     * 行政等级 0门店 1城市 2大区 3总部
     */
    public static final String UNIT_LEVEL_0 = "0";
    public static final String UNIT_LEVEL_1 = "1";
    public static final String UNIT_LEVEL_2 = "2";
    public static final String UNIT_LEVEL_3 = "3";

    /**
     * scm配置-城市开关key
     */
    public static final String CITY_CODE_SWITCH = "CITY_CODE_SWITCH";

    /**
     * scm配置:1表示关闭：0表示打开
     */
    public static final String CITY_CLOSE = "1";
    public static final String CITY_OPEN = "0";

    /**
     * 路由地址：导购详情地址
     */
    public static final String ROUTER_ADDRESS_GUIDE_DETAIL = "36";
    // 店家渠道
    public static final String STOREAPP_CHANNEL = "20";
    /**
     * 图片格式
     */
    public static final String IMG_TYPE_PNG = ".png";
    /**
     * 图片尺寸--160
     */
    public static final int IMG_SIZE_160 = 160;

    /**
     * 二维码颜色
     */
    public static final String CODE_COLOR_BLACK = "000000";

    /**
     * 二维码logo地址
     */
    public static final String QR_CODE_LOGO_URL = "http://m.suning.com/RES/wap/V3/images/suning-icon-114-114.png";

    /**
     * get请求
     */
    public static final String GET = "GET";
    /**
     * 超时时间-5秒
     */
    public static final int TIMEOUT = 5000;

    /**
     * 预约业务名称，系统固定4种服务类型，选择新业务类型，选项为,1:V购预约、2:育婴顾问
     */
    public static final String BUSINESS_CODE_VGO = "1";
    public static final String BUSINESS_CODE_MOMINFANT = "2";
    //未销单
    public static final String UN_CANCEL_ORDER = "3";
    //所有已完成预约单
    public static final String ALL_COMPLETE = "7";
    //更久以前
    public static final int LONGAGO = -3;
    //默认名称
    public static final String DEFAULT_NAME = "V购用户";

    /**
     * 门店V购标识关闭
     */
    public static final String STORE_V_FLAG_OFF = "0";

    /**
     * 门店V购标识开启
     */
    public static final String STORE_V_FLAG_ON = "1";

    /**
     * 导购标识 0-关闭，1-开启
     */
    public static final String OPEN_FLAG_OFF = "0";
    public static final String OPEN_FLAG_ON = "1";

    /**
     * 调用会员中心返回失败标识
     */
    public static final String FAIL = "FAIL";

    /**
     * orderNum的默认数量
     */
    public static final String ORDER_NUM_DEFAULT = "20";

    /**
     * orderNum的SCM标签
     */
    public static final String ORDER_NUM_DEFAULT_SCM = "ORDER_NUM_DEFAULT_SCM";

    /**
     * 金牌导购
     */
    public static final String STATIC_GUIDE_INFO = "11";

    /**
     * 是否是V购 0-不是 1-是
     */
    public static final Integer IS_VGO = 1;

    public static final Integer IS_NOT_VGO = 0;

    /**
     * 预约完成状态：0:未到店,1:到店购买,2:到店未购买,3:未销单,4:通用,5:用户取消,6:系统取消
     */
    public static final String COMPLETE_STATUS_NO_ORDER = "3";

    /**
     * 预约状态：0：未完成，1：已完成
     */
    public static final String BOOKING_STATUS_NO_COMPLETE = "0";

    /**
     * 预约渠道：0:无渠道
     */
    public static final String CHANEL_NO = "0";

    /**
     * 是否评价 0:未到店，1：已到店（可评价），2：已评价
     */
    public static final String EVALUATE_NO_STORE = "0";

    /**
     * 来源
     */
    public static final String RESOURCE_TYPE = "1";

    /**
     * 审核标识 0-待审核，1-通过，2-驳回
     */
    public static final int TO_BE_AUDITED = 0;
    public static final int AUDIT_ADOPT = 1;
    public static final int AUDIT_REJECT = 2;

    /**
     * V购标识 0不是，1是
     */
    public static final int NOT_VGO_FLAG = 0;
    public static final int IS_VGO_FLAG = 1;

    /**
     * 服务项目
     */
    public static final String SERVER_ITEM = "1";

    /**
     * V购删除标记 0-正常，1-删除
     */
    public static final Integer VGO_DELETE_FLAG = 1;
    public static final Integer VGO_NORMAL_FLAG = 0;

    /**
     * 待审核提示信息scm配置
     */
    public static final String TO_BE_AUDIT_DATA = "TO_BE_AUDIT_DATA";

    /**
     * 待审核提示Code
     */
    public static final String TO_BE_AUDIT_CODE = "02";

    /**
     * 信息不完整提示信息scm配置
     */
    public static final String IMPERFECT_DATA_MENTION = "IMPERFECT_DATA_MENTION";

    /**
     * 信息不完整提示Code
     */
    public static final String IMPERFECT_MENTION_CODE = "01";

    /**
     * 图片更新是否完成，0-否，1-是
     */
    public static final Integer NOT_COMPLETE_CHANGE_PIC = 0;
    public static final Integer COMPLETE_PIC_CHANGE = 1;

    /**
     * SUIS苏宁联盟系统响应成功码
     */
    public static final String SUIS_SUCCESS_STATUS = "0000";

    /**
     * 预约数倍数*2
     */
    public static final long ORDER_NUM_MULTIPLE = 3;

    /**
     * 获赞数倍数*3
     */
    public static final long RECEIVE_PRAISE_NUM_MULTIPLE = 2;

    /**
     * 接单数Map的key值
     */
    public static final String ORDER_NUM_MAP_KEY = "orderNum";

    /**
     * 获赞数Map的key值
     */
    public static final String RECEIVE_PRAISE_MAP_KEY = "receivePraise";

    /**
     * 提示语类型（云信）
     */
    public static final String YUN_XIN_TIP_TYPE = "1";

    /**
     * 提示语模板（云信）
     */
    public static final String YUN_XIN_TEMPLATE = "vgo.yunxin.template";

    /**
     * 最大服务人员条数
     */
    public static final Integer MAX_SERVANTS_SIZE = 6;

    /**
     * 起始下标
     */
    public static final Integer START_INDEX = 0;

    /**
     * 中文字符串-其它
     */
    public static final String OTHER_CN_STR = "其它";

    /**
     * 电器店
     */
    public static final String ATTACH_ELECTRIC = "电器店";

    /**
     * 中文字符串-关闭
     */
    public static final String CLOSE_CN_STR = "关闭";

    /**
     * 中文字符串-开启
     */
    public static final String OPEN_CN_STR = "开启";

    /**
     * 中文字符串-是
     */
    public static final String YES_CN_STR = "是";

    /**
     * 中文字符串-否
     */
    public static final String NO_CN_STR = "否";

    /**
     * V购信息导出头信息
     */
    public static final String[] VGO_EXPORT_HEARDER = {"工号", "姓名", "门店", "大区", "业态", "接单数", "开启状态", "岗位", "所属", "是否V购"};

    /**
     * 在职
     */
    public static final String ON_THE_JOB = "0";

    /**
     * 来自moss 0-不是，1-是
     */
    public static final Integer NOT_FROM_MOSS = 0;

    /**
     * 客户经理：1-是
     */
    public static final String IS_CUSTOMER_MANAGER = "1";

    /**
     * 客户经理：0-不是
     */
    public static final String NOT_CUSTOMER_MANAGER = "0";

    public static final int MAX_ERROR_MESSAGE_LENGTH = 1000;

    /**
     * 设置客户经理成功
     */
    public static final String SET_CUSTOMER_MANAGER_SUCCESS = "感谢您将我设为客户经理，欢迎随时咨询我";

    /**
     * 设置客户经理成功标识：flag：0
     */
    public static final String SET_CUSTOMER_MANAGER_SUCCESS_FLAG = "0";

    /**
     * 设置客户经理失败标识：flag：1
     */
    public static final String SET_CUSTOMER_MANAGER_FAIL_FLAG = "1";

    /**
     * 设置客户经理：yx_type,dj_type
     */
    public static final String SET_MANAGER_TIPS = "SET_MANAGER_TIPS";

    /**
     * 设置客户经理成功：yx_code
     */
    public static final String SUCCESS_YX = "SUCCESS_YX";

    /**
     * 设置客户经理成功：dj_code
     */
    public static final String SUCCESS_DJ = "SUCCESS_DJ";

    /**
     * 设置客户经理失败：code
     */
    public static final String FAIL_YX = "FAIL_YX";

    /**
     * 忙碌时预约到店提示语模板：code
     */
    public static final String SHOPPER_BUSY_TEMPLAT = "SHOPPER_BUSY_TEMPLAT";

    /**
     * 会员和导购当天存在未完成预约单：0
     */
    public static final String IS_BOOK_ORDER = "0";

    /**
     * 会员和导购当天不存在未完成预约单：1
     */
    public static final String NO_BOOK_ORDER = "1";

}
