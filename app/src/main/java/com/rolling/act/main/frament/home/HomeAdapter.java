package com.rolling.act.main.frament.home;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.ViewGroup;

import com.rolling.R;
import com.rolling.act.main.frament.home.adapter.holder.FilterHolder;
import com.rolling.act.main.frament.home.adapter.holder.TicketHolder;
import com.rolling.bean.home.HomeDataBean;
import com.rolling.view.adapter.BaseStandardAdapter;
import com.rolling.view.adapter.BaseViewHolder;

/**
 * @author zhangyao
 * @date 2020/11/4  12:21
 * @E-mail android_n@163.com
 */
public class HomeAdapter extends BaseStandardAdapter<HomeDataBean.DataBean.UserListBean, BaseViewHolder> {

    public int TYPE_FILTER = 3;

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(BaseViewHolder holder, HomeDataBean.DataBean.UserListBean item) {
        if (item.getViewType() == TYPE_FILTER) {
            FilterHolder filterHolder = (FilterHolder) holder;
        } else {
            TicketHolder ticketHolder = (TicketHolder) holder;
            ticketHolder.buildData(item);
        }
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FILTER) {
            return new FilterHolder(mLayoutInflater.inflate(R.layout.layout_filter_view, parent, false));
        } else {
            return new TicketHolder(mLayoutInflater.inflate(R.layout.item_ticket_holder, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getViewType();
    }
}
