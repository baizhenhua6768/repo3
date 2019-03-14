/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StationVo
 * Author:   18032490_赵亚奇
 * Date:     2018/7/9 9:23
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.vo;

/**
 * 后台展示网页信息
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/9
 */
public class StationVo {
    private String stationCode;
    private String stationName;

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    @Override
    public String toString() {
        return "StationVo{" +
                "stationCode='" + stationCode + '\'' +
                ", stationName='" + stationName + '\'' +
                '}';
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
