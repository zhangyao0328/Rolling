package com.rolling.act.main.frament.home

import android.content.Context
import android.view.ViewGroup
import com.rolling.R
import com.rolling.act.main.frament.home.adapter.holder.FilterHolder
import com.rolling.act.main.frament.home.adapter.holder.TicketHolder
import com.rolling.bean.home.HomeDataBean.DataBean.UserListBean
import com.rolling.view.adapter.BaseStandardAdapter
import com.rolling.view.adapter.BaseViewHolder

/**
 * @author zhangyao
 * @date 2020/11/4  12:21
 * @E-mail android_n@163.com
 */
class HomeAdapter(context: Context) : BaseStandardAdapter<UserListBean?, BaseViewHolder<*>>(context) {
    @JvmField
    var TYPE_FILTER = 3
    override  fun convert(holder: BaseViewHolder<*>, item: UserListBean?) {
        if(item == null){
            return
        }
        if (item.viewType == TYPE_FILTER) {
            val filterHolder = holder as FilterHolder
        } else {
            val ticketHolder = holder as TicketHolder
            ticketHolder.buildData(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return if (viewType == TYPE_FILTER) {
            FilterHolder(mLayoutInflater.inflate(R.layout.layout_filter_view, parent, false))
        } else {
            TicketHolder(mLayoutInflater.inflate(R.layout.item_ticket_holder, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mDataList[position]!!.viewType
    }
}