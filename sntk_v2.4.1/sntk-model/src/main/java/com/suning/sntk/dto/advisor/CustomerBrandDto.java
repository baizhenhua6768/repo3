package com.suning.sntk.dto.advisor;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：客户购买品牌意向DTO
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public class CustomerBrandDto implements Serializable {

    private static final long serialVersionUID = 6508440405417463974L;

    /**
     * 品类id
     */
    private String categoryNo;

    /**
     * 品类名称
     */
    private String categoryName;

    /**
     * 品牌，多个以#分割
     */
    private String brandName;

    private String[] brandNameList;

    private Integer minPrice;

    private Integer maxPrice;

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String[] getBrandNameList() {
        return brandNameList;
    }

    public void setBrandNameList(String[] brandNameList) {
        this.brandNameList = brandNameList;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

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
