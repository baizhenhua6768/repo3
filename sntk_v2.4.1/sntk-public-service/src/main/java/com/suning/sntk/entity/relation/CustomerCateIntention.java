/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerCategoryIntention
 * Author:   17061157_王薛
 * Date:     2018/7/7 12:13
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
 * 功能描述：顾客购买意向品类信息
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@Entity(name = "mb_customer_cate_intention")
public class CustomerCateIntention extends BaseEntity2 {

    private Long id;

    //店员工号
    private String staffId;

    //公众平台客户唯一编号
    private String unionId;

    //品类编码
    private String categoryNo;

    //品类名称
    private String categoryName;

    //品牌名称
    private String brands;

    //价格区间（下线）
    private Integer minPrice;

    //价格区间（上线）
    private Integer maxPrice;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "union_id")
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Column(name = "category_no")
    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Column(name = "brands")
    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    @Column(name = "min_price")
    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    @Column(name = "max_price")
    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
