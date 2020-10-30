package com.rollling.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

import com.rollling.view.FrescoImage;

import butterknife.ButterKnife;

/**
 * @author zhangyao
 * @date 2017/11/26:00:12
 * @E-mail android_n@163.com
 */

public class BaseViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener {

    private final SparseArray<View> mViews;

    RecyclerView.Adapter recyclerBaseAdapter;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();
        ButterKnife.bind(this, itemView);
    }


    @SuppressWarnings("unchecked")
    public <V extends View> V findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        view.setOnClickListener((View.OnClickListener) this);

        return (V) view;
    }

    public RecyclerView.Adapter getRecyclerBaseAdapter() {
        return recyclerBaseAdapter;
    }

    public void setRecyclerBaseAdapter(RecyclerView.Adapter recyclerBaseAdapter) {
        this.recyclerBaseAdapter = recyclerBaseAdapter;
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    public void openActivity(Context mContext, Class<?> act) {
        mContext.startActivity(new Intent(mContext, act));
    }

    public void openActivity(Context mContext, Class<?> act, Bundle bundle) {
        Intent intent = new Intent(mContext, act);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public void openActivity(Context mContext, Class<?> act, Bundle bundle, int flag) {
        Intent intent = new Intent(mContext, act);
        intent.putExtras(bundle);
        intent.setFlags(flag);
        mContext.startActivity(intent);
    }

    public void openActivity(Context mContext, Class<?> act, String key, int tag) {
        Intent intent = new Intent(mContext, act);
        intent.putExtra(key, tag);
        mContext.startActivity(intent);
    }

    public void openActivity(Context mContext, Class<?> act, String key, String value) {
        Intent intent = new Intent(mContext, act);
        intent.putExtra(key, value);
        mContext.startActivity(intent);
    }

    public void openActivity(Context mContext, Class<?> act, String key, String value, int flag) {
        Intent intent = new Intent(mContext, act);
        intent.putExtra(key, value);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(flag);
        mContext.startActivity(intent);
    }


    /**
     * 以tag的形式异步加载头像，防止recyclerview错乱
     *
     * @param frescoImage
     * @param url         原始图片link
     */
    public void setImgHead(FrescoImage frescoImage, String url) {
        if (frescoImage != null) {
            frescoImage.setTag(url);
            if (!TextUtils.isEmpty(String.valueOf(frescoImage.getTag()))) {
                frescoImage.setImageURL(String.valueOf(frescoImage.getTag()));
            }
        }
    }
}
