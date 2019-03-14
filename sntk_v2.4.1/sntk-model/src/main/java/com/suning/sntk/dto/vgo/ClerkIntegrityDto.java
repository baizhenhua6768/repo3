/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ClerkIntegrityDto
 * Author:   88397670_张辉
 * Date:     2018-9-4 16:15
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：店员信息完整性校验对象
 *
 * @author 88397670_张辉
 * @since 2018-9-4
 */
public class ClerkIntegrityDto implements Serializable {
    private static final long serialVersionUID = -6295239138824812678L;

    /**
     * 导购工号
     */
    private String guideId;

    /**
     * 导购头像
     */
    private String guidePhoto;


    /**
     * 导购个签
     */
    private String introduction;

    /**
     * 销售年限
     */
    private Integer saleAge;

    /**
     * 业态 1-v购 2-母婴
     */
    private String storeType;

    /**
     * 擅长品类
     */
    private String categoryIds;

    /**
     * 服务项目
     */
    private String serviceItems;

    /**
     * 开启状态 0-关闭 1-开启
     */
    private String openFlag;

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getGuidePhoto() {
        return guidePhoto;
    }

    public void setGuidePhoto(String guidePhoto) {
        this.guidePhoto = guidePhoto;
    }


    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getSaleAge() {
        return saleAge;
    }

    public void setSaleAge(Integer saleAge) {
        this.saleAge = saleAge;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(String serviceItems) {
        this.serviceItems = serviceItems;
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
