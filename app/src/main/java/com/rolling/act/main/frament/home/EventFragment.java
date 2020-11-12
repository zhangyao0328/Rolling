package com.rolling.act.main.frament.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.rolling.R;
import com.rolling.act.main.MainActivity;
import com.rolling.act.main.frament.home.adapter.holder.SportFilterAdapter;
import com.rolling.base.view.BaseFragment;
import com.rolling.bean.home.HomeDataBean;
import com.rolling.bean.tab.TopTabBean;
import com.rolling.bean.tab.TopTabListBean;
import com.rolling.net.HttpConfig;
import com.rolling.view.RlRecyclerView;
import com.rolling.view.layout.FilterView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2020/11/6  16:06
 * @E-mail android_n@163.com
 * 赛事
 */
public class EventFragment extends BaseFragment {
//
//    @BindView(R.id.convenientBanner)
//    RollingBanner convenientBanner;

    private final int NET_GET_IRS_DATA = 1001;
    private final int NET_GET_SPORT_TYPE = 1002;


    @BindView(R.id.rlRecyclerView)
    public RlRecyclerView recyclerView;

    @BindView(R.id.filterView)
    FilterView filterView;

    ViewStub viewStubFilter;

    RlRecyclerView recyclerViewFilter;

    RlRecyclerView recyclerViewSort;

    public RelativeLayout rootFilterContent;

    HomeAdapter adapter;

    public SportFilterAdapter sportFilterAdapter;

    public SportFilterAdapter sportSortAdapter;

    TopTabBean topTabBean;

    TopTabListBean sportTypeBean;

    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_event;
    }

    @Override
    public void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            topTabBean = (TopTabBean) bundle.getSerializable(this.getClass().getName());
        }

        recyclerView.initCineRecyclerViewNoDecoration(getContext());
        adapter = new HomeAdapter(getContext());
        recyclerView.setAdapter(adapter);

        filterView.setData(this);
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {
        getData(NET_GET_IRS_DATA);
        getSportTypeData();
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
            case NET_GET_SPORT_TYPE:
                sportTypeBean = JSON.parseObject(o.toString(), TopTabListBean.class);
                break;
        }

    }

    @Override
    public void error(Object o, int tag) {

    }

    private void getData(int tag) {
        getLoad(topTabBean.getLink(), null, null, tag, false);
    }

    private void getSportTypeData() {
        getLoad(HttpConfig.URL_API_SPORT_TYPE, null, null, NET_GET_SPORT_TYPE, false);
    }

    private void buildData(HomeDataBean homeDataBean) {
        adapter.addItems(homeDataBean.getData().getUserList());
    }

    public void initFilterView(int id) {
        if (viewStubFilter == null) {
            viewStubFilter = getView().findViewById(R.id.viewStubFilter);
            View viewS = viewStubFilter.inflate();
            rootFilterContent = viewS.findViewById(R.id.rootFilterContent);
            if (rootFilterContent != null) {
                recyclerViewFilter = (RlRecyclerView) rootFilterContent.getChildAt(0);
                recyclerViewSort = (RlRecyclerView) rootFilterContent.getChildAt(1);
            }
        }
        if (recyclerViewFilter != null && recyclerViewSort != null) {
            ((MainActivity) getActivity()).rootContentBg.setVisibility(View.VISIBLE);
            ((MainActivity) getActivity()).rootContentBg.setOnClickListener(filterBgOnclick);
            rootFilterContent.setOnClickListener(filterBgOnclick);
            rootFilterContent.setVisibility(View.VISIBLE);

            switch (id) {
                case 0:
                    if (sportFilterAdapter == null) {
                        sportFilterAdapter = new SportFilterAdapter(getContext());
                        if (sportTypeBean != null) {
                            sportFilterAdapter.addItems(sportTypeBean.getData());
                        }
                        recyclerViewFilter.initCineRecyclerViewFlexboxLayoutManager2(getContext());
                        filterView.setAdapterOnClick();
                    }
                    recyclerViewFilter.setAdapter(sportFilterAdapter);
                    recyclerViewFilter.setVisibility(View.VISIBLE);
                    recyclerViewSort.setVisibility(View.GONE);
                    break;
                case 1:
                    if (sportSortAdapter == null) {
                        sportSortAdapter = new SportFilterAdapter(getContext());
                        recyclerViewSort.initCineRecyclerViewNoDecoration(getContext());
                        String[] strs = getResources().getStringArray(R.array.home_filter_sort_list);
                        List<TopTabBean> topTabBeans = new ArrayList<>();
                        for (String str : strs) {
                            TopTabBean topTabBean = new TopTabBean();
                            topTabBean.setName(str);
                            topTabBeans.add(topTabBean);
                        }
                        sportSortAdapter.addItems(topTabBeans);
                        filterView.setAdapterOnClick();
                    }
                    recyclerViewSort.setAdapter(sportSortAdapter);
                    recyclerViewFilter.setVisibility(View.GONE);
                    recyclerViewSort.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    View.OnClickListener filterBgOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismissFilter();
        }
    };

    public void dismissFilter() {
        rootFilterContent.setVisibility(View.GONE);
        ((MainActivity) getActivity()).rootContentBg.setVisibility(View.GONE);
    }


}
