package com.rolling.act.main.frament.home;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.rolling.R;
import com.rolling.act.fragment.RecyclerViewFragment;
import com.rolling.act.location.LocationActivity;
import com.rolling.base.view.BaseTab3Fragment;
import com.rolling.bean.tab.TopTabBean;
import com.rolling.bean.tab.TopTabListBean;
import com.rolling.net.HttpConfig;
import com.rolling.util.OpenAcitivtyUtils;
import com.rolling.view.tab.CineTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zhangyao
 * @date 2018/8/4  00:51
 * @E-mail android_n@163.com
 * 首页
 */
public class HomeFragment extends BaseTab3Fragment {

    private final int API_HOME_TABS = 1001;

    @BindView(R.id.cineTabLayout)
    CineTabLayout cineTabLayout;

    public List<Fragment> fragmentList = new ArrayList<>();

    public static int bannerW, bannerH;


    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {
        getBannerData();
        getHomeTabData();
    }

    @Override
    public void succeed(Object t, int tag) {
        switch (tag) {
            case API_HOME_TABS:
                TopTabListBean topTabBeans = JSON.parseObject(t.toString(), TopTabListBean.class);
                if (topTabBeans != null) {

                    for (TopTabBean baseDataBean : topTabBeans.getData()) {
                        if(baseDataBean.getName().contains("赛事")){
                            EventFragment eventFragment = new EventFragment();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(EventFragment.class.getName(), baseDataBean);
                            eventFragment.setArguments(bundle);
                            fragmentList.add(eventFragment);
                        }else {
                            RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(RecyclerViewFragment.class.getName(), baseDataBean);
                            recyclerViewFragment.setArguments(bundle);
                            fragmentList.add(recyclerViewFragment);
                        }
                    }
                    setViewPager(fragmentList, topTabBeans.getData());
                }
                break;
        }
    }

    @Override
    public void error(Object t, int tag) {

    }

    @Override
    public void responseCode(int code) {

    }

    private void initBanner() {
//        convenientBanner.setPages(new CBViewHolderCreator() {
//            @Override
//            public BannerImageHolderView createHolder(View itemView) {
//                BannerImageHolderView bannerImageHolderView = new BannerImageHolderView(itemView);
//                return bannerImageHolderView;
//            }
//
//            @Override
//            public int getLayoutId() {
//                return R.layout.layout_main_banner;
//            }
//        }, localImages)
//                .setPageIndicator(new int[]{R.drawable.point_round_s, R.drawable.point_round_n})
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//        ;
    }

    private void getBannerData() {

    }

    @OnClick({R.id.tvLocation})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.tvLocation:
                OpenAcitivtyUtils.openAct(getContext(), LocationActivity.class);
                break;
        }
    }

    private void getHomeTabData() {
        String url = HttpConfig.URL_API_HOME_TABS;

        getLoad(url, null, null, API_HOME_TABS, true);
    }

}
