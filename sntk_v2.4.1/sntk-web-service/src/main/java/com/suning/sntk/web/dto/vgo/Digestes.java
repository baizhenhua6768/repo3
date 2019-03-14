package com.suning.sntk.web.dto.vgo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public abstract class Digestes {
    private Digestes() {

    }

    public static String getSHA1Signed(String str) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            return new String(Hex.encodeHex(crypt.digest()));
        } catch (NoSuchAlgorithmException e1) {
            throw new IllegalStateException(e1);
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalStateException(e2);
        }
    }
}
