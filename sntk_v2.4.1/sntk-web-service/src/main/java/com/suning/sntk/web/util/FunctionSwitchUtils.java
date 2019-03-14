/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: FunctionSwitchUtils
 * Author:   17061157_王薛
 * Date:     2018/8/19 14:51
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.util;

/**
 * 功能描述：开关工具类
 *
 * @author 17061157_王薛
 * @since 2018/8/19
 */
public class FunctionSwitchUtils {

    // 开关开启 (eg:降级开关开启表示需要降级)
    private static final String SWITCH_OPEN = "1";
    // 降级开关关闭
    private static final String SWITCH_CLOSE = "0";

    // 开关默认值：关闭
    private static final String DEFAULT_SWITCH_STATUS = SWITCH_CLOSE;

    //四级页开关
    private static final String FOURTH_PAGE_SWITCH_KEY = "FOURTH_PAGE_DOWNGRADE_SWITCH";

    //Vanish降级开关
    private static final String VANISH_SWITCH_KEY = "VANISH_DOWNGRADE_SWITCH";

    //我的客户经理开关
    private static final String CUST_MANAGER_SWITCH_KEY = "CUST_MANAGER_DOWNGRADE_SWITCH";

    private static boolean switchOpen(String scmKey) {
        String value = ScmWebConfigUtil.getConfig(scmKey, DEFAULT_SWITCH_STATUS);
        return SWITCH_CLOSE.equals(value);
    }

    /**
     * 功能描述: 四级页降级开关 <br>
     *
     * @param
     * @return boolean true-功能正常  false-降级
     * @author 17061157_王薛
     * @since 15:13  2018/8/19
     */
    public static boolean fourthPageSwitchOpen() {
        return switchOpen(FOURTH_PAGE_SWITCH_KEY);
    }

    /**
     * vanish降级开关
     *
     * @return boolean true-功能正常  false-降级
     * @author 18041004_余长杰
     * @since 17:28 2018/8/28
     */
    public static boolean vanishSwitchOpen() {
        return switchOpen(VANISH_SWITCH_KEY);
    }

    /**
     * 我的客户经理开关
     *
     * @return boolean  true-功能正常  false-降级
     * @author 18041004_余长杰
     * @since 15:37 2018/9/4
     */
    public static boolean custManagerSwitchOpen() {
        return switchOpen(CUST_MANAGER_SWITCH_KEY);
    }

}
