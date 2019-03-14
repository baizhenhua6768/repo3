/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ExceptionCodeEnum
 * Author:   17074520
 * Date:     2018/1/8 18:47
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.exception;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * 〈〉<br>
 *
 * @author 17074520 章多亮
 */
public enum ExceptionCodeEnum implements ServiceExceptionNameProvider {
    NAME_IS_NULL("sntk.nameIsNull"),
    USERNAME_IS_NULL("sntk.usernameIsNull"),
    USER_NOT_FOUND("sntk.userNotFound"),
    PRODUCT_ID_IS_NULL("sntk.productIdIsNull"),
    PRODUCT_NAME_IS_NULL("sntk.productNameIsNull"),
    PRODUCT_NOT_FOUND("sntk.productNotFound"),
    FIND_PRODUCT_ERROR("sntk.findProductError");

    private String name;

    ExceptionCodeEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
