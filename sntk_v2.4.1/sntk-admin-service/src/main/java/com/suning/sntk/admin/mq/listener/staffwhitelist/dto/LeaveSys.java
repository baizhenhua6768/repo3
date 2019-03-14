/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: LeaveISys
 * Author:   18032490_赵亚奇
 * Date:     2018/7/10 15:19
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.mq.listener.staffwhitelist.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 接收人员离职信息的iSys标签
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/10
 */
public class LeaveSys implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 系统名称
     */
    @XStreamAlias("DISTRIBUTE_SYS")
    private String distributeSys;
    /**
     * 秘钥
     */
    @XStreamAlias("secretKey")
    private String secretKey;
    /**
     * 分发时间
     */
    @XStreamAlias("distributeTime")
    private String distributeTime;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(String distributeTime) {
        this.distributeTime = distributeTime;
    }

    public String getDistributeSys() {
        return distributeSys;
    }

    public void setDistributeSys(String distributeSys) {
        this.distributeSys = distributeSys;
    }

    @Override
    public String toString() {
        return "LeaveSys{" +
                "distributeSys='" + distributeSys + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", distributeTime='" + distributeTime + '\'' +
                '}';
    }
}
