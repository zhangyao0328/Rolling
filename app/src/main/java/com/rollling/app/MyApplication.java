package com.rollling.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.rollling.R;

import cn.bmob.v3.Bmob;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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

        Fresco.initialize(this);
        initBmob();
        initFont();
    }

    private void initBmob(){
        Bmob.initialize(this, "12299351c40d78301ebf709efe6d926e");
    }

    private void initFont(){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
