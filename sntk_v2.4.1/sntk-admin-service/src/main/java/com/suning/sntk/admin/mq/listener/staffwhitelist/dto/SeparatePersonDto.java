/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SeparatePersonDto
 * Author:   18032490_赵亚奇
 * Date:     2018/7/3 14:59
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.mq.listener.staffwhitelist.dto;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 接收离职人员信息的Dto
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/3
 */
@XStreamAlias("MbfBody")
public class SeparatePersonDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @XStreamAlias("iSys")
    private LeaveSys iSys;

    @XStreamImplicit(itemFieldName = "iTab")
    private List<LeaveTab> iTabList;

    public LeaveSys getiSys() {
        return iSys;
    }

    public void setiSys(LeaveSys iSys) {
        this.iSys = iSys;
    }

    public List<LeaveTab> getiTabList() {
        return iTabList;
    }

    public void setiTabList(List<LeaveTab> iTabList) {
        this.iTabList = iTabList;
    }

    @Override
    public String toString() {
        return "SeparatePersonDto{" +
                "iSys=" + iSys +
                ", iTabList=" + iTabList +
                '}';
    }
}

