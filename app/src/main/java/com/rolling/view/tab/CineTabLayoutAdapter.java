package com.rolling.view.tab;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.rolling.R;
import com.rolling.bean.tab.TopTabBean;
import com.rolling.util.anim.AnimUtils;
import com.rolling.view.TextViewIcon;
import com.rolling.view.adapter.BaseSingleSelectAdapter;
import com.rolling.view.adapter.BaseViewHolder;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2019-08-11  19:57
 * @E-mail android_n@163.com
 */
public class CineTabLayoutAdapter extends BaseSingleSelectAdapter<TopTabBean> {

    public CineTabLayoutAdapter(Context context, ViewPager viewPager) {
        super(context);
        setViewPager(viewPager);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabHolder(mLayoutInflater.inflate(R.layout.item_cine_tab_layout_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TabHolder tabHolder = (TabHolder) holder;
        tabHolder.bindViewData(getDataList().get(position), position);
    }


    class TabHolder extends BaseViewHolder {

        @BindView(R.id.tvTabName)
        TextViewIcon tvTabName;

        public TabHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCurrentSelect(getAdapterPosition());
                    setViewPageSelect();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });

        }

        public void bindViewData(TopTabBean topTabBean, int position) {
            if (topTabBean != null) {
                tvTabName.setText(topTabBean.getName());
                tvTabName.setTextColor(position == mCurrentSelect ? ContextCompat.getColor(mContext, R.color.color222222) : ContextCompat.getColor(mContext, R.color.color999999));
                tvTabName.setTypeface(position == mCurrentSelect ? Typeface.defaultFromStyle(Typeface.BOLD) : Typeface.defaultFromStyle(Typeface.NORMAL));

                if (position == mCurrentSelect) {
                    tvTabName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                } else {
                    tvTabName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                }
                AnimUtils.scaleViewAnimation(tvTabName, position == mCurrentSelect ? 1.2f : 1);
            }
        }
    }

}
