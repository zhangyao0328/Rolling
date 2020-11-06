package com.rolling.base.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.rolling.base.prsenter.BasePresenter;
import com.rolling.base.prsenter.BasePresenterImpl;
import com.rolling.bean.BaseBean;

import butterknife.ButterKnife;

/**
 * @author zhangyao
 * @date 2018/8/4  00:23
 * @E-mail android_n@163.com
 */
public abstract class BaseFragment extends Fragment implements BaseView{


   public BasePresenter basePresenter;

    Context mContext;
    View mRootView;

    /**
     * 控件是否初始化完成
     */
    private boolean isViewCreated;
    /**
     * 数据是否加载完毕
     */
    private boolean isLoadDataComleted;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        isViewCreated = true;
        if (getLayoutContextView() != 0) {
            mRootView = inflater.inflate(getLayoutContextView(), container, false);
            ButterKnife.bind(this, mRootView);
            this.mContext = getActivity();
            init();
            return mRootView;
        } else {
            init();
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            loadDate();
        }
    }

    public abstract int getLayoutContextView();

    public abstract void init();

    public abstract void onCreate();

    public abstract void firstLoadDate();


    public void openActivity(Class<?> act) {
        startActivity(new Intent(getActivity(), act));
    }

    public void openActivity(Class<?> act, Bundle bundle) {
        if (bundle != null) {
            Intent intent = new Intent(mContext, act);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            openActivity(act);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadDataComleted) {
            loadDate();
        }
    }

    public void loadDate() {
        isLoadDataComleted = true;
        firstLoadDate();
    }

    public void getLoad(String url, String[] key, String[] value, int tag, boolean isDialog) {
        if (basePresenter == null) {
            basePresenter = new BasePresenterImpl(getActivity(), this);
        }
        BaseBean baseBean = new BaseBean(url, key, value, tag, isDialog);
        basePresenter.getAsync(getActivity(), baseBean);
    }

    public void postLoad(BaseBean baseBean) {
        if (baseBean != null) {
            if (basePresenter == null) {
                basePresenter = new BasePresenterImpl(getActivity(), this);
            }
            basePresenter.postAsync(getActivity(), baseBean);
        }
    }

    @Override
    public void responseCode(int code) {

    }

    public void closeRecycler(SwipeToLoadLayout swipeToLoadLayout) {
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
    }


}
