package com.rolling.act.main.frament.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.rolling.R;
import com.rolling.act.main.MainActivity;
import com.rolling.act.main.frament.home.adapter.holder.SportFilterAdapter;
import com.rolling.base.view.BaseFragment;
import com.rolling.bean.home.HomeDataBean;
import com.rolling.bean.tab.TopTabBean;
import com.rolling.bean.tab.TopTabListBean;
import com.rolling.net.HttpConfig;
import com.rolling.util.AppUtils;
import com.rolling.view.recvcler.RlRecyclerView;
import com.rolling.view.banner.Config;
import com.rolling.view.banner.ImageAdapter;
import com.rolling.view.layout.FilterView;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.util.BannerUtils;

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

    @BindView(R.id.appBarLayout)
    public AppBarLayout appBarLayout;

    @BindView(R.id.collapsingToolbarLayout)
    public CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.banner)
    Banner banner;

    ViewStub viewStubFilter;

    RlRecyclerView recyclerViewFilter;

    RlRecyclerView recyclerViewSort;

    public RelativeLayout rootFilterContent;

    HomeAdapter adapter;

    public SportFilterAdapter sportFilterAdapter;

    public SportFilterAdapter sportSortAdapter;

    TopTabBean topTabBean;

    TopTabListBean sportTypeBean;

    HomeDataBean homeDataBean;

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

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                int position = layoutManager.findFirstVisibleItemPosition();
//                CineLog.d("位置信息"+position);
            }
        });
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
                homeDataBean = JSON.parseObject(o.toString(), HomeDataBean.class);
                if (adapter.getItemCount() > 0) {
                    adapter.clearItems();
                }
                buildData();
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

    private void buildData() {
        adapter.addItems(this.homeDataBean.getData().getUserList());

        initBanner();
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

    @Override
    public void onStart() {
        super.onStart();
        banner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        banner.destroy();
    }

    private void initBanner() {

        ImageAdapter imageAdapter = new ImageAdapter(homeDataBean.getData().getUserList().subList(0, 4));

        banner.setAdapter(imageAdapter);
        banner.setLoopTime(Config.LOOP_TIME);
        banner.setScrollTime(Config.SCROLL_TIME);
        banner.setIndicator(new RectangleIndicator(getContext()));
        banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
        banner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(12));
        banner.setIndicatorSpace((int) BannerUtils.dp2px(4));
        banner.setIndicatorRadius(0);

        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        int w = AppUtils.getScreenWidth(getContext());
        layoutParams.width = w;
        layoutParams.height = w / 4 * 3;
        banner.setLayoutParams(layoutParams);
    }


}
