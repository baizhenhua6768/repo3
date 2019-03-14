/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ReservationGuide
 * Author:   88396455_白振华
 * Date:     2018-9-4 9:52
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
 * 功能描述：预约项目人员关系
 *
 * @author 88396455_白振华
 * @since 2018-9-4
 */
@Entity(name = "o2ob_reservation_guide")
public class ReservationGuide implements Serializable {

    private static final long serialVersionUID = -5001165273027175609L;

    /**
     * 自增id
     */
    private Long reservationGuideId;
    /**
     * 预约服务项目类型（1：服务项目，2：服务特色及保障，3：厂家授权品牌）
     */
    private String serviceItemType;

    /**
     * 预约服务项目
     */
    private String serviceItemVal;

    /**
     * 导购工号
     */
    private String guideId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_guide_id")
    public Long getReservationGuideId() {
        return reservationGuideId;
    }

    public void setReservationGuideId(Long reservationGuideId) {
        this.reservationGuideId = reservationGuideId;
    }

    @Column(name = "service_item_type")
    public String getServiceItemType() {
        return serviceItemType;
    }

    public void setServiceItemType(String serviceItemType) {
        this.serviceItemType = serviceItemType;
    }

    @Column(name = "service_item_val")
    public String getServiceItemVal() {
        return serviceItemVal;
    }

    public void setServiceItemVal(String serviceItemVal) {
        this.serviceItemVal = serviceItemVal;
    }

    @Column(name = "guide_id")
    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
