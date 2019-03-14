/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StationEnum
 * Author:   18032490_赵亚奇
 * Date:     2018/7/9 9:08
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums;

/**
 * 岗位信息
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/9
 */
public enum StationEnum {

    /* 店长00000290
       副店长00000291
               销售督导00000300
       营业员00010001
               促销员00010002
       服务专员00000569
   */
    STORE_MANAGER("00000290", "店长"),
    ASSITANT_STORE_MANAGER("00000291", "副店长"),
    SELLER("00000300", "销售督导"),
    SHOP_ASSITANT("00010001", "营业员"),
    SALE_PROMOTION("00010002", "促销员"),
    SERVICE_MAN("00000569", "服务专员");

    private String station;
    private String description;

    public String getStation() {
        return station;
    }

    public String getDescription() {
        return description;
    }

    StationEnum(String station, String description) {
        this.station = station;
        this.description = description;
    }
}
