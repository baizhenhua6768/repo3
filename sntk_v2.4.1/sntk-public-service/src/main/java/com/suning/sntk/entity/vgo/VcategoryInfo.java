/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VcategoryInfo
 * Author:   88396455_白振华
 * Date:     2018-8-18 17:38
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.vgo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：后台品类实体
 *
 * @author 88396455_白振华
 * @since 2018-8-18
 */
@Entity(name = "o2ob_v_category")
public class VcategoryInfo implements Serializable {

    private static final long serialVersionUID = 948626675462038552L;

    /**
     * 表主键id
     */
    private Long id;

    /**
     * 品类名称
     */
    private String catagoryName;

    /**
     * 品类编码
     */
    private String categoryCode;

    /**
     * 品类编码
     */
    private String categoryOutname;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CATEGORY_NAME")
    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    @Column(name = "CATEGORY_CODE")
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Column(name = "CATEGORY_OUTNAME")
    public String getCategoryOutname() {
        return categoryOutname;
    }

    public void setCategoryOutname(String categoryOutname) {
        this.categoryOutname = categoryOutname;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
