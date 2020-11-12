package com.rolling.view.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rolling.R;
import com.rolling.util.CineLog;
import com.rolling.view.FrescoImage;

import java.util.List;
import java.util.Random;


public class AutoPollAdapter extends RecyclerView.Adapter<AutoPollAdapter.BaseViewHolder> {
    private final Context mContext;

    List<String> listData;

    Random random = new Random();

    public AutoPollAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.listData = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_fresco, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        CineLog.e("***********" + position);
        int r = random.nextInt(listData.size());
        holder.imageView.setImageURL(listData.get(r));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        FrescoImage imageView;

        public BaseViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.frescoImage);

        }
    }

}
