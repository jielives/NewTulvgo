package com.yg.tulvgo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.yg.tulvgo.MyApplication;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

;


/**
 * Created by sunwin on 2016/2/3.
 */
public class Util {
    private static final String TAG = "SDK_Sample.Util";
    private static final int MAX_DECODE_PICTURE_SIZE = 1920 * 1440;

    /**
     * @param context
     * @return true 有网 false 没网
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    //获取设备ID device_id
    public static String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) MyApplication.getContext().getSystemService(MyApplication.getContext().TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static String getVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 判断手机号码格式
     *
     * @param mobiles
     * @return true 是手机号码  false 不是手机号码
     */
    public static boolean isMobile(String mobiles) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(mobiles);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    // 判断是否符合身份证号码的规范
    public static boolean isIDCard(String IDCard) {
        if (IDCard != null) {
            String IDCardRegex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x|Y|y)$)";
            return IDCard.matches(IDCardRegex);
        }
        return false;
    }
    /**
     * @param strEmail
     * @return true 是邮箱  false 不是不是邮箱
     */
    public static boolean isEmail(String strEmail) {
        String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字典排序
     */
    public static String sort(Map<String, String> m) {

        Set<Map.Entry<String, String>> set = m.entrySet();
        List<Map.Entry<String, String>> list = new ArrayList<>(set);
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> lhs, Map.Entry<String, String> rhs) {
                return lhs.getKey().compareTo(rhs.getKey());
            }
        });
        StringBuffer stringBuffer = new StringBuffer();

        for (Map.Entry<String, String> entry : list) {
            if (list.indexOf(entry) == list.size() - 1) {
                stringBuffer.append(entry.getKey() + "=" + entry.getValue());
            } else {
                stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 将手机号码中间的4位隐藏
     */
    public static String hideSomeMobilePhoneNumber(String mobile) {
        return mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            //注意："com.example.try_downloadfile_progress"对应AndroidManifest.xml里的package="……"部分
            verCode = context.getPackageManager().getPackageInfo("com.sunwin.zukelai", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.d("msg", e.getMessage());
        }
        return verCode;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo("com.tolvgo", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.d("msg", e.getMessage());
        }
        return verName;
    }

    public static String sha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes());
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> stringsToList(final String[] src) {
        if (src == null || src.length == 0) {
            return null;
        }
        final List<String> result = new ArrayList<String>();
        for (int i = 0; i < src.length; i++) {
            result.add(src[i]);
        }
        return result;
    }

    public static String getTimeFromInt(int time) {
        if (time <= 0) {
            return "0:00";
        }else {
            int secondnd = (time / 1000) / 60;
            int million = (time / 1000) % 60;
            String f = String.valueOf(secondnd);
            String m = million >= 10? String.valueOf(million):"0" + String.valueOf(million);
            return f + ":" + m;
        }
    }
    public static String getUrl(Map<String, String> params) {
        String url = "";
        // 添加url参数
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            url += sb.toString();
        }
        return url;
    }
}