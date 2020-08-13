package com.summertrain.pre_util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author tianshu
 * @Date 2019/7/3
 * @Description 用于md5摘要算法
 * */
public class MD5 {
    public static byte[] hashSalt(String origin,String salt) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update((origin+salt).getBytes());
        return md5.digest();
    }

    public static byte[] hashSalt(byte[] origin,String salt) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update((origin+salt).getBytes());
        return md5.digest();
    }
    /**进行两次MD5摘要*/
    public static String summaryDoubleWithSalt(String origin,String salt) {
        byte[] secretBytes = hashSalt(hashSalt(origin,salt),salt);
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

}
