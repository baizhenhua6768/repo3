package com.suning.sntk.admin.mq.listener.staffwhitelist.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 功能描述：微信扫码事件包体
 *
 * @author 18060177_顾磊
 * @since 2018/6/26
 */
@XStreamAlias("MbfBody")
public class ScanCodeBodyDto implements Serializable {
    private static final long serialVersionUID = -6470425365885059171L;
    /**
     * 开发者微信号
     */
    @XStreamAlias("devWeixinNo")
    private String devWeixinNo;
    /**
     * 场景值
     */
    @XStreamAlias("sceneId")
    private String sceneId;
    /**
     * 客户OpenID
     */
    @XStreamAlias("openId")
    private String openId;

    public String getDevWeixinNo() {
        return devWeixinNo;
    }

    public void setDevWeixinNo(String devWeixinNo) {
        this.devWeixinNo = devWeixinNo;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
