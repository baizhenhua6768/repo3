package com.suning.sntk.support.enums;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * 功能描述：
 *
 * @author 18010645_黄成
 * @since 2018-8-17
 */
public enum VgoErrorEnum implements ServiceExceptionNameProvider {

    /**
     * 参数错误
     */
    PARAM_ERROR("sntk.vgo.paramError"),

    /**
     * 没有数据
     */
    NO_DATAS("sntk.vgo.noDatas"),

    /**
     * 预约失败
     */
    BOOKING_FAIL("sntk.vgo.bookingFail"),

    /**
     * 云信提示语模板传入类型不合法
     */
    TEMPLATE_TYPE_ERROR("sntk.vgo.typeError"),

    /**
     * 导购已存在
     */
    GUIDE_ALREADY_EXISTED("sntk.vgo.guideAlreadyExisted"),

    /**
     * 路由地址不存在
     */
    ROUTER_ADDRESS_NOT_EXISTED("sntk.vgo.routerAddressNotExisted"),

    /**
     * 调用会员中心个人基本资料信息错误
     */
    VISIT_MEMBER_SYSTEM_FAIL("sntk.vgo.visitMemberSystemFail"),

    /**
     * 预约时间错误（12点之前：算当天之后7天；12点之后次日开始7天）
     */
    BOOKING_TIME_ERROR("sntk.vgo.bookingTimeError"),

    /**
     * 不能超过1000行
     */
    MORE_THAN_MAXNUM("sntk.vgo.cannotMoreThanOneThousand"),

    /**
     * 查询数据库异常
     */
    QUERY_DATA_BASE_EXCEPTION("sntk.vgo.queryDataBaseException"),

    CALL_BAOZI_ERROR("sntk.vgo.callBaoziError");

    private String name;

    private VgoErrorEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
