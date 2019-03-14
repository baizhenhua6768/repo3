package com.suning.sntk.admin.util;

import com.suning.istore.common.config.ScmProperties;

/**
 * SCM配置工具类(后台)
 *
 * @author root
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SCMConfigUtil {
    /**
     * 配置项名称
     */
    public static final String CONFIG_NAME = "/sntkadminconfig";

    private SCMConfigUtil() {
        // EMPTY
    }

    /*
     * 懒加载
     */
    private static class Holder {
        static final ScmProperties GLOBAL_CONFIG = new ScmProperties(CONFIG_NAME);

        private Holder() {
        }
    }

    public static String getConfig() {
        return Holder.GLOBAL_CONFIG.getScmConfig().getConfig();
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
    public static String getConfig(String key) {
        return Holder.GLOBAL_CONFIG.getProperty(key);
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
    public static String getConfig(String key, String defaultVal) {
        return Holder.GLOBAL_CONFIG.getProperty(key, defaultVal);
    }
}
