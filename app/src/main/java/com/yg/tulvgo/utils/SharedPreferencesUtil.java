package com.yg.tulvgo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference封装
 * 
 * @author Kevin
 * @date 2015-10-17
 */
public class SharedPreferencesUtil {
	private static SharedPreferences sp;
	public static boolean getBoolean(Context ctx, String key, boolean defValue) {
		sp = ctx.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
	public static void setBoolean(Context ctx, String key, boolean value) {
		sp = ctx.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}
	public static void saveString(String key, String value) {
		if (sp == null) {
			sp = UIUtils.getContext().getSharedPreferences("config", UIUtils.getContext().MODE_PRIVATE);
		}
		sp.edit().putString(key, value).commit();
	}

	public static String getString(String key, String defValue) {
		if (sp == null) {
			sp = UIUtils.getContext().getSharedPreferences("config", UIUtils.getContext().MODE_PRIVATE);
		}
		return sp.getString(key, defValue);
	}
	public static void clearData() {
		sp.edit().clear().commit();
	}
}
