/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: WechatConsumerServiceImpl
 * Author:   17061157_王薛
 * Date:     2018/7/7 14:58
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.suning.aimp.intf.req.individual.QueryIndividualCompleteInfoReq;
import com.suning.aimp.intf.req.social.QuerySocialInfoReq;
import com.suning.aimp.intf.req.thirdparty.spcecial.QueryWechatRelationReq;
import com.suning.aimp.intf.resp.individual.QueryIndividualCompleteInfoResp;
import com.suning.aimp.intf.resp.social.QuerySocialInfoResp;
import com.suning.aimp.intf.resp.thirdparty.special.QueryWechatRelationResp;
import com.suning.aimp.intf.rsf.IndividualService;
import com.suning.aimp.intf.rsf.SocialInfoService;
import com.suning.aimp.intf.rsf.ThirdPartyWechatService;
import com.suning.rsf.consumer.ExceedRetryLimitException;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.consumer.WechatConsumerService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.common.MemberCenterPublicParams;
import com.suning.sntk.support.common.utils.PublicParamUtils;
import com.suning.store.commons.lang.BeanUtils;
import com.suning.vgs.wgg.dto.ResponseData;
import com.suning.vgs.wgg.dto.ResponseDto;
import com.suning.vgs.wgg.dto.member.WeixinSNSUserInfo;
import com.suning.vgs.wgg.dto.qrcode.QrcodeTempBussinessListDto;
import com.suning.vgs.wgg.service.intf.rsf.QrcodeApplicationRemoteService;
import com.suning.vgs.wgg.service.intf.rsf.WechatFansRemoteService;

/**
 * 功能描述：微信相关的外部接口 consumer
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@Service
public class WechatConsumerServiceImpl implements WechatConsumerService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatConsumerServiceImpl.class);

    /**
     * RSF 查询客户unionID 服务
     */
    private WechatFansRemoteService wechatFansRemoteService = ServiceLocator
            .getService(WechatFansRemoteService.class, "wechatFansRemoteService");

    /**
     * 特殊第三方关系微信服务接口
     */
    private ThirdPartyWechatService thirdPartyWechatService = ServiceLocator
            .getService(ThirdPartyWechatService.class, "thirdPartyWechatServiceImpl");

    /**
     * 根据场景值查询业务数据服务
     */
    private QrcodeApplicationRemoteService qrcodeApplicationRemoteService = ServiceLocator
            .getService(QrcodeApplicationRemoteService.class, "qrcodeApplicationRemoteService");

    /**
     * 社交资料服务接口
     */
    private SocialInfoService socialInfoService = ServiceLocator.getService(SocialInfoService.class, "socialInfoServiceImpl");

    /**
     * 查询会员信息资料的服务
     */
    private IndividualService individualService = ServiceLocator.getService(IndividualService.class, "individualServiceImpl");


    @Override
    public QrcodeTempBussinessListDto queryShoperInfoBySceneId(String sceneId) {
        try {
            LOGGER.info("WechatConsumerServiceImpl.queryShoperInfoBySceneId,params[sceneId:{}]", sceneId);
            ResponseDto<QrcodeTempBussinessListDto> responseDto = qrcodeApplicationRemoteService
                    .queryQrcodeBySceneStr(sceneId);
            LOGGER.info("WechatConsumerServiceImpl.queryShoperInfoBySceneId,result[{}]", responseDto);
            if (null == responseDto || CommonConstants.FLAG_FAIL == responseDto.getRetCode()) {
                return null;
            }
            return responseDto.getData();
        } catch (ExceedRetryLimitException e) {
            LOGGER.info("WechatConsumerServiceImpl.queryShoperInfoBySceneId exception:{}", e);
        }
        return null;
    }

    @Override
    public QuerySocialInfoResp queryCustomerSocialInfo(String customerNo) {
        QuerySocialInfoReq querySocialInfoReq = new QuerySocialInfoReq();
        MemberCenterPublicParams publicParams = PublicParamUtils.obtainingPublicParameters();
        BeanUtils.copy(publicParams, querySocialInfoReq);
        querySocialInfoReq.setCustNum(customerNo);
        LOGGER.info("WechatConsumerServiceImpl.queryCustomerSocialInfo,params[querySocialInfoReq:{}]", querySocialInfoReq);
        QuerySocialInfoResp querySocialInfoResp = socialInfoService.querySocialInfo(querySocialInfoReq);
        LOGGER.info("WechatConsumerServiceImpl.queryCustomerSocialInfo,result[{}]", querySocialInfoResp);
        if (null == querySocialInfoResp || CommonConstants.FAIL.equals(querySocialInfoResp.getState())) {
            return null;
        }
        return querySocialInfoResp;
    }

    @Override
    public QueryWechatRelationResp queryWechatRelation(String unionId) {
        QueryWechatRelationReq queryWechatRelation = new QueryWechatRelationReq();
        MemberCenterPublicParams publicParams = PublicParamUtils.obtainingPublicParameters();
        BeanUtils.copy(publicParams, queryWechatRelation);
        queryWechatRelation.setUnionId(unionId);
        LOGGER.info("WechatConsumerServiceImpl.queryWechatRelation,params[queryWechatRelation:{}]", queryWechatRelation);
        QueryWechatRelationResp relationResp = thirdPartyWechatService.queryWechatRelation(queryWechatRelation);
        LOGGER.info("WechatConsumerServiceImpl.queryWechatRelation,result[{}]", relationResp);
        if (null == relationResp || CommonConstants.FAIL.equals(relationResp.getStatus())) {
            return null;
        }
        return relationResp;
    }

    @Override
    public WeixinSNSUserInfo queryWeixinSNSUserInfo(String devWeixinNo, String openId) {
        try {
            LOGGER.info("WechatConsumerServiceImpl.queryWeixinSNSUserInfo,params[devWeixinNo:{},openId:{}]", devWeixinNo, openId);
            ResponseData<WeixinSNSUserInfo> responseData = wechatFansRemoteService.getFans(devWeixinNo, openId);
            LOGGER.info("WechatConsumerServiceImpl.queryWeixinSNSUserInfo,result[{}]", responseData);
            if (null == responseData || ResponseData.FAIL.equals(responseData.getRetFlag())) {
                return null;
            }
            return responseData.getData();
        } catch (ExceedRetryLimitException e) {
            LOGGER.info("WechatConsumerServiceImpl.queryWeixinSNSUserInfo exception:{}", e);
        }
        return null;

    }


    @Override
    public QueryIndividualCompleteInfoResp queryCustCompleteInfo(String customerNo) {
        QueryIndividualCompleteInfoReq req = new QueryIndividualCompleteInfoReq();
        req.setCustNum(customerNo);
        QueryIndividualCompleteInfoResp resp = individualService.queryIndividualCompleteInfo(req);
        LOGGER.info("查询会员完整资料 入参customerNo:{},出参resp:{}", customerNo, resp);
        return resp;
    }
}
