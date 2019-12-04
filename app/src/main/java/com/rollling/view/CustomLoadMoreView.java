package com.rollling.view;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.q42.android.scrollingimageview.ScrollingImageView;
import com.rollling.R;

/**
 * @author zhangyao
 * @date 2018/8/10  17:18
 * @E-mail android_n@163.com
 */
public class CustomLoadMoreView extends LoadMoreView {

//    @BindView(R.id.scrolling_background)
    public ScrollingImageView scrollingBackground;
//
    public CustomLoadMoreView (Context context){
        View view = View.inflate(context, R.layout.layout_loadmore_rolling_view, null);
        scrollingBackground = view.findViewById(R.id.scrolling_background);
//        ButterKnife.bind(context, view);
    }

    @Override public int getLayoutId() {
        return R.layout.layout_loadmore_rolling_view;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override protected int getLoadEndViewId() {
        scrollingBackground.stop();
        return R.id.load_more_load_end_view;
    }


}
