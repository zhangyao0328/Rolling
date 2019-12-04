package com.rollling.act.main.frament;

import android.support.v4.widget.SwipeRefreshLayout;

import com.rollling.R;
import com.rollling.base.view.BaseFragment;
import com.rollling.util.CineLog;
import com.rollling.view.RollingRecyclerView;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2018/8/7  19:19
 * @E-mail android_n@163.com
 * 首页-发现-子类
 */
public class DiscoverChildFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rollingRecyclerView)
    RollingRecyclerView rollingRecyclerView;

    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_discover_child;
    }

    @Override
    public void init() {

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {
    }

    @Override
    public void succeed(Object t, int tag) {
        CineLog.e(t.toString());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void error(Object t, int tag) {
        CineLog.e(t.toString());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void responseCode(int code) {
        CineLog.e(String.valueOf(code));
    }

    @Override
    public void onRefresh() {

        String url = "http://192.168.13.29:8080/sd/disk";

        getLoad(url, null, null, 1001, false);

    }
}
