package com.rolling.base.view;

/**
 * @author zhangyao
 * 2017/11/18
 * android_n@163.com
 */

public interface BaseView<T> {

    void succeed(T t, int tag);

    void error(T t, int tag);

    void responseCode(int code);
}
