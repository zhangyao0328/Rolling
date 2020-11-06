package com.rolling.bean.tab;

import com.rolling.bean.BaseDataBean;

import java.util.List;

/**
 * @author zhangyao
 * @date 2020/11/3  17:38
 * @E-mail android_n@163.com
 */
public class TopTabListBean extends BaseDataBean {

    List<TopTabBean> data;

    public List<TopTabBean> getData() {
        return data;
    }

    public void setData(List<TopTabBean> data) {
        this.data = data;
    }
}
