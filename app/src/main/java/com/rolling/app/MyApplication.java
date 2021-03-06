package com.rolling.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.anzewei.parallaxbacklayout.ParallaxHelper;
import com.hjq.toast.ToastUtils;
import com.rolling.R;
import com.rolling.bean.user.UserLoginBean;
import com.rolling.helper.ActivityStackManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * @author zhangyao
 * @date 2018/7/29  22:10
 * @E-mail android_n@163.com
 */
public class MyApplication extends Application {

    private static MyApplication application;

    public static boolean isDebug = true;

    public static UserLoginBean userLoginBean;

    public static Context getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        // Activity 栈管理初始化
        ActivityStackManager.getInstance().init(application);
        Fresco.initialize(this);
        initFont();
        initActivityLifecycleCallbacks();
        ToastUtils.init(this);
    }

    private void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static UserLoginBean getUserLoginBean() {
        return userLoginBean;
    }

    private void initActivityLifecycleCallbacks(){
        registerActivityLifecycleCallbacks(ParallaxHelper.getInstance());
    }
}
