package com.rolling.event;

/**
 * @author zhangyao
 * @date 2/2/21  11:54 AM
 * @E-mail android_n@163.com
 * 更新用户信息
 */
public class UploadUserInfoEvent {

    public UploadUserInfoEvent(boolean isS){
        setSucceed(isS);
    }

    boolean isSucceed;

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }
}
