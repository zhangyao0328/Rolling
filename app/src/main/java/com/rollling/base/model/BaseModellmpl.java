package com.rollling.base.model;

import android.content.Context;

import com.rollling.R;
import com.rollling.base.view.BaseView;
import com.rollling.bean.BaseBean;
import com.rollling.util.LogUtils;
import com.rollling.util.ToastUtils;
import com.rollling.view.LoadingDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author zhangyao
 * 2017/11/18
 * android_n@163.com
 */

public class BaseModellmpl implements BaseModel {

    BaseView mBaseView;

    LoadingDialog loadingDialog;

    public BaseModellmpl(Context context, BaseView baseView) {
        this.mBaseView = baseView;
        loadingDialog = new LoadingDialog(context, R.style.loadingDialog);
    }

    @Override
    public void saveData(Context context, final BaseBean baseBean) {

        if (baseBean.isShowLoading()) {
            if(!loadingDialog.isShowing()){
                loadingDialog.show();
            }
        }
        baseBean.getBmobObject().save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    LogUtils.e("添加数据成功，返回objectId为：" + s);
                    mBaseView.succeed(s, baseBean.getTag());
                } else {
                    LogUtils.e(e.getMessage());
                    ToastUtils.showShort(e.getMessage());
                    mBaseView.error(e.getMessage(), baseBean.getTag());
                }

                loadingDialog.dismiss();
            }
        });
    }

}
