package com.rollling.base.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * @author zhangyao
 * @date 2018/7/29  22:11
 * @E-mail android_n@163.com
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutContextView());
        ButterKnife.bind(this);
        init();
    }


    public abstract int getLayoutContextView();

    @Subscribe
    public abstract void init();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void openActivity(Class<?> act) {
        startActivity(new Intent(this, act));
    }

}
