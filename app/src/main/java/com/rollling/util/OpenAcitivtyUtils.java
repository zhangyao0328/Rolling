package com.rollling.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author zhangyao
 * @date 2020-03-12  11:42
 * @E-mail android_n@163.com
 * 公共跳转工具类
 */
public class OpenAcitivtyUtils {

    public static void openAct(Context mContext, Class<?> act, Bundle bundle) {
        if (bundle != null) {
            Intent intent = new Intent(mContext, act);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        } else {
            openAct(mContext, act);
        }
    }

    public static void openAct(Context mContext, Class<?> act) {
        Intent intent = new Intent(mContext, act);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public static void openAct(Context mContext, Class<?> act, Bundle bundle, int flag) {
        if (bundle != null) {
            Intent intent = new Intent(mContext, act);
            intent.putExtras(bundle);
            intent.addFlags(flag | Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        } else {
            openAct(mContext, act);
        }
    }

    public static void openAct(Context mContext, Class<?> act, int flag) {
            Intent intent = new Intent(mContext, act);
            intent.addFlags(flag | Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
    }

}
