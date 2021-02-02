package com.rolling.act.main.frament;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.rolling.R;
import com.rolling.act.set.EditUserInfoActivity;
import com.rolling.act.set.SysSetActivity;
import com.rolling.app.MyApplication;
import com.rolling.base.prsenter.BasePresenterImpl;
import com.rolling.base.view.BaseFragment;
import com.rolling.bean.BaseBean;
import com.rolling.bean.tab.TopTabBean;
import com.rolling.bean.user.UserLoginBean;
import com.rolling.event.UploadUserInfoEvent;
import com.rolling.net.HttpConfig;
import com.rolling.util.CineToast;
import com.rolling.util.LoginUtils;
import com.rolling.util.OpenAcitivtyUtils;
import com.rolling.util.sp.RollingSp;
import com.rolling.view.FrescoImage;
import com.rolling.view.TextViewIcon;

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

    @BindView(R.id.edContent)
    EditText edContent;

    @BindView(R.id.imgHead)
    FrescoImage imgHead;

    @BindView(R.id.frescoImageBg)
    FrescoImage frescoImageBg;

    @BindView(R.id.tvUserName)
    TextViewIcon tvUserName;

    @Override
    public int getLayoutContextView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void init() {
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


    @OnClick({R.id.btAddBanner, R.id.btAddUser, R.id.btSet, R.id.layoutUserInfo})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.btAddBanner:
//                addBanner();
                getUserInfo();
                break;
            case R.id.btAddUser:
                addUser();
                break;
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

    private void addBanner() {

        if (basePresenter == null) {
            basePresenter = new BasePresenterImpl(getActivity(), this);
        }

        if (!TextUtils.isEmpty(edContent.getText())) {
            String url = HttpConfig.URL_API_SPORT_TYPE;
            TopTabBean topTabBean = new TopTabBean();
            topTabBean.setName(edContent.getText().toString());
            String json = JSON.toJSONString(topTabBean);
            postLoad(new BaseBean(url, json, 1001, true, null));
        }
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
        String[] key = {"id"};
        String[] value = {String.valueOf(MyApplication.getUserLoginBean().getData().getId())};
        getLoad(HttpConfig.URL_API_USER_INFO, key, value, NET_TAG_USER_INFO, true);
    }
}
