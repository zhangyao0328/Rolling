package com.rollling.bean.main;

import cn.bmob.v3.BmobObject;

/**
 * @author zhangyao
 * @date 2018/8/4  12:12
 * @E-mail android_n@163.com
 */
public class TestBean extends BmobObject{

    private String name;
    private String address;
    UserBean userBean;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class UserBean{

        private String nickName;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
