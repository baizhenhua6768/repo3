/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoException
 * Author:   18041004_余长杰
 * Date:     2018/8/18 11:00
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.exception.vgo;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * 功能描述：导购错误类
 *
 * @author 18041004_余长杰
 * @since 2018/8/18
 */
public enum GuideInfoExceptionEnum implements ServiceExceptionNameProvider {

    PARAM_IS_NULL("sntk.guideInfo.ParamIsNull"),
    CUST_MANAGER_DOWNGRADE("sntk.guideInfo.CustManagerDowngrade");

    private String name;

    GuideInfoExceptionEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
