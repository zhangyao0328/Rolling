package com.rolling.act.main.frament;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

import com.alibaba.fastjson.JSON;
import com.rolling.R;
import com.rolling.act.set.EditUserInfoActivity;
import com.rolling.act.set.SysSetActivity;
import com.rolling.app.MyApplication;
import com.rolling.base.view.BaseFragment;
import com.rolling.bean.user.UserLoginBean;
import com.rolling.event.UploadUserInfoEvent;
import com.rolling.net.HttpConfig;
import com.rolling.util.CineLog;
import com.rolling.util.CineToast;
import com.rolling.util.LoginUtils;
import com.rolling.util.OpenAcitivtyUtils;
import com.rolling.util.sp.RollingSp;
import com.rolling.view.FrescoImage;
import com.rolling.view.TextViewIcon;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.listener.OnMultiListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zhangyao
 * @date 2018/8/4  01:03
 * @E-mail android_n@163.com
 * 首页-我的
 */
public class MineFragment extends BaseFragment {

    private final int NET_TAG_USER_INFO = 1001;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.imgHead)
    FrescoImage imgHead;

    @BindView(R.id.frescoImageBg)
    FrescoImage frescoImageBg;

    @BindView(R.id.tvUserName)
    TextViewIcon tvUserName;

    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    private int mOffset = 0;
    private int mScrollY = 0;

    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_main_mine;
    }

    @Override
    public void init() {
        setOnMultiPurposeListener();
        getUserInfo();
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void firstLoadDate() {

    }

    @Override
    public void succeed(Object o, int tag) {
        switch (tag) {
            case NET_TAG_USER_INFO:
                UserLoginBean userLoginBean = JSON.parseObject(o.toString(), UserLoginBean.class);
                switch (userLoginBean.getCode()) {
                    case 0:
                        MyApplication.userLoginBean = userLoginBean;
                        RollingSp.putData(getContext(), RollingSp.ROLLING_USER_LOGIN_DATA, o.toString());
                        initUserInfo();
                        break;
                    default:
                        if (!TextUtils.isEmpty(userLoginBean.getMessage())) {
                            CineToast.showShort(userLoginBean.getMessage());
                        }
                        break;
                }
                break;
        }
//        BaseDataBean baseDataBean = JSONObject.parseObject(o.toString(), BaseDataBean.class);
//        CineToast.showShort(baseDataBean.getMessage());
    }

    @Override
    public void error(Object o, int tag) {

    }

    @Override
    public void responseCode(int code) {

    }


    @OnClick({R.id.btSet, R.id.layoutUserInfo})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.btSet:
                OpenAcitivtyUtils.openAct(getContext(), SysSetActivity.class);
                break;
            case R.id.layoutUserInfo:
                openUserInfo();
                break;
        }
    }

    //    @IsLogin(true)
    private void openUserInfo() {
        OpenAcitivtyUtils.openAct(getContext(), EditUserInfoActivity.class);
    }


    private void addUser() {
    }

    private void initUserInfo() {
        if (LoginUtils.isLogin()) {
            imgHead.setImageURL(MyApplication.getUserLoginBean().getData().getAvatarUrl());
            frescoImageBg.showUrlBlur(MyApplication.getUserLoginBean().getData().getAvatarUrl(), 10, 5);
            tvUserName.setText(MyApplication.getUserLoginBean().getData().getNickName());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void refreshView(UploadUserInfoEvent uploadUserInfoEvent) {
        if (uploadUserInfoEvent.isSucceed()) {
            getUserInfo();
        }
    }

    public void getUserInfo() {
        if(MyApplication.getUserLoginBean() != null){

            String[] key = {"id"};
            String[] value = {String.valueOf(MyApplication.getUserLoginBean().getData().getId())};
            getLoad(HttpConfig.URL_API_USER_INFO, key, value, NET_TAG_USER_INFO, true);
        }
    }

    private void setOnMultiPurposeListener() {



        refreshLayout.setOnRefreshLoadMoreListener(new OnMultiListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                frescoImageBg.setTranslationY(mOffset - mScrollY);

                CineLog.e("onHeaderMoving");
            }

            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {
                CineLog.e("onHeaderReleased");
            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {
                CineLog.e("onHeaderStartAnimator");
            }

            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {
                CineLog.e("onHeaderFinish");
            }

            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {
                CineLog.e("onFooterMoving");

                mOffset = offset / 2;
                frescoImageBg.setTranslationY(mOffset - mScrollY);
            }

            @Override
            public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {
                CineLog.e("onFooterReleased");
            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {
                CineLog.e("onFooterStartAnimator");
            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {
                CineLog.e("onFooterFinish");
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                CineLog.e("onLoadMore");
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                CineLog.e("onRefresh");
            }

            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
                CineLog.e("onStateChanged");
            }
        });

    }
}
