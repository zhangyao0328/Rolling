package com.rolling.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author zhangyao
 * @date 2018/8/5  00:07
 * @E-mail android_n@163.com
 */
public class FrescoImage extends SimpleDraweeView {

    public String mUrl = null;

    public FrescoImage(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public FrescoImage(Context context) {
        super(context);
    }

    public FrescoImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FrescoImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FrescoImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setImageURL(String url) {
        if (!TextUtils.isEmpty(url)) {
            this.mUrl = url;
            super.setImageURI(Uri.parse(url));
        }
    }

    public void setImageURLFilePath(Uri url) {
        if (!TextUtils.isEmpty(url.getPath())) {
            this.mUrl = url.getPath();
            super.setImageURI(url);
        }
    }

}
