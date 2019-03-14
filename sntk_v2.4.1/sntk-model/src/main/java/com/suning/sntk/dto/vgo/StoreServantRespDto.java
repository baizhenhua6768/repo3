/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreVservantDto
 * Author:   18010645_黄成
 * Date:     2018/8/30 16:45
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：pc端门店及服务人员信息
 *
 * @author 18010645_黄成
 * @since 2018/8/30
 */
public class StoreServantRespDto implements Serializable {
    private static final long serialVersionUID = 5845729669335223158L;

    //门店下服务人员信息
    private List<ServantInfoPcDto> uServantInfoList;
    //门店信息
    private StoreInfoPcDto storeInfo;
    //初始时间
    private String initTime;

    public List<ServantInfoPcDto> getuServantInfoList() {
        return uServantInfoList;
    }

    public void setuServantInfoList(List<ServantInfoPcDto> uServantInfoList) {
        this.uServantInfoList = uServantInfoList;
    }

    public StoreInfoPcDto getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfoPcDto storeInfo) {
        this.storeInfo = storeInfo;
    }

    public String getInitTime() {
        return initTime;
    }

    public void setInitTime(String initTime) {
        this.initTime = initTime;
    }

    @Override
    public String toString() {
        return "StoreServantRespDto{" +
                "uServantInfoList=" + uServantInfoList +
                ", storeInfo=" + storeInfo +
                ", initTime='" + initTime + '\'' +
                '}';
    }
}
