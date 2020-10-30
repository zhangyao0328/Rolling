package com.rollling.view.adapter;

import android.content.Context;


/**
 * @author zhangyao
 * @date 2017/11/26:00:17
 * @E-mail android_n@163.com
 */

public abstract class BaseStandardAdapter<T, VH extends BaseViewHolder> extends BaseAdapter<T, BaseViewHolder> {

    public static final int TYPE_EMPTY= -1;

    public boolean isEmpty;

    public Context mContext;

    public int checkPosition = -1;

    public BaseStandardAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    protected final void bindHolder(BaseViewHolder holder, int position) {
        convert((VH) holder, getDataList().get(holder.getLayoutPosition()));
    }

    abstract protected void convert(VH holder, T item);

    @Override
    public final void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.itemView.setClickable(true);
        bindHolder((VH) holder, position);
    }
}
