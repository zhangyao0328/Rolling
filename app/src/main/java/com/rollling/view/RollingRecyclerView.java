package com.rollling.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author zhangyao
 * @date 2018/8/7  00:05
 * @E-mail android_n@163.com
 */
public class RollingRecyclerView extends RecyclerView{

    public RollingRecyclerView(Context context) {
        super(context);
    }

    public RollingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void initRecyclerView(Context context){
        setLayoutManager(new LinearLayoutManager(context));
    }
}
