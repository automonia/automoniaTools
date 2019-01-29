package com.automonia.tools;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @作者 温腾
 * @创建时间 2019年01月26日 19:01
 */
public enum CryptoUtils {

    singleton;

    private final String ALGORITHM = "PBEWithMD5AndDES";
    public final String Salt = "63293188";

    /**
     * 加密明文字符串
     *
     * @param keyValue 生成密钥时使用的密钥
     * @param password 待加密的内容
     */
    public String encrypt(String keyValue, String password) throws Exception {
        Key key = getPBEKey(password);
        PBEParameterSpec parameterSpec = new PBEParameterSpec(getStaticSalt(), 1000);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
        return StringUtils.singleton.bytesToHexString(cipher.doFinal(keyValue.getBytes()));
    }


    public String decodeBASE64(String str) {
        return decodeBASE64(str, "UTF-8");
    }

    public String encodeBASE64(String str) {
        return encodeBASE64(str, "UTF-8");
    }

    /**
     * BASE64解密
     *
     * @param str     a {@link String} object.
     * @param charset 字符编码
     * @return a {@link String} object.
     */
    public String decodeBASE64(String str, String charset) {
        try {
            byte[] bytes = str.getBytes(charset);
            return new String(Base64.decodeBase64(bytes));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * BASE64加密
     *
     * @param str     a {@link String} object.
     * @param charset a {@link String} object.
     * @return a {@link String} object.
     */
    public String encodeBASE64(final String str, String charset) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(charset);
            return Base64.encodeBase64String(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    /////

    private byte[] getStaticSalt() {
        return Salt.getBytes();
    }

    private Key getPBEKey(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        /**
         * 实例化使用的算法
         */
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);

        /**
         * 设置PBE密钥参数
         */
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

        return keyFactory.generateSecret(keySpec);
    }

}
