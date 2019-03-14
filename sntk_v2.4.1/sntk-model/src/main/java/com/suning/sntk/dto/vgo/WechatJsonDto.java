/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: WechatJsonDto
 * Author:   18032490_赵亚奇
 * Date:     2018/10/15 11:38
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 推送给会员集市系统的dto
 *
 * @author 18032490_赵亚奇
 * @since 2018/10/15
 */
public class WechatJsonDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公众号openId
     */
    private String openId;
    /**
     * 公众号appid
     */
    private String appId;
    /**
     * 微信消息模板id
     */
    private String templateId;
    /**
     * 小程序appid
     */
    private String miniProAppId;
    /**
     * 小程序页面路径
     */
    private String miniPagepath;
    /**
     * 消息详细内容
     */
    private String first;
    /**
     * 其他属性1
     */
    private String keyword1;
    /**
     * 其他属性2
     */
    private String keyword2;
    /**
     * 其他属性3
     */
    private String keyword3;
    /**
     * remark
     */
    private String remark;
    /**
     * url
     */
    private String url;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getMiniProAppId() {
        return miniProAppId;
    }

    public void setMiniProAppId(String miniProAppId) {
        this.miniProAppId = miniProAppId;
    }

    public String getMiniPagepath() {
        return miniPagepath;
    }

    public void setMiniPagepath(String miniPagepath) {
        this.miniPagepath = miniPagepath;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WechatJsonDto{" +
                "openId='" + openId + '\'' +
                ", appId='" + appId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", miniProAppId='" + miniProAppId + '\'' +
                ", miniPagepath='" + miniPagepath + '\'' +
                ", first='" + first + '\'' +
                ", keyword1='" + keyword1 + '\'' +
                ", keyword2='" + keyword2 + '\'' +
                ", keyword3='" + keyword3 + '\'' +
                ", remark='" + remark + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
