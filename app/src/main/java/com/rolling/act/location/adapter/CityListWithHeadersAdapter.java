package com.rolling.act.location.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.rolling.R;
import com.rolling.app.MyApplication;
import com.rolling.bean.location.CityBean;
import com.rolling.util.CineToast;
import com.rolling.view.adapter.OnItemClickListener;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * @author zhangyao
 * @date 2017/12/14:19:27
 * @E-mail android_n@163.com
 */

public class CityListWithHeadersAdapter extends LocationAdapter<RecyclerView.ViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder>, View.OnClickListener {

    OnItemClickListener onItemClickListener;

    /**
     * 热门城市
     */
    public int TYPE_HOT = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (TYPE_HOT == viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_work_type_child_hot, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_work_type_child, parent, false);
            view.setOnClickListener(this);
            return new RecyclerView.ViewHolder(view) {
            };
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItem(position).getViewType() == TYPE_HOT) {
            if (holder.itemView.getTag() != null) {
                if ((Boolean) holder.itemView.getTag()) {
                    return;
                }
            }

            FlexboxLayout flexboxLayout = (FlexboxLayout) holder.itemView;
            for (CityBean cityBean : getItem(position).getHotCity()) {
                TextView textView = (TextView) View.inflate(holder.itemView.getContext(), R.layout.item_work_type_child, null);
                textView.setText(cityBean.getNameZh());
                textView.setTag(cityBean);
                textView.setOnClickListener(onHotClick);
                flexboxLayout.addView(textView);
            }
            holder.itemView.setTag(true);
        } else {
            TextView textView = (TextView) holder.itemView;
            textView.setText(getItem(position).getNameZh());
            textView.setTag(position);
        }
    }

    @Override
    public long getHeaderId(int position) {
        if (TextUtils.isEmpty(getItem(position).getName())) {
            return 0;
        } else {
            return getItem(position).getName().substring(0, 1).charAt(0);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_work_type_head, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItem(position).getViewType() == TYPE_HOT) {
            TextView textView = (TextView) holder.itemView;
            textView.setText(MyApplication.getInstance().getString(R.string.location_hot_city));
        } else {
            TextView textView = (TextView) holder.itemView;
            if (!TextUtils.isEmpty(getItem(position).getName())) {
                textView.setText(getItem(position).getName().substring(0, 1));
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            if(view.getTag() != null){
                onItemClickListener.onItemClick(view, (int) view.getTag());
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    View.OnClickListener onHotClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getTag() != null){
                CityBean cityBean = (CityBean) view.getTag();
                CineToast.showShort(cityBean.getNameZh());
            }
        }
    };
}