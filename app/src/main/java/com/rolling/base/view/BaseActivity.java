package com.rolling.base.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.anzewei.parallaxbacklayout.ParallaxHelper;
import com.rolling.base.prsenter.BasePresenter;
import com.rolling.base.prsenter.BasePresenterImpl;
import com.rolling.bean.BaseBean;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * @author zhangyao
 * @date 2018/7/29  22:11
 * @E-mail android_n@163.com
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    BasePresenter basePresenter;

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

    public void getLoad(String url, String[] key, String[] value, int tag, boolean isDialog) {
        if (basePresenter == null) {
            basePresenter = new BasePresenterImpl(this, this);
        }
        BaseBean baseBean = new BaseBean(url, key, value, tag, isDialog);
        basePresenter.getAsync(this, baseBean);
    }

    public void postLoad(String url, String strJson, int tag, boolean isDialog, String method) {
        if (basePresenter == null) {
            basePresenter = new BasePresenterImpl(this, this);
        }
        BaseBean baseBean = new BaseBean(url, strJson, tag, isDialog, method);
        basePresenter.postAsync(this, baseBean);
    }


    @Override
    public void responseCode(int code) {

    }

    /**
     * 左滑返回-禁止
     */
    public void disableBack(){
        ParallaxHelper.disableParallaxBack(this);
    }

}
