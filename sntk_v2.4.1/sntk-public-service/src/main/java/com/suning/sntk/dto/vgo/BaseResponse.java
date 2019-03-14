package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 
 * Response基类，所有response类继承此类共用三个状态
 *
 * @author 14091439
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class BaseResponse implements Serializable {
    /**
     */
    private static final long serialVersionUID = -8783237550874394404L;
    /**
     * 错误描述
     */
    private String errDesc;
    /**
     * 错误码
     */
    private String errCode;
    /**
     * 状态 0=成功； 1=失败。
     */
    private String status;

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseResponse [errDesc=" + errDesc + ", errCode=" + errCode + ", status=" + status + "]";
    }

}
