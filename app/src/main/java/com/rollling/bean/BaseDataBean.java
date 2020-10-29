package com.rollling.bean;

/**
 * @author zhangyao
 * @date 2020/10/28  15:15
 * @E-mail android_n@163.com
 */
public class BaseDataBean {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseDataBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
