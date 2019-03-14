/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CommonUtils
 * Author:   88397670_张辉
 * Date:     2018-7-23 15:21
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.util;

import java.sql.Timestamp;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * 功能描述：公共方法工具类
 *
 * @author 88397670_张辉
 * @since 2018-7-23
 */
public class CommonUtils {

    private CommonUtils() {
    }

    /**
     * 商品编码总长度
     */
    public static final int IN_PART_NUMBER_LEN = 18;

    /**
     * 状态:0
     */
    public static final String STATUS_ZERO = "0";

    /**
     * 处理string字符串返回非null的值
     *
     * @param str 待处理字符串
     * @author 88397670_张辉
     * @since 15:23 2018-7-23
     */
    public static String nullToEmptyString(String str) {
        if (null == str) {
            return StringUtils.EMPTY;
        }
        return str;
    }

    /**
     * 生成随机多位数字
     *
     * @param length 长度
     * @author 88395115_史小配
     * @since 15:25 2018/8/17
     */
    public static String randomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 当前商品编码有效位数，补足18位商品编码
     *
     * @param inPartNumber
     * @return java.lang.String
     * @author 18041004_余长杰
     * @since 9:40 2018/8/18
     */
    public static String getPartNumber(String inPartNumber) {
        StringBuilder str = new StringBuilder();
        if (StringUtils.isNotBlank(inPartNumber)) {
            //有效长度
            int len = inPartNumber.length();
            //除了有效长度，剩下位数补0
            for (int i = 0; i < IN_PART_NUMBER_LEN - len; i++) {
                str.append(STATUS_ZERO);
            }
        }
        str.append(inPartNumber);
        return str.toString();
    }

    /**
     * 获得当前时间戳
     *
     * @author 88396455_白振华
     * @since 14:43  2018-9-4
     */
    public static Timestamp obtainCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
