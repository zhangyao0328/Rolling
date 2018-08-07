package com.rollling.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author zhangyao
 * @date 2018/8/7  19:29
 * @E-mail android_n@163.com
 */
public class RollingViewPage extends ViewPager{

    public boolean isCanScroll=true;
    public RollingViewPage(Context context) {
        this(context,null);
    }
    public RollingViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean isCanScroll){
        this.isCanScroll=isCanScroll;
    }
    //第一种
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!isCanScroll){
            return false;
        }
        return super.onTouchEvent(ev);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if(!isCanScroll){
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }
    //第二种
//    @Override
//    public void scrollTo(int x, int y) {
//        if(isCanScroll){
//            super.scrollTo(x, y);
//        }
//    }

}
