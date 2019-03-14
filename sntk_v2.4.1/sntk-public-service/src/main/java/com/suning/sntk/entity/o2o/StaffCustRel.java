package com.suning.sntk.entity.o2o;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.common.O2OConstants;

/**
 * 导购和会员关系的实体类
 * 
 * @author 17121439
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity(name = "o2o_staff_cust_rel")
public class StaffCustRel implements Serializable {
    /**
     */
    private static final long serialVersionUID = 3336451346058565067L;

    private Long id;
    /**
     * 导购工号
     */
    private String staffId;
    /**
     * 导购名称
     */
    private String staffName;
    /**
     * 会员编号
     */
    private String custNo;

    /**
     * 门店编码
     */
    private String storeCode;
    /**
     * 渠道 0:线上 1:线下 2:预存，3:预售 4:小程序’
     */
    private String channel;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 业务类型
     */
    private String businessType = O2OConstants.MACHINE;

    /**
     * 解除关系状态 0：表示存在绑定关系1：表示关系解除 ’,
     */
    private String deleteFlag = String.valueOf(CommonConstants.UNDELETE_FLAG);// 0：表示关系没解除
                                                                              // 1：表示关系解除
    /**
     * 解除时间
     */
    private String deleteTime;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "staff_id")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "staff_name")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Column(name = "cust_no")
    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    @Column(name = "store_code")
    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    @Column(name = "channel")
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Column(name = "create_time")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Column(name = "business_type")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Column(name = "delete_flag")
    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Column(name = "delete_time")
    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
