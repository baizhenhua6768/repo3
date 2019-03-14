/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CommentErrorEnum.java
 * Author:   15050536
 * Date:     2018年3月30日 上午10:14:50
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.support.exception.enums;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * 通用错误的枚举类
 *
 * @author 15050536 石键平
 */
public enum CommonErrorEnum implements ServiceExceptionNameProvider {

    /**
     * 1001 必填参数为空
     */
    PARAMS_EMPTY("sntk.common.paramsEmpty"),
    /**
     * 3001 调用会员中心失败
     */
    CALL_MEMBER_CENTER_ERROR("sntk.common.callMemberCenterError"),
    /**
     * 4001 调用查询区域RSF失败
     */
    CALL_REGION_RSF_ERROR("sntk.common.callRegionRsfError"),
    /**
     * 4002 调用Nsfuaa RSF失败
     */
    CALL_NSFUAA_ERROR("sntk.commom.callNsfuaaRsfError"),
    /**
     * 9000 新增的字典信息已存在
     */
    DICT_EXIST("sntk.common.dict.exist"),

    /**
     * 9001 编辑的字典信息不存在
     */
    DICT_UN_EXIST("sntk.common.dict.notExist"),

    /**
     * 9998 不支持的redis结构
     */
    REDIS_NOT_SUPPORT_STRUCT("sntk.common.redis.notSupportStruct"),

    /**
     * 客户不存在
     */
    CUSTOMER_NOT_EXIST("sntk.common.customerNotExist"),

    /**
     * 手机号格式不正确
     */
    MOBILE_FORMAT_ERR("sntk.common.mobileFormatErr"),

    /**
     * 9999 系统错误
     */
    SYS_ERROR("sntk.common.sysError");



    private String name;

    CommonErrorEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
