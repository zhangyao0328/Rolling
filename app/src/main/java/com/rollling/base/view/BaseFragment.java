package com.rollling.base.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rollling.base.prsenter.BasePresenter;
import com.rollling.base.prsenter.BasePresenterImpl;
import com.rollling.bean.BaseBean;
import com.rollling.bean._Article;
import com.rollling.bean.main.TestBean;
import com.rollling.util.LogUtils;

import butterknife.ButterKnife;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * @author zhangyao
 * @date 2018/8/4  00:23
 * @E-mail android_n@163.com
 */
public abstract class BaseFragment extends Fragment implements BaseView{


    BasePresenter basePresenter;

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

    /**
     * 添加数据
     * @param bmobObject
     * @param tag
     * @param isShowLoding
     */
    public void savaData(final BmobObject bmobObject, final int tag, boolean isShowLoding){
        if (basePresenter == null) {
            basePresenter = new BasePresenterImpl(getActivity(), this);
        }
        basePresenter.saveData(getActivity(), new BaseBean(bmobObject, tag, isShowLoding));
    }

    /**
     * 获取一行数据
     * @param objectID
     */
    public void getData(TestBean BmobObject, String objectID){

        BmobQuery<_Article> bmobQuery = new BmobQuery<_Article>();
        objectID = "hDnzWWWa";
        //查找Person表里面id为6b6c11c537的数据
        bmobQuery.getObject(objectID, new QueryListener<_Article>() {
            @Override
            public void done(_Article object,BmobException e) {
                if(e==null){
                    LogUtils.e("查询成功" + object.toString());
                }else{
                    LogUtils.e("查询失败：" + e.getMessage());
                }
            }
        });
    }

}
