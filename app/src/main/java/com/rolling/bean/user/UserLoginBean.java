package com.rolling.bean.user;

import com.rolling.bean.BaseDataBean;

/**
 * @author zhangyao
 * @date 2019-07-30  16:56
 * @E-mail android_n@163.com
 * 登录
 */
public class UserLoginBean extends BaseDataBean {


    /**
     * code : 0
     * message : OK
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2MDM3MTg4NTQsImlkIjowLCJuYmYiOjE2MDM3MTg4NTQsInVzZXJuYW1lIjoiMTIzNDU2In0.c2_52OznSbTm2FOsRS14mJgRW_vs-pA-ePOUGYCcv4E"}
     */


    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2MDM3MTg4NTQsImlkIjowLCJuYmYiOjE2MDM3MTg4NTQsInVzZXJuYW1lIjoiMTIzNDU2In0.c2_52OznSbTm2FOsRS14mJgRW_vs-pA-ePOUGYCcv4E
         */

        private String token;

        private String username;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
