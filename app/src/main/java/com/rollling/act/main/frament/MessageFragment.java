package com.rollling.act.main.frament;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rollling.R;
import com.rollling.act.main.frament.adapter.MessageAdapter;
import com.rollling.base.view.BaseFragment;
import com.rollling.bean.main.TestBean;
import com.rollling.bean.msg.MessageBena;
import com.rollling.view.RollingRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2018/8/4  01:02
 * @E-mail android_n@163.com
 */
public class MessageFragment extends BaseFragment {

    @BindView(R.id.rollingRecyclerView)
    RollingRecyclerView rollingRecyclerView;

    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_message;
    }

    @Override
    public void init() {
        rollingRecyclerView.initRecyclerView(getContext());

        initAdapter();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {
        getData(new TestBean(), "05f07c7133");
    }

    private void initAdapter(){

        List<MessageBena> messageBenas = new ArrayList<>();
        for(int i = 0 ; i < 100; i ++){
            MessageBena messageBena = new MessageBena();
            messageBena.setUserName(" yao " + i);
            messageBenas.add(messageBena);
        }

        MessageAdapter messageAdapter = new MessageAdapter(R.layout.item_message_list, messageBenas);
        messageAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        messageAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, rollingRecyclerView);
        rollingRecyclerView.setAdapter(messageAdapter);

    }

    @Override
    public void succeed(String t, int tag) {

    }

    @Override
    public void error(String t, int tag) {

    }

    @Override
    public void responseCode(int code) {

    }
}
