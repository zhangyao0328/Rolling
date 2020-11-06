package com.rolling.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author zhangyao
 * @date 2020/11/4  16:54
 * @E-mail android_n@163.com
 */
public class TimeUtils {
    private static SimpleDateFormat sdf = null;
    public  static String formatUTC(long l, String strPattern) {
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (sdf == null) {
            try {
                sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
            } catch (Throwable e) {
            }
        } else {
            sdf.applyPattern(strPattern);
        }
        return sdf == null ? "NULL" : sdf.format(l);
    }
}
