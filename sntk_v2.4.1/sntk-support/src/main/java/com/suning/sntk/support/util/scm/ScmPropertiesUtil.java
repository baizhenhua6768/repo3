/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ScmPropertiesUtil
 * Author:   18041004_余长杰
 * Date:     2018/9/8 16:44
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.util.scm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.suning.istore.common.config.ScmProperties;

/**
 * 功能描述：SCM工具类
 *
 * @author 18041004_余长杰
 * @since 2018/9/8
 */
public class ScmPropertiesUtil {

    private ScmPropertiesUtil() {
    }

    private static Map<ScmPropertyFileEnum, ScmProperties> map = new ConcurrentHashMap<>();

    static {
        ScmPropertyFileEnum[] values = ScmPropertyFileEnum.values();
        for (ScmPropertyFileEnum scmPropertyFileEnum : values) {
            map.put(scmPropertyFileEnum, new ScmProperties(scmPropertyFileEnum.getName()));
        }
    }

    /**
     * 功能描述: 获取配置项<br>
     * 〈功能详细描述〉
     *
     * @param key
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getConfig(ScmPropertyFileEnum fileEnum, String key) {
        return map.get(fileEnum).getProperty(key);
    }

    /**
     * 功能描述: 获取配置项，带默认值<br>
     * 〈功能详细描述〉
     *
     * @param key
     * @param defaultVal
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getConfig(ScmPropertyFileEnum fileEnum, String key, String defaultVal) {
        return map.get(fileEnum).getProperty(key, defaultVal);
    }
}
