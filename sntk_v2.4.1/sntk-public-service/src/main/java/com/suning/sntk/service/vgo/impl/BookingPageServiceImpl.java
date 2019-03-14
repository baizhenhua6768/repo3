/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BookingPageServiceImpl
 * Author:   18010645_黄成
 * Date:     2018/8/31 16:23
 * Description: 预约页初始信息接口
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 * 黄成                   20180907            sntk_v2.2.1          预约页初始信息
 */

package com.suning.sntk.service.vgo.impl;

import com.suning.aimp.intf.dto.CustAliasInfo;
import com.suning.aimp.intf.resp.social.QuerySocialInfoResp;
import com.suning.sntk.constant.CustomerConstants;
import com.suning.sntk.consumer.WechatConsumerService;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dto.vgo.AppointInitQueryRespDto;
import com.suning.sntk.dto.vgo.AppointPromptDto;
import com.suning.sntk.dto.vgo.GuideDetailDto;
import com.suning.sntk.service.vgo.BookingPageService;
import com.suning.sntk.service.vgo.GuideInfoService;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.store.commons.lang.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 功能描述：预约页初始信息接口实现（会员手机号、导购信息、服务器初始时间）
 *
 * @author 18010645_黄成
 * @since 2018/8/31
 */
@Service
public class BookingPageServiceImpl implements BookingPageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingPageServiceImpl.class);

    @Autowired
    private WechatConsumerService wechatConsumerService;

    @Autowired
    private GuideInfoService guideInfoService;

    @Autowired
    private GuideInfoDao guideInfoDao;

    /**
     * 功能：查询预约页初始化信息
     *
     * @param customerNum 会员编码
     * @param guideId     导购工号
     * @author 18010645_黄成
     * @since 15:09 2018/9/8
     */
    @Override
    public AppointInitQueryRespDto queryBookingPageInitInfo(String customerNum, String guideId) {
        AppointInitQueryRespDto appointInitQueryResponse = new AppointInitQueryRespDto();
        //会员电话号码
        appointInitQueryResponse.setCustomerMobile(queryMemberPhone(customerNum));
        //获取导购详情
        GuideDetailDto guideDetailDto = guideInfoService.getGuideDetail(guideId);
        if (guideDetailDto != null) {
            //组装预约页初始化需要的导购信息
            getGuideInfo(guideDetailDto, appointInitQueryResponse);
        }
        return appointInitQueryResponse;
    }

    /**
     * 功能：获取会员手机号
     *
     * @param customerNum 会员编号
     * @author 18010645_黄成
     * @since 19:06 2018/9/4
     */
    public String queryMemberPhone(String customerNum) {
        List<CustAliasInfo> aliasInfoList = null;
        try {
            QuerySocialInfoResp socialInfo = wechatConsumerService.queryCustomerSocialInfo(customerNum);
            LOGGER.info("获取会员信息，customerNum：{}", customerNum);
            if (socialInfo != null) {
                aliasInfoList = socialInfo.getAliasInfoList();
            }
        } catch (Exception e) {
            LOGGER.warn("获取会员信息失败！", e);
        }
        if (CollectionUtils.isNotEmpty(aliasInfoList)) {
            for (CustAliasInfo custAliasInfo : aliasInfoList) {
                if (CustomerConstants.MEMBER_MOBILE_KEY.equals(custAliasInfo.getAliasType())) {
                    return custAliasInfo.getCustAlias();
                }
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * 功能：组装返回结果
     *
     * @param guideDto 店员主页导购详情
     * @param respDto  返回封装对象
     * @author 18010645_黄成
     * @since 18:04 2018/8/31
     */
    private void getGuideInfo(GuideDetailDto guideDto, AppointInitQueryRespDto respDto) {
        BeanUtils.copy(guideDto, respDto);
        //服务器初始时间(yyyy-MM-dd HH:mm:ss)
        respDto.setInitTime(DateUtils.format(new Date()));
    }

    /**
     * 功能：查询预约弹窗语
     *
     * @param customerNum 会员编码
     * @author 18010645_黄成
     * @since 10:36 2018/9/7
     */
    @Override
    public AppointPromptDto queryBookingPrompDialogue(String customerNum, String bookingTime) {
        AppointPromptDto appointPromptDtoResponse = null;
        String guideId = guideInfoDao.queryBookingGuideId(customerNum, bookingTime, DateUtils.formatPatten10(new Date()));
        LOGGER.info("查询会员当天预约的导购工号，customerNum：{}，bookingTime：{}，today：{}"
                , customerNum, bookingTime, DateUtils.formatPatten10(new Date()));
        //当天已经有预约导购记录
        if (StringUtils.isNotEmpty(guideId)) {
            //查询预约弹窗信息
            appointPromptDtoResponse = guideInfoDao
                    .queryBookingDialogue(guideId, customerNum, bookingTime, DateUtils.formatPatten10(new Date()));
            LOGGER.info("查询导购预约信息，guideId：{}，customerNum：{}，bookingTime：{}，today：{}"
                    , guideId, customerNum, bookingTime, DateUtils.formatPatten10(new Date()));
        }
        return appointPromptDtoResponse;
    }
}
