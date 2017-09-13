package com.yg.tulvgo.utils;

import android.content.Context;
import android.content.res.Resources;

import com.yg.tulvgo.MyApplication;

import java.util.Random;

/**
 * 创建者     shenjie
 * 描述	      封装和ui相关的操作
 */
public class UIUtils {
    /**
     * 得到上下文
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 得到Resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到String.xml中的字符串信息
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }


    /**
     * 得到String.xml中的字符串数组信息
     */
    public static String[] getStrings(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到Color.xml中的颜色信息
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 得到应用程序包名
     *
     * @return
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * dip-->px
     *
     * @param dip
     * @return
     */
    public static int dip2Px(int dip) {
        /*
        1.  px/(ppi/160) = dp
        2.  px/dp = density
         */

        //取得当前手机px和dp的倍数关系
        float density = getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + .5f);
        return px;
    }

    public static int px2Dip(int px) {
        // 2.  px/dp = density

        //取得当前手机px和dp的倍数关系
        float density = getResources().getDisplayMetrics().density;

        int dip = (int) (px / density + .5f);
        return dip;
    }
    public static String getTime() {
//      return (System.currentTimeMillis() + "").trim();
        long time= System.currentTimeMillis();//获取系统时间的10位的时间戳
        String str= String.valueOf(time);
        return str;
    }
    public static int getRandom() {
        Random ran = new Random(System.currentTimeMillis());//把当前时刻长整形传给Random对象让其产生值随时间而变化
        return ran.nextInt(100);
    }
}
