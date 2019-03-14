/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: UpdateIndividualCompleteInfoReqDto
 * Author:   88396455_白振华
 * Date:     2018-7-12 16:21
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.dto.mombaby;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.suning.aimp.intf.dto.AddressInfo;
import com.suning.aimp.intf.dto.IndividualBaseInfo;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-7-12
 */
public class UpdateIndividualCompleteInfoReqDto implements Serializable {
    private static final long serialVersionUID = -8778963155109072111L;

    private String custNum;

    private AddressInfo addressInfo;

    private IndividualBaseInfo individualBaseInfo;

    private MaternalInfantInfoDto maternalInfantInfo;

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public IndividualBaseInfo getIndividualBaseInfo() {
        return individualBaseInfo;
    }

    public void setIndividualBaseInfo(IndividualBaseInfo individualBaseInfo) {
        this.individualBaseInfo = individualBaseInfo;
    }

    public MaternalInfantInfoDto getMaternalInfantInfo() {
        return maternalInfantInfo;
    }

    public void setMaternalInfantInfo(MaternalInfantInfoDto maternalInfantInfo) {
        this.maternalInfantInfo = maternalInfantInfo;
    }

    public String getCustNum() {
        return custNum;
    }

    public void setCustNum(String custNum) {
        this.custNum = custNum;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
