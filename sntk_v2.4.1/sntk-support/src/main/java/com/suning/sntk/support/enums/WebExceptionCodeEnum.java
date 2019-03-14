/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: PrechargeAdminExceptionCodeEnum
 * Author:   17061157
 * Date:     2018/1/8 18:47
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * 〈〉<br>
 *
 * @author 17061157
 */
public enum WebExceptionCodeEnum implements ServiceExceptionNameProvider {
    // 用户未登录
    MBMBER_NOT_LOGIN("snsmp.web.mbmberNotLogin"),

    // 查询失败
    QUERY_ERROR("snsmp.web.queryError"),

    // 预充值失败
    PRECHARGE_ERROR("snsmp.web.prechargeError"),

    // 充值已达上限
    PRECHARGE_LIMIT_ERROR("snsmp.web.prechargeLimitError"),

    // 活动已过期，{0}个工作日退款{1}元到你的微信账户
    PRECHARGE_ACTIVITY_ERROR("snsmp.web.prechargeActivityError"),

    // {0}个工作日退款{1}元到你的微信账户
    PRECHARGE_SALE_ERROR("snsmp.web.prechargeSaleError"),

    // 取消订单失败
    CANCLE_PRECHARGE_ERROR("snsmp.web.canclePrechargeError"),

    // 退款失败
    REFUND_PRECHARGE_ERROR("snsmp.web.refundPrechargeError"),

    // 订单已打印发票，请在{0}收银台申请退款{1}元
    REFUND_PRECHARGE_PRINTED_ERROR("snsmp.web.refundPrechargePrintedError"),

    // 继续支付失败
    CONTINUE_PRECHARGE_ERROR("snsmp.web.continuePrechargeError"),

    /**
     * 微信config获取失败
     */
    GET_JSAPI_CONFIG_ERROR("snsmp.web.getJsapiConfigError"),

    /**
     * 查询默认微信分享信息失败
     */
    FIND_DEFULAT_WX_SHARE_ERROR("snsmp.web.findDefulatWxShareError"),

    // END占位
    END_ENUM("endEnum");

    private String name;

    WebExceptionCodeEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
