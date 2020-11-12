package com.rolling.act.main.frament.home.adapter.holder;

import android.content.Context;
import android.graphics.Typeface;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.rolling.R;
import com.rolling.bean.tab.TopTabBean;
import com.rolling.view.TextViewIcon;
import com.rolling.view.adapter.BaseSingleSelectAdapter;
import com.rolling.view.adapter.BaseViewHolder;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2020/11/10  22:31
 * @E-mail android_n@163.com
 */
public class SportFilterAdapter extends BaseSingleSelectAdapter<TopTabBean> {


    public SportFilterAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabHolder(mLayoutInflater.inflate(R.layout.item_sport_filter, parent, false));
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
            }
        }
    }
}
