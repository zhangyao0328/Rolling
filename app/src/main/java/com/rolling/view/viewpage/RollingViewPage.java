package com.rolling.view.viewpage;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author zhangyao
 * @date 2018/9/7  18:13
 * @E-mail android_n@163.com
 * 控制是否可以滑动的viewpage
 */
public class RollingViewPage extends ViewPager {

    private static final int MOVE_LIMITATION = 200;// 触发移动的像素距离

    private float mLastMotionX; // 手指触碰屏幕的最后一次x坐标

    public boolean isCanScroll = true;

    public RollingViewPage(Context context) {
        this(context, null);
    }

    public RollingViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    //第一种
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        final float x = ev.getX();

        if (!isCanScroll) {
            return false;
        }

//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mLastMotionX = x;
//                break;
//            case MotionEvent.ACTION_UP:
//                if (Math.abs(x - mLastMotionX) < MOVE_LIMITATION) {
//                    return false;
//                }
//                break;
//        }

        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!isCanScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

}
