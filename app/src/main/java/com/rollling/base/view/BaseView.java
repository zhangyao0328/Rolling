package com.rollling.base.view;

/**
 * @author zhangyao
 * 2017/11/18
 * android_n@163.com
 */

public interface BaseView<T> {

    void succeed(String t, int tag);

    void error(String t, int tag);

    void responseCode(int code);
}
