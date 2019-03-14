/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ScanCodeListener
 * Author:   17061157_王薛
 * Date:     2018/7/7 12:12
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.admin.mq.listener.wechat;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.suning.rsc.dto.responsedto.MbfServiceResponse;
import com.suning.rsc.mqservice.ei.annotation.EsbEIServerService;
import com.suning.rsc.server.NullResponseDto;
import com.suning.rsc.server.mq.MQServerServiceHandler;
import com.suning.sntk.admin.mq.listener.NullRespService;
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.ScanCodeBodyDto;
import com.suning.sntk.admin.service.vgo.WechatPublicAccountService;
import com.suning.sntk.consumer.WechatConsumerService;
import com.suning.sntk.entity.relation.CustomerAdvisor;
import com.suning.sntk.entity.relation.WechatFans;
import com.suning.sntk.service.advisor.AdvisorService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.vgs.wgg.dto.member.WeixinSNSUserInfo;
import com.suning.vgs.wgg.dto.qrcode.QrcodeTempBussinessListDto;

/**
 * 功能描述：用户扫码事件
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@EsbEIServerService(connectionFactory = "sntkQueueConnectionFactory", receiveQueue = "MBF_scanSceneQrCode_SNTK")
@Trace
public class ScanCodeListener extends MQServerServiceHandler<ScanCodeBodyDto, NullResponseDto, NullRespService> {

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(ScanCodeListener.class);

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private WechatConsumerService wechatConsumerService;

    @Autowired
    private WechatPublicAccountService wechaService;

    @Override
    public NullResponseDto handleBizz(ScanCodeBodyDto scanCodeBodyDto, String s) {
        log.info("ScanCodeListener receive message:{}", scanCodeBodyDto);
        NullResponseDto response = new NullResponseDto();
        String sceneId = scanCodeBodyDto.getSceneId();
        //判断是不是扫描带参数二维码
        if (isEmpty(scanCodeBodyDto) || !sceneId.startsWith(CommonConstants.PREFIX_FOR_SCENEID)) {
            log.error("ScanCodeListener params error");
            return response;
        }
        try {
            // 根据业务场景查询业务数据,获取店员工号,unionId,storeCode
            QrcodeTempBussinessListDto bussinessDto = wechatConsumerService.queryShoperInfoBySceneId(sceneId);
            // 查询微信信息（unionId）
            WeixinSNSUserInfo weixinSNSUserInfo = wechatConsumerService
                    .queryWeixinSNSUserInfo(scanCodeBodyDto.getDevWeixinNo(), scanCodeBodyDto.getOpenId());
            String unionId = weixinSNSUserInfo.getUnionid();
            String storeCode = bussinessDto.getApplicantId();
            String staffId = bussinessDto.getCustomData();
            if (isValidWechatFansRelation(bussinessDto, weixinSNSUserInfo)) {
                //查询顾客和店员是不是专业顾问关系
                CustomerAdvisor customerAdvisorInfo = advisorService.queryCustomer(staffId, unionId, storeCode);
                if (null != customerAdvisorInfo && StringUtils.isNotBlank(customerAdvisorInfo.getUnionId())) {
                    //是专属顾问关系：更新最后扫码时间
                    advisorService.updateLastScanTimeSkipNull(customerAdvisorInfo.getId(), DateUtils.getNowFullStr());
                } else {
                    CustomerAdvisor customerAdvisor = createCustomerAdvisor(weixinSNSUserInfo, unionId, staffId, storeCode);
                    WechatFans wechatFans = createWeChatFans(scanCodeBodyDto, unionId, bussinessDto.getWechatId());
                    //不是专属顾问关系：建立粉丝关系和专属顾问关系
                    if (null != customerAdvisor) {
                        //建立粉丝关系和专属顾问关系
                        advisorService.buildFansAndAdvisorRelation(customerAdvisor, wechatFans);
                    } else {
                        log.error("创建专属顾问关系实体失败：customerAdvisor is null");
                    }
                }

            } else {
                log.error("根据业务场景查询业务数据失败或查询微信信息失败：bussinessDto:{},weixinSNSUserInfo:{}", bussinessDto,
                        weixinSNSUserInfo);
            }
            //发送模板消息到会员集市STORM系统
            wechaService.scanCodeAffair(scanCodeBodyDto.getOpenId(), staffId, storeCode);

        } catch (Exception e) {
            log.error("ScanCodeListener exception message：", e);
        }
        return response;
    }

    /**
     * 创建专属顾问实体
     *
     * @param weixinSNSUserInfo 微信信息
     * @param unionId           公众平台客户唯一编号
     * @param staffId           店员工号
     * @param storeCode         门店编码
     * @author 88396455_白振华
     * @since 16:53  2018-7-10
     */
    private CustomerAdvisor createCustomerAdvisor(WeixinSNSUserInfo weixinSNSUserInfo, String unionId, String staffId, String storeCode) {
        CustomerAdvisor customerAdvisor = new CustomerAdvisor();
        customerAdvisor.setRemarkName(weixinSNSUserInfo.getNickname());
        customerAdvisor.setStoreCode(storeCode);
        customerAdvisor.setStaffId(staffId);
        customerAdvisor.setUnionId(unionId);
        customerAdvisor.setSex(weixinSNSUserInfo.getSex());
        //获取专属顾问关系实体
        return advisorService.createAdvisorEntity(customerAdvisor);
    }

    /**
     * 创建粉丝实体
     *
     * @param scanCodeBodyDto 扫码事件实体
     * @param unionId         公众平台客户唯一编号
     * @param appId           公众号Id
     * @author 88396455_白振华
     * @since 16:55  2018-7-10
     */
    private WechatFans createWeChatFans(ScanCodeBodyDto scanCodeBodyDto, String unionId, String appId) {
        WechatFans wechatFans = new WechatFans();
        wechatFans.setSceneId(scanCodeBodyDto.getSceneId());
        wechatFans.setOpenId(scanCodeBodyDto.getOpenId());
        wechatFans.setAppId(appId);
        wechatFans.setUnionId(unionId);
        wechatFans.setCreater(CommonConstants.SYS);
        wechatFans.setCreateTime(new Date());
        wechatFans.setUpdater(CommonConstants.SYS);
        wechatFans.setUpdateTime(new Date());
        return wechatFans;
    }

    /**
     * 功能描述: 判断是否是有效的粉丝关系 <br>
     *
     * @param bussinessDto      微信关注业务数据
     * @param weixinSNSUserInfo 微信公众号信息
     * @return boolean
     * @author 17061157_王薛
     * @since 14:54  2018/7/7
     */
    private boolean isValidWechatFansRelation(QrcodeTempBussinessListDto bussinessDto, WeixinSNSUserInfo weixinSNSUserInfo) {
        return bussinessDto != null
                && weixinSNSUserInfo != null
                && StringUtils.isNotBlank(bussinessDto.getCustomData())
                && !CommonConstants.NULLSTR.equals(bussinessDto.getCustomData())
                && StringUtils.isNotBlank(weixinSNSUserInfo.getUnionid());
    }

    @Override
    public void sendResponse(NullRespService nullRespService, NullResponseDto nullResponseDto,
            MbfServiceResponse mbfServiceResponse) {
        //empty method
    }

    /**
     * 判断扫码下发信息是否为空
     *
     * @param scbd 扫码事件实体
     * @author 88396455_白振华
     * @since 17:05  2018-7-11
     */
    private boolean isEmpty(ScanCodeBodyDto scbd) {
        return (StringUtils.isEmpty(scbd.getDevWeixinNo()) || StringUtils.isEmpty(scbd.getOpenId())
                || StringUtils.isEmpty(scbd.getSceneId()));
    }
}
