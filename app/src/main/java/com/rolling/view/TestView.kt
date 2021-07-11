package com.rolling.view

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 *  @author zhangyao
 *  @date 4/7/21  10:04 PM
 *  @E-mail android_n@163.com
 */
 class TestView {

    companion object {
        fun isEmpty(str: String): Boolean {
            return "" == str
        }
        fun get() : TestView{
            return  Holder.intance
        }

    }



    private object Holder{
        val intance = TestView()
    }
}