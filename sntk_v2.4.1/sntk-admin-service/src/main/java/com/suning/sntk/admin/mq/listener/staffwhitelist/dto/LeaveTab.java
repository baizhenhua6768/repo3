/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: LeaveITab
 * Author:   18032490_赵亚奇
 * Date:     2018/7/10 15:17
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.mq.listener.staffwhitelist.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 接收人员离职信息的iTab标签
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/10
 */

public class LeaveTab implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 行政级别
     */
    @XStreamAlias("administrativelevel")
    private String administrativelevel;
    /**
     * 离职日期
     */
    @XStreamAlias("departuredate")
    private String departuredate;
    /**
     * 姓名
     */
    @XStreamAlias("name")
    private String name;
    /**
     * 工号
     */
    @XStreamAlias("personNo")
    private String personNo;
    /**
     * 操作
     */
    @XStreamAlias("opera")
    private String opera;

    public String getAdministrativelevel() {
        return administrativelevel;
    }

    public void setAdministrativelevel(String administrativelevel) {
        this.administrativelevel = administrativelevel;
    }

    public String getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(String departuredate) {
        this.departuredate = departuredate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    public String getOpera() {
        return opera;
    }

    public void setOpera(String opera) {
        this.opera = opera;
    }

    @Override
    public String toString() {
        return "ITab{" +
                "administrativelevel='" + administrativelevel + '\'' +
                ", departuredate='" + departuredate + '\'' +
                ", name='" + name + '\'' +
                ", personNo='" + personNo + '\'' +
                ", opera='" + opera + '\'' +
                '}';
    }
}
