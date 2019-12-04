package com.rollling.act.main.frament;

import android.support.v4.app.Fragment;

import com.rollling.R;
import com.rollling.base.view.BaseTabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyao
 * @date 2018/8/4  00:59
 * @E-mail android_n@163.com
 */
public class DiscoverFragment extends BaseTabFragment {
    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_discover;
    }

    @Override
    public void init() {

        setHeadView(R.array.my_discover_tab);
        List<Fragment> fragmentList = new ArrayList<>();
        for (String s : getResources().getStringArray(R.array.my_discover_tab)){
            fragmentList.add(new DiscoverChildFragment());
        }
        setViewPager(fragmentList);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {

    }

    @Override
    public void succeed(Object t, int tag) {

    }

    @Override
    public void error(Object t, int tag) {

    }

    @Override
    public void responseCode(int code) {

    }
}
