package com.rolling.view.viewpage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;
import com.rolling.R;

/**
 * @author zhangyao
 * @date 2017/11/24:01:32
 * @E-mail android_n@163.com
 */

public class RefreshHeaderView extends SwipeRefreshHeaderLayout {

//    private ImageView ivArrow;

    private TextView tvRefresh;

    private ProgressBar progressBar;

    private int mHeaderHeight;

    private Animation rotateUp;

    private Animation rotateDown;

    private boolean rotated = false;

//    private ImageView imgRefresh;


    public RefreshHeaderView(Context context) {
        this(context, null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_60);
        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
//        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

//        imgRefresh = (ImageView) findViewById(R.id.imgRefresh);

        addShowProgress();
    }

    @Override
    public void onRefresh() {
//        ivArrow.clearAnimation();
//        ivArrow.setVisibility(GONE);
//        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText("正在刷新...");
    }

    @Override
    public void onPrepare() {
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
//            ivArrow.setVisibility(VISIBLE);
//            progressBar.setVisibility(GONE);
            if (y > mHeaderHeight) {
                tvRefresh.setText("释放刷新");
                if (!rotated) {
//                    ivArrow.clearAnimation();
//                    ivArrow.startAnimation(rotateUp);
                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {
//                    ivArrow.clearAnimation();
//                    ivArrow.startAnimation(rotateDown);
                    rotated = false;
                }
                tvRefresh.setText("下拉刷新");
            }
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        rotated = false;
//        ivArrow.clearAnimation();
//        ivArrow.setVisibility(GONE);
//        progressBar.setVisibility(GONE);
        tvRefresh.setText("刷新完成");

//        if(circularProgressDrawable != null){
//            circularProgressDrawable.start();
//        }

    }

    @Override
    public void onReset() {
        rotated = false;
//        ivArrow.clearAnimation();
//        ivArrow.setVisibility(GONE);
//        progressBar.setVisibility(GONE);
        tvRefresh.setText("下拉刷新");


//        if(circularProgressDrawable != null){
//            circularProgressDrawable.stop();
//        }

    }

    private void addShowProgress(){
//        circularProgressDrawable = new MaterialProgressDrawable(getContext(),imgRefresh );
////        circularProgressDrawable.setBackgroundColor(ContextCompat.getColor(MyApplication.getInstance(), R.color.color_tab_bottom_navigation_bar_active_n));
//
//
//        int[] colors = {R.color.clorTextGreen, R.color.color4999AD};
//        circularProgressDrawable.setColorSchemeColors(colors);
//        circularProgressDrawable.setProgressRotation(0f);
//        //圆环范围，0-1
//        circularProgressDrawable.setStartEndTrim(0f,  1f);
//        //        circularProgressDrawable.setStrokeWidth(10f);
////        circularProgressDrawable.setStrokeCap(Paint.Cap.ROUND);
////        circularProgressDrawable.setCenterRadius(50f);
//        circularProgressDrawable.showArrow(true);
//        imgRefresh.setImageDrawable(circularProgressDrawable);

    }
}
