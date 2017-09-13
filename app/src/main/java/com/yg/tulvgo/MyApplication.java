package com.yg.tulvgo;

import android.app.Application;
import android.content.Context;

/**
 * Created by shenjie on 2017/9/13.
 */

public class MyApplication extends Application{
    public static Context context;
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
