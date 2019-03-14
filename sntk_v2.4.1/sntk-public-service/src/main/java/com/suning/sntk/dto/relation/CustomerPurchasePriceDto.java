/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerPurchasePriceDto
 * Author:   17061157_王薛
 * Date:     2018/7/12 16:41
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.relation;

/**
 * 功能描述：客户购买意向 金额范围 DTO
 *
 * @author 17061157_王薛
 * @since 2018/7/12
 */
public class CustomerPurchasePriceDto {

    private String unionId;

    private String minPrice;

    private String maxPrice;

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }
}
