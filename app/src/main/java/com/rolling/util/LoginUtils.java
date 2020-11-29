package com.rolling.util;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.rolling.app.MyApplication;
import com.rolling.bean.user.UserLoginBean;
import com.rolling.util.sp.RollingSp;

/**
 * @author zhangyao
 * @date 2020/10/29  11:47
 * @E-mail android_n@163.com
 * 登录相关工具类
 */
public class LoginUtils {

    /**
     * 是否是有效登录用户
     *
     * @return
     */
    public static boolean isLogin(Context baseActivity) {

        if (MyApplication.userLoginBean == null) {
            String token = (String) RollingSp.getData(baseActivity, RollingSp.ROLLING_USER_LOGIN_DATA, "");
            if (!TextUtils.isEmpty(token)) {
                UserLoginBean bean = JSON.parseObject(token, UserLoginBean.class);
                MyApplication.userLoginBean = bean;
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

}
