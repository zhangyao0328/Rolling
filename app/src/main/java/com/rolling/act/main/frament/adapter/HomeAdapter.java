package com.rolling.act.main.frament.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.rolling.R;
import com.rolling.act.main.frament.adapter.holder.IrsHolder;
import com.rolling.bean.home.HomeDataBean;
import com.rolling.view.adapter.BaseStandardAdapter;
import com.rolling.view.adapter.BaseViewHolder;

/**
 * @author zhangyao
 * @date 2020/11/4  12:21
 * @E-mail android_n@163.com
 */
public class HomeAdapter extends BaseStandardAdapter<HomeDataBean.DataBean.UserListBean, BaseViewHolder> {
    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(BaseViewHolder holder, HomeDataBean.DataBean.UserListBean item) {
        IrsHolder irsHolder = (IrsHolder) holder;
        irsHolder.buildData(item);
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IrsHolder(mLayoutInflater.inflate(R.layout.item_message_list, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getViewType();
    }
}
