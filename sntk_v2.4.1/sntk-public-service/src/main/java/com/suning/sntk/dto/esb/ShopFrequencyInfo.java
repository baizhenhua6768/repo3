/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ShopFrequencyInfo
 * Author:   17061157_王薛
 * Date:     2018/7/11 20:34
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.esb;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 功能描述：店员首购复购信息
 *
 * @author 17061157_王薛
 * @since 2018/7/11
 */
@XStreamAlias("cust")
public class ShopFrequencyInfo {

    @XStreamAlias("custNum")
    private String custNum;

    @XStreamAlias("shopFrequency")
    private String shopFrequency;

    public String getCustNum() {
        return custNum;
    }

    public void setCustNum(String custNum) {
        this.custNum = custNum;
    }

    public String getShopFrequency() {
        return shopFrequency;
    }

    public void setShopFrequency(String shopFrequency) {
        this.shopFrequency = shopFrequency;
    }
}
