/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: SendMsgServiceImpl
 * Author:   17061157_王薛
 * Date:     2018/8/17 15:23
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.message.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.suning.sntk.dto.message.SsmsMessageDto;
import com.suning.sntk.service.message.SendMsgService;
import com.suning.sntk.support.common.utils.JsonUtils;

/**
 * 功能描述：发送短信实现类
 *
 * @author 17061157_王薛
 * @since 2018/8/17
 */
@Service
public class SendMsgServiceImpl implements SendMsgService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMsgServiceImpl.class);

    // 超出每分钟调用次数限制
    //    private static final String SSMS_EXCEED_MIN_LIMIT = "9824";

    // 模板调用超速
    //    private static final String SSMS_TEMPLATE_SPEEDING = "9831";

    // V购预约通知店员短信模板
    private static final String SSMS_VGO_APPOINT_TEMPLATE = "vg_appoint_01";

    // V购取消预约通知店员短信模板
    private static final String SSMS_VGO_CANCEL_TEMPLATE = "vg_cancel_01";

    @Value("${ssms_send_url}")
    private String ssmsSendUrl;

    @Value("${ssms_app_code}")
    private String appCode;

    @Value("${ssms_app_secret}")
    private String appSecret;

    @Override
    public boolean sendVgoAppointMsg(String appointDate, String customerPhone, String mobile) {
        Map<String, String> params = Maps.newHashMap();
        params.put("appointDate", appointDate);
        params.put("customerPhone", customerPhone);
        return sendMsg(SSMS_VGO_APPOINT_TEMPLATE, params, mobile);
    }

    @Override
    public boolean sendVgoCancelMsg(String appointDate, String customerPhone, String mobile) {
        Map<String, String> params = Maps.newHashMap();
        params.put("appointDate", appointDate);
        params.put("customerPhone", customerPhone);
        return sendMsg(SSMS_VGO_CANCEL_TEMPLATE, params, mobile);
    }

    /**
     * 功能描述: HTTP方式调用ssms平台接口发送短信 <br>
     *
     * @param params 模板参数
     * @param mobile 接收短信的手机号
     * @return boolean
     * @author 17061157_王薛
     * @since 19:28  2018/8/28
     */
    private boolean sendMsg(String templateId, Map<String, String> params, String mobile) {
        if (StringUtils.isBlank(ssmsSendUrl) || StringUtils.isBlank(appCode) || StringUtils.isBlank(appSecret)) {
            LOGGER.info("短信平台配置错误");
            return false;
        }

        try {
            CloseableHttpClient httpClient = HttpClientPool.getHttpClient();

            HttpPost httpPost = new HttpPost(ssmsSendUrl);
            //装配post请求参数
            UrlEncodedFormEntity entity = buildRequestParam(templateId, params, mobile);
            //设置post求情参数
            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            LOGGER.info("HTTP方式调用短信平台接口响应:" + httpResponse);
            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获取响应对象
                HttpEntity resEntity = httpResponse.getEntity();
                if (resEntity != null) {
                    String res = EntityUtils.toString(resEntity, Charsets.UTF_8);
                    LOGGER.info("短信平台发送短信结果：", res);
                }
                // SSMS平台并未返回响应，暂不考虑失败重发情况
                return true;
            }
        } catch (IOException e) {
            LOGGER.error("发送短信失败:{}, mobile:{}", e, mobile);
        }
        return false;
    }

    /**
     * 功能描述: 组装HTTP请求参数 <br>
     *
     * @param templateId 短信模板
     * @param params     模板参数
     * @param mobile     接收短信的手机号
     * @return com.suning.sntk.service.message.SsmsMessageDto
     * @author 17061157_王薛
     * @since 19:15  2018/8/28
     */
    private UrlEncodedFormEntity buildRequestParam(String templateId, Map<String, String> params, String mobile) {
        SsmsMessageDto messageDto = new SsmsMessageDto();
        messageDto.setAppCode(appCode);
        String timeStamp = String.valueOf(new Date().getTime());
        messageDto.setTimeStamp(timeStamp);
        messageDto.setToken(DigestUtils.md5Hex(appCode + appSecret + timeStamp));
        messageDto.setTemplateCode(templateId);// 信息模板编码
        messageDto.setMobiles(mobile); // 接收短信的手机号
        messageDto.setParam(JsonUtils.object2Json(params)); // 请求参数
        messageDto.setCountryCode("0086"); // 国家编码

        List<BasicNameValuePair> list = Lists.newArrayList();
        list.add(new BasicNameValuePair("smsMessage", JsonUtils.object2Json(messageDto)));

        return new UrlEncodedFormEntity(list, Charsets.UTF_8);
    }

}
