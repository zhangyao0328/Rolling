package com.rollling.bean.user;

/**
 * @author zhangyao
 * @date 2019-07-30  16:56
 * @E-mail android_n@163.com
 * 登录
 */
public class UserSginInBean {


    /**
     * code : 0
     * message : OK
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1NjQ0NzY2OTAsImlkIjo3LCJuYmYiOjE1NjQ0NzY2OTAsInVzZXJuYW1lIjoiMSJ9.P_p-ya--cm4fBAS1a33_MfTXg-adDVXILmGVB7rL668"}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1NjQ0NzY2OTAsImlkIjo3LCJuYmYiOjE1NjQ0NzY2OTAsInVzZXJuYW1lIjoiMSJ9.P_p-ya--cm4fBAS1a33_MfTXg-adDVXILmGVB7rL668
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
