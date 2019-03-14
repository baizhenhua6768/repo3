/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: WechatFans
 * Author:   17061157_王薛
 * Date:     2018/7/7 12:11
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.suning.sntk.entity.BaseEntity2;

/**
 * 功能描述：微信粉丝信息
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@Entity(name = "mb_wechat_fans")
public class WechatFans extends BaseEntity2 {

    private Long id;

    // 公众号客户唯一编号
    private String openId;

    //公众号appid
    private String appId;

    //公众平台客户唯一编号
    private String unionId;

    //场景值
    private String sceneId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "open_id")
    public String getOpenId() {
        return openId;
    }

    @Column(name = "appid")
    public String getAppId() {
        return appId;
    }

    @Column(name = "union_id")
    public String getUnionId() {
        return unionId;
    }

    @Column(name = "scene_id")
    public String getSceneId() {
        return sceneId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
