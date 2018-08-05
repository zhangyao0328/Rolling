package com.rollling.act.main.frament;

import com.rollling.R;
import com.rollling.base.view.BaseFragment;
import com.rollling.bean.main.TestBean;

/**
 * @author zhangyao
 * @date 2018/8/4  01:02
 * @E-mail android_n@163.com
 */
public class MessageFragment extends BaseFragment {
    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_message;
    }

    @Override
    public void init() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {
        getData(new TestBean(), "05f07c7133");
    }

    @Override
    public void succeed(String t, int tag) {

    }

    @Override
    public void error(String t, int tag) {

    }

    @Override
    public void responseCode(int code) {

    }
}
