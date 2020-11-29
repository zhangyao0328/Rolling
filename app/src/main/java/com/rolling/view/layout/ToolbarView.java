package com.rolling.view.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.rolling.R;
import com.rolling.base.view.BaseActivity;
import com.rolling.view.TextViewIcon;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zhangyao
 * @date 2020/11/17  21:50
 * @E-mail android_n@163.com
 */
public class ToolbarView extends RelativeLayout {

    @BindView(R.id.tvToolbarBack)
    TextViewIcon tvToolbarBack;

    @BindView(R.id.tvToolbarTitle)
    TextViewIcon tvToolbarTitle;

    @BindView(R.id.tvToolbarRight)
    TextViewIcon tvToolbarRight;

    public ToolbarView(Context context) {
        super(context);
        initView();
    }

    public ToolbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.layout_toolbar);
            tvToolbarBack.setText(typedArray.getString(R.styleable.layout_toolbar_arrow_left));
            tvToolbarTitle.setText(typedArray.getString(R.styleable.layout_toolbar_title));
            tvToolbarRight.setText(typedArray.getString(R.styleable.layout_toolbar_arrow_right));
        }

    }


    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.toolbar_layout, this, true);
        ButterKnife.bind(this, view);
    }

    @OnClick({R.id.tvToolbarBack})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.tvToolbarBack:
                if (getContext() instanceof BaseActivity) {
                    ((BaseActivity) getContext()).onBackPressed();
                }
                break;
        }
    }
}
