package com.rolling.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author zhangyao
 * @date 2020/11/3  17:26
 * @E-mail android_n@163.com
 */
public class RollingRecyclerView extends RecyclerView {

    public LinearLayoutManager linearLayoutManager;

    public RollingRecyclerView(Context context) {
        super(context);
        linearLayoutManager = new LinearLayoutManager(context);
    }

    public RollingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        linearLayoutManager = new LinearLayoutManager(context);
    }

    public void initCineRecyclerViewNoDecoration(Context context) {
        setLayoutManager(linearLayoutManager);
    }

    public void initCineRecyclerViewHorizontal10White(Context context) {
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(linearLayoutManager);
        addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }
}
