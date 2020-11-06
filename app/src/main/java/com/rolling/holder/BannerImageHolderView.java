package com.rolling.holder;

import android.view.View;
import android.widget.FrameLayout;

import com.bigkoo.convenientbanner.holder.Holder;
import com.rolling.R;
import com.rolling.act.main.frament.HomeFragment;
import com.rolling.bean.MainBannerBean;
import com.rolling.view.FrescoImage;
import com.rolling.view.TextViewIcon;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author zhangyao
 * @date 2018/8/4  23:19
 * @E-mail android_n@163.com
 */
public class BannerImageHolderView extends Holder<MainBannerBean> {

    @BindView(R.id.imageBanner)
    FrescoImage frescoImage;

    @BindView(R.id.frescoImage)
    FrescoImage head;

    @BindView(R.id.userName)
    TextViewIcon userName;

    public BannerImageHolderView(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        frescoImage.setLayoutParams(new FrameLayout.LayoutParams(HomeFragment.bannerW, HomeFragment.bannerH));
    }

    @Override
    protected void initView(View itemView) {
    }

    @Override
    public void updateUI(MainBannerBean data) {
        frescoImage.setImageURL(data.getUrl());
        head.setImageURL(data.getHeadUrl());
        userName.setTexts(data.getUsername());
    }
}
