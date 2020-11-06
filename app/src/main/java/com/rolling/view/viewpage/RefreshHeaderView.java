package com.rolling.view.viewpage;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * @author zhangyao
 * @date 2017/11/24:01:32
 * @E-mail android_n@163.com
 */

public class RefreshHeaderView extends AppCompatTextView implements SwipeRefreshTrigger, SwipeTrigger {

    public RefreshHeaderView(Context context) {
        super(context);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onRefresh() {
        setText("REFRESHING");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled >= getHeight()) {
                setText("RELEASE TO REFRESH");
            } else {
                setText("SWIPE TO REFRESH");
            }
        } else {
            setText("REFRESH RETURNING");
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        setText("COMPLETE");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
