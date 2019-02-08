package com.jiwenjie.common;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2018-12-13
 * desc: 加密部分
 */
public class MD5Crypto {

    public static String MD5(String inStr) {
        try {
            char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(inStr.getBytes(StandardCharsets.UTF_8));

            byte[] bytes = md.digest();
            int j = bytes.length;
            char[] c = new char[j * 2];
            int k = 0;

            for (byte b : bytes) {
                c[k++] = hexDigits[b >>> 4 & 0xf];
                c[k++] = hexDigits[b & 0xf];
            }

            return new String(c);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static String decryptBASE64(String key) {
        byte[] bt;
        try {
            bt = (new BASE64Decoder()).decodeBuffer(key);
            return new String(bt);// 如果出现乱码可以改成: String(bt, "utf-8") 或 gbk
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encryptBASE64(String key) {
        byte[] bt = key.getBytes();
        return (new BASE64Encoder()).encodeBuffer(bt);
    }

    public static void main(String[] args) {
        String pwd = "123456";
        String md5Pwd = MD5(pwd);
        String afterEncode = encryptBASE64(md5Pwd);
        String afterDecode = decryptBASE64(afterEncode);
        System.out.println("Md5Pwd: " + md5Pwd + ", afterEncode: " + afterEncode + ", afterDecode: " + afterDecode);
    }
}

