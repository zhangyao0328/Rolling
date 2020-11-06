package com.rolling.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author zhangyao
 * @date 2018/8/5  16:26
 * @E-mail android_n@163.com
 * 渐变-黑白黑
 */
public class Black2White2BlackGradint extends View {

    public Black2White2BlackGradint(Context context) {
        super(context);
    }

    public Black2White2BlackGradint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int colorStart = Color.BLACK;
        int color1 = Color.TRANSPARENT;
        int colorEnd = Color.BLACK;

        Paint paint = new Paint();
        LinearGradient backGradient = new LinearGradient(
                0, 0, 0, height,
                new int[]{colorStart, color1, colorEnd},
                new float[]{0f, 0.8f, 1f},
                Shader.TileMode.CLAMP);
        paint.setShader(backGradient);
        paint.setAlpha(120);
        canvas.drawRect(0, 0, width, height, paint);
    }
}
