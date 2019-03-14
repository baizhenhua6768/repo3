/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookingSendMsgService
 * Author:   18032490_赵亚奇
 * Date:     2018/8/31 11:29
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo;

/**
 * @author 18032490_赵亚奇
 * @since 2018/8/31
 */
public interface BookingSendMsgService {

    /**
     * 入库操作
     *
     * @param appointDate   预约时间
     * @param customerPhone 预约顾客电话
     * @param guidePhone    店员电话
     * @param type          1：建立预约,2 :取消预约
     * @param bookingCode   预约编码
     * @return true:推迟 false:不推迟
     * @author 18032490_赵亚奇
     * @since 10:23  2018/8/31
     */
    void delaySendMsg(String appointDate, String customerPhone, String guidePhone, String type, String bookingCode);
}