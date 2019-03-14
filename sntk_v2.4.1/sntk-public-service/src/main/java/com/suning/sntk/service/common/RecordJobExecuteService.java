/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: RecordJobExecuteService
 * Author:   88397670_张辉
 * Date:     2018-9-20 11:01
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.common;

/**
 * 功能描述：记录job执行信息服务
 *
 * @author 88397670_张辉
 * @since 2018-9-20
 */
public interface RecordJobExecuteService {

    /**
     * 添加job执行记录
     *
     * @param jobDesc job功能描述
     * @author 88397670_张辉
     * @since 11:05 2018-9-20
     */
    Long addRecord(String jobDesc);

    /**
     * 更新job执行记录
     *
     * @param id           记录主键
     * @param isSuccess    执行成功标识
     * @param errorMessage 错误信息
     * @author 88397670_张辉
     * @since 11:28 2018-9-20
     */
    Boolean updateRecord(Long id, Boolean isSuccess, String errorMessage);
}
