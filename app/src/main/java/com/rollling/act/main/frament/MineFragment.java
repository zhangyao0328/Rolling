package com.rollling.act.main.frament;

import android.view.View;

import com.rollling.R;
import com.rollling.act.login.LoginActivity;
import com.rollling.base.view.BaseActivity;
import com.rollling.base.view.BaseFragment;
import com.rollling.bean.MainBannerBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * @author zhangyao
 * @date 2018/8/4  01:03
 * @E-mail android_n@163.com
 * 首页-我的
 */
public class MineFragment extends BaseFragment {
    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void init() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {

    }

    @Override
    public void succeed(String t, int tag) {

    }

    @Override
    public void error(String t, int tag) {

    }

    @Override
    public void responseCode(int code) {

    }

    @OnClick({R.id.btAddBanner, R.id.btAddUser})
    public void onClicks(View view){
        switch (view.getId()){
            case R.id.btAddBanner:
                addBanner();
                break;
            case R.id.btAddUser:
                addUser();
                break;
        }
    }

    private void addBanner(){
        List<String> list = new ArrayList<>();
        list.add("https://tattoodo-mobile-app.imgix.net/images/posts/postImage_FrbD6eJgoX.png?w=1122&fit=crop&auto=format%2Ccompress");
        list.add("https://tattoodo-mobile-app.imgix.net/images/posts/ScreenShot2017-11-13at094213_YLwIX6GkPi.png?w=567&fit=crop&auto=format%2Ccompress");
        list.add("https://tattoodo-mobile-app.imgix.net/images/posts/postImage_0JiZ41Gjgx.png?w=1122&fit=crop&auto=format%2Ccompress");
        for (String str : list){
            MainBannerBean mainBannerBean = new MainBannerBean();
            mainBannerBean.setUrl(str);
            mainBannerBean.setUsername(BmobUser.getCurrentUser().getUsername());
            mainBannerBean.setHeadUrl("https://images.unsplash.com/photo-1533369257659-6132475f793c?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=1c926464dc14a8282bdb1dad352424a6&auto=format&fit=crop&w=654&q=80");
            mainBannerBean.setTitle("今日推荐");
            savaData(mainBannerBean, 0, true );
        }
    }

    private void addUser(){
        ((BaseActivity)getActivity()).openActivity(LoginActivity.class);
    }
}
