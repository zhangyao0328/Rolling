package com.rolling.act.main.frament;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rolling.R;
import com.rolling.act.set.EditUserInfoActivity;
import com.rolling.act.set.SysSetActivity;
import com.rolling.app.MyApplication;
import com.rolling.base.prsenter.BasePresenterImpl;
import com.rolling.base.view.BaseFragment;
import com.rolling.bean.BaseBean;
import com.rolling.bean.BaseDataBean;
import com.rolling.bean.tab.TopTabBean;
import com.rolling.net.HttpConfig;
import com.rolling.util.CineToast;
import com.rolling.util.LoginUtils;
import com.rolling.util.OpenAcitivtyUtils;
import com.rolling.view.FrescoImage;
import com.rolling.view.TextViewIcon;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zhangyao
 * @date 2018/8/4  01:03
 * @E-mail android_n@163.com
 * 首页-我的
 */
public class MineFragment extends BaseFragment {

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
        initUserInfo();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void firstLoadDate() {

    }

    @Override
    public void succeed(Object o, int tag) {
        BaseDataBean baseDataBean = JSONObject.parseObject(o.toString(), BaseDataBean.class);
        CineToast.showShort(baseDataBean.getMessage());
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
                addBanner();
                break;
            case R.id.btAddUser:
                addUser();
                break;
            case R.id.btSet:
                OpenAcitivtyUtils.openAct(getContext(), SysSetActivity.class);
                break;
            case R.id.layoutUserInfo:
                OpenAcitivtyUtils.openAct(getContext(), EditUserInfoActivity.class);
                break;
        }
    }

    private void addBanner() {
//        List<String> list = new ArrayList<>();
//        list.add("https://tattoodo-mobile-app.imgix.net/images/posts/postImage_FrbD6eJgoX.png?w=1122&fit=crop&auto=format%2Ccompress");
//        list.add("https://tattoodo-mobile-app.imgix.net/images/posts/ScreenShot2017-11-13at094213_YLwIX6GkPi.png?w=567&fit=crop&auto=format%2Ccompress");
//        list.add("https://tattoodo-mobile-app.imgix.net/images/posts/postImage_0JiZ41Gjgx.png?w=1122&fit=crop&auto=format%2Ccompress");
//        for (String str : list){
//            MainBannerBean mainBannerBean = new MainBannerBean();
//            mainBannerBean.setUrl(str);
//            mainBannerBean.setUsername("hhh");
//            mainBannerBean.setHeadUrl("https://images.unsplash.com/photo-1533369257659-6132475f793c?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=1c926464dc14a8282bdb1dad352424a6&auto=format&fit=crop&w=654&q=80");
//            mainBannerBean.setTitle("今日推荐");
//        }

        if (basePresenter == null) {
            basePresenter = new BasePresenterImpl(getActivity(), this);
        }

        if(!TextUtils.isEmpty(edContent.getText())){
            String url = HttpConfig.URL_API_SPORT_TYPE;
            TopTabBean topTabBean = new TopTabBean();
            topTabBean.setName(edContent.getText().toString());
            String json = JSON.toJSONString(topTabBean);
            postLoad(new BaseBean(url, json, 1001, true, null));
        }
    }

    private void addUser() {
    }

    private void initUserInfo(){
        if(LoginUtils.isLogin(getContext())){
            imgHead.setImageURL(MyApplication.getUserLoginBean().getData().getAvatarUrl());
            frescoImageBg.showUrlBlur(MyApplication.getUserLoginBean().getData().getAvatarUrl(), 10, 5);
            tvUserName.setText(MyApplication.getUserLoginBean().getData().getUsername());
        }

    }
}
