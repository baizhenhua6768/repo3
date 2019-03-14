/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: sendLeaveStaffInfo
 * Author:   18032490_赵亚奇
 * Date:     2018/7/4 9:35
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.mq.sender.staffwhitelist;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.rsc.utils.XStreamUtil;
import com.suning.sntk.admin.mq.sender.JmsTemplateService;
import com.suning.sntk.admin.mq.sender.staffwhitelist.dto.StaffSenderDto;

/**
 * 后台人员管理系统的sender
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/4
 */
@Service
public class StaffSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaffSender.class);

    /**
     * MQ消息服务
     */
    @Autowired
    JmsTemplateService jmsTemplateService;

    /**
     * 发送人员信息到NSFDAS
     *
     * @param dtoList 员工信息集合
     * @param staffStatus I.新增 D.删除 S.启用 H.禁用 L.离职
     */
    public void sendStaffInfo(List<StaffSenderDto> dtoList, String staffStatus) {
        LOGGER.info("发送人员信息到NSFDAS,入参staffId:{},staffStatus:{}", dtoList, staffStatus);
        if (null == dtoList || dtoList.isEmpty() || StringUtils.isEmpty(staffStatus)) {
            LOGGER.info("发送取消，参数为空");
            return;
        }
        // 默认系统NSFDAS
        jmsTemplateService.send("SNTK_INPUTQ", XStreamUtil.objectToXml(new StaffMsg(dtoList, staffStatus)));
        LOGGER.info("发送人员信息到NSFDAS结束");
    }
}

