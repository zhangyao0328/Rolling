package com.rollling.net;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhangyao
 * @date 2017/11/20:10:31
 * @E-mail android_n@163.com
 */

public abstract class ResultCallback<T> {

    Type mType;

    public ResultCallback() {

        mType = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public void onBefore(Request request) {

    }

    public void onAfter() {

    }

    public abstract void onError(Response response, Exception e, int tag);

    public abstract void onResponse(T resbase, int tag);

    public abstract void onFailure(Call call, IOException e, int tag);

}
