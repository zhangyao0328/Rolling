package com.rolling.act.main.frament.home.ritem.adapter

import android.content.Context
import android.view.ViewGroup
import com.rolling.R
import com.rolling.act.main.frament.home.adapter.holder.TicketHolder
import com.rolling.act.main.frament.home.ritem.holder.RItemHolder
import com.rolling.bean.home.Item
import com.rolling.view.adapter.BaseStandardAdapter
import com.rolling.view.adapter.BaseViewHolder

/**
 *  @author zhangyao
 *  @date 2/16/21  8:30 PM
 *  @E-mail android_n@163.com
 *  一个RItem项目adapter
 */
class RItemAdapter(context: Context) : BaseStandardAdapter<Item, BaseViewHolder<*>?>(context) {
    override fun convert(holder: BaseViewHolder<*>?, item: Item) {
        holder as RItemHolder
        holder.buildData(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return RItemHolder(mLayoutInflater.inflate(R.layout.item_r_item_holder, parent, false))
    }

}