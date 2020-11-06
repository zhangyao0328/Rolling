package com.rolling.bean.location;

import com.rolling.bean.BaseDataBean;

import java.util.List;

/**
 * @author zhangyao
 * @date 2020/11/5  14:31
 * @E-mail android_n@163.com
 */
public class CityBeanResponse extends BaseDataBean {

    List<CityBean> data;

    public List<CityBean> getData() {
        return data;
    }

    public void setData(List<CityBean> data) {
        this.data = data;
    }
}
