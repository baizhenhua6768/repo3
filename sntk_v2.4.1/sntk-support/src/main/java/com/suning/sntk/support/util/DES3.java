package com.suning.sntk.support.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DES3	加密解密
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/3
 */
public class DES3 {

    private static Logger logger = LoggerFactory.getLogger(DES3.class);

    private DES3() {
    }

    public static String encryptProperty(String clearText, String keyBase64) {
        if (StringUtils.isBlank(clearText)) {
            return "";
        }
        byte[] key = Base64.decodeBase64(keyBase64);
        return DES3.performDESedeCoder(clearText, key, true);
    }

    public static String decryptProperty(String cipherText, String keyBase64) {
        if (StringUtils.isBlank(cipherText)) {
            return "";
        }
        byte[] key = Base64.decodeBase64(keyBase64);
        return DES3.performDESedeCoder(cipherText, key, false);
    }

    public static String performDESedeCoder(String inputValue, byte[] key,
            boolean encrypt) {
        byte[] k = key;
        byte keyByteArray[];
        if (k.length < 24) {
            keyByteArray = new byte[24];
            System.arraycopy(k, 0, keyByteArray, 0, k.length);
            for (int i = k.length; i < 24; i++) {
                keyByteArray[i] = 48;// 不足24字节时，补0
            }
            k = keyByteArray;
        }
        String KEY_ALGORITHM = "DESede";
        String CIPHER_ALGORITHM = "DESede/CBC/PKCS5Padding";

        byte[] data = null;
        try {
            DESedeKeySpec dks = new DESedeKeySpec(k);

            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance(KEY_ALGORITHM);

            SecretKey secretKey = keyFactory.generateSecret(dks);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

            byte[] input;
            if (encrypt) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
                input = inputValue.getBytes("UTF-8");
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
                input = Base64.decodeBase64(inputValue);
            }
            data = cipher.doFinal(input);

        } catch (Exception e) {
            logger.error("DES3解析出错，error:e=" + e);
        }

        String rtnValue = "";
        if (data == null) {
            rtnValue = inputValue;
        } else {
            if (encrypt) {
                rtnValue = Base64.encodeBase64String(data);
            } else {
                try {
                    rtnValue = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error("DES3解析出错，error:e=" + e);
                }
            }
        }

        return rtnValue;
    }

    public static String bytes2hex(byte[] bytes) {
        final String HEX = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt((b >> 4) & 0x0f));
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt(b & 0x0f));
        }
        return sb.toString();
    }

    public static String genRandomNum() {
        int maxNum = 36;
        int i;
        int count = 0;
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuilder pwd = new StringBuilder("");
        Random r = new Random();
        while (count < 8) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

}