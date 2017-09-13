package com.yg.tulvgo.utils;

import java.util.HashMap;

/**
 * =====================
 * Author: shenjie
 * Date: 13-11-1
 * Time: 下午14:17
 * describe:对此类的描述
 * 存放错误信息
 * =====================
 */

public class ErrorInfo {
 public static HashMap<String,String> INFO = new HashMap<>();
    static {
        INFO.put("0","成功");
        INFO.put("1","用户名或密码有误");
    }
}
