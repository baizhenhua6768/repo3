package com.suning.sntk.dto.advisor;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：客户基本信息 DTO
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public class CustomerBaseDto implements Serializable {

    private static final long serialVersionUID = -3041737409314437997L;

    /**
     * 客户微信公众号唯一编号
     */
    private String unionId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 会员编号
     */
    private String customerNo;

    /**
     * 客户备注名
     */
    private String remarkName;

    /**
     * 客户手机号
     */
    private String customerMobile;

    /**
     * 会员等级 0-非会员
     */
    private String memberLevel;

    /**
     * 购买标识
     */
    private String buyFlag;

    /**
     * 与店员关系
     */
    private int relation;

    /**
     * 客户标签
     */
    private List<String> labels;

    /**
     * 客户购买意向
     */
    private List<CustomerBrandDto> buyIntentions;

    /**
     * 预计购买时间
     */
    private String buyTime;

    /**
     * 购买备注
     */
    private String remarkInfo;

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getBuyFlag() {
        return buyFlag;
    }

    public void setBuyFlag(String buyFlag) {
        this.buyFlag = buyFlag;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<CustomerBrandDto> getBuyIntentions() {
        return buyIntentions;
    }

    public void setBuyIntentions(List<CustomerBrandDto> buyIntentions) {
        this.buyIntentions = buyIntentions;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getRemarkInfo() {
        return remarkInfo;
    }

    public void setRemarkInfo(String remarkInfo) {
        this.remarkInfo = remarkInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
