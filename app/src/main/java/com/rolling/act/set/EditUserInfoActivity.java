package com.rolling.act.set;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.hjq.permissions.Permission;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.rolling.R;
import com.rolling.aop.Permissions;
import com.rolling.aop.SingleClick;
import com.rolling.app.MyApplication;
import com.rolling.base.view.BaseActivity;
import com.rolling.bean.BaseDataBean;
import com.rolling.bean.other.QiNiuTokenBean;
import com.rolling.bean.user.UserLoginBean;
import com.rolling.event.UploadUserInfoEvent;
import com.rolling.net.HttpConfig;
import com.rolling.net.HttpManage;
import com.rolling.util.CineLog;
import com.rolling.view.FrescoImage;
import com.rolling.view.dialog.InputDialog;
import com.rolling.view.layout.ToolbarView;
import com.rolling.view.widget.SettingBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class EditUserInfoActivity extends BaseActivity {

    private final int NET_GET_QINIU_TOKEN = 1001;

    private final int NET_UPLOAD_INFO = 1002;


    private final int REQUEST_IMAGE = 100;

    @BindView(R.id.iv_person_data_avatar)
    FrescoImage iv_person_data_avatar;

    @BindView(R.id.rootToobarView)
    ToolbarView rootToobarView;

    @BindView(R.id.sb_person_data_name)
    SettingBar mNameView;

    String locImgPath;

    String avatarUrl;

    @Override
    public int getLayoutContextView() {
        return R.layout.activity_edit_user_info;
    }

    @Override
    public void init() {
        EventBus.getDefault().register(this);
        rootToobarView.setiOnClickToolBarRight(this);
    }

    @Override
    public void succeed(Object o, int tag) {
        switch (tag) {
            case NET_GET_QINIU_TOKEN:
                QiNiuTokenBean qiNiuTokenBean = JSON.parseObject(o.toString(), QiNiuTokenBean.class);
                if (qiNiuTokenBean != null) {
                    CineLog.e(qiNiuTokenBean.toString());
                    upLoadQiNiu(qiNiuTokenBean);
                }
                break;
            case NET_UPLOAD_INFO:
                BaseDataBean baseDataBean = JSON.parseObject(o.toString(), BaseDataBean.class);
                if (baseDataBean != null) {
                    if (baseDataBean.getCode() == 0) {
                        EventBus.getDefault().post(new UploadUserInfoEvent(true));
                    }
                }
                break;
        }
    }

    @Override
    public void error(Object o, int tag) {

    }

    @SingleClick
    @OnClick({R.id.iv_person_data_avatar, R.id.sb_person_data_name})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.iv_person_data_avatar:
                startSelectorImg();
                break;
            case R.id.sb_person_data_name:
                new InputDialog.Builder(this)
                        // 标题可以不用填写
                        .setTitle(getString(R.string.personal_data_name_hint))
                        .setContent(mNameView.getRightText())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener((dialog, content) -> {
                            if (!mNameView.getRightText().equals(content)) {
                                mNameView.setRightText(content);
                            }
                        })
                        .show();
                break;
        }
    }

    @Permissions({Permission.CAMERA, Permission.MANAGE_EXTERNAL_STORAGE})
    private void startSelectorImg() {
        MultiImageSelector.create()
                .single()
                .showCamera(false)
                .start(this, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_IMAGE) {
            switch (resultCode) {
                case RESULT_OK:
                    List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    if (path != null) {
                        if (path.size() > 0) {
                            locImgPath = path.get(0);
                            iv_person_data_avatar.setImageFilePath2(locImgPath);
                            getQiNiuToken();
                        }
                    }
                    break;
                case NET_UPLOAD_INFO:
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 上传图片到七牛
     */
    private void upLoadQiNiu(QiNiuTokenBean bean) {
//
        UploadManager uploadManager = new UploadManager();


        uploadManager.put(locImgPath, null, bean.getData().getToken(),
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        if (info.isOK()) {
                            CineLog.e("上传成功" + info + "\n" + res);
                            try {
                                avatarUrl = bean.getData().getHost() + res.getString("key");
                                CineLog.e("*********\n" + avatarUrl);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            CineLog.e("上传失败" + info);
                        }
                    }
                }, null);
    }

    private void getQiNiuToken() {
        getLoad(HttpConfig.URL_API_QINIU_TOKEN, new String[]{}, new String[]{}, NET_GET_QINIU_TOKEN, true);
    }

    private void uploadUserInfo() {
        UserLoginBean.DataBean dataBean = new UserLoginBean.DataBean();
        dataBean.setId(MyApplication.getUserLoginBean().getData().getId());
        if (!TextUtils.isEmpty(mNameView.getRightText())) {
            dataBean.setNickName(mNameView.getRightText().toString());
        }
        if (!TextUtils.isEmpty(avatarUrl)) {
            dataBean.setAvatarUrl(avatarUrl);
        }
        String json = JSON.toJSONString(dataBean);
        postLoad(HttpConfig.URL_API_USER + "/" + MyApplication.getUserLoginBean().getData().getId(), json, NET_UPLOAD_INFO, true, HttpManage.PUT);
    }

    @Override
    public void onClickToolBarRight() {
        uploadUserInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void refreshView(UploadUserInfoEvent uploadUserInfoEvent){
        if(uploadUserInfoEvent.isSucceed()){
            finish();
        }
    }
}