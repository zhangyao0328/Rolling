package com.rollling.act.login;

import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.rollling.R;
import com.rollling.act.main.MainActivity;
import com.rollling.app.MyApplication;
import com.rollling.base.view.BaseActivity;
import com.rollling.bean.user.UserLoginBean;
import com.rollling.bean.user.UserLoginPostBean;
import com.rollling.net.HttpConfig;
import com.rollling.net.HttpManage;
import com.rollling.util.CineLog;
import com.rollling.util.CineToast;
import com.rollling.util.ScreenUtils;
import com.rollling.util.sp.RollingSp;
import com.rollling.view.CustomVideoView;
import com.rollling.view.FrescoImage;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private final int NET_TAG_SIGN_IN = 1002;

    private final int NET_TAG_AUTH_CODE = 1003;

    @BindView(R.id.userName)
    EditText userName;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.customVideoView)
    CustomVideoView customVideoView;

    @BindView(R.id.frescoImageBg)
    FrescoImage frescoImageBg;

    @Override
    public int getLayoutContextView() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        ScreenUtils.setFullScreen(this);
//        initVideo();

        setView();
    }

    @Override
    public void succeed(Object o, int tag) {
        switch (tag) {
            case NET_TAG_SIGN_IN:
                UserLoginBean userLoginBean = JSON.parseObject(o.toString(), UserLoginBean.class);
                switch (userLoginBean.getCode()) {
                    case 0:
                        MyApplication.userLoginBean = userLoginBean;
                        RollingSp.putData(this, RollingSp.ROLLING_USER_LOGIN_DATA, o.toString());
                        CineLog.e(" 用户登录成功");
                        openActivity(MainActivity.class);
                        break;
                    default:
                        if (!TextUtils.isEmpty(userLoginBean.getMessage())) {
                            CineToast.showShort(userLoginBean.getMessage());
                        }
                        break;
                }
                break;
            case NET_TAG_AUTH_CODE:
                break;
        }
    }

    @Override
    public void error(Object o, int tag) {

    }

    @OnClick({R.id.btSignIn, R.id.btGetAuthCode, R.id.tvBack})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.btSignIn:
                signIn();
                break;
            case R.id.btGetAuthCode:
                getAuthCode();
                break;
            case R.id.tvBack:
                finish();
                break;
        }
    }

    /**
     * 登录
     */
    private void signIn() {
        if (!isUsernamPsdIsEmpty(false)) {
            UserLoginPostBean userLoginPostBean = new UserLoginPostBean();
            userLoginPostBean.setUsername(userName.getText().toString().trim());
            userLoginPostBean.setPassword(password.getText().toString().trim());
            String json = JSON.toJSONString(userLoginPostBean);
            postLoad(HttpConfig.URL_API_LOGIN, json, NET_TAG_SIGN_IN, true, HttpManage.POST);
        }
    }


    /**
     * 获取验证码
     */
    public void getAuthCode() {

        if (!isUsernamPsdIsEmpty(true)) {
            UserLoginPostBean userLoginPostBean = new UserLoginPostBean();
            userLoginPostBean.setUsername(userName.getText().toString().trim());
            String json = JSON.toJSONString(userLoginPostBean);
            postLoad(HttpConfig.URL_API_AUTH_CODE, json, NET_TAG_AUTH_CODE, true, HttpManage.POST);
        }
    }

    private boolean isUsernamPsdIsEmpty(boolean isGetAuthCode) {


        if (TextUtils.isEmpty(userName.getText().toString().trim())) {
            CineToast.showShort(R.string.sign_username_empty);
            return true;
        }

        if(isGetAuthCode){
            return false;
        }

        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            CineToast.showShort(R.string.sign_password_empty);
            return true;
        }

        return false;
    }

    private void initVideo() {

        customVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.login_video));
        customVideoView.start();
        customVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
//                customVideoView.start();
            }
        });
    }

    //返回重启加载
    @Override
    protected void onRestart() {
//        initVideo();
        super.onRestart();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        customVideoView.stopPlayback();
        super.onStop();
    }

    private void setView(){
        frescoImageBg.setImageURL("https://images.unsplash.com/photo-1603880007902-ec1a7bc05958?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60");
    }
}

