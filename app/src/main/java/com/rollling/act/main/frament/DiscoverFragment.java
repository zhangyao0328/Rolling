package com.rollling.act.main.frament;

import com.rollling.R;
import com.rollling.base.view.BaseFragment;
import com.rollling.bean.main.TestBean;

/**
 * @author zhangyao
 * @date 2018/8/4  00:59
 * @E-mail android_n@163.com
 */
public class DiscoverFragment extends BaseFragment {
    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_discover;
    }

    @Override
    public void init() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {

        TestBean testBean = new TestBean();
        testBean.setName("YAOYAOYAOYAO");
        testBean.setAddress("内蒙古呼和浩特");
        TestBean.UserBean userBean = new TestBean.UserBean();
        userBean.setNickName("二十二");
        testBean.setUserBean(userBean);

        savaData(testBean, 111, true);
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
