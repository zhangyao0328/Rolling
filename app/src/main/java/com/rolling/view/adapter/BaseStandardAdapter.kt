package com.rolling.view.adapter

import android.content.Context

/**
 * @author zhangyao
 * @date 2017/11/26:00:17
 * @E-mail android_n@163.com
 */
abstract class BaseStandardAdapter<T, VH : BaseViewHolder<*>?>(context: Context) : BaseAdapter<T, BaseViewHolder<*>?>(context) {
    var isEmpty = false
     var mContext = context
    var checkPosition = -1
    protected fun bindHolder(holder: VH, position: Int) {
        convert(holder as VH, dataList[position])
    }

    abstract fun convert(holder: VH, item: T)
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        holder.itemView.isClickable = true
        bindHolder(holder as VH, position)
    }

    companion object {
        const val TYPE_EMPTY = -1
    }

}