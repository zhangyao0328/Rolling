package com.rollling.util;

import android.util.Log;

import com.rollling.app.MyApplication;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/21
 *     desc  : utils about log
 * </pre>
 */
public final class CineLog {

    private CineLog() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 是否需要打印bug，可以在application的onCreate函数里面初始化
     */
    public static final String TAG = "ROLLING-LOG";

    public static void i(String msg) {
        if (MyApplication.isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (MyApplication.isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (MyApplication.isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (MyApplication.isDebug) {
            Log.v(TAG, msg);
        }
    }

    /**
     * 传入自定义tag的函数
     */
    public static void i(String tag, String msg) {
        if (MyApplication.isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (MyApplication.isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (MyApplication.isDebug) {
            Log.i(tag, msg);
        }

    }

    public static void v(String tag, String msg) {
        if (MyApplication.isDebug) {
            Log.i(tag, msg);
        }
    }
}
