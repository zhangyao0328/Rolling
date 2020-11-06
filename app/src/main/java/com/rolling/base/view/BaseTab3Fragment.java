package com.rolling.base.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.rolling.R;
import com.rolling.app.MyApplication;
import com.rolling.bean.tab.TopTabBean;
import com.rolling.view.tab.CineTabLayout;
import com.rolling.view.viewpage.RollingViewPage;
import com.rolling.view.viewpage.ViewPageFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2019-08-06  12:39
 * @E-mail android_n@163.com
 */
public abstract class BaseTab3Fragment extends BaseFragment {

    @BindView(R.id.cineTabLayout)
    public CineTabLayout cineTabLayout;

    @BindView(R.id.viewPager)
    public RollingViewPage viewPager;

    public String[] tabList;

    public void setViewPager(final List<Fragment> fragmentList, int array) {

        FragmentPagerAdapter pagerAdapter = new ViewPageFragmentPagerAdapter(getChildFragmentManager(), fragmentList);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        tabList = MyApplication.getInstance().getResources().getStringArray(array);
        cineTabLayout.setViewPager(viewPager, arrayToList(tabList), 0);
    }

    public void setViewPager(final List<Fragment> fragmentList, String[] array) {

        FragmentPagerAdapter pagerAdapter = new ViewPageFragmentPagerAdapter(getChildFragmentManager(), fragmentList);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        cineTabLayout.setViewPager(viewPager, arrayToList(array), 0);
    }

    public void setViewPager(final List<Fragment> fragmentList, List<TopTabBean> list) {

        FragmentPagerAdapter pagerAdapter = new ViewPageFragmentPagerAdapter(getChildFragmentManager(), fragmentList);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        cineTabLayout.setViewPager(viewPager, list, 0);
    }

    private List<TopTabBean> arrayToList(String[] arr) {

        List<TopTabBean> list = new ArrayList<>();
        for (String str : arr) {
            TopTabBean topTabBean = new TopTabBean();
            topTabBean.setName(str);
            list.add(topTabBean);
        }
        return list;
    }




}
