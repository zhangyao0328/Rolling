package com.yaoyao.yaolibrary.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.yaoyao.yaolibrary.R


/**
 *  @author zhangyao
 *  @date 4/9/21  11:00 AM
 *  @E-mail android_n@163.com
 */
class SettingBarEditText @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SettingBar(context, attrs, defStyleAttr) {


    init {

        val array = getContext().obtainStyledAttributes(attrs, R.styleable.SettingBar)

        var edRight = EditText(context)
        rightView.visibility = GONE

        // 提示设置
        if (array.hasValue(R.styleable.SettingBar_bar_rightEdHintText)) {
            edRight.setHint(array.getString(R.styleable.SettingBar_bar_rightEdHintText))
        }
        if (array.hasValue(R.styleable.SettingBar_bar_rightEdBg)) {
            edRight.background = (array.getDrawable(R.styleable.SettingBar_bar_rightEdBg))
        }else{
            edRight.background = null
        }

        // 文字颜色设置
        edRight.setTextColor(array.getColor(R.styleable.SettingBar_bar_rightColor, ContextCompat.getColor(getContext(), R.color.black60)))

        // 文字大小设置
        edRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, array.getDimensionPixelSize(R.styleable.SettingBar_bar_rightSize, 14).toFloat())


        var rightParams = LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT)
        rightParams.gravity = Gravity.CENTER_VERTICAL
        rightParams.weight = 1f
        edRight.gravity = Gravity.RIGHT
        mainLayout.addView(edRight, rightParams)
    }


}