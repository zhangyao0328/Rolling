package com.rolling.act.set;

import android.view.View;

import com.alibaba.fastjson.JSON;
import com.rolling.R;
import com.rolling.app.MyApplication;
import com.rolling.base.view.BaseActivity;
import com.rolling.bean.BaseDataBean;
import com.rolling.bean.user.UserLoginPostBean;
import com.rolling.event.ExitEvent;
import com.rolling.net.HttpConfig;
import com.rolling.net.HttpManage;
import com.rolling.util.CineToast;
import com.rolling.util.sp.RollingSp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.OnClick;

public class SysSetActivity extends BaseActivity {

    private final int NET_TAG_SIGN_OUT = 1001;

    float f = 3;

    @Override
    public int getLayoutContextView() {
        return R.layout.activity_sys_set;
    }

    @Override
    public void init() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void succeed(Object o, int tag) {
        switch (tag) {
            case NET_TAG_SIGN_OUT:
                BaseDataBean dataBean = JSON.parseObject(o.toString(), BaseDataBean.class);
                switch (dataBean.getCode()) {
                    case 0:
                        RollingSp.remove(getApplicationContext(), RollingSp.ROLLING_USER_LOGIN_DATA);
                        EventBus.getDefault().post(new ExitEvent());
                        break;
                    default:
                        CineToast.showShort(dataBean.getMessage());
                        break;
                }

                break;
        }
    }

    @Override
    public void error(Object o, int tag) {

    }

    @OnClick({R.id.btExit})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.btExit:
                signOut();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void eventBus(ExitEvent exitEvent) {
        MyApplication.userLoginBean = null;
        finish();
    }

    /**
     * 退出登录
     */
    private void signOut() {

        if (MyApplication.getUserLoginBean() != null) {
            UserLoginPostBean userLoginPostBean = new UserLoginPostBean();
            userLoginPostBean.setUsername(MyApplication.getUserLoginBean().getData().getUsername());
            String json = JSON.toJSONString(userLoginPostBean);
            postLoad(HttpConfig.URL_API_LOGOUT, json, NET_TAG_SIGN_OUT, true, HttpManage.POST);
        }
    }
}