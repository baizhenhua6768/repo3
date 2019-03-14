/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: SsmsMessageDto
 * Author:   17061157_王薛
 * Date:     2018/8/28 11:50
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.message;

/**
 * 功能描述：SSMS短信平台参数DTO
 *
 * @author 17061157_王薛
 * @since 2018/8/28
 */
public class SsmsMessageDto {

    // 短片平台应用编码
    private String appCode;

    // 时间戳
    private String timeStamp;

    // 将 appCode、appSecret、timeStamp 拼接成字符串，使用 md5 进行加密
    private String token;

    // 接收短信的手机号
    private String mobiles;

    // 短信模板编码
    private String templateCode;

    // 短信模板变量替换
    private String param;

    // 业务系统生成，保证唯一的一个字符串
    private String externalMsgId;

    // 短信需要进行短信上下行匹配时传，值为 true
    private String needMatch;

    // 国家编码
    private String countryCode;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getExternalMsgId() {
        return externalMsgId;
    }

    public void setExternalMsgId(String externalMsgId) {
        this.externalMsgId = externalMsgId;
    }

    public String getNeedMatch() {
        return needMatch;
    }

    public void setNeedMatch(String needMatch) {
        this.needMatch = needMatch;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
