/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MemberCenterPublicParams
 * Author:   88396455_白振华
 * Date:     2018-7-4 15:40
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.common;

import java.util.Date;

/**
 * 功能描述：会员中心请求必填公共参数
 *
 * @author 88396455_白振华
 * @since 2018-7-4
 */
public class MemberCenterPublicParams {
    /**
     * 请求方系统代码(系统英文名称)
     */
    private String appCode;
    /**
     * 请求时间（通常取当前时间）
     */
    private Date tranTimestamp;
    /**
     * 请求方系统代码
     */
    private String sourceSystemNo;
    /**
     * 建立或更新记录的渠道
     */
    private String sourceChannel;
    /**
     * 调用方应用IP地址
     */
    private String srcIp;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Date getTranTimestamp() {
        return tranTimestamp;
    }

    public void setTranTimestamp(Date tranTimestamp) {
        this.tranTimestamp = tranTimestamp;
    }

    public String getSourceSystemNo() {
        return sourceSystemNo;
    }

    public void setSourceSystemNo(String sourceSystemNo) {
        this.sourceSystemNo = sourceSystemNo;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    @Override
    public String toString() {
        return "MemberCenterPublicParams{" +
                "appCode='" + appCode + '\'' +
                ", tranTimestamp=" + tranTimestamp +
                ", sourceSystemNo='" + sourceSystemNo + '\'' +
                ", sourceChannel='" + sourceChannel + '\'' +
                ", srcIp='" + srcIp + '\'' +
                '}';
    }
}
