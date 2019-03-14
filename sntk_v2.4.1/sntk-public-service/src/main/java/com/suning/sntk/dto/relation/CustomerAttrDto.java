/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CustomerAttrDto
 * Author:   88396455_白振华
 * Date:     2018-7-11 15:28
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.relation;

import java.io.Serializable;

/**
 * 功能描述：会员部分属性
 *
 * @author 88396455_白振华
 * @since 2018-7-11
 */
public class CustomerAttrDto implements Serializable {

    private static final long serialVersionUID = 8844936460193941271L;
    private String unionId;
    private String customerNo;
    private String memberName;
    private String remarkPhone;
    private int mobileFlag;
    private String sex;

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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRemarkPhone() {
        return remarkPhone;
    }

    public void setRemarkPhone(String remarkPhone) {
        this.remarkPhone = remarkPhone;
    }

    public int getMobileFlag() {
        return mobileFlag;
    }

    public void setMobileFlag(int mobileFlag) {
        this.mobileFlag = mobileFlag;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "CustomerAttrDto{" +
                "unionId='" + unionId + '\'' +
                ", customerNo='" + customerNo + '\'' +
                ", remarkName='" + memberName + '\'' +
                ", remarkPhone='" + remarkPhone + '\'' +
                ", mobileFlag=" + mobileFlag +
                ", sex='" + sex + '\'' +
                '}';
    }
}
