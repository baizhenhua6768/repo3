/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: JmsTemplateService
 * Author:   18032490_赵亚奇
 * Date:     2018/7/4 14:17
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.mq.sender;

/**
 * MQ发送消息的服务
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/4
 */
public interface JmsTemplateService {

    /**
     * 发送消息
     *
     * @param destinationName
     * @param msg
     */
    void send(String destinationName, String msg);
}