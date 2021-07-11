package com.rolling.view.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
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

    IOnClickToolBarRight iOnClickToolBarRight;

    public ToolbarView(Context context) {
        super(context);
        initView();
    }

    public ToolbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.layout_toolbar);
           if(typedArray.hasValue(R.styleable.layout_toolbar_arrow_left)){
               tvToolbarBack.setCompoundDrawablesWithIntrinsicBounds(typedArray.getDrawable(R.styleable.layout_toolbar_arrow_left), null, null
               , null);
           }
            tvToolbarTitle.setText(typedArray.getString(R.styleable.layout_toolbar_title));
            String right = typedArray.getString(R.styleable.layout_toolbar_arrow_right);
            if (!TextUtils.isEmpty(right)) {
                tvToolbarRight.setVisibility(VISIBLE);
                tvToolbarRight.setText(right);
            }

        }

    }


    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.toolbar_layout, this, true);
        ButterKnife.bind(this, view);
    }

    public void setiOnClickToolBarRight(IOnClickToolBarRight iOnClickToolBarRight) {
        this.iOnClickToolBarRight = iOnClickToolBarRight;
    }

    @OnClick({R.id.tvToolbarBack, R.id.tvToolbarRight})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.tvToolbarBack:
                if (getContext() instanceof BaseActivity) {
                    ((BaseActivity) getContext()).onBackPressed();
                }
                break;
            case R.id.tvToolbarRight:
                if (iOnClickToolBarRight != null) {
                    iOnClickToolBarRight.onClickToolBarRight();
                }
                break;
        }
    }

    public interface IOnClickToolBarRight {
        void onClickToolBarRight();
    }
}
