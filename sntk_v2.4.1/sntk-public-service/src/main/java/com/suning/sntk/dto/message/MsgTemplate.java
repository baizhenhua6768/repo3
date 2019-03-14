package com.suning.sntk.dto.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 短信中心短信模板
 */
@XStreamAlias("MTMessage")
public class MsgTemplate {

    @XStreamAlias("AppId")
    private String appId;
    @XStreamAlias("AppMsgId")
    private String appMsgId;
    @XStreamAlias("Content")
    private String content;
    @XStreamAlias("Important")
    private String important = "1";
    @XStreamAlias("NeedReply")
    private String needReply = "FALSE";
    @XStreamAlias("MTReceiver")
    private MTReceiver mtReceiver;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppMsgId() {
        return appMsgId;
    }

    public void setAppMsgId(String appMsgId) {
        this.appMsgId = appMsgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public String getNeedReply() {
        return needReply;
    }

    public void setNeedReply(String needReply) {
        this.needReply = needReply;
    }

    public MTReceiver getMtReceiver() {
        return mtReceiver;
    }

    public void setMtReceiver(MTReceiver mtReceiver) {
        this.mtReceiver = mtReceiver;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"appId\":\"");
        builder.append(appId);
        builder.append("\",\"appMsgId\":\"");
        builder.append(appMsgId);
        builder.append("\",\"content\":\"");
        builder.append(content);
        builder.append("\",\"important\":\"");
        builder.append(important);
        builder.append("\",\"needReply\":\"");
        builder.append(needReply);
        builder.append("\",\"mTReceiver\":\"");
        builder.append(mtReceiver);
        builder.append("\"}");
        return builder.toString();
    }

    
 

}
