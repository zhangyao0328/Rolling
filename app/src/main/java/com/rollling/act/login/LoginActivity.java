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
import com.rollling.bean.user.SignUpBean;
import com.rollling.bean.user.UserSginInBean;
import com.rollling.bean.user.UserSginUpBean;
import com.rollling.net.HttpConfig;
import com.rollling.net.HttpManage;
import com.rollling.util.CineLog;
import com.rollling.util.CineToast;
import com.rollling.util.ScreenUtils;
import com.rollling.view.CustomVideoView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private final int NET_TAG_SIGN_UP = 1001;

    private final int NET_TAG_SIGN_IN = 1002;

    @BindView(R.id.userName)
    EditText userName;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.customVideoView)
    CustomVideoView customVideoView;

    @Override
    public int getLayoutContextView() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        ScreenUtils.setFullScreen(this);
//        initVideo();
    }

    @Override
    public void succeed(Object o, int tag) {
        switch (tag) {
            case NET_TAG_SIGN_UP:
                UserSginUpBean userResultBean = JSON.parseObject(o.toString(), UserSginUpBean.class);
                switch (userResultBean.getCode()) {
                    case 0:
                        CineLog.e(" 创建用户成功");
                        signIn();
                        break;
                    default:
                        CineLog.e(userResultBean.getMessage());
                        CineToast.showShort(userResultBean.getMessage());
                        break;
                }
                break;
            case NET_TAG_SIGN_IN:
                UserSginInBean userLoginBean = JSON.parseObject(o.toString(), UserSginInBean.class);
                switch (userLoginBean.getCode()) {
                    case 0:
                        MyApplication.apiConfig = userLoginBean.getData().getToken();
                        CineLog.e(" 用户登录成功");
                        openActivity(MainActivity.class);
                        break;
                    default:
                        CineLog.e(userLoginBean.getMessage());
                        CineToast.showShort(userLoginBean.getMessage());
                        break;
                }
                break;
        }
    }

    @Override
    public void error(Object o, int tag) {

    }

    @OnClick({R.id.btSignIn, R.id.btSignUp})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.btSignIn:
                signIn();
                break;
            case R.id.btSignUp:
                signUp();
                break;
        }
    }

    /**
     * 登录
     */
    private void signIn() {
        if (!isUsernamPsdIsEmpty()) {
            SignUpBean singUpBean = new SignUpBean();
            singUpBean.setUsername(userName.getText().toString().trim());
            singUpBean.setPassword(password.getText().toString().trim());
            String json = JSON.toJSONString(singUpBean);
            postLoad(HttpConfig.URL_API_LOGIN, json, NET_TAG_SIGN_IN, true, HttpManage.POST);
        }
    }

    /**
     * 注册
     */
    public void signUp() {

        if (!isUsernamPsdIsEmpty()) {
            SignUpBean singUpBean = new SignUpBean();
            singUpBean.setUsername(userName.getText().toString().trim());
            singUpBean.setPassword(password.getText().toString().trim());
            String json = JSON.toJSONString(singUpBean);
            postLoad(HttpConfig.URL_API_CEEATE_USER, json, NET_TAG_SIGN_UP, true, HttpManage.POST);
        }

    }

    private boolean isUsernamPsdIsEmpty() {
        if (TextUtils.isEmpty(userName.getText().toString().trim())) {
            CineToast.showShort(R.string.sign_username_empty);
            return true;
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
        initVideo();
        super.onRestart();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        customVideoView.stopPlayback();
        super.onStop();
    }
}

