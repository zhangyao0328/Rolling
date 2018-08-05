package com.rollling.bmom;

import com.rollling.R;
import com.rollling.admin.AdminUserConfig;
import com.rollling.app.MyApplication;
import com.rollling.bean.MyUser;
import com.rollling.util.LogUtils;
import com.rollling.util.ToastUtils;
import com.rollling.view.LoadingDialog;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author zhangyao
 * @date 2018/8/5  20:44
 * @E-mail android_n@163.com
 * 查询数据工具类
 */
public class BmomManageUtils {

    LoadingDialog loadingDialog;

    public BmomManageUtils(boolean isShowLoading){
        if(isShowLoading){
            if(loadingDialog == null){
                loadingDialog = new LoadingDialog(MyApplication.getInstance(), R.style.loadingDialog);
            }
            loadingDialog.isShowing();
        }
    }

    /**
     * 注册用户
     * @return
     */
    public void registerUser(final BmobUser bmobUser){
        if(bmobUser != null){
            bmobUser.signUp(new SaveListener<MyUser>() {
                @Override
                public void done(MyUser myUser, BmobException e) {
                    if(e == null){
                        login(bmobUser);
                    }else {
                        ToastUtils.showLong(e.getMessage());
                        LogUtils.e(e.getMessage());
                    }
                }
            });
        }
    }

    /**
     * 登录
     * @param bmobUser
     */
    public void login(BmobUser bmobUser){
        if(bmobUser != null){
            bmobUser.login(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                    if(e == null){
                        ToastUtils.showLong("登录成功");
                    }else {
                        ToastUtils.showLong("登录失败" + e.getMessage());
                        LogUtils.e("登录失败" + e.getMessage());
                    }
                }
            });
        }
    }


    public boolean findUser(){



        final boolean[] isUser = new boolean[1];

        BmobQuery<MyUser> bmobQuery = new BmobQuery<MyUser>();
        bmobQuery.addWhereEqualTo("userid", AdminUserConfig.USER_YAO);
        bmobQuery.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if(e == null){
                    LogUtils.e("有");
                    if(list != null){
                        isUser[0] = true;
                    }
                }else {
                    LogUtils.e("无");
                }
                loadingDialog.dismiss();
            }
        });
        return isUser[0];
    }
}
