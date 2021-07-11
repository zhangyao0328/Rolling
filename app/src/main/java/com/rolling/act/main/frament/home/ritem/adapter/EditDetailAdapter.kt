package com.rolling.act.main.frament.home.ritem.adapter

import android.content.Context
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.google.android.flexbox.FlexboxLayout
import com.rolling.app.MyApplication
import com.rolling.bean.home.ImagesBean
import com.rolling.view.FrescoImage
import com.rolling.view.adapter.BaseStandardAdapter
import com.rolling.view.adapter.BaseViewHolder
import com.rolling.view.recvcler.ItemTouchHelperInter
import java.util.*
import com.rolling.R
import com.rolling.util.AppUtils

/**
 *  @author zhangyao
 *  @date 4/14/21  10:23 AM
 *  @E-mail android_n@163.com
 */
class EditDetailAdapter(context: Context) : BaseStandardAdapter<ImagesBean, BaseViewHolder<*>?>(context), ItemTouchHelperInter {
    override fun convert(holder: BaseViewHolder<*>?, item: ImagesBean) {
        holder as EditDetailHolder
        holder.buildData(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return EditDetailHolder(mLayoutInflater.inflate(R.layout.item_edit_detail_adapter, parent, false))
    }


    inner class EditDetailHolder(itemView: View) : BaseViewHolder<ImagesBean>(itemView) {

        @BindView(R.id.frescoImage)
        lateinit var frescoImage: FrescoImage

        override fun buildData(t: ImagesBean) {
            frescoImage.layoutParams = RelativeLayout.LayoutParams(AppUtils.getScreenWidth(MyApplication.getInstance()) / 5, AppUtils.getScreenWidth(MyApplication.getInstance()) / 5)
            frescoImage.setImageURL(t.url)
        }


    }

    override fun onItemChange(formPos: Int, toPos: Int) {
        Collections.swap(this.dataList, formPos, toPos)
        notifyItemMoved(formPos, toPos)
    }

    override fun onItemDelete(pos: Int) {
        this.dataList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}


