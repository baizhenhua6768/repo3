/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: AdvisorErrorEnum
 * Author:   17061157_王薛
 * Date:     2018/7/12 18:00
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.exception.enums;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * 功能描述：
 *
 * @author 17061157_王薛
 * @since 2018/7/12
 */
public enum AdvisorErrorEnum implements ServiceExceptionNameProvider {

    /**
     * 店员不在白名单
     */
    STAFF_NOT_IN_WHITELIST("sntk.advisor.staffNotInWhitelist"),

    /**
     * 店员不存在
     */
    STAFF_NOT_EXIST("sntk.advisor.staffNotExist"),

    /**
     * 9999 系统错误
     */
    ADVISOR_END("sntk.advisor.end");

    private String name;

    AdvisorErrorEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
