package com.rollling.app;

import android.app.Application;
import android.content.Context;

/**
 * @author zhangyao
 * @date 2018/7/29  22:10
 * @E-mail android_n@163.com
 */
public class MyApplication extends Application{

    private static MyApplication mContext;

    public static boolean isDebug = true;

    public static Context getInstance() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
