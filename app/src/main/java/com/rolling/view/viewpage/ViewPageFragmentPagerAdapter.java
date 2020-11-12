package com.rolling.view.viewpage;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author zhangyao
 * @date 2018/10/26  11:43
 * @E-mail android_n@163.com
 */
public class ViewPageFragmentPagerAdapter extends FragmentPagerAdapter {


    private List<Fragment> returnFragment;

    public ViewPageFragmentPagerAdapter(FragmentManager fm, List<Fragment> returnFragment) {
        super(fm);
        this.returnFragment = returnFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return returnFragment.get(position);
    }

    @Override
    public int getCount() {
        return returnFragment.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView(returnFragment.get(position).getView());
    }
}