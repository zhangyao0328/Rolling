package com.rolling.view.tab;

import android.content.Context;
import android.graphics.Typeface;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.RecyclerView;
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
        tabHolder.buildData(getDataList().get(position));
    }


    class TabHolder extends BaseViewHolder<TopTabBean> {

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

        @Override
        public void buildData(TopTabBean topTabBean) {
             if (topTabBean != null) {
                tvTabName.setText(topTabBean.getName());
                tvTabName.setTextColor(getAdapterPosition() == mCurrentSelect ? ContextCompat.getColor(mContext, R.color.color222222) : ContextCompat.getColor(mContext, R.color.color999999));
                tvTabName.setTypeface(getAdapterPosition() == mCurrentSelect ? Typeface.defaultFromStyle(Typeface.BOLD) : Typeface.defaultFromStyle(Typeface.NORMAL));

                if (getAdapterPosition() == mCurrentSelect) {
                    tvTabName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                } else {
                    tvTabName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                }
                AnimUtils.scaleViewAnimation(tvTabName, getAdapterPosition() == mCurrentSelect ? 1.2f : 1);
            }
        }
        }
}
