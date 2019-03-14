/*
 * Copyright (C), 2002-2016, 苏宁易购电子商务有限公司
 * FileName: VServantInfoDto.java
 * Author:   18010645
 * Date:     2018-8-30
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * pc端预约页展示：通用预约服务人员信息 <br>
 *
 * @author 18010645
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ServantInfoPcDto implements Serializable {
    /**
     */
    private static final long serialVersionUID = 4381031956373110612L;

    /**
     * 导购工号
     */
    private String guideId;
    /**
     * 店员头像
     */
    private String guidePhoto;
    /**
     * 店员姓名
     */
    private String guideName;
    /**
     * 服务人数（接单数）
     */
    private String orderNum;
    /**
     * 擅长品类
     */
    private String categoryName;
    /**
     * 个人简介
     */
    private String introduction;

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

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "ServantInfoPcDto{" +
                "guideId='" + guideId + '\'' +
                ", guidePhoto='" + guidePhoto + '\'' +
                ", guideName='" + guideName + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServantInfoPcDto)) return false;

        ServantInfoPcDto that = (ServantInfoPcDto) o;

        if (!guideId.equals(that.guideId)) return false;
        if (!guidePhoto.equals(that.guidePhoto)) return false;
        if (!guideName.equals(that.guideName)) return false;
        if (!orderNum.equals(that.orderNum)) return false;
        if (!categoryName.equals(that.categoryName)) return false;
        return introduction.equals(that.introduction);
    }

    @Override
    public int hashCode() {
        int result = guideId.hashCode();
        result = 31 * result + guidePhoto.hashCode();
        result = 31 * result + guideName.hashCode();
        result = 31 * result + orderNum.hashCode();
        result = 31 * result + categoryName.hashCode();
        result = 31 * result + introduction.hashCode();
        return result;
    }
}