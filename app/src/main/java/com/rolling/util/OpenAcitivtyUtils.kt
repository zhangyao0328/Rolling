package com.rolling.util

import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * @author zhangyao
 * @date 2020-03-12  11:42
 * @E-mail android_n@163.com
 * 公共跳转工具类
 */
object OpenAcitivtyUtils {


    fun openAct(mContext: Context, act: Class<*>?, bundle: Bundle?) {
        if (bundle != null) {
            val intent = Intent(mContext, act)
            intent.putExtras(bundle)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            mContext.startActivity(intent)
        } else {
            openAct(mContext, act)
        }
    }

    @JvmStatic
    fun openAct(mContext: Context, act: Class<*>?) {
        val intent = Intent(mContext, act)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mContext.startActivity(intent)
    }

    fun openAct(mContext: Context, act: Class<*>?, bundle: Bundle?, flag: Int) {
        if (bundle != null) {
            val intent = Intent(mContext, act)
            intent.putExtras(bundle)
            intent.addFlags(flag or Intent.FLAG_ACTIVITY_NEW_TASK)
            mContext.startActivity(intent)
        } else {
            openAct(mContext, act)
        }
    }

    fun openAct(mContext: Context, act: Class<*>?, flag: Int) {
        val intent = Intent(mContext, act)
        intent.addFlags(flag or Intent.FLAG_ACTIVITY_NEW_TASK)
        mContext.startActivity(intent)
    }
}