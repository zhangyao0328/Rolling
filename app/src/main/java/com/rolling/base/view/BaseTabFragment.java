package com.rolling.base.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.rolling.R;

import java.util.List;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2018/8/7  19:06
 * @E-mail android_n@163.com
 */
public abstract class BaseTabFragment extends BaseFragment{

    @BindView(R.id.navigationTab)
    public NavigationTabStrip navigationTab;

    @BindView(R.id.viewPager)
    public ViewPager viewPager;

    public void setHeadView(int res){
        navigationTab.setTitles(getResources().getStringArray(res));
    }

    public void setHeadView(String[] res){
        navigationTab.setTitles(res);
    }

    public void setViewPager(final List<Fragment> fragmentList){

        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        navigationTab.setViewPager(viewPager);
    }
}
