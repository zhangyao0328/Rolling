package com.rolling.act.main;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jpeng.jptabbar.JPTabBar;
import com.rolling.R;
import com.rolling.act.login.LoginActivity;
import com.rolling.act.main.frament.DiscoverFragment;
import com.rolling.act.main.frament.MessageFragment;
import com.rolling.act.main.frament.MineFragment;
import com.rolling.act.main.frament.home.EventFragment;
import com.rolling.act.main.frament.home.HomeFragment;
import com.rolling.app.MyApplication;
import com.rolling.base.view.BaseActivity;
import com.rolling.event.ExitEvent;
import com.rolling.util.CineLog;
import com.rolling.util.CineToast;
import com.rolling.util.LoginUtils;
import com.rolling.util.OpenAcitivtyUtils;
import com.rolling.view.viewpage.RollingViewPage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.viewPager)
    RollingViewPage viewPager;

    @BindView(R.id.navigation)
    JPTabBar navigation;

    @BindView(R.id.rootContentBg)
    public View rootContentBg;

    private FragmentPagerAdapter pagerAdapter;

    private List<Fragment> fragmentList;

    HomeFragment homeFragment;

    @Override
    public int getLayoutContextView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        disableBack();
        EventBus.getDefault().register(this);
        initNavigationBar();
        initViewPage();


        if (!LoginUtils.isLogin()) {
            OpenAcitivtyUtils.openAct(MyApplication.getInstance(), LoginActivity.class);
        }
//
//        String url = HttpConfig.URL_HOST +  "/irs/list";
//
//        getLoad(url, null, null, 1002, false);


    }


    








    /**
     * 快速排序
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 递归排序
     * @param nums
     * @param l
     * @param r
     */
    public void quickSort(int nums[], int l, int r){
        if (l < r) {
            int pos = randomizedPartition(nums, l, r);
            quickSort(nums, l, pos - 1);
            quickSort(nums, pos + 1, r);
        }
    }

    public int randomizedPartition(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l; // 随机选一个作为我们的主元
        swap(nums, r, i);
        return partition(nums, l, r);
    }

    /**
     * 对数组nums的l和r进行划分
     * @param nums
     * @param l
     * @param r
     * @return 返回分界值下标pos
     */
    public int partition(int nums[], int l, int r){
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    /**
     * 交换位置
     * @param nums
     * @param i
     * @param j
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    private void initNavigationBar() {

        navigation
                .setNormalIcons(R.drawable.ic_home_black_24dp, R.drawable.ic_notifications_black_24dp, R.drawable.ic_dashboard_black_24dp, R.drawable.ic_dashboard_black_24dp)
                .generate();

        navigation.setContainer(viewPager);
    }

    private void initViewPage() {

        homeFragment = new HomeFragment();

        fragmentList = new ArrayList<>();
        fragmentList.add(homeFragment);
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void eventBus(ExitEvent exitEvent) {
        CineToast.showShort("退出登录");
    }

    @Override
    public void onBackPressed() {
        if (homeFragment.fragmentList.size() > 0) {
            EventFragment eventFragment = (EventFragment) homeFragment.fragmentList.get(0);
            if (eventFragment.rootFilterContent.getVisibility() == View.VISIBLE) {
                eventFragment.rootFilterContent.setVisibility(View.GONE);
                rootContentBg.setVisibility(View.GONE);
                return;
            }
        }
        super.onBackPressed();
    }
}
