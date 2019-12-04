package com.rollling.bean.user;

/**
 * @author zhangyao
 * @date 2019-07-30  16:10
 * @E-mail android_n@163.com
 */
public class UserSginUpBean {


    /**
     * code : 20103
     * message : The token was invalid.
     * data : null
     */

    private int code;
    private String message;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
