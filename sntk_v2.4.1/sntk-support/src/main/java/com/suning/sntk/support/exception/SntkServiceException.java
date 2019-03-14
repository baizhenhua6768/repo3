/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SampleServiceException
 * Author:   17074520
 * Date:     2017/12/28 15:08
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.exception;

import com.suning.store.commons.lang.exception.ServiceException;
import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * Sample系统业务异常
 *
 * @author 17074520 章多亮
 */
public class SntkServiceException extends ServiceException {
    private static final long serialVersionUID = -928698719172998543L;

    public SntkServiceException(ServiceExceptionNameProvider provider) {
        super(provider.getName());
    }

    public SntkServiceException(ServiceExceptionNameProvider provider, Object... args) {
        super(provider.getName(), args);
    }

    SntkServiceException(String code, String message) {
        super(code, message);
    }
}
