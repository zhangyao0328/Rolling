package com.rollling.act.main.frament.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rollling.R;
import com.rollling.bean.msg.MessageBena;

import java.util.List;

/**
 * @author zhangyao
 * @date 2018/8/7  00:11
 * @E-mail android_n@163.com
 */
public class MessageAdapter extends BaseQuickAdapter<MessageBena, BaseViewHolder>{

    public MessageAdapter(int layoutResId, @Nullable List<MessageBena> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBena item) {
        helper.setText(R.id.userName, item.getUserName());
    }
}
