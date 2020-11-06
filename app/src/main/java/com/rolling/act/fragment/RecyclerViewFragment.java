package com.rolling.act.fragment;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.rolling.R;
import com.rolling.act.main.frament.adapter.HomeAdapter;
import com.rolling.base.view.BaseFragment;
import com.rolling.bean.home.HomeDataBean;
import com.rolling.bean.tab.TopTabBean;
import com.rolling.view.RollingRecyclerView;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2020/11/3  12:05
 * @E-mail android_n@163.com
 */
public class RecyclerViewFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    private final int NET_GET_IRS_DATA = 1001;
    private final int NET_GET_IRS_DATA_LOADMORE = 1002;

    @BindView(R.id.swipeToLoadLayout)
    public SwipeToLoadLayout swipeToLoadLayout;

    @BindView(R.id.swipe_target)
    public RollingRecyclerView recyclerView;

    HomeAdapter adapter;

    TopTabBean topTabBean;

    @Override
    public int getLayoutContextView() {
        return R.layout.swipe_to_load_layout_new;
    }

    @Override
    public void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            topTabBean = (TopTabBean) bundle.getSerializable(this.getClass().getName());
        }

        recyclerView.initCineRecyclerViewNoDecoration(getContext());
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        adapter = new HomeAdapter(getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {
        swipeToLoadLayout.setRefreshing(true);
    }

    @Override
    public void succeed(Object o, int tag) {
        switch (tag) {
            case NET_GET_IRS_DATA:
                HomeDataBean homeDataBean = JSON.parseObject(o.toString(), HomeDataBean.class);
                if(adapter.getItemCount() > 0){
                    adapter.clearItems();
                }
                buildData(homeDataBean);
                break;
            case NET_GET_IRS_DATA_LOADMORE:
                HomeDataBean homeDataBeanMore = JSON.parseObject(o.toString(), HomeDataBean.class);
                buildData(homeDataBeanMore);
                break;
        }
        closeRecycler(swipeToLoadLayout);
    }

    @Override
    public void error(Object o, int tag) {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {
        if (topTabBean != null) {
            getData();
        }
    }

    private void getData() {
        getLoad(topTabBean.getLink(), null, null, 1002, false);
    }

    private void buildData(HomeDataBean homeDataBean) {
        adapter.addItems(homeDataBean.getData().getUserList());
    }
}
