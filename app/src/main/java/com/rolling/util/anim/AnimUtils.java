package com.rolling.util.anim;

import android.view.View;

/**
 * @author zhangyao
 * @date 2020/11/3  11:42
 * @E-mail android_n@163.com
 */
public class AnimUtils {

    /**
     * 缩放动画
     *
     * @param value
     */
    public static void scaleViewAnimation(View view, float value) {
        view.animate().scaleX(value).scaleY(value).setDuration(80).start();
    }
}
