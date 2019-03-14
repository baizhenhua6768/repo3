package com.suning.sntk.admin.mq.listener.staffwhitelist.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * 接收NSF-MQ推送过来的门店基础信息<br> 
 * 〈功能详细描述〉
 *
 * @author 12061818
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@XStreamAlias("MbfBody")
public class NsfStoreMqDto implements Serializable {

    /**
     */
    private static final long serialVersionUID = -8372730854274245787L;
    
    @XStreamAlias("headInfo")
    private MqHeadInfo headInfo;
    
    @XStreamAlias("storeBaseInfo")
    private StoreBaseInfoDto storeBaseInfo;
    
    @XStreamAlias("operationFlag")
    private String operationFlag;

    public MqHeadInfo getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(MqHeadInfo headInfo) {
        this.headInfo = headInfo;
    }

    public StoreBaseInfoDto getStoreBaseInfo() {
        return storeBaseInfo;
    }

    public void setStoreBaseInfo(StoreBaseInfoDto storeBaseInfo) {
        this.storeBaseInfo = storeBaseInfo;
    }

    public String getOperationFlag() {
        return operationFlag;
    }

    public void setOperationFlag(String operationFlag) {
        this.operationFlag = operationFlag;
    }
    
    @Override
    public String toString() {
        return "{operationFlag=" + operationFlag + ", storeBaseInfo=" + (null != storeBaseInfo ? storeBaseInfo.toString() : "") + "}" ;
    }
}
