/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: Label
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
 * 功能描述：标签信息
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@Entity(name = "mb_label_info")
public class Label extends BaseEntity2 {

    private Long id;

    // 标签编号
    private String labelNo;

    // 标签名称
    private String labelName;

    // 标签分组id
    private String groupId;

    //必选标签标志,1:必选, 0:非必选
    private Short mandatoryFlag;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "label_no")
    public String getLabelNo() {
        return labelNo;
    }

    @Column(name = "label_name")
    public String getLabelName() {
        return labelName;
    }

    @Column(name = "group_id")
    public String getGroupId() {
        return groupId;
    }

    @Column(name = "mandatory_flag")
    public Short getMandatoryFlag() {
        return mandatoryFlag;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabelNo(String labelNo) {
        this.labelNo = labelNo;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setMandatoryFlag(Short mandatoryFlag) {
        this.mandatoryFlag = mandatoryFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
