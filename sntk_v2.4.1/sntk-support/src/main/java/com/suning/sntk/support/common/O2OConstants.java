package com.suning.sntk.support.common;

import java.util.HashMap;
import java.util.Map;

public class O2OConstants {

    private O2OConstants() {
        // 默认私有构造器
    }

    // 默认的星值，5星
    public static final int DEFAULT_STAR = 5;

    // 默认平均星值
    public static final String DEFAULT_AVG_STAR = "5.0";

    // UAA用1 表示离职
    public static final int UAA_QUIT = 1;

    // O2O用0 表示离职
    public static final String O2O_QUIT = "0";

    // 线上(易购)
    public static final String CHANNEL_ONLINE = "1";

    // 线下
    public static final String CHANNEL_OFFLINE = "0";

    // 云信设置客户经理渠道
    public static final String YUN_XIN_LINE = "33";


    // 电器业态
    public static final String MACHINE = "1";

    // 为-1，表示忽略
    public static final String SKIP = "-1";

    // 默认品类，数据字典表中如果没有数据的时候，取这个值
    public static final String DEFAULT_CATEGORIES = "00001:空调,00002:冰洗,00003:黑电,00004:数码,00005:电脑,00006:通讯,00007:小家电,000013:厨卫";

    // 逗号 ","
    public static final String SP_DH = ",";

    // 冒号 ":"
    public static final String SP_MH = ":";

    // NSF系统成功返回值
    public static final String NSF_SCUUESS = "0";

    // NSF系统失败返回值
    public static final String NSF_FAIL = "1";

    // YES = 1
    public static final String YES = "1";

    // NO = 0
    public static final String NO = "0";

    // 更新成功
    public static final String UPDATE_SUCCESS = "更新成功";

    // 星号：*
    public static final String ASTERISK = "*";

    //解除关系
    public static final String DELETE_FLAG = "1";

    //建立关系
    public static final String BUILD_FLAG = "0";
    /**
     * 默认的星级初始标签编码
     */
    public static final Map<Integer, String> DEFAULT_LABLE_CODE_MAP = new HashMap<Integer, String>(); // NOSONAR

    static {
        DEFAULT_LABLE_CODE_MAP.put(1, "1001");
        DEFAULT_LABLE_CODE_MAP.put(2, "2001");
        DEFAULT_LABLE_CODE_MAP.put(3, "3001");
        DEFAULT_LABLE_CODE_MAP.put(4, "4001");
        DEFAULT_LABLE_CODE_MAP.put(5, "5001");
    }

    ;
}
