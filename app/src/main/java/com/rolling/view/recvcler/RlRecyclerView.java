package com.rolling.view.recvcler;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.rolling.R;
import com.rolling.util.CineLog;

/**
 * @author zhangyao
 * @date 2020/11/3  17:26
 * @E-mail android_n@163.com
 */
public class RlRecyclerView extends RecyclerView {

    public LinearLayoutManager linearLayoutManager;

    public RlRecyclerView(Context context) {
        super(context);
        linearLayoutManager = new LinearLayoutManager(context);
    }

    public RlRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        linearLayoutManager = new LinearLayoutManager(context);
    }

    public void initCineRecyclerViewNoDecoration(Context context) {
        setLayoutManager(linearLayoutManager);
    }

    public void initCineRecyclerViewHorizontal10White(Context context) {
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(linearLayoutManager);
        addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL_LIST));
    }

    public void initCineRecyclerViewFlexboxLayoutManager(Context context) {
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        setLayoutManager(flexboxLayoutManager);
    }

    public void initCineRecyclerViewFlexboxLayoutManager2(Context context) {
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        setLayoutManager(flexboxLayoutManager);
    }

    public void initCineRecyclerViewGrid10White(Context context, int count) {
        setLayoutManager(new GridLayoutManager(context, count));
        addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.BOTH_SET, R.drawable.divider10_transparent_bg));
    }


    /**
     * list中 解决与CoordinatorLayout的滑动事件冲突
     */
    public void setViewShow(final SwipeToLoadLayout swipeToLoadLayout) {

        if (swipeToLoadLayout != null) {


            addOnScrollListener(new OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (!recyclerView.canScrollVertically(-1)) {
                        swipeToLoadLayout.requestFocus();
//                    swipeToLoadLayout.setRefreshing(true);
                    CineLog.e("顶部");
                        //顶部
                    } else if (!recyclerView.canScrollVertically(1)) {
                        //底部
                        swipeToLoadLayout.setLoadingMore(true);
                    CineLog.e("底部");
//                        50是手动加的滑动生效距离，防止滑动1像素view就抖动
                    }
                }
            });
        }
    }



}
