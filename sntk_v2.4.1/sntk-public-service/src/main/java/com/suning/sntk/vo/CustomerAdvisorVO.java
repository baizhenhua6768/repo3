/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerAdvisorVO
 * Author:   88402362_欧小冬
 * Date:     2018/7/10 14:48
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述： 客户列表查询
 * VO
 *
 * @author 88402362_欧小冬
 * @since 2018/7/10
 */
public class CustomerAdvisorVO implements Serializable {

    private static final long serialVersionUID = 587019184820153908L;

    /**
     *  客户公众平台唯一编号
     */
    private String unionId;


    /**
     *  客户会员编号
     */
    private String customerNo;

    /**
     *  店员维护的客户备注名
     */
    private String remarkName;

    /**
     *  店员维护的客户手机号
     */
    private String remarkMobile;

    /*
     *  关系建立时间
     */
    private String relationTime;

    /**
     *  预计购买时间
     */
    private String expectPurchaseTime;

    /**
     *  客户的必选标签名称用逗号隔开
     */
    private String labelName;

    /**
     *  预计客户最低消费
     */
    private Double minPrice;

    /**
     *  预计客户最大消费
     */
    private Double maxPrice;

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getRemarkMobile() {
        return remarkMobile;
    }

    public void setRemarkMobile(String remarkMobile) {
        this.remarkMobile = remarkMobile;
    }

    public String getRelationTime() {
        return relationTime;
    }

    public void setRelationTime(String relationTime) {
        this.relationTime = relationTime;
    }

    public String getExpectPurchaseTime() {
        return expectPurchaseTime;
    }

    public void setExpectPurchaseTime(String expectPurchaseTime) {
        this.expectPurchaseTime = expectPurchaseTime;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
