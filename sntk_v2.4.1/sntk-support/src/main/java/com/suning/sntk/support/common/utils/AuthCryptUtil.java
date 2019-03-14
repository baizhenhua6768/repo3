/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: AuthCryptUtil
 * Author:   88396455_白振华
 * Date:     2018-7-2 11:29
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.common.utils;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 权限加密解密方法 <br>
 * 使用Base64 + 3DES方式加解密<br>
 * 3DES加解密的效率较低
 *
 * @author 14062651
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AuthCryptUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthCryptUtil.class);
    private static final String DEFAULT_KEY = "NSF_SUAA_systemauth0p;-o";
    /**
     * 加解密类型是 3DES
     */
    private static final String ALGORITHM = "DESede";
    /**
     * 对应base64编码
     */
    private static Base64 encoder = new Base64();

    private AuthCryptUtil() {

    }

    /**
     * 功能描述: 3DES加密
     *
     * @param target 明文
     * @param key    秘钥(推荐24位,不足24位使用0补足)
     * @return 密文
     */
    private static byte[] encrypt(byte[] target, byte[] key) {

        // 获得24位key值
        byte[] cryptKey = getCryptKey(key);
        try {
            // 获得desKey值
            SecretKey deskey = new SecretKeySpec(cryptKey, ALGORITHM);
            // 生成密文
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            return cipher.doFinal(target);
        } catch (Exception e) {
            LOGGER.error("3DES加密失败", e);
        }
        return null;
    }

    /**
     * 功能描述: 3DES解密<br>
     *
     * @param cipherText 密文
     * @param key        解密key
     * @return 明文
     */
    private static byte[] decrypt(byte[] cipherText, byte[] key) {
        byte[] cryptKey = getCryptKey(key);
        try {
            if (cipherText.length % 8 != 0) {
                LOGGER.error("密文格式不正确,必须是8的整数倍位");
                return null;
            }

            // 获得desKey值
            SecretKey deskey = new SecretKeySpec(cryptKey, ALGORITHM);
            // 生成密文
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            return cipher.doFinal(cipherText);
        } catch (Exception e) {
            LOGGER.error("3DES解密失败", e);
        }
        return null;
    }

    /**
     * 功能描述: 加密
     *
     * @param target 被加密的字符串
     * @param key    加密秘钥(建议24位字符串)
     * @return 密文
     */
    public static String encrypt(String target, String key) {
        byte[] cipher = null;
        try {
            cipher = encrypt(target.getBytes("utf-8"), key.getBytes("utf-8"));
            if (cipher == null) {
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("字符编码不正确, target=" + target + ",key=" + key, e);
            return null;
        }
        return encoder.encodeAsString(encoder.encode(cipher));
    }

    /**
     * 功能描述: 解密
     *
     * @param cipherText 密文
     * @param key        加解密key
     * @return 明文
     */
    public static String decrypt(String cipherText, String key) {
        byte[] clearText = null;
        try {
            clearText = decrypt(encoder.decode(encoder.decode(cipherText)), key.getBytes("utf-8"));
            if (null == clearText) {
                return null;
            }
            return new String(clearText, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("加密失败,不支持的编码类型", e);
            return null;
        }
    }

    /**
     * 功能描述: 获得24位key<br>
     * 不足补齐,超出截取
     *
     * @param key 源key值
     * @return 24位key
     */
    private static byte[] getCryptKey(byte[] key) {
        byte[] cryptKey = new byte[24];
        if (null != key && key.length > 0) {
            if (cryptKey.length > key.length) {
                // 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
                System.arraycopy(key, 0, cryptKey, 0, key.length);
            } else {
                // 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
                System.arraycopy(key, 0, cryptKey, 0, cryptKey.length);
            }
        }
        return cryptKey;
    }

    /**
     * 使用默认的key进行解密<br>
     *
     * @param cipherText
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String decrypt(String cipherText) {
        return decrypt(cipherText, DEFAULT_KEY);
    }

    /**
     * 使用默认key进行加密 <br>
     *
     * @param target 明文
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String encrypt(String target) {
        return encrypt(target, DEFAULT_KEY);
    }

}
