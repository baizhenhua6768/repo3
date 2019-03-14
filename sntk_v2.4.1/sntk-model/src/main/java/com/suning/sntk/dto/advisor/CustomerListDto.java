/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerListDto
 * Author:   88402362_欧小冬
 * Date:     2018/7/10 19:51
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.advisor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：
 *
 * @author 88402362_欧小冬
 * @since 2018/7/10
 */
public class CustomerListDto implements Serializable {

    private static final long serialVersionUID = 5930629351905384261L;

    // 客户公众平台唯一编号
    private String unionId;

    // 客户会员编号
    private String custId;

    // 店员维护的客户备注名
    private String custName;

    // 店员维护的客户手机号
    private String mobile;

    // 专业顾问建立时间
    private String relationTime;

    // 专业顾问建立时间
    private Date createTimeDate;

    // 专业顾问建立时间字符串类型
    private String createTime;

    // 预计最低消费
    private String minPrice;

    // 预计最好消费
    private String maxPrice;

    // 预计购买时间
    private String expectDate;

    // 客户标签名字符串
    private String labelName;

    // 客户状态(0-未购物，1首购， 2-复购， 3-非会员)
    private String buyStatus;

    private List<String> custLabelList = new ArrayList<String>();

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRelationTime() {
        return relationTime;
    }

    public void setRelationTime(String relationTime) {
        this.relationTime = relationTime;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(String expectDate) {
        this.expectDate = expectDate;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(String buyStatus) {
        this.buyStatus = buyStatus;
    }

    public Date getCreateTimeDate() {
        return createTimeDate;
    }

    public void setCreateTimeDate(Date createTimeDate) {
        this.createTimeDate = createTimeDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<String> getCustLabelList() {
        return custLabelList;
    }

    public void setCustLabelList(List<String> custLabelList) {
        this.custLabelList = custLabelList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
