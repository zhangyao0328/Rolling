package com.rolling.view.base;

import android.view.View;

import com.rolling.view.adapter.BaseViewHolder;

import butterknife.ButterKnife;

/**
 * @author zhangyao
 * @date 2020/10/30  11:24
 * @E-mail android_n@163.com
 */
public class RollingBaseViewHolder extends BaseViewHolder {
    public RollingBaseViewHolder(View view) {
        super(view);
        ButterKnife.bind(view);
    }
}
