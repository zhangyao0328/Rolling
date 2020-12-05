package com.rolling.act.set;

import android.content.Intent;
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
import com.rolling.bean.other.QiNiuTokenBean;
import com.rolling.bean.user.UserLoginBean;
import com.rolling.net.HttpConfig;
import com.rolling.net.HttpManage;
import com.rolling.util.CineLog;
import com.rolling.view.FrescoImage;
import com.rolling.view.layout.ToolbarView;

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

    String locImgPath;

    @Override
    public int getLayoutContextView() {
        return R.layout.activity_edit_user_info;
    }

    @Override
    public void init() {
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
        }
    }

    @Override
    public void error(Object o, int tag) {

    }

    @SingleClick
    @OnClick({R.id.iv_person_data_avatar})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.iv_person_data_avatar:
                startSelectorImg();
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
                                String url = bean.getData().getHost() + res.getString("key");
                                CineLog.e("*********\n" + url);
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
        dataBean.setUsername("测试修改");
        String json = JSON.toJSONString(dataBean);
        postLoad(HttpConfig.URL_API_USER + "/" + MyApplication.getUserLoginBean().getData().getId(), json, NET_UPLOAD_INFO, true, HttpManage.PUT);
    }

    @Override
    public void onClickToolBarRight() {
        uploadUserInfo();
    }
}