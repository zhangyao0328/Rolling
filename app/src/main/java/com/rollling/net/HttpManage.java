package com.rollling.net;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.rollling.app.MyApplication;
import com.rollling.bean.BaseBean;
import com.rollling.util.CineLog;
import com.rollling.util.CineToast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.ConnectionSpec;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author zhangyao
 * @date 2017/11/20:10:08
 * @E-mail android_n@163.com
 */

public class HttpManage {

    private GetDelegate mGetDelegate = new GetDelegate();
    private PostDelegate mPostDelegate = new PostDelegate();
    private static HttpManage mInstance;
    private OkHttpClient okHttpClient;
    private Handler mHandler;

    public static String POST = "POST";

    public static String PUT = "PUT";

    public static String DELETE = "DELETE";

    public static String PATCH = "PATCH";

    public static String FILE = "FILE";

    public static String PUT_EDIT = "PUT_EDIT";

//    public static String WX_PAY = "WX_PAY";

    public final static int READ_TIMEOUT = 20;


    HttpManage() {
        ConnectionSpec connectionSpec = null;

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            connectionSpec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
//                    .tlsVersions(TlsVersion.TLS_1_2)
//                    .cipherSuites(
//                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
//                    .build();
//        } else {
//            connectionSpec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
//                    .tlsVersions(TlsVersion.TLS_1_0)
//                    .cipherSuites(CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA)
//                    .build();
//        }

//        if (connectionSpec != null) {
//            okHttpClient = new OkHttpClient.Builder()
//                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
//                    .connectionSpecs(Collections.singletonList(connectionSpec)).build();
//            CineLog.e("已设置证书");
//        } else {
//            okHttpClient = new OkHttpClient.Builder()
//                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
//                    .build();
//            CineLog.e("未设置");
//        }

        okHttpClient = new OkHttpClient.Builder().build();

        mHandler = new Handler(Looper.getMainLooper());
    }

    public static HttpManage getInStance() {

        if (mInstance == null) {
            synchronized (HttpManage.class) {
                if (mInstance == null) {
                    mInstance = new HttpManage();
                }
            }
            mInstance = new HttpManage();
        }
        return mInstance;
    }

    public GetDelegate getmGetDelegate() {
        return mGetDelegate;
    }

    public PostDelegate postDelegate() {
        return mPostDelegate;
    }

    /**
     * ============GET方便的访问方式===============
     */
    public static void getAsyn(BaseBean baseBean, ResultCallback callback) {
        if (NetUtils.isNetworkConnected(MyApplication.getInstance())) {
            getInStance().getmGetDelegate().getAsyn(baseBean, callback);
        } else {
            CineToast.showShort("请检查网络连接");
        }

    }

    /**
     * ============POST方便的访问方式===============
     */
    public static void postAsyn(BaseBean baseBean, ResultCallback callback) {
        if (NetUtils.isNetworkConnected(MyApplication.getInstance())) {
            getInStance().postDelegate().postAsyn(baseBean, callback);
        } else {
            CineToast.showShort("请检查网络连接");
        }
    }

    /**
     * 请求成功返回数据
     *
     * @param object
     * @param callback
     * @param tag
     */
    private void sendSuccessResultCallback(final Object object, final ResultCallback callback, final int tag) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object, tag);
                callback.onAfter();
            }
        });
    }

    /**
     * 请求失败
     *
     * @param response
     * @param e
     * @param callback
     */
    private void sendErrorStringCallback(final Response response, final Exception e, final ResultCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response, e, (int) response.request().tag());
                callback.onAfter();
            }
        });
    }

    /**
     * 请求错误
     *
     * @param call
     * @param e
     * @param resultCallback
     */
    private void sendFailureStringCallback(final Call call, final IOException e, final ResultCallback resultCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                resultCallback.onFailure(call, e, (int) call.request().tag());
                resultCallback.onAfter();
            }
        });
    }


    public class GetDelegate {

        private Request buildGetRequest(BaseBean baseBean, ResultCallback resultCallback) {

            String url = baseBean.getUrl();
            if (baseBean.getKey() != null && baseBean.getValue() != null) {
                if (baseBean.getKey().length != baseBean.getValue().length) {
                    throw new IllegalArgumentException("check your Params key or value length!");
                }
                Map<String, String> mapParams = new TreeMap<>();
                if (baseBean.getKey().length > 0) {
                    StringBuffer strBf = new StringBuffer();
                    for (int i = 0; i < baseBean.getKey().length; i++) {
                        if (i == 0) {
                            strBf.append("?");
                        }
                        mapParams.put(baseBean.getKey()[i], baseBean.getValue()[i]);
                        strBf.append(baseBean.getKey()[i] + "=");
                        strBf.append(baseBean.getValue()[i]);
                        if (i != baseBean.getKey().length - 1) {
                            strBf.append("&");
                        }
                    }
                    url = baseBean.getUrl() + strBf.toString();
                }
            }
            Request.Builder builder = new Request.Builder();
            builder.tag(baseBean.getTag());
            builder.url(url);
            if (getHead().size() > 0) {
                for (Map.Entry<String, String> entry : getHead().entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            CineLog.e(url);
            return builder.build();
        }

        public void getAsyn(BaseBean baseBean, ResultCallback callback) {
            final Request request = buildGetRequest(baseBean, callback);
            deliveryResult(callback, request);
        }

        /**
         * 传递结果
         */
        private void deliveryResult(ResultCallback callback, Request request) {
            if (callback != null) {
                final ResultCallback resCallBack = callback;
                callback.onBefore(request);
                okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        sendFailureStringCallback(call, e, resCallBack);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            if (response.code() == 200) {
                                final String string = response.body().string();
                                if (resCallBack.mType == String.class) {
                                    sendSuccessResultCallback(string, resCallBack, (int) response.request().tag());
                                } else {
                                    Object o = JSON.parseObject(string, resCallBack.mType);
                                    sendSuccessResultCallback(o, resCallBack, (int) response.request().tag());
                                }

                            } else {
                                sendErrorStringCallback(response, null, resCallBack);
                            }


                        } catch (IOException e) {
                            sendErrorStringCallback(response, e, resCallBack);
                        }
                    }
                });
            }
        }
    }

    public class PostDelegate {

        /**
         * 异步的post请求，params为map
         */
        public void postAsyn(BaseBean baseBean, final ResultCallback callback) {
            Request request;
            if (TextUtils.isEmpty(baseBean.getStrJson())) {
                request = buildPostFormRequest(baseBean);
            } else {
                request = buildPostFormRequestJson(baseBean);
            }
            deliveryResult(callback, request);
        }

        /**
         * 以map形式post请求
         */
        private Request buildPostFormRequest(BaseBean baseBean) {

            Param[] params = map2Params(baseBean.getParams());
            if (params == null) {
                params = new Param[0];
            }
            FormBody.Builder builder = new FormBody.Builder();
            for (Param param : params) {
                String key = buildBussiness(param.key);
                builder.add(key, param.value);
                CineLog.e(param.key + "=" + param.value);
            }
            RequestBody requestBody = builder.build();
            Request.Builder reqBuilder = new Request.Builder();
            if (!TextUtils.isEmpty(baseBean.getMethod())) {
                if (baseBean.getMethod().equals(PUT)) {
                    reqBuilder.url(baseBean.getUrl())
                            .put(requestBody);
                }
                if (baseBean.getMethod().equals(DELETE)) {
                    reqBuilder.url(baseBean.getUrl())
                            .delete(requestBody);
                }
                if (baseBean.getMethod().equals(POST)) {
                    reqBuilder.url(baseBean.getUrl())
                            .post(requestBody);
                }
                if (baseBean.getMethod().equals(PATCH)) {
                    reqBuilder.url(baseBean.getUrl())
                            .patch(requestBody);
                }
                if (baseBean.getMethod().equals(FILE)) {
                    File file = new File(baseBean.getFileValue());
                    RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Builder rBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    rBody.addFormDataPart(baseBean.getFileKey(), file.getName() + ".jpg", fileBody);
                    for (Param param : params) {
                        String key = buildBussiness(param.key);
                        rBody.addFormDataPart(key, param.value);
                    }
                    reqBuilder.url(baseBean.getUrl())
                            .post(rBody.build())
                            .build();
                }
                if (baseBean.getMethod().equals(PUT_EDIT)) {
                    File file = new File(baseBean.getFileValue());
                    RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Builder rBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    rBody.addFormDataPart(baseBean.getFileKey(), file.getName() + ".jpg", fileBody);
                    for (Param param : params) {
                        String key = buildBussiness(param.key);
                        rBody.addFormDataPart(key, param.value);
                    }
                    reqBuilder.url(baseBean.getUrl())
                            .put(rBody.build())
                            .build();
                }
//                if (baseBean.getMethod().equals(WX_PAY)) {
//                    RequestBody formBody = RequestBody.create(MediaType.parse("contentType, text/xml"), WXConfig.toXml(baseBean.getParams()));
//                    reqBuilder.url(baseBean.getUrl())
//                            .post(formBody)
//                            .build();
//                }
            } else {
                reqBuilder.url(baseBean.getUrl())
                        .post(requestBody);
            }
            reqBuilder.tag(baseBean.getTag());
            if (getHead().size() > 0) {
                for (Map.Entry<String, String> entry : getHead().entrySet()) {
                    reqBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            CineLog.e(baseBean.getUrl());
            return reqBuilder.build();
        }

        /**
         * 以json形式post请求
         */
        private Request buildPostFormRequestJson(BaseBean baseBean) {

            Request.Builder reqBuilder = new Request.Builder();
            reqBuilder
                    .url(baseBean.getUrl())
                    .tag(baseBean.getTag());
            if (!TextUtils.isEmpty(baseBean.getMethod())) {
                if (baseBean.getMethod().equals(PUT)) {
                    reqBuilder.put(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), baseBean.getStrJson()));
                }
                if (baseBean.getMethod().equals(PATCH)) {
                    reqBuilder.patch(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), baseBean.getStrJson()));
                }
                if (baseBean.getMethod().equals(POST)) {
                    reqBuilder.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), baseBean.getStrJson()));
                }
            } else {
                reqBuilder.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), baseBean.getStrJson()));
            }
            if (getHead().size() > 0) {
                for (Map.Entry<String, String> entry : getHead().entrySet()) {
                    reqBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            CineLog.e("json=" + baseBean.getStrJson());
            CineLog.e(baseBean.getUrl());
            return reqBuilder.build();
        }

        private Param[] map2Params(Map<String, String> params) {
            if (params == null) {
                return new Param[0];
            }
            int size = params.size();
            Param[] res = new Param[size];
            Set<Map.Entry<String, String>> entries = params.entrySet();
            int i = 0;
            for (Map.Entry<String, String> entry : entries) {
                res[i++] = new Param(entry.getKey(), entry.getValue());
            }
            return res;
        }

        /**
         * 传递结果
         */
        private void deliveryResult(ResultCallback callback, final Request request) {
            if (callback != null) {
                CineLog.e(request.method());
                final ResultCallback resCallBack = callback;
                //UI thread
                callback.onBefore(request);
                //执行异步任务
                okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        sendFailureStringCallback(call, e, resCallBack);
                        CineLog.e("http异常=" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            if (response.code() == 200) {
                                final String string = response.body().string();
                                if (resCallBack.mType == String.class) {
                                    sendSuccessResultCallback(string, resCallBack, (int) response.request().tag());
                                } else {
                                    Object o = JSON.parseObject(string, resCallBack.mType);
                                    sendSuccessResultCallback(o, resCallBack, (int) response.request().tag());
                                }
                            } else {
                                sendErrorStringCallback(response, null, resCallBack);
                            }
                        } catch (IOException | JSONException e) {
                            sendErrorStringCallback(response, e, resCallBack);
                        }
                    }
                });
            }
        }
    }

    public static class Param {

        String key;
        String value;

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static Map<String, String> getHead() {
        Map<String, String> headMap = new HashMap<>();
        return headMap;
    }

    /**
     * 对作品上传-职位特殊处理
     * 不得已-后续优化
     *
     * @param key
     * @return
     */
    private String buildBussiness(String key) {
        if (key.contains("film[business_ids][")) {
            key = "film[business_ids][]";
        }
        return key;
    }
}
