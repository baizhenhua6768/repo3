/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SntkRedisJobException
 * Author:   18041004_余长杰
 * Date:     2018/10/10 11:08
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.exception.vgo;

/**
 * 功能描述：更新redis异常类
 *
 * @author 18041004_余长杰
 * @since 2018/10/10
 */
public class SntkRedisJobException extends RuntimeException {
    public SntkRedisJobException() {
        super();
    }

    public SntkRedisJobException(Throwable cause) {
        super(cause);
    }
}
