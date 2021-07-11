package com.rolling.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

/**
 *  @author zhangyao
 *  @date 5/20/21  9:51 AM
 *  @E-mail android_n@163.com
 */
class TestView : ViewGroup {

    constructor (context: Context) : super(context)
    constructor (context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor (context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //度量孩子
        for(i in 0..childCount){
           val childView = getChildAt(i)
            val childLP  = childView.layoutParams
            childView.measure(widthMeasureSpec, heightMeasureSpec)
        }




        //度量自己
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        for ( it in 0..childCount){

        }
    }


}