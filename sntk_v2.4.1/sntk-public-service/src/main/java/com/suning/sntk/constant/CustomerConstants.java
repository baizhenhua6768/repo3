/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerConstants
 * Author:   17061157_王薛
 * Date:     2018/7/7 15:40
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.constant;

/**
 * 功能描述：
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public class CustomerConstants {

    public static final int INVALID = 0; // 失效

    public static final int EFFECTIVE = 1; // 有效

    public static final int SIZE = 10;//默认查询每页数量

    //平台品类redis缓存key
    public static final String BUY_INFO_BRAND_CODE_RE_KEY_MAP = "BUY_INFO_BRAND_CODE_RE_KEY_MAP";

    //苏宁拓客(SNTK) 会员中心RSF平台权限配置
    public static final String SNTK_ON_MEMBER_INFO_APP_CODE = "sntkSTMPsntk-service-web";

    //苏宁拓客(SNTK) 会员中心RSF系统代码
    public static final String SNTK_ON_MEMBER_INFO_SOURCE_SYSTEM_NO = "139000002740";

    //会员中心RSF 客户手机号KEY
    public static final String MEMBER_MOBILE_KEY = "138000000010";

    //会员中心RSF 客户性别 : 女
    public static final String MEMBER_SEX_WOMAN = "124000000020";
    //会员中心RSF 客户性别 : 男
    public static final String MEMBER_SEX_MAN = "124000000010";

    //时间格式
    public static final String TIME_FORMAT = "yyyy-MM-dd";

    //购买标识
    public static final String NO_BUY = "0";//未购物
    public static final String FIRST_BUY = "1";//已首购
    public static final String AGAIN_BUY = "2";//已复购
    public static final String NOT_MEMBER = "3";//非会员

    public static final String DEFAULT_REMARK_NAME = "顾客";

    public static final String NO_LEVEL = "无等级";

    private CustomerConstants() {
    }
}
