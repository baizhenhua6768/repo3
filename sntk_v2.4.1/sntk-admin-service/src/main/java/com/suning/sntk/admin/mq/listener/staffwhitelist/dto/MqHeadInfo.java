package com.suning.sntk.admin.mq.listener.staffwhitelist.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * 
 * 功能描述：模板消息回执报文头信息
 * @author 作者 13075694@cnsuning.com
 * @created 2015年10月20日 下午3:15:49
 * @version 1.8.0
 * @date 2015年10月20日 下午3:15:49
 */
public class MqHeadInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6801699867809631451L;
    
    @XStreamAlias("DISTRIBUTE_SYS")
    private String distribute;

    public String getDistribute() {
        return distribute;
    }

    public void setDistribute(String distribute) {
        this.distribute = distribute;
    }

}
