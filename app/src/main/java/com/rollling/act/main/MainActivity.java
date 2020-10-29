package com.rollling.act.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.jpeng.jptabbar.JPTabBar;
import com.rollling.R;
import com.rollling.act.login.LoginActivity;
import com.rollling.act.main.frament.DiscoverFragment;
import com.rollling.act.main.frament.HomeFragment;
import com.rollling.act.main.frament.MessageFragment;
import com.rollling.act.main.frament.MineFragment;
import com.rollling.base.view.BaseActivity;
import com.rollling.util.CineLog;
import com.rollling.view.RollingViewPage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.viewPager)
    RollingViewPage viewPager;

    @BindView(R.id.navigation)
    JPTabBar navigation;

    private FragmentPagerAdapter pagerAdapter;

    private List<Fragment> fragmentList;

    @Override
    public int getLayoutContextView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        initNavigationBar();
        initViewPage();

//        if(!LoginUtils.isLogin(this)){
        openActivity(LoginActivity.class);
//        }
//
//        String url = HttpConfig.URL_HOST +  "/irs/list";
//
//        getLoad(url, null, null, 1002, false);
    }

    private void initNavigationBar() {

        navigation
                .setNormalIcons(R.drawable.ic_home_black_24dp, R.drawable.ic_notifications_black_24dp, R.drawable.ic_dashboard_black_24dp, R.drawable.ic_dashboard_black_24dp)
                .generate();

        navigation.setContainer(viewPager);
    }

    private void initViewPage() {

        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new MessageFragment());
        fragmentList.add(new MineFragment());

        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

//        viewPager.setCanScroll(false);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(fragmentList.size());


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_dashboard:
                return true;
            case R.id.navigation_notifications:
                return true;
        }
        return false;
    }


    @Override
    public void succeed(Object o, int tag) {

        CineLog.e(this.getClass().getName());
    }

    @Override
    public void error(Object o, int tag) {

    }

    @Override
    public void responseCode(int code) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
