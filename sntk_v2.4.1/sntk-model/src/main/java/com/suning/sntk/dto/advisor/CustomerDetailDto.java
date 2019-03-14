package com.suning.sntk.dto.advisor;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 功能描述：客户详细信息 DTO
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public class CustomerDetailDto implements Serializable {

    private static final long serialVersionUID = 2030578897275868872L;

    private String personNo;
    /**
     * 店员工号
     */
    @NotBlank
    private String staffId;

    /**
     * 客户微信公众号唯一编号
     */
    @NotBlank
    private String unionId;

    /**
     * 客户备注名
     */
    private String remarkName;

    /**
     * 客户手机号
     */
    private String custMobile;

    /**
     * 用户专属标签
     */
    @NotBlank
    private String custLabel;

    /**
     * 店员定义的客户标签
     */
    private String selfLabel;

    private String remarkInfo;// 备注

    private String buyTime; // 预计购买时间

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

    public String getCustLabel() {
        return custLabel;
    }

    public void setCustLabel(String custLabel) {
        this.custLabel = custLabel;
    }

    public String getSelfLabel() {
        return selfLabel;
    }

    public void setSelfLabel(String selfLabel) {
        this.selfLabel = selfLabel;
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
