/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ScmPropertyFileEnum
 * Author:   18041004_余长杰
 * Date:     2018/9/8 16:48
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.util.scm;

/**
 * 功能描述：
 *
 * @author 18041004_余长杰
 * @since 2018/9/8
 */
public enum ScmPropertyFileEnum {

    //中台，sntkserviceconfig 文件配置信息
    SNTK_SERVICE_WEB_CONFIG("sntkSTMPsntk-service-web", "/sntkserviceconfig", "存放sntk中台配置信息"),
    SNTK_WEB_CONFIG("20307-sntk", "/sntkwebconfig", "存放sntk前台台配置信息"),
    SNTK_ADMIN_CONFIG("20309-sntk", "/sntkadminconfig", "存放sntk后台配置信息");

    //系统编码
    private String appCode;
    //文件名称
    private String name;
    //文件备注
    private String remark;

    ScmPropertyFileEnum(String appCode, String name, String remark) {
        this.appCode = appCode;
        this.name = name;
        this.remark = remark;
    }

    public String getAppCode() {
        return appCode;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

}
