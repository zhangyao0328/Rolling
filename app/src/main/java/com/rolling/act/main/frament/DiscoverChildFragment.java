package com.rolling.act.main.frament;

import android.support.v4.widget.SwipeRefreshLayout;

import com.rolling.R;
import com.rolling.base.view.BaseActivity;
import com.rolling.base.view.BaseFragment;
import com.rolling.net.HttpConfig;
import com.rolling.util.CineLog;
import com.rolling.util.LoginUtils;
import com.rolling.view.RlRecyclerView;

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

    @BindView(R.id.rlRecyclerView)
    RlRecyclerView rlRecyclerView;

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

//        String url = HttpConfig.URL_HOST +  "/sd/disk";
//
//        getLoad(url, null, null, 1001, false);

        LoginUtils.isLogin((BaseActivity) getActivity());

        String url = HttpConfig.URL_HOST +  "/irs/list";

        getLoad(url, null, null, 1002, false);

    }
}
