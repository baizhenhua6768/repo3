/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffMsg
 * Author:   18032490_赵亚奇
 * Date:     2018/7/4 14:50
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.mq.sender.staffwhitelist;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.suning.sntk.admin.mq.sender.MbfHeader;
import com.suning.sntk.admin.mq.sender.staffwhitelist.dto.StaffSenderDto;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微服后台人员管理发送消息
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/4
 */
@XStreamAlias("MbfService")
public class StaffMsg {
    /**
     * 输入
     */
    private Input1 input1;

    /**
     * 初始化消息
     */
    public void init(List<StaffSenderDto> staffDtoList, String staffStatus) {
        Input1 input = new Input1();
        MbfHeader header = new MbfHeader();
        // 系统编码
        header.setAppCode("SNTK");
        // 服务编码
        header.setServiceCode("SntkManagement");
        // 操作编码
        header.setOperation("syncWhitelistInfo");
        // uuid
        header.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
        // 鉴权id
        header.setAuthId("SNTK;NULL");
        // 头信息
        input.setHeader(header);

        //设置消息体
        Body1 body = new Body1();
        body.setStaffDtoList(staffDtoList);
        body.setStaffStatus(staffStatus);
        // 消息体
        input.setBody(body);
        input1 = input;
    }

    public Input1 getInput1() {
        return input1;
    }

    public void setInput1(Input1 input1) {
        this.input1 = input1;
    }

    public StaffMsg(List<StaffSenderDto> staffDtoList, String staffStatus) {
        // 初始化数据
        init(staffDtoList, staffStatus);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}

/**
 * 输入对象
 */
class Input1 {

    /**
     * 消息头
     */
    @XStreamAlias("MbfHeader")
    private MbfHeader header;

    /**
     * 消息体
     */
    @XStreamAlias("MbfBody")
    private Body1 body;

    public MbfHeader getHeader() {
        return header;
    }

    public void setHeader(MbfHeader header) {
        this.header = header;
    }

    public Body1 getBody() {
        return body;
    }

    public void setBody(Body1 body) {
        this.body = body;
    }

}

/**
 * 报文消息体
 */
class Body1 {

    /**
     * 人员信息集合
     */
    @XStreamAlias("staffInfoList")
    private List<StaffSenderDto> staffInfoList;

    /**
     * 人员状态
     */
    @XStreamAlias("staffStatus")
    private String staffStatus;

    public List<StaffSenderDto> getStaffDtoList() {
        return staffInfoList;
    }

    public void setStaffDtoList(List<StaffSenderDto> staffInfoList) {
        this.staffInfoList = staffInfoList;
    }

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }

}
