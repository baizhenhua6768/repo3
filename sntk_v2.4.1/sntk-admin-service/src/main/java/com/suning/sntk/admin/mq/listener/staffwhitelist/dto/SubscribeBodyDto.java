package com.suning.sntk.admin.mq.listener.staffwhitelist.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 功能描述：微信关注事件包体
 *
 * @author 18060177_顾磊
 * @since 2018/6/26
 */

@XStreamAlias("MbfBody")
public class SubscribeBodyDto implements Serializable {
    private static final long serialVersionUID = 7817916988460361376L;

    /**
     * 用户所在的分组
     */
    @XStreamAlias("groupId")
    private String groupId;
    /**
     * 用户所在城市
     */
    @XStreamAlias("city")
    private String city;
    /**
     *用户关注时间；为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @XStreamAlias("subscribeTime")
    private String subscribeTime;
    /**
     *用户所在国家
     */
    @XStreamAlias("country")
    private String country;
    /**
     *unionId
     */
    @XStreamAlias("unionId")
    private String unionId;
    /**
     *用户语言
     */
    @XStreamAlias("language")
    private String language;
    /**
     *粉丝openId
     */
    @XStreamAlias("openId")
    private String openId;
    /**
     *公众号运营者对粉丝的备注
     */
    @XStreamAlias("remark")
    private String remark;
    /**
     *用户昵称
     */
    @XStreamAlias("nickName")
    private String nickName;
    /**
     *公众号appId
     */
    @XStreamAlias("appId")
    private String appId;
    /**
     *性别1-男，2-女、0-未知
     */
    @XStreamAlias("sex")
    private String sex;
    /**
     *场景ID
     */
    @XStreamAlias("sceneId")
    private String sceneId;
    /**
     *用户头像
     */
    @XStreamAlias("headImgUrl")
    private String headImgUrl;
    /**
     *用户所在省份
     */
    @XStreamAlias("province")
    private String province;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
