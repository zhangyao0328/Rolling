package com.rolling.act.main.frament.adapter.holder;

import android.view.View;

import com.rolling.R;
import com.rolling.bean.home.HomeDataBean;
import com.rolling.view.TextViewIcon;
import com.rolling.view.adapter.BaseViewHolder;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2020/11/4  12:32
 * @E-mail android_n@163.com
 */
public class IrsHolder extends BaseViewHolder {

    @BindView(R.id.userName)
    TextViewIcon userName;

    public IrsHolder(View itemView) {
        super(itemView);
    }

    public void buildData(HomeDataBean.DataBean.UserListBean bean){
        userName.setText(bean.getName());
    }
}
