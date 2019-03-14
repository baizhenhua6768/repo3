package com.suning.sntk.web.controller.vgo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suning.sntk.support.enums.WebExceptionCodeEnum;
import com.suning.sntk.web.dto.vgo.WeixinJsSdkConfig;
import com.suning.sntk.web.service.vgo.impl.WeixinConfigService;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.lang.validator.Validators;

/**
 * 轻应用-微信相关联业务控制类<br>
 * 〈功能详细描述〉
 *
 * @author 12061818
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Trace
@RestController
@RequestMapping("/weixin")
@Validated
public class WeixinConfigController {

    @Resource
    WeixinConfigService weixinConfigService;

    /**
     * 功能描述: 获取微信JS-SDK config所需的配置参数<br>
     * 〈功能详细描述〉
     *
     * @param url
     * @param appId
     */
    @GetMapping("/getJsapiConfig")
    public WeixinJsSdkConfig getjsapiconfig(@NotBlank String url, @NotBlank String appId)
            throws UnsupportedEncodingException {

        // 调用公众号接口获取jsticket
        String jsticket = weixinConfigService.getJsticket(appId);
        Validators.ifBlank(jsticket).thenThrow(WebExceptionCodeEnum.GET_JSAPI_CONFIG_ERROR);

        // weixinJS-SDK 设置
        String realUrl = URLDecoder.decode(url, "UTF-8");
        WeixinJsSdkConfig result = weixinConfigService.getWeixinJsSdkConfig(appId, jsticket, realUrl);

        return result;
    }
}
