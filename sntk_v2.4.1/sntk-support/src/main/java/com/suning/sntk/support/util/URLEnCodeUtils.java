/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: URLEnCodeUtils
 * Author:   18032490_赵亚奇
 * Date:     2018/10/19 15:57
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 18032490_赵亚奇
 * @since 2018/10/19
 */
public class URLEnCodeUtils {

    private URLEnCodeUtils() {
    }

    private static Logger logger = LoggerFactory.getLogger(URLEnCodeUtils.class);

    /**
     * url转码
     */
    public static String urlEncode(String s) {
        String value = "";
        try {
            logger.info("url转码 s:{}", s);
            value = URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("URLEncode失败", e);
        }
        return value;
    }
}
