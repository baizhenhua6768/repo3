package com.suning.sntk.web.service.vgo.impl;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.web.dto.vgo.Digestes;
import com.suning.sntk.web.dto.vgo.WeixinJsSdkConfig;
import com.suning.vgs.wgg.dto.ResponseData;
import com.suning.vgs.wgg.dto.member.MpsWeixinTokenInfo;
import com.suning.vgs.wgg.dto.member.WeixinTokenRequest;
import com.suning.vgs.wgg.service.intf.rsf.WechatRemoteService;

@Service
public class WeixinConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinConfigService.class);

    private static final String JS_API_TICKET = "2";

    private static final String SHA1_PREFIX = "SNStoreSNweixin";

    private WechatRemoteService wechatRemoteService = ServiceLocator.getService(WechatRemoteService.class,
            "wechatRemoteService");

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param appId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getJsticket(String appId) {

        WeixinTokenRequest weixinTokenRequest = new WeixinTokenRequest();
        weixinTokenRequest.setAppId(appId);
        weixinTokenRequest.setAccessType(JS_API_TICKET);
        String timeStamp = String.valueOf(System.currentTimeMillis());
        weixinTokenRequest.setTimeStamp(timeStamp);
        String sourceStr = SHA1_PREFIX + timeStamp + appId;
        String sign = Digestes.getSHA1Signed(sourceStr);
        weixinTokenRequest.setSign(sign);

        LOGGER.info("WeixinConfigService  RSF Requst : wechatRemoteService.getWeChatTokenInfo : reqs={}",
                weixinTokenRequest);
        ResponseData<MpsWeixinTokenInfo> response = null;
        try {
            response = wechatRemoteService.getWeChatTokenInfo(weixinTokenRequest);
            LOGGER.info("WeixinConfigService  RSF Response : wechatRemoteService.getWeChatTokenInfo : resp={}",
                    weixinTokenRequest);
        } catch (Exception e) {
            LOGGER.error("WeixinConfigService invoke wechatRemoteService.getWeChatTokenInfo error, params:{}",
                    weixinTokenRequest, e);
        }
        if (null != response && ResponseData.SUCCESS.equals(response.getRetFlag())) {
            MpsWeixinTokenInfo mpsWeixinTokenInfo = response.getData();
            if (null != mpsWeixinTokenInfo && StringUtils.isNotBlank(mpsWeixinTokenInfo.getToken())) {
                return mpsWeixinTokenInfo.getToken();
            }
        }

        LOGGER.error("WeixinConfigService getJsticket appId:{} error! rsRresponse:{}", appId, response);
        return null;
    }

    public WeixinJsSdkConfig getWeixinJsSdkConfig(String appId, String jsApiTicket, String url) {

        LOGGER.info("WeixinConfigService.getWeixinJsSdkConfig : appId={},jsApiTicket={},url={}", appId, jsApiTicket,
                url);

        // 随机字符串
        String nonceStr = RandomStringUtils.randomAlphanumeric(16);
        // 时间戳
        String timestamp = String.valueOf(new Date().getTime() / 1000);
        // 需要做sha1签名的字符串
        String sourceStr = new StringBuilder("jsapi_ticket=").append(jsApiTicket).append("&noncestr=").append(nonceStr)
                .append("&timestamp=").append(timestamp).append("&url=").append(url).toString();
        String sign = Digestes.getSHA1Signed(sourceStr);

        WeixinJsSdkConfig config = new WeixinJsSdkConfig();
        config.setAppId(appId);
        config.setNonceStr(nonceStr);
        config.setSignature(sign);
        config.setTimestamp(timestamp);

        return config;
    }

}
