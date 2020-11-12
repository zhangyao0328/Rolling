package com.rolling.view.banner;

import android.view.View;

import com.rolling.bean.home.HomeDataBean;
import com.rolling.view.FrescoImage;
import com.rolling.view.adapter.BaseViewHolder;

public class ImageHolder extends BaseViewHolder<HomeDataBean.DataBean.UserListBean> {
    public FrescoImage imageView;

    public ImageHolder(View itemView) {
        super(itemView);
        imageView = (FrescoImage) itemView;
    }


    @Override
    public void buildData(HomeDataBean.DataBean.UserListBean userListBean) {
        imageView.setImageURL(userListBean.getCover());
    }


}