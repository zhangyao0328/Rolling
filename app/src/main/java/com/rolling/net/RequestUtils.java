package com.rolling.net;

import android.content.Context;

import com.rolling.base.prsenter.BasePresenterImpl;
import com.rolling.base.view.BaseView;
import com.rolling.bean.BaseBean;

/**
 * @author zhangyao
 * @date 2020/11/3  16:55
 * @E-mail android_n@163.com
 */
public class RequestUtils {

    public static void postLoad(Context context, BasePresenterImpl basePresenter, BaseView baseView, BaseBean baseBean) {
        if (baseBean != null) {
            if (basePresenter == null) {
                basePresenter = new BasePresenterImpl(context, baseView);
            }
            basePresenter.postAsync(context, baseBean);
        }
    }
}
