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
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.SubscribeBodyDto;
import com.suning.sntk.admin.service.vgo.WechatPublicAccountService;
import com.suning.sntk.consumer.WechatConsumerService;
import com.suning.sntk.entity.relation.CustomerAdvisor;
import com.suning.sntk.entity.relation.WechatFans;
import com.suning.sntk.service.advisor.AdvisorService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.vgs.wgg.dto.qrcode.QrcodeTempBussinessListDto;

/**
 * 功能描述：用户关注事件
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@EsbEIServerService(connectionFactory = "sntkQueueConnectionFactory", receiveQueue = "MBF_subscribePush_SNTK")
@Trace
public class SubscribeListener extends MQServerServiceHandler<SubscribeBodyDto, NullResponseDto, NullRespService> {

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(SubscribeListener.class);

    @Autowired
    private WechatConsumerService wechatConsumerService;

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private WechatPublicAccountService wechaService;

    @Override
    public NullResponseDto handleBizz(SubscribeBodyDto subscribeBodyDto, String s) {
        log.info("SubscribeListener receive message:{}", subscribeBodyDto);
        NullResponseDto response = new NullResponseDto();
        //判断是不是扫描带参数二维码
        if (null == subscribeBodyDto || StringUtils.isBlank(subscribeBodyDto.getSceneId()) || !subscribeBodyDto.getSceneId()
                .startsWith(CommonConstants.PREFIX_FOR_SCENEID)) {
            log.error("SubscribeListener params error");
            return response;
        }
        try {
            saveFansAndAdvisorRelation(subscribeBodyDto);
        } catch (Exception e) {
            log.error("SubscribeListener exception message：", e);
        }
        return response;
    }

    @Override
    public void sendResponse(NullRespService nullRespService, NullResponseDto nullResponseDto, MbfServiceResponse mbfServiceResponse) {
        //empty method
    }

    /**
     * 保存关注事件建立的粉丝与专业顾问关系
     *
     * @param subscribeBodyDto 微信关注事件dto
     * @author 88396455_白振华
     * @since 17:36  2018-7-10
     */
    private void saveFansAndAdvisorRelation(SubscribeBodyDto subscribeBodyDto) {
        // 根据业务场景查询业务数据,获取店员工号,unionId,storeCode
        QrcodeTempBussinessListDto bussinessDto = wechatConsumerService.queryShoperInfoBySceneId(subscribeBodyDto.getSceneId());
        if (null != bussinessDto && StringUtils.isNotBlank(bussinessDto.getCustomData()) && !CommonConstants.NULLSTR
                .equals(bussinessDto.getCustomData())) {
            //创建粉丝关系实体
            WechatFans wechatFans = createWechatFans(subscribeBodyDto);
            //创建专属顾问关系实体
            CustomerAdvisor customerAdvisor = createCustomerAdvisor(subscribeBodyDto, bussinessDto);
            if (null != customerAdvisor) {
                //建立粉丝关系和专属顾问关系
                advisorService.buildFansAndAdvisorRelation(customerAdvisor, wechatFans);
            } else {
                log.error("创建专属顾问关系实体失败：customerAdvisor is null");
            }

            //发送模板消息到会员集市STORM系统
            wechaService.scanCodeAffair(subscribeBodyDto.getOpenId(), bussinessDto.getCustomData(), bussinessDto.getApplicantId());
        } else {
            log.error("根据业务场景查询业务数据失败：bussinessDto:{}", bussinessDto);
        }
    }

    /**
     * 创建专属顾问实体
     *
     * @param bussinessListDto 扫码业务信息
     * @param subscribeBodyDto 关注事件实体
     * @author 88396455_白振华
     * @since 16:53  2018-7-10
     */
    private CustomerAdvisor createCustomerAdvisor(SubscribeBodyDto subscribeBodyDto, QrcodeTempBussinessListDto bussinessListDto) {
        //建立专属顾问关系实体
        CustomerAdvisor customerAdvisorReq = new CustomerAdvisor();
        customerAdvisorReq.setRemarkName(subscribeBodyDto.getRemark());
        customerAdvisorReq.setStoreCode(bussinessListDto.getApplicantId());
        customerAdvisorReq.setStaffId(bussinessListDto.getCustomData());
        customerAdvisorReq.setUnionId(subscribeBodyDto.getUnionId());
        customerAdvisorReq.setSex(subscribeBodyDto.getSex());
        //获取专属顾问关系实体
        return advisorService.createAdvisorEntity(customerAdvisorReq);
    }

    /**
     * 创建粉丝实体
     *
     * @param subscribeBodyDto 关注事件实体
     * @author 88396455_白振华
     * @since 16:55  2018-7-10
     */
    private WechatFans createWechatFans(SubscribeBodyDto subscribeBodyDto) {
        WechatFans wechatFans = new WechatFans();
        wechatFans.setOpenId(subscribeBodyDto.getOpenId());
        wechatFans.setSceneId(subscribeBodyDto.getSceneId());
        wechatFans.setAppId(subscribeBodyDto.getAppId());
        wechatFans.setUnionId(subscribeBodyDto.getUnionId());
        wechatFans.setCreater(CommonConstants.SYS);
        wechatFans.setCreateTime(new Date());
        wechatFans.setUpdater(CommonConstants.SYS);
        wechatFans.setUpdateTime(new Date());
        return wechatFans;
    }
}
