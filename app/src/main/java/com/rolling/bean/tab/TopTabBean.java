package com.rolling.bean.tab;

import java.io.Serializable;

/**
 * @author zhangyao
 * @date 2020/11/3  11:35
 * @E-mail android_n@163.com
 */
public class TopTabBean implements Serializable {

    private String name;

    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



