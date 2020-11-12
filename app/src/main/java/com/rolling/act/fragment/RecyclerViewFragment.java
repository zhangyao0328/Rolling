package com.rolling.act.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.rolling.R;
import com.rolling.act.main.frament.home.HomeAdapter;
import com.rolling.base.view.BaseFragment;
import com.rolling.bean.home.HomeDataBean;
import com.rolling.bean.tab.TopTabBean;
import com.rolling.util.CineLog;
import com.rolling.view.RlRecyclerView;

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
    public RlRecyclerView recyclerView;

    @BindView(R.id.layoutFilter)
    RelativeLayout layoutFilter;

    HomeAdapter adapter;

    TopTabBean topTabBean;

    private int mCurrentPosition = 0;

    int mSuspensionHeight = 0;

    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_recycler_view;
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


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspensionHeight = layoutFilter.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView rView, int dx, int dy) {
                super.onScrolled(rView, dx, dy);
                CineLog.e("dy=" + dy);
                if (adapter.getItemViewType(mCurrentPosition + 1) == adapter.TYPE_FILTER) {

                    View view = recyclerView.linearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                    if (view != null) {
                        if (view.getTop() <= mSuspensionHeight) {
                            layoutFilter.setY(-(mSuspensionHeight - view.getTop()));
                            CineLog.e("1");
                            if(dy < 0){

                            }
                        } else {
                            layoutFilter.setY(0);
                            CineLog.e("2");
                        }
                    }
                }

                if (mCurrentPosition != recyclerView.linearLayoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = recyclerView.linearLayoutManager.findFirstVisibleItemPosition();
                    layoutFilter.setY(0);

                    updateSuspensionBar();

                    CineLog.e("4");
                }

            }
        });
    }

    private void updateSuspensionBar() {

//        layoutFilter.setText("button " + mCurrentPosition);
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
                if (adapter.getItemCount() > 0) {
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
        closeRecycler(swipeToLoadLayout);
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

        HomeDataBean.DataBean.UserListBean userListBean = new HomeDataBean.DataBean.UserListBean();
        userListBean.setViewType(adapter.TYPE_FILTER);
        adapter.addItem(userListBean, 3);
    }
}
