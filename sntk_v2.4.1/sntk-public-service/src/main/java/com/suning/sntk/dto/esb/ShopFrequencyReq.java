/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ShopFrequencyReq
 * Author:   17061157_王薛
 * Date:     2018/7/11 20:32
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.esb;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 功能描述：店员首购复购查询请求参数
 *
 * @author 17061157_王薛
 * @since 2018/7/11
 */
@XStreamAlias("custNumList")
public class ShopFrequencyReq {

    @XStreamImplicit(itemFieldName = "custNum")
    private List<String> custNum;

    public ShopFrequencyReq() {
    }

    public ShopFrequencyReq(List<String> custNum) {
        this.custNum = custNum;
    }

    public List<String> getCustNum() {
        return custNum;
    }

    public void setCustNum(List<String> custNum) {
        this.custNum = custNum;
    }

    @Override
    public String toString() {
        return "ShopFrequencyReq{" + "custNum=" + custNum + '}';
    }
}
