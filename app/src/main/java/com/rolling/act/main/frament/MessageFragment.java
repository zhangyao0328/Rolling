package com.rolling.act.main.frament;

import com.rolling.R;
import com.rolling.base.view.BaseFragment;
import com.rolling.bean.msg.MessageBena;
import com.rolling.view.recvcler.RlRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author zhangyao
 * @date 2018/8/4  01:02
 * @E-mail android_n@163.com
 */
public class MessageFragment extends BaseFragment {

    @BindView(R.id.rlRecyclerView)
    RlRecyclerView rlRecyclerView;

    boolean isLoadMore = true;

    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_message;
    }

    @Override
    public void init() {
        rlRecyclerView.initCineRecyclerViewNoDecoration(getContext());

        initAdapter();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {
    }

    private void initAdapter() {

        List<MessageBena> messageBenas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            MessageBena messageBena = new MessageBena();
            messageBena.setUserName(" yao " + i);
            messageBenas.add(messageBena);
        }

//        final MessageAdapter messageAdapter = new MessageAdapter(R.layout.item_message_list, messageBenas);
//        messageAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
//        final CustomLoadMoreView customLoadMoreView = new CustomLoadMoreView(getContext());
//        messageAdapter.setLoadMoreView(customLoadMoreView);
//        customLoadMoreView.scrollingBackground.start();
//        messageAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                if(isLoadMore){
//                    customLoadMoreView.scrollingBackground.start();
//                }
//
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            messageAdapter.loadMoreComplete();
//                            isLoadMore = false;
//                        }
//                    }, 2000);
//            }
//        }, rollingRecyclerView);
//
//
//
//        rollingRecyclerView.setAdapter(messageAdapter);

    }

    @Override
    public void succeed(Object o, int tag) {

    }

    @Override
    public void error(Object o, int tag) {

    }
}
