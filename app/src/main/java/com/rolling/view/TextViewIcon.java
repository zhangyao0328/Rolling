package com.rolling.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.rolling.util.AppUtils;

/**
 * @author zhangyao
 * @date 2018/8/2  18:59
 * @E-mail android_n@163.com
 */
@SuppressLint("AppCompatCustomView")
public class TextViewIcon extends TextView implements ITextView{

    Context mContext;

    public TextViewIcon(Context context) {
        super(context);
        this.mContext = context;
        super.setTypeface(AppUtils.getIconfont());
    }

    public TextViewIcon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        super.setTypeface(AppUtils.getIconfont());
    }

    public TextViewIcon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        super.setTypeface(AppUtils.getIconfont());
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(AppUtils.getIconfont(), style);
    }

    @Override
    public void setTexts(String str) {
        if(!TextUtils.isEmpty(str)){
            setText(str);
        }
    }

    @Override
    public void setTexts(int str) {
        setText(mContext.getResources().getString(str));
    }

    @Override
    public void setTextInt(int str) {
        setText(String.valueOf(str));
    }

}
