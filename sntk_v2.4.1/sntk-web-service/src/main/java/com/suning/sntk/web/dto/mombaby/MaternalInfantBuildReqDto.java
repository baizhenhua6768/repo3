/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MaternalInfantBuildReqDto
 * Author:   88396455_白振华
 * Date:     2018-7-12 16:20
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.dto.mombaby;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 功能描述： 请求入参
 *
 * @author 88396455_白振华
 * @since 2018-7-12
 */
public class MaternalInfantBuildReqDto implements Serializable {
    private static final long serialVersionUID = -6821033789247574473L;

    private UpdateIndividualCompleteInfoReqDto updateIndividualInfo;

    private String personNo;

    public UpdateIndividualCompleteInfoReqDto getUpdateIndividualInfo() {
        return updateIndividualInfo;
    }

    public void setUpdateIndividualInfo(UpdateIndividualCompleteInfoReqDto updateIndividualInfo) {
        this.updateIndividualInfo = updateIndividualInfo;
    }

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
