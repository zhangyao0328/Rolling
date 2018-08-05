package com.rollling.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.rollling.R;


/**
 * @author zhangyao
 * @date 2017/11/20:16:40
 * @E-mail android_n@163.com
 */

public class LoadingDialog extends AlertDialog {

    TextViewIcon tvMessage;

    Context mContext;

    String msg;

    public LoadingDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public LoadingDialog(Context context, int theme){
        super(context, theme);
        this.mContext = context;
    }

    public LoadingDialog(Context context, int theme, String str){
        super(context, theme);
        this.msg = str;
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext, R.layout.view_spin_kit,null);
        tvMessage = view.findViewById(R.id.tvMessage);
        setContentView(view);
        setCanceledOnTouchOutside(false);

        if(!TextUtils.isEmpty(msg)){
            tvMessage.setText(msg);
            tvMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void dismiss() {
        if(this != null){
            super.dismiss();
        }
    }

    @Override
    public void show() {
        if(this != null){
            super.show();
        }
    }
}
