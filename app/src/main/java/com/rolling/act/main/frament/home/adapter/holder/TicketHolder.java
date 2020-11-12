package com.rolling.act.main.frament.home.adapter.holder;

import android.view.View;
import android.widget.RelativeLayout;

import com.rolling.R;
import com.rolling.app.MyApplication;
import com.rolling.bean.home.HomeDataBean;
import com.rolling.util.AppUtils;
import com.rolling.view.FrescoImage;
import com.rolling.view.TextViewIcon;
import com.rolling.view.adapter.BaseViewHolder;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2020/11/10  11:19
 * @E-mail android_n@163.com
 * 票务
 */
public class TicketHolder extends BaseViewHolder<HomeDataBean.DataBean.UserListBean> {

    @BindView(R.id.frescoCover)
    FrescoImage frescoCover;

    @BindView(R.id.tvTitle)
    TextViewIcon tvTitle;

    @BindView(R.id.tvPrice)
    TextViewIcon tvPrice;

    int coverW,coverH;

    public TicketHolder(View itemView) {
        super(itemView);
        coverW = AppUtils.getScreenWidth(MyApplication.getInstance())/ 4;
        coverH = (int) (coverW * 1.5);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) frescoCover.getLayoutParams();
        layoutParams.width = coverW;
        layoutParams.height = coverH;
        frescoCover.setLayoutParams(layoutParams);
    }

    @Override
    public void buildData(HomeDataBean.DataBean.UserListBean userListBean) {
        frescoCover.setImageURL(userListBean.getCover());
        tvTitle.setText(userListBean.getName());
        tvPrice.setText("100");
    }



}
