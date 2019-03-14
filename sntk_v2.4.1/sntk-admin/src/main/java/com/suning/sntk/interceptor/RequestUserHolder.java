/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: RequestUserHolder
 * Author:   88396455_白振华
 * Date:     2018-7-2 14:04
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.interceptor;

import com.suning.sntk.dto.region.EmployeeInfo;

/**
 * 〈一句话功能简述〉<br>
 * 保存请求的用户信息
 *
 * @author hadoop
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RequestUserHolder {
    private static ThreadLocal<EmployeeInfo> reqUserHolder = new ThreadLocal<EmployeeInfo>();

    private RequestUserHolder() {
    }

    /**
     * 功能描述: <br>
     * 重新设置请求的用户信息
     *
     * @param session
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void resetRequestUser(EmployeeInfo session) {
        removeRequestUser();
        setRequestUser(session);
    }

    /**
     * 功能描述: <br>
     * 获取用户信息
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static EmployeeInfo getRequestUser() {
        return reqUserHolder.get();
    }

    /**
     * 功能描述: <br>
     * 保存用户信息
     *
     * @param session
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void setRequestUser(EmployeeInfo session) {
        reqUserHolder.set(session);
    }

    /**
     * 功能描述: <br>
     * 移出用户信息
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void removeRequestUser() {
        reqUserHolder.remove();
    }
}
