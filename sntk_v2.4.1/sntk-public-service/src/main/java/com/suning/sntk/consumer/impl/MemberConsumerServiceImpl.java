/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: MemberConsumerServiceImpl
 * Author:   17061157_王薛
 * Date:     2018/7/7 14:58
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer.impl;

import com.suning.aimp.intf.req.individual.QueryIndividualBaseInfoReq;
import com.suning.aimp.intf.resp.individual.QueryIndividualBaseInfoResp;
import com.suning.aimp.intf.rsf.IndividualService;
import com.suning.mds.dto.MemShoppingGenesInfo;
import com.suning.mds.dto.ResponseContent;
import com.suning.mds.service.MemberPortaintInfoService;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.consumer.MemberConsumerService;
import com.suning.sntk.support.common.MemberCenterPublicParams;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.utils.PublicParamUtils;
import com.suning.sntk.support.enums.VgoErrorEnum;
import com.suning.store.commons.lang.BeanUtils;
import com.suning.store.commons.lang.validator.Validators;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 功能描述：会员中台服务 consumer
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@Service
public class MemberConsumerServiceImpl implements MemberConsumerService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberConsumerServiceImpl.class);

    /**
     * RSF 客户预付购服务类
     */
    private MemberPortaintInfoService memberPortaintInfoService = ServiceLocator
            .getService(MemberPortaintInfoService.class, null);

    //查询个人基本资料信息(会员中心RSF)
    private IndividualService individualService = ServiceLocator
            .getService(IndividualService.class, "individualServiceImpl");

    @Override
    public MemShoppingGenesInfo queryMemberFirstPurchaseInfo(String custNum) {
        LOGGER.info("MemberConsumerServiceImpl.queryMemberFirstPurchaseInfo,params[custNum:{}]", custNum);
        ResponseContent<MemShoppingGenesInfo> memShoppingGenesInfo = memberPortaintInfoService
                .queryShoppingGenesInfo(custNum);
        LOGGER.info("MemberConsumerServiceImpl.queryMemberFirstPurchaseInfo,result[{}]", memShoppingGenesInfo.getResponseObject());
        if ("0".equals(memShoppingGenesInfo.getResponseCode())) {
            return memShoppingGenesInfo.getResponseObject();
        }
        return null;
    }

    /**
     * 功能：查询会员手机号码
     *
     * @param custNum 会员编码
     * @author 18010645_黄成
     * @since 16:17 2018/8/24
     */
    @Override
    public String queryMemberPhone(String custNum) {
        LOGGER.info("MemberConsumerServiceImpl.queryMemberPhone,params[custNum:{}]", custNum);
        Validators.ifInValid(StringUtils.isEmpty(custNum)).thenThrow(VgoErrorEnum.PARAM_ERROR);
        QueryIndividualBaseInfoReq queryIndividualBaseInfoReq = new QueryIndividualBaseInfoReq();
        //会员中心公共参数获取
        MemberCenterPublicParams publicParams = PublicParamUtils.obtainingPublicParameters();
        BeanUtils.copy(publicParams, queryIndividualBaseInfoReq);
        queryIndividualBaseInfoReq.setCustNum(custNum);
        //查询个人基本资料信息
        QueryIndividualBaseInfoResp queryIndividualBaseInfoResp = individualService.queryIndividualBaseInfo(queryIndividualBaseInfoReq);
        LOGGER.info("MemberConsumerServiceImpl.queryMemberPhone,result[{}]", queryIndividualBaseInfoResp);
        if (null == queryIndividualBaseInfoResp || VgoConstants.FAIL.equals(queryIndividualBaseInfoResp.getStatus())) {
            return null;
        }
        return queryIndividualBaseInfoResp.getMobileNum();
    }
}
