package com.rollling.act.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.rollling.R;
import com.rollling.base.view.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.message)
    TextView mTextMessage;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    public int getLayoutContextView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        navigation.setOnNavigationItemSelectedListener(this);

        getLoad("https://api.paipianbang.com/api/v2/activities/hot_posts?per_page=20&page=1&token=9a5c14256cd612bdfbbf", null, null, 111, true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                mTextMessage.setText(R.string.title_home);
                return true;
            case R.id.navigation_dashboard:
                mTextMessage.setText(R.string.title_dashboard);
                return true;
            case R.id.navigation_notifications:
                mTextMessage.setText(R.string.title_notifications);
                return true;
        }
        return false;
    }

    @Override
    public void succeed(Object o, int tag) {
        mTextMessage.setText(o.toString());
    }

    @Override
    public void error(Object o, int tag) {

    }

    @Override
    public void responseCode(int code) {

    }
}
