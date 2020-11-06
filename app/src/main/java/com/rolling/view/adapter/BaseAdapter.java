package com.rolling.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyao
 * @date 2017/11/24:19:14
 * @E-mail android_n@163.com
 */

public abstract class BaseAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {
    protected final String TAG = getClass().getSimpleName();
    protected final Context mContext;
    protected final LayoutInflater mLayoutInflater;

    protected List<T> mDataList = new ArrayList<>();

    public BaseAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public T getItemData(int position) {
        if (position != -1) {
            return position < mDataList.size() ? mDataList.get(position) : null;
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    /**
     * 移除某一条记录
     *
     * @param position 移除数据的position
     */
    public void removeItem(int position) {
        if (position != -1) {
            if (position < mDataList.size()) {
                mDataList.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    /**
     * 移除某多条记录
     *
     * @param beginPosition 移除数据的position
     */
    public void removeItem(int beginPosition, int endBegin) {
        if (beginPosition < mDataList.size() && endBegin <= mDataList.size()) {
            for (int i = 0; i < mDataList.size(); i++) {
                mDataList.remove(beginPosition);
                if (endBegin == i) {
                    return;
                }
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 添加一条记录
     *
     * @param data     需要加入的数据结构
     * @param position 插入位置
     */
    public void addItem(T data, int position) {
        if (position <= mDataList.size()) {
            mDataList.add(position, data);
            notifyItemInserted(position);
        }
    }

    /**
     * 添加一条记录
     *
     * @param data 需要加入的数据结构
     */
    public void addItem(T data) {
        addItem(data, mDataList.size());
    }

    /**
     * 移除所有记录
     */
    public void clearItems() {
        int size = mDataList.size();
        if (size > 0) {
            mDataList.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    /**
     * 批量添加记录
     *
     * @param data     需要加入的数据结构
     * @param position 插入位置
     */
    public void addItems(List<T> data, int position) {
        if (position <= mDataList.size() && data != null && data.size() > 0) {
            mDataList.addAll(position, data);
            notifyItemRangeChanged(position, data.size());
//            notifyDataSetChanged();
        }
    }

    /**
     * 批量添加记录
     *
     * @param data 需要加入的数据结构
     */
    public void addItems(List<T> data) {
        addItems(data, mDataList.size());
    }

    public void resetItems(List<T> data, int position) {
        int size = mDataList.size();
        if (size > 0) {
            mDataList.clear();
            notifyItemRangeInserted(position, data.size());
        }
        addItems(data);
    }

    /**
     * 批量添加记录
     * 更新指定位置
     */
    public void addItemsUpDate(List<T> data) {
        mDataList.addAll(data);
        notifyItemRangeInserted(getDataList().size() - data.size(), data.size());
    }

    /**
     * 以tag的形式异步加载头像，防止recyclerview错乱
     *
     * @param frescoImage
     * @param url         原始图片link
     * @param wh          加载尺寸
//     */
//    public void setImgHead(FrescoImage frescoImage, String url, String wh) {
//        if (frescoImage != null) {
//            if (TextUtils.isEmpty(wh)) {
//                frescoImage.setTag(AppUtils.imgThumbnail(url, AppUtils.imgFormW60H60));
//            } else {
//                frescoImage.setTag(AppUtils.imgThumbnail(url, wh));
//            }
//            if (!TextUtils.isEmpty(String.valueOf(frescoImage.getTag()))) {
//                frescoImage.setImageURL(String.valueOf(frescoImage.getTag()));
//            }
//        }
//    }
}
