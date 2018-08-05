package com.rollling.act.main.frament;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.rollling.R;
import com.rollling.base.view.BaseFragment;
import com.rollling.bean.MainBannerBean;
import com.rollling.holder.BannerImageHolderView;
import com.rollling.util.ScreenUtils;
import com.rollling.util.ToastUtils;
import com.rollling.view.RollingBanner;
import com.rollling.view.TextViewIcon;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

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

    private ArrayList<MainBannerBean> localImages = new ArrayList<MainBannerBean>();

    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {

        //通知系统主动测试toolbar高度
        toolbar.measure(0,0);
        layoutBanner.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(), (int) (ScreenUtils.getScreenHeight()) / 4 * 3 - toolbar.getMeasuredHeight()));

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {
        getBannerData();
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

    private void initBanner() {
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public BannerImageHolderView createHolder(View itemView) {
                BannerImageHolderView bannerImageHolderView = new BannerImageHolderView(itemView);
                return bannerImageHolderView;
            }

            @Override
            public int getLayoutId() {
                return R.layout.layout_main_banner;
            }
        }, localImages)
                .setPageIndicator(new int[]{R.drawable.point_round_s, R.drawable.point_round_n})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
        ;
    }

    private void getBannerData() {
        BmobQuery<MainBannerBean> bmobQuery = new BmobQuery<MainBannerBean>();

        bmobQuery.findObjects(new FindListener<MainBannerBean>() {
            @Override
            public void done(List<MainBannerBean> list, BmobException e) {
                if (e == null) {
                    for (MainBannerBean bannerBean : list) {
                        localImages.add(bannerBean);
                        titleBanner.setTexts(bannerBean.getTitle());
                    }
                    initBanner();
                } else {
                    ToastUtils.showShort(e.getMessage());
                }
            }
        });
    }
}
