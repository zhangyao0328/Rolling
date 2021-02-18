package com.rolling.view.adapter;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.rolling.view.FrescoImage;

import butterknife.ButterKnife;

/**
 * @author zhangyao
 * @date 2017/11/26:00:12
 * @E-mail android_n@163.com
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements OnItemClickListener {

    private final SparseArray<View> mViews;

    RecyclerView.Adapter recyclerBaseAdapter;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();
        ButterKnife.bind(this, itemView);
    }

    public  abstract void buildData(T t);


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
