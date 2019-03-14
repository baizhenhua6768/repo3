/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CharacterUtils
 * Author:   17061157_王薛
 * Date:     2018/7/17 21:51
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 功能描述：字符串处理工具类
 *
 * @author 17061157_王薛
 * @since 2018/7/17
 */
public class CharacterUtils {

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source 源字符串
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isEmpty(source)) {
            return source;
        }

        int len = source.length();
        StringBuilder buf = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isNotEmojiCharacter(codePoint)) {
                buf.append(codePoint);
            }
        }
        return buf.toString();
    }

    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
}
