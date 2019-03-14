/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: WechatPublicAccountDto
 * Author:   18032490_赵亚奇
 * Date:     2018/10/10 17:16
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
 * @since 2018/10/10
 */
public class WechatPublicAccountDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 场景编码：Y00030
     */
    private String sceneCode = "Y00030";

    /**
     * 接收时间,格式yyyy-MM-dd,只处理当天的数据，默认传当天
     */
    private String receiveTime;

    private WechatJsonDto wx;

    public WechatJsonDto getWechatJsonDto() {
        return wx;
    }

    public void setWechatJsonDto(WechatJsonDto wechatJsonDto) {
        this.wx = wechatJsonDto;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Override
    public String toString() {
        return "WechatPublicAccountDto{" +
                "sceneCode='" + sceneCode + '\'' +
                ", receiveTime='" + receiveTime + '\'' +
                ", wx=" + wx +
                '}';
    }
}
