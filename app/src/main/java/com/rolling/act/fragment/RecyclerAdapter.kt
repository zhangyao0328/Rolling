package com.rolling.act.fragment

import android.content.Context
import android.view.ViewGroup
import com.rolling.R
import com.rolling.act.main.frament.home.adapter.holder.FilterHolder
import com.rolling.act.main.frament.home.adapter.holder.TicketHolder
import com.rolling.bean.home.HomeDataBean
import com.rolling.bean.home.Item
import com.rolling.view.adapter.BaseStandardAdapter
import com.rolling.view.adapter.BaseViewHolder

/**
 *  @author zhangyao
 *  @date 1/29/21  11:34 AM
 *  @E-mail android_n@163.com
 */
class RecyclerAdapter(context: Context) : BaseStandardAdapter<Item?, BaseViewHolder<*>>(context) {
    override fun convert(holder: BaseViewHolder<*>, item: Item?) {
        if (item == null) {
            return
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return TicketHolder(mLayoutInflater.inflate(R.layout.item_ticket_holder, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return mDataList[position]!!.ViewType
    }
}