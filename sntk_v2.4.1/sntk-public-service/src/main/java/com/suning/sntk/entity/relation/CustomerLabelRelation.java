/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerLabelRelation
 * Author:   17061157_王薛
 * Date:     2018/7/7 12:12
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.suning.sntk.entity.BaseEntity2;

/**
 * 功能描述：客户标签关联信息
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@Entity(name = "mb_customer_label_relation")
public class CustomerLabelRelation extends BaseEntity2 {

    private Long id;

    //店员工号
    private String staffId;

    //公众平台客户唯一编号
    private String unionId;

    //标签ID
    private String labelId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "staff_id")
    public String getStaffId() {
        return staffId;
    }

    @Column(name = "union_id")
    public String getUnionId() {
        return unionId;
    }

    @Column(name = "label_id")
    public String getLabelId() {
        return labelId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
