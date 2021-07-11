package com.rolling.act.calendar;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.rolling.R;
import com.rolling.act.main.frament.home.HomeAdapter;
import com.rolling.base.view.BaseActivity;
import com.rolling.bean.home.HomeDataBean;
import com.rolling.net.HttpConfig;
import com.rolling.util.CineToast;
import com.rolling.view.recvcler.RlRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CalendarSportActivity extends BaseActivity implements CalendarView.OnCalendarSelectListener, CalendarView.OnYearChangeListener {

    private final int NET_GET_IRS_DATA = 1001;

    @BindView(R.id.rlRecyclerView)
    RlRecyclerView rlRecyclerView;

    @BindView(R.id.calendarView)
    CalendarView mCalendarView;

    @BindView(R.id.calendarLayout)
    CalendarLayout mCalendarLayout;

    @BindView(R.id.tv_month_day)
    TextView mTextMonthDay;

    @BindView(R.id.tv_lunar)
    TextView mTextLunar;

    @BindView(R.id.tv_year)
    TextView mTextYear;

    HomeAdapter adapter;

    private int mYear;

    @Override
    public int getLayoutContextView() {
        return R.layout.activity_calendar_sport;
    }

    @Override
    public void init() {

        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");


        getData(NET_GET_IRS_DATA);
        rlRecyclerView.initCineRecyclerViewNoDecoration(this);
        adapter = new HomeAdapter(this);
        rlRecyclerView.setAdapter(adapter);

        initData();
    }

    @Override
    public void succeed(Object o, int tag) {
        switch (tag) {
            case NET_GET_IRS_DATA:
                HomeDataBean homeDataBean = JSON.parseObject(o.toString(), HomeDataBean.class);
                if (adapter.getItemCount() > 0) {
                    adapter.clearItems();
                }
                adapter.addItems(homeDataBean.getData().getUserList());
                break;
        }
    }

    @Override
    public void error(Object o, int tag) {

    }

    private void getData(int tag) {
        String url = HttpConfig.URL_HOST + "/irs/list";
        getLoad(url, null, null, tag, false);
    }

    private void initData() {
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        Map<String, Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year, month, 3, 0xFF40db25, "假").toString(),
                getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
        map.put(getSchemeCalendar(year, month, 6, 0xFFe69138, "事").toString(),
                getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
        map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356, "议").toString(),
                getSchemeCalendar(year, month, 9, 0xFFdf1356, "议"));

        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);
    }


    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {


        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();

        if (isClick) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(String.valueOf(calendar.getYear()));
            stringBuffer.append("年");
            stringBuffer.append(String.valueOf(calendar.getMonth()));
            stringBuffer.append("月");
            stringBuffer.append(String.valueOf(calendar.getDay()));
            stringBuffer.append("日");
            CineToast.showShort(stringBuffer.toString());
        }
    }

    @OnClick({R.id.tv_month_day})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.tv_month_day:
                if (!mCalendarLayout.isExpand()) {
                    mCalendarLayout.expand();
                    return;
                }
                if (mCalendarView.isYearSelectLayoutVisible()) {
                    mCalendarView.closeYearSelectLayout();
                    mTextLunar.setVisibility(View.VISIBLE);
                    mTextYear.setVisibility(View.VISIBLE);
                    mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
                    mTextYear.setText(String.valueOf(mYear));
                } else {
                    mCalendarView.showYearSelectLayout(mYear);
                    mTextLunar.setVisibility(View.GONE);
                    mTextYear.setVisibility(View.GONE);
                    mTextMonthDay.setText(String.valueOf(mYear));
                }
                break;
        }
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }

    @Override
    public void onBackPressed() {
        if (mCalendarView.isYearSelectLayoutVisible()) {
            mCalendarView.closeYearSelectLayout();
            mTextLunar.setVisibility(View.VISIBLE);
            mTextYear.setVisibility(View.VISIBLE);
            mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
            mTextYear.setText(String.valueOf(mYear));
            return;
        }
        super.onBackPressed();
    }
}