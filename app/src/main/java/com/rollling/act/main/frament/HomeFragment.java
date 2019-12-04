package com.rollling.act.main.frament;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.rollling.R;
import com.rollling.base.view.BaseFragment;
import com.rollling.util.ScreenUtils;
import com.rollling.view.RollingBanner;
import com.rollling.view.TextViewIcon;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zhangyao
 * @date 2018/8/4  00:51
 * @E-mail android_n@163.com
 * 首页-每日推荐
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.convenientBanner)
    RollingBanner convenientBanner;

    @BindView(R.id.layoutBanner)
    View layoutBanner;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.titleBanner)
    TextViewIcon titleBanner;

    public static int bannerW, bannerH;

    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {

        //通知系统主动测试toolbar高度
        toolbar.measure(0,0);
        bannerW = ScreenUtils.getScreenWidth();
        bannerH = (ScreenUtils.getScreenHeight()) / 4 * 3 - toolbar.getMeasuredHeight();
        layoutBanner.setLayoutParams(new LinearLayout.LayoutParams(bannerW, bannerH));

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {
        getBannerData();
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

    @OnClick({R.id.tbLeft})
    public void onClicks(View view){
        switch (view.getId()){
            case R.id.tbLeft:
                break;
        }
    }

}
