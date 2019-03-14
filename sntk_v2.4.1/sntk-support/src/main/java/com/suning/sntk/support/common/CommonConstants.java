package com.suning.sntk.support.common;

public class CommonConstants {

    /**
     * 启用状态
     */
    public static final Integer STATUS_ENABLE = 1;
    /**
     * 禁用状态
     */
    public static final Integer STATUS_DISABLE = 0;
    /**
     * 成功
     */
    public static final String RSF_SUCCESS = "Y";
    /**
     * 失败
     */
    public static final String RSF_FAIL = "N";
    /**
     * 已删除
     */
    public static final Integer DELETE_FLAG = 1;
    /**
     * 未删除
     */
    public static final Integer UNDELETE_FLAG = 0;
    /**
     * 返回失败:0
     */
    public static final String RESPONSE_FAIL = "0";
    /**
     * 返回成功:1
     */
    public static final String RESPONSE_SUCCESS = "1";
    /**
     * 导购推广码
     */
    public static final String CHANNEL_GUIDE_CODE = "0";
    /**
     * 1--是客户经理关系；0--不是客户经理关系
     */
    public static final String IS_MANAGER_RELATION = "1";
    public static final String NOT_MANAGER_RELATION = "0";
    /**
     * 调用会员中心返回成功标识
     */
    public static final String SUCCESS = "COMPLETE";
    /**
     * 调用会员中心返回失败标识
     */
    public static final String FAIL = "FAIL";
    /**
     * 苏宁拓客编码（会员中心制定）
     */
    public static final String SYSTEM_CODE_SNTK = "139000002740";
    /**
     * 苏宁拓客简称（会员中心制定）
     */
    public static final String SYSTEM_ABBREVIATION_SNTK = "SNTK";
    /**
     * 前缀（扫描但参数二维码的推送前缀）
     */
    public static final String PREFIX_FOR_SCENEID = "WGG_";
    /**
     * 失败标识
     */
    public static final Integer FLAG_FAIL = 0;
    /**
     * 系统名称
     */
    public static final String SYS = "system";

    /**
     * 是
     */
    public static final Integer YES = 1;

    /**
     * 否
     */
    public static final Integer NO = 0;

    /**
     * 数字 0 的字符串形式
     */
    public static final String ZERO_STR = "0";

    /**
     * null 字符串，根据场景值查询店员信息时，有可能返回"null"字符串
     */
    public static final String NULLSTR = "null";

    /**
     * 文件上传OSS存储区名字
     */
    public static final String FILE_BUCKEN_NAME = "upload-file";
    /**
     * 图片上传OSS存储区名字
     */
    public static final String PIC_BUCKET_NAME = "sntk_image";

    /**
     * 默认文件导出行数
     */
    public static final String DEFAULT_MAX_EXPORT_DATA_COUNT = "500000";
    private CommonConstants() {
    }
}
