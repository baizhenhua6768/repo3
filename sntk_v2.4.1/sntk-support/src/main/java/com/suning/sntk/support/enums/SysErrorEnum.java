package com.suning.sntk.support.enums;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-8-17
 */
public enum SysErrorEnum implements ServiceExceptionNameProvider {

    /**
     * oss签名失败
     */
    OSS_SIGN_FAIL("sntk.ossSignFail"),

    /**
     * oss上传文件失败
     */
    OSS_UPLOAD_FAIL("sntk.ossUploadFail"),

    /**
     * 上传图片失败
     */
    UPLOAD_PIC_FAIL("sntk.uploadPictureFail"),

    /**
     * 无可导出数据
     */
    NO_DATA_EXPORT("sntk.noDataExport"),

    EXCEED_MAX_EXPORT_COUNT("sntk.exceedMaxExportCount");

    private String name;

    private SysErrorEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
