package com.rolling.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.rolling.R;
import com.rolling.act.calendar.CalendarSportActivity;
import com.rolling.act.main.frament.home.EventFragment;
import com.rolling.util.OpenAcitivtyUtils;
import com.rolling.view.TextViewIcon;
import com.rolling.view.adapter.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zhangyao
 * @date 2020/11/10  12:54
 * @E-mail android_n@163.com
 * 首页筛选器
 */
public class FilterView extends RelativeLayout {

    @BindView(R.id.tvAllType)
    TextViewIcon tvAllType;

    @BindView(R.id.tvSort)
    TextViewIcon tvSort;

    @BindView(R.id.tvCalendar)
    TextViewIcon tvCalendar;

    EventFragment eventFragment;

    public FilterView(Context context) {
        super(context);
        initView();
    }

    public FilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_filter_view, this, true);
        ButterKnife.bind(this, view);
    }

    public void setData(EventFragment fragment) {
        this.eventFragment = fragment;
    }

    public void setAdapterOnClick(){
        if (this.eventFragment.sportFilterAdapter != null) {
            this.eventFragment.sportFilterAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    tvAllType.setText(eventFragment.sportFilterAdapter.getItemData(position).getName());
                    eventFragment.dismissFilter();
                }
            });
        }

        if (this.eventFragment.sportSortAdapter != null) {
            this.eventFragment.sportSortAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    tvSort.setText(eventFragment.sportSortAdapter.getItemData(position).getName());
                    eventFragment.dismissFilter();
                }
            });
        }
    }

    @OnClick({R.id.tvAllType, R.id.tvSort, R.id.tvCalendar})
    public void onClicks(View view) {
        if (eventFragment != null) {
            switch (view.getId()) {
                case R.id.tvAllType:
                    eventFragment.initFilterView(0);
                    break;
                case R.id.tvSort:
                    eventFragment.initFilterView(1);
                    break;
                case R.id.tvCalendar:
                    OpenAcitivtyUtils.openAct(getContext(), CalendarSportActivity.class);
                    break;
            }
        }

    }
}
