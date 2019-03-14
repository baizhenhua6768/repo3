/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: SendMsgService
 * Author:   17061157_王薛
 * Date:     2018/8/17 15:22
 * Description: 模块目的、功能描述
 * History: 修改记录
 * <author>      <time>         <version>    <desc>
 * 修改人姓名     修改时间       版本号        描述
 */

package com.suning.sntk.service.message;

/**
 * 功能描述：发送短信接口
 *
 * @author 17061157_王薛
 * @since 2018/8/17
 */
public interface SendMsgService {

    /**
     * 功能描述: 给店员发送预约提示短信 <br>
     *
     * @param appointDate   预约时间
     * @param customerPhone 预约顾客电话
     * @param mobile        店员电话
     * @return boolean
     * @author 17061157_王薛
     * @since 11:32  2018/8/28
     */
    boolean sendVgoAppointMsg(String appointDate, String customerPhone, String mobile);

    /**
     * 功能描述: 给店员发送取消预约提示短信 <br>
     *
     * @param appointDate   预约时间
     * @param customerPhone 预约顾客电话
     * @param mobile        店员电话
     * @return boolean
     * @author 17061157_王薛
     * @since 11:32  2018/8/28
     */
    boolean sendVgoCancelMsg(String appointDate, String customerPhone, String mobile);

}
