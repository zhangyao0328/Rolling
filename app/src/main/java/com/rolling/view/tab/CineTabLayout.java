package com.rolling.view.tab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.rolling.bean.tab.TopTabBean;
import com.rolling.util.AppUtils;
import com.rolling.util.CineLog;
import com.rolling.view.RollingRecyclerView;
import com.rolling.view.adapter.BaseSingleSelectAdapter;
import com.rolling.view.adapter.OnItemClickListener;

import java.util.List;

/**
 * @author zhangyao
 * @date 2019-08-11  19:50
 * @E-mail android_n@163.com
 */
public class CineTabLayout extends RollingRecyclerView implements OnItemClickListener, BaseSingleSelectAdapter.CurrentSelectI {

    Context mContext;

    CineTabLayoutAdapter adapter;

    public CineTabLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public CineTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context);
    }

    private void initView(Context context) {

        initCineRecyclerViewHorizontal10White(context);
        setClipChildren(false);
    }

    public void setViewPager(ViewPager viewPager, List<TopTabBean> array, int position) {
        adapter = new CineTabLayoutAdapter(mContext, viewPager);
        adapter.addItems(array);
        adapter.setCurrentSelect(position);
        adapter.setCurrentSelectI(this);
        adapter.setOnItemClickListener(this);
        setAdapter(adapter);
    }

    @Override
    public CineTabLayoutAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onItemClick(View view, int position) {
    }


    private void setTabClikData(int position) {

    }

    @Override
    public void onCurrentSelect(final int position) {
        setTabClikData(position);

        if (linearLayoutManager != null) {
            View view = linearLayoutManager.findViewByPosition(position);
            if (view != null) {
                int x = AppUtils.getScreenWidth(getContext()) / 10;
                if (view.getX() <= 15) {
                    if (position > 0) {
                        scrollToPosition(position - 1);
                    } else {
                        scrollToPosition(position);
                    }
                } else if (view.getX() > x) {
                    if (adapter.getItemCount() - 1 > position) {
                        scrollToPosition(position + 1);
                    }
                    CineLog.e("移动");
                }
            }
        }

    }
}
