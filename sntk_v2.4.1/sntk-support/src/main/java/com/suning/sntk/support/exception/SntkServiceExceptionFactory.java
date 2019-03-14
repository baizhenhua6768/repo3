/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SampleServiceExceptionFactory
 * Author:   17074520
 * Date:     2018/2/2 11:48
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.exception;

import com.suning.store.commons.lang.exception.AbstractServiceExceptionFactory;
import com.suning.store.commons.lang.exception.ServiceException;
import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * 〈〉<br>
 *
 * @author 17074520 章多亮
 */
public class SntkServiceExceptionFactory extends AbstractServiceExceptionFactory {

    @Override
    public ServiceException create(ServiceExceptionNameProvider provider, Object... args) {
        return new SntkServiceException(provider, args);
    }

    @Override
    public ServiceException create(String code, String message) {
        return new SntkServiceException(code, message);
    }
}
