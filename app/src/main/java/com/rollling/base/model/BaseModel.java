package com.rollling.base.model;

import android.content.Context;

import com.rollling.bean.BaseBean;

/**
 * @author zhangyao
 * 2017/11/18
 * android_n@163.com
 */

public interface BaseModel {

    void loadGet(Context context, BaseBean baseBean);

    void loadPost(Context context, BaseBean baseBean);
}
