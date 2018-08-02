package com.rollling.base.prsenter;

import android.content.Context;

import com.rollling.bean.BaseBean;

/**
 * zhangyao
 * 2017/11/18
 * android_n@163.com
 * @author zhangyao
 */

public interface BasePresenter {

    void getAsync(Context context, BaseBean baseBean);

    void postAsync(Context context, BaseBean baseBean);
}
