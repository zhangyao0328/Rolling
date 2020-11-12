package com.rolling.act.location.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.rolling.bean.location.CityBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author zhangyao
 * @date 2017/12/8:11:14
 * @E-mail android_n@163.com
 */

public abstract class LocationAdapter <VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    private ArrayList<CityBean> items = new ArrayList<CityBean>();

    public LocationAdapter() {
        setHasStableIds(true);
    }

    public void add(CityBean object) {
        items.add(object);
        notifyDataSetChanged();
    }

    public void add(int index, CityBean object) {
        items.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends CityBean> collection) {
        if (collection != null) {
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void addAll(CityBean... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void remove(String object) {
        items.remove(object);
        notifyDataSetChanged();
    }

    public CityBean getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
