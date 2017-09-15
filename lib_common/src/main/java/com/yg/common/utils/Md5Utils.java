package com.yg.common.utils;

import java.security.MessageDigest;

/**
 * Created by 1323 on 2016/2/23.
 */
public class Md5Utils {

//    public static String md5(String in) {
//        MessageDigest digest;
//        try {
//            digest = MessageDigest.getInstance("MD5");
//            digest.reset();
////            digest.update(in.getBytes());
////            byte[] a = digest.digest();
////            int len = a.length;
////            StringBuilder sb = new StringBuilder(len << 1);
////            for (int i = 0; i < len; i++) {
////                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
////                sb.append(Character.forDigit(a[i] & 0x0f, 16));
////            }
////            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
public final static String MD5(String s) {
    char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    try {
        byte[] btInput = s.getBytes();
        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        // 使用指定的字节更新摘要
        mdInst.update(btInput);
        // 获得密文
        byte[] md = mdInst.digest();
        // 把密文转换成十六进制的字符串形式
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}
