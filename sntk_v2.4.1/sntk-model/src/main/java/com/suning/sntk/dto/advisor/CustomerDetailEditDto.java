/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CustomerDetailEditDto
 * Author:   18041004_余长杰
 * Date:     2018/7/10 15:50
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.advisor;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 功能描述：客户详细信息编辑Dto
 *
 * @author 18041004_余长杰
 * @since 2018/7/10
 */
public class CustomerDetailEditDto implements Serializable {

    private static final long serialVersionUID = 7300290852809067098L;

    private String personNo;
    /**
     * 店员工号
     */
    private String staffId;

    /**
     * 客户微信公众号唯一编号
     */
    @NotBlank
    private String unionId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 会员编号
     */
    private String custNo;

    /**
     * 客户姓名
     */
    private String custName;

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
     * 客户备注名
     */
    private String remarkName;

    /**
     * 客户手机号
     */
    private String custMobile;

    /**
     * 手机标识 1:会员手机,0:非会员手机号
     */
    private Integer mobileFlag;

    /**
     * 必选标签
     */
    private List<RequiredLabelTypeDto> requiredLabel;

    /**
     * 其他标签
     */
    private List<CustomerLabelDto> otherLabel;

    /**
     * 自定义标签
     */
    private String[] selfLabel;

    /**
     * 品类标签
     */
    private List<CustomerLabelDto> categoryLabel;

    /**
     * 备注
     */
    private String remarkInfo;

    /**
     * 预计购买时间
     */
    private String buyTime;

    private List<CustomerBrandDto> buyDatas;

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

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

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public Integer getMobileFlag() {
        return mobileFlag;
    }

    public void setMobileFlag(Integer mobileFlag) {
        this.mobileFlag = mobileFlag;
    }

    public List<RequiredLabelTypeDto> getRequiredLabel() {
        return requiredLabel;
    }

    public void setRequiredLabel(List<RequiredLabelTypeDto> requiredLabel) {
        this.requiredLabel = requiredLabel;
    }

    public List<CustomerLabelDto> getOtherLabel() {
        return otherLabel;
    }

    public void setOtherLabel(List<CustomerLabelDto> otherLabel) {
        this.otherLabel = otherLabel;
    }

    public String[] getSelfLabel() {
        return selfLabel;
    }

    public void setSelfLabel(String[] selfLabel) {
        this.selfLabel = selfLabel;
    }

    public List<CustomerLabelDto> getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(List<CustomerLabelDto> categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public String getRemarkInfo() {
        return remarkInfo;
    }

    public void setRemarkInfo(String remarkInfo) {
        this.remarkInfo = remarkInfo;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public List<CustomerBrandDto> getBuyDatas() {
        return buyDatas;
    }

    public void setBuyDatas(List<CustomerBrandDto> buyDatas) {
        this.buyDatas = buyDatas;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
