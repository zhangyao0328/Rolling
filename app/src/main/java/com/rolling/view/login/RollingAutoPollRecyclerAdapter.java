package com.rolling.view.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.rolling.R;
import com.rolling.bean.login.PollBgBean;
import com.rolling.view.FrescoImage;
import com.rolling.view.adapter.BaseStandardAdapter;
import com.rolling.view.adapter.BaseViewHolder;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2020/10/30  12:18
 * @E-mail android_n@163.com
 */
public class RollingAutoPollRecyclerAdapter extends BaseStandardAdapter<PollBgBean, BaseViewHolder> {
    public RollingAutoPollRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(BaseViewHolder holder, PollBgBean item) {
        PollViewHolder pollViewHolder = (PollViewHolder) holder;
//        pollViewHolder.frescoImage.setImageURL("https://images.unsplash.com/photo-1416425243776-b8fd4dc53f50?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");
        pollViewHolder.buildData(item);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PollViewHolder(mLayoutInflater.inflate(R.layout.layout_fresco, parent, false));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class PollViewHolder extends BaseViewHolder {

        @BindView(R.id.frescoImage)
        FrescoImage frescoImage;

        public PollViewHolder(View itemView) {
            super(itemView);
        }

        public void buildData(PollBgBean item) {
            frescoImage.setImageURL(item.getUrl());
        }

    }
}
