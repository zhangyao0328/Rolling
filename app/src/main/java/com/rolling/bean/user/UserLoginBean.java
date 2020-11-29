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
         * id : 1
         * username : 1111
         * password : $2a$10$w.WdK5vVAfrffa2k7VMbFeixn/.VeMaa6/.9cMovDrzks3mL5X2Ze
         * Type : 0
         * openId :
         * nickName :
         * gender : 0
         * city :
         * province :
         * country :
         * avatarUrl :
         * unionId :
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDU1MDg5NjAsImlhdCI6MTYwNTUwNTM2MCwiaWQiOjAsIm5iZiI6MTYwNTUwNTM2MCwidXNlcm5hbWUiOiIxMTExIn0.G5nPnh8H17PnOV4W9nncrKH24Kq95NX0hu9P93dCBfU
         */

        private int id;
        private String username;
        private String password;
        private int Type;
        private String openId;
        private String nickName;
        private int gender;
        private String city;
        private String province;
        private String country;
        private String avatarUrl;
        private String unionId;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getUnionId() {
            return unionId;
        }

        public void setUnionId(String unionId) {
            this.unionId = unionId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
        /**
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2MDM3MTg4NTQsImlkIjowLCJuYmYiOjE2MDM3MTg4NTQsInVzZXJuYW1lIjoiMTIzNDU2In0.c2_52OznSbTm2FOsRS14mJgRW_vs-pA-ePOUGYCcv4E
         */


    }
}
