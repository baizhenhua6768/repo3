package com.suning.sntk.web.filter;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.suning.framework.webtraffic.UriTraffic;
import com.suning.framework.webtraffic.WebTrafficRejectHandler;
import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.web.dto.vgo.BaseResponse;

/**
 * 功能描述：流控 Reject Handler <br>
 *
 * @author 17061157
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SntkWebTrafficRejectHandler extends WebTrafficRejectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebTrafficRejectHandler.class);

    /**
     * 失败标识
     */
    private static final String FAIL = "1";

    /**
     * @param request                HttpServletRequest
     * @param response               HttpServletResponse
     * @param matchedUriTraffic      uriTraffic对象，包含uriPattern（对应配置文件url-pattern、last-segemnt）、
     *                               limit（对应max-concurrency）,参见使用手册
     * @param uriPatternCurrentCount matchedUriTraffic的UriPattern的当前并发量
     * @throws IOException
     */
    @Override
    public void handleOverUriPatternMaxConcurrency(HttpServletRequest request, HttpServletResponse response,
            UriTraffic matchedUriTraffic, int uriPatternCurrentCount) throws IOException {
        LOGGER.warn("request was refused because pending request {} was " + "greater than allowed {}.",
                request.getRequestURI(), matchedUriTraffic.getTraffic());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ServletOutputStream sos = response.getOutputStream();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setV("sntk_v2.2.1");
        baseResponse.setCode(FAIL);
        baseResponse.setMsg("The request reaches the threshold concurrently!");
        //RsfResponseDto<Void> ret = RsfResponseDto.error("9997", "该请求并发达到阀值");
        sos.write(JsonUtils.object2Json(baseResponse).getBytes(Charsets.UTF_8));
        sos.flush();

        return;
    }

}
