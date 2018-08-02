package com.rollling.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rollling.base.prsenter.BasePresenter;
import com.rollling.base.prsenter.BasePresenterImpl;
import com.rollling.bean.BaseBean;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

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
}
