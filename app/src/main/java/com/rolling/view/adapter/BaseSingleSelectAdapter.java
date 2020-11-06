package com.rolling.view.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;

/**
 * @author zhangyao
 * @date 2017/12/4:18:38
 * @E-mail android_n@163.com
 * 单选
 */

public abstract class BaseSingleSelectAdapter<T> extends BaseRecyclerAdapter<T> {

    public OnItemClickListener onItemClickListener;

    CurrentSelectI currentSelectI;

    public int mCurrentSelect = -1;
    public boolean isEnableSelect = true;
    //选择后,默认加载数据
    public boolean isLoad = true;

   public ViewPager viewPager;

    public BaseSingleSelectAdapter(Context context) {
        super(context);
    }

    public boolean isSelectDate() {
        return mCurrentSelect >= 0;
    }

    public void setEnableSelect(boolean isEnableSelect) {
        this.isEnableSelect = isEnableSelect;
    }

    public int getCurrentSelect() {
        return mCurrentSelect;
    }

    public void setCurrentSelect(int currentSelect) {
        notifyItemChanged(mCurrentSelect);
        isLoad = true;
        this.mCurrentSelect = currentSelect;
        notifyItemChanged(mCurrentSelect);
//        setViewPageSelect();
        if(currentSelectI != null){
            currentSelectI.onCurrentSelect(mCurrentSelect);
        }
    }

    public void setCurrentSelect(int currentSelect, boolean isLoads) {
        notifyItemChanged(mCurrentSelect);
        this.mCurrentSelect = currentSelect;
        this.isLoad = isLoads;
        notifyItemChanged(mCurrentSelect);
//        setViewPageSelect();
        if(currentSelectI != null){
            currentSelectI.onCurrentSelect(mCurrentSelect);
        }
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position != mCurrentSelect){
                    setCurrentSelect(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setViewPageSelect(){
        if(getViewPager() != null){
            if(mCurrentSelect != viewPager.getCurrentItem()){
                viewPager.setCurrentItem(mCurrentSelect);
            }
        }
    }

//    public void setOnClicks(View view, final int position){
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setCurrentSelect(position);
//                if(onItemClickListener != null){
//                    onItemClickListener.onItemClick(view, position);
//                }
//            }
//        });
//    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setCurrentSelectI(CurrentSelectI currentSelectI) {
        this.currentSelectI = currentSelectI;
    }

    public interface CurrentSelectI{
        void onCurrentSelect(int pos);
    }
}
