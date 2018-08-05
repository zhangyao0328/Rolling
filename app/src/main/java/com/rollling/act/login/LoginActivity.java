package com.rollling.act.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.rollling.R;
import com.rollling.base.view.BaseActivity;
import com.rollling.bmom.BmomManageUtils;
import com.rollling.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity{

    @BindView(R.id.userName)
    EditText userName;

    @BindView(R.id.password)
    EditText password;

    @Override
    public int getLayoutContextView() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {

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

    @OnClick({R.id.btSignIn})
    public void onClicks(View view){
        switch (view.getId()){
            case R.id.btSignIn:
                signUp();
                break;
        }
    }

    public void signUp(){
        if(TextUtils.isEmpty(userName.getText().toString().trim())){
            ToastUtils.showShort(R.string.sign_username_empty);
        }
        if(TextUtils.isEmpty(password.getText().toString().trim())){
            ToastUtils.showShort(R.string.sign_password_empty);
        }
        BmomManageUtils bmomManageUtils = new BmomManageUtils(true);
        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(userName.getText().toString().trim());
        bmobUser.setPassword(password.getText().toString().trim());
        bmobUser.setMobilePhoneNumber(userName.getText().toString().trim());
        bmomManageUtils.registerUser(bmobUser);
    }
}

