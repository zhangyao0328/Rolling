package com.rolling.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

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

    /**
     * 以高斯模糊显示。
     *
     * @param url        url.
     * @param iterations 迭代次数，越大越魔化。
     * @param blurRadius 模糊图半径，必须大于0，越大越模糊。
     */
    public void showUrlBlur(String url, int iterations, int blurRadius) {
        try {
            if (!TextUtils.isEmpty(url)) {
                Uri uri = Uri.parse(url);
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setPostprocessor(new IterativeBoxBlurPostProcessor(iterations, blurRadius))
                        .build();
                AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setOldController(getController())
                        .setImageRequest(request)
                        .build();
                setController(controller);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
