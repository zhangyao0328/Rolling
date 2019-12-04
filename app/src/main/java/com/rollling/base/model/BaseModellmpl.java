package com.rollling.base.model;

import android.content.Context;

import com.rollling.R;
import com.rollling.base.view.BaseView;
import com.rollling.bean.BaseBean;
import com.rollling.net.HttpManage;
import com.rollling.net.ResultCallback;
import com.rollling.util.CineLog;
import com.rollling.util.CineToast;
import com.rollling.view.LoadingDialog;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhangyao
 * 2017/11/18
 * android_n@163.com
 */

public class BaseModellmpl implements BaseModel {

    BaseView mBaseView;


    public BaseModellmpl(Context context, BaseView baseView) {
        this.mBaseView = baseView;
    }

    @Override
    public void loadGet(Context context, BaseBean baseBean) {
        HttpManage.getAsyn(baseBean, new MyResultCallback<String>(context, mBaseView, baseBean) {
            @Override
            public void onError(Response response, Exception e, int tag) {
                onErrors(response, e, tag);
            }

            @Override
            public void onResponse(Object resbase, int tag) {
                onResponses(resbase, tag);
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e, int tag) {
                onFailures(call, e, tag);
            }
        });
    }

    @Override
    public void loadPost(Context context, BaseBean baseBean) {
        HttpManage.postAsyn(baseBean, new MyResultCallback<String>(context, mBaseView, baseBean) {
            @Override
            public void onError(Response response, Exception e, int tag) {
                onErrors(response, e, tag);
            }

            @Override
            public void onResponse(Object resbase, int tag) {
                onResponses(resbase, tag);
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e, int tag) {
                onFailures(call, e, tag);
            }
        });
    }


    public abstract class MyResultCallback<T> extends ResultCallback {

        BaseView baseView;
        BaseBean baseBean;

        LoadingDialog loadingDialog;


        MyResultCallback(Context mContext, BaseView bs, BaseBean baseBeans) {
            this.baseView = bs;
            this.baseBean = baseBeans;

            loadingDialog = new LoadingDialog(mContext, R.style.loadingDialog);
        }

        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            if (baseBean.isDoalog()) {
                loadingDialog.show();
            }
        }

        @Override
        public void onAfter() {
            super.onAfter();
            if (baseBean.isDoalog()) {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        }
    }

    private void onErrors(Response response, Exception e, int tag) {
        mBaseView.error(response, tag);
        mBaseView.responseCode(response.code());
        try {
            byte[] responseBytes = response.body().bytes();
            String responseUrl = new String(responseBytes);
            CineLog.e("onErrors-" + response.code() + response.message() + "\n" + responseUrl + "\n" + tag);
            CineToast.showLong(R.string.net_error_failures);
        } catch (Exception es) {
            CineToast.showLong(R.string.net_error_failures);
        }
    }

    private void onResponses(Object response, int tag) {
        mBaseView.succeed(response.toString(), tag);
        CineLog.e(response.toString());
    }

    private void onFailures(Call call, IOException e, int tag) {
        mBaseView.error(new Response.Builder(), tag);
        CineLog.e("onFailures-" + "请求失败" + "-" + tag + "-" + call.request().url());
        CineToast.showLong(R.string.net_error_failures);
    }

}
