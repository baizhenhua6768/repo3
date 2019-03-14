/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: BaseResponse.java
 * Author:   15050648
 * Date:     2017-8-16 上午10:03:42
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.web.dto.vgo;

import java.io.Serializable;

import com.suning.sntk.support.enums.ErrorConstantEnum;

/**
 * MTS接口服务基础返回信息类
 *
 * @author 14041276
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class BaseResponse implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 4924661442663074994L;


    /**
     * 接口名称(mts.user.register)[系统名.模块名.操作名]
     */
    private String api;

    /**
     * 版本号(默认1.0)
     */
    private String v = "1.0";

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回描述
     */
    private String msg;

    /**
     * 业务数据对象
     */
    private Object data;

    public BaseResponse() {
    }

    private BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 定义降级开关返回的resp<br>
     *
     * @return
     * @author 15050648
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static BaseResponse getSwitchResp() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ErrorConstantEnum.ERROR_0013.getErrorCode());
        baseResponse.setMsg(ErrorConstantEnum.ERROR_0013.getErrorMsg());
        return baseResponse;
    }

    public static BaseResponse error(String errorCode, String errorMsg) {
        return new BaseResponse(errorCode, errorMsg);

    }

    public BaseResponse(Object data, String api, String v) {
        this.api = api;
        this.v = v;
        this.data = data;
    }

    /**
     * 设置数据，api及版本号数据转换
     *
     * @return
     */
    public static BaseResponse of(Object data, String api, String v) {
        return new BaseResponse(data, api, v);
    }

}
