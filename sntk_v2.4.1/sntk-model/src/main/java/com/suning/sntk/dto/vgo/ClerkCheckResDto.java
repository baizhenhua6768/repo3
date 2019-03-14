/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ClerkCheckResDto
 * Author:   88397670_张辉
 * Date:     2018-9-7 11:44
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
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-9-7
 */
public class ClerkCheckResDto implements Serializable {

    private static final long serialVersionUID = 5432615761047841174L;

    /**
     * 店员信息校验结果
     */
    private Boolean flag;

    /**
     * 校验错误码 01-资料不完善 02-资料待审核
     */
    private String code;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 导购头像
     */
    private String guidePhoto;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getGuidePhoto() {
        return guidePhoto;
    }

    public void setGuidePhoto(String guidePhoto) {
        this.guidePhoto = guidePhoto;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
