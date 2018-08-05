package com.rollling.bean;

import cn.bmob.v3.BmobObject;

/**
 * @author zhangyao
 * @date 2017/11/19
 * @E-mail android_n@163.com
 */

public class BaseBean {

    public BaseBean(BmobObject object, int tag, boolean isShowLoading){
        setBmobObject(object);
        setTag(tag);
        setShowLoading(isShowLoading);
    }

    BmobObject bmobObject;

    int tag;

    boolean isShowLoading;

    public boolean isShowLoading() {
        return isShowLoading;
    }

    public void setShowLoading(boolean showLoading) {
        isShowLoading = showLoading;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public BmobObject getBmobObject() {
        return bmobObject;
    }

    public void setBmobObject(BmobObject bmobObject) {
        this.bmobObject = bmobObject;
    }
}
