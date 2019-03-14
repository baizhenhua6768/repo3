/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SeparatePersonListener
 * Author:   18032490_赵亚奇
 * Date:     2018/7/3 14:54
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.mq.listener.staffwhitelist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.suning.rsc.dto.responsedto.MbfServiceResponse;
import com.suning.rsc.mqservice.ei.annotation.EsbEIServerService;
import com.suning.rsc.server.NullResponseDto;
import com.suning.rsc.server.mq.MQServerServiceHandler;
import com.suning.sntk.admin.dao.staffwhitelist.StaffDao;
import com.suning.sntk.admin.mq.listener.NullRespService;
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.LeaveTab;
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.SeparatePersonDto;
import com.suning.sntk.admin.mq.sender.staffwhitelist.StaffSender;
import com.suning.sntk.admin.mq.sender.staffwhitelist.dto.StaffSenderDto;
import com.suning.sntk.admin.util.SCMConfigUtil;
import com.suning.sntk.dao.vgo.GuideAppointmentDao;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.service.vgo.VgoModifyRedisService;
import com.suning.sntk.support.enums.StaffTypeEnum;
import com.suning.sntk.support.util.DES3;

/**
 * 监听离职人员信息
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/3
 */
@EsbEIServerService(connectionFactory = "sntkQueueConnectionFactory", receiveQueue = "MBF_hrdsSeparatePersonInfo_SNTK")
@Transactional
public class SeparatePersonListener extends MQServerServiceHandler<SeparatePersonDto, NullResponseDto, NullRespService> {

    private static final Logger LOGGER_SPL = LoggerFactory.getLogger(SeparatePersonListener.class);
    /**
     * 离职人员解密秘钥 key
     */
    private static final String LEAVE_STAFF_SECRET_KEY = "leaveStaffSecret";
    /**
     * 系统名称
     */
    private static final String SYS_NAME = "SNTK";
    private static final Integer DELETE_FLAG_LEAVE = 2;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private StaffSender staffSender;
    @Autowired
    private GuideAppointmentDao guideAppointmentDao;
    @Autowired
    private GuideInfoDao guideInfoDao;
    @Autowired
    private VgoModifyRedisService vgoModifyRedisService;

    @Override
    public NullResponseDto handleBizz(SeparatePersonDto separatePersonDto, String s) {
        LOGGER_SPL.info("MQ监听人员离职信息开始 info:{} ", separatePersonDto);
        NullResponseDto response = new NullResponseDto();
        if (null == separatePersonDto || null == separatePersonDto.getiSys() || separatePersonDto.getiTabList().isEmpty()) {
            LOGGER_SPL.info("离职人员信息为空");
            return response;
        }
        //解密，获取员工工号
        List<String> staffList = achieveStaffId(separatePersonDto);
        LOGGER_SPL.info("解密后的员工工号 staffList:{}", staffList);
        if (staffList.isEmpty()) {
            return response;
        }
        //更新人员信息为离职删除
        staffDao.updateDeleteFalgByStaff(staffList, DELETE_FLAG_LEAVE);
        //发送消息
        List<StaffSenderDto> staffInfoList = new ArrayList<>();
        for (String staffId : staffList) {
            StaffSenderDto dto = new StaffSenderDto();
            dto.setStaffId(staffId);
            staffInfoList.add(dto);

        }
        staffSender.sendStaffInfo(staffInfoList, StaffTypeEnum.LEAVE_STAFF.getStaffStatus());
        LOGGER_SPL.info("MQ监听人员离职信息结束");

        //更新导购详情为离职(前台)
        guideInfoDao.updateDimissionFlag(staffList);
        //更新导购详情为离职(后台)
        guideInfoDao.updatebDimissionFlag(staffList);
        //更新预约详情为离职
        guideAppointmentDao.updateDimissionFlag(staffList);

        //删除缓存
        vgoModifyRedisService.batchDeleteGuideInfoCache(staffList);
        return response;
    }

    /**
     * 解密获取员工工号
     */
    private List<String> achieveStaffId(SeparatePersonDto separatePersonDto) {
        //获取秘钥
        String[] keyArray = separatePersonDto.getiSys().getSecretKey().split(",");
        String encryptText = "";
        for (String secretKey : keyArray) {
            if (secretKey.contains(SYS_NAME)) {
                encryptText = secretKey.substring(5, secretKey.length() - 1);
            }
        }
        if (StringUtils.isBlank(encryptText)) {
            return Collections.emptyList();
        }
        //解密秘钥
        String leaveStaffSecret = SCMConfigUtil.getConfig(LEAVE_STAFF_SECRET_KEY);
        if (StringUtils.isBlank(leaveStaffSecret)) {
            LOGGER_SPL.info("SCM no config leaveStaffSecret");
            return Collections.emptyList();
        }

        String text = DES3.decryptProperty(encryptText, leaveStaffSecret);
        //解密密文
        List<String> staffList = new ArrayList<>();
        for (LeaveTab tab : separatePersonDto.getiTabList()) {
            String staffId = DES3.decryptProperty(tab.getPersonNo(), text);
            staffList.add(staffId);
        }

        return staffList;
    }

    @Override
    public void sendResponse(NullRespService nullRespService, NullResponseDto nullResponseDto, MbfServiceResponse mbfServiceResponse) {

    }
}
