package com.rolling.act.main.frament.home.ritem.holder

import android.os.Bundle
import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.rolling.R
import com.rolling.act.main.frament.home.ritem.RItemActivity
import com.rolling.act.set.SysSetActivity
import com.rolling.app.MyApplication
import com.rolling.bean.home.Item
import com.rolling.util.OpenAcitivtyUtils
import com.rolling.util.OpenAcitivtyUtils.openAct
import com.rolling.view.FrescoImage
import com.rolling.view.TextViewIcon
import com.rolling.view.adapter.BaseViewHolder

/**
 *  @author zhangyao
 *  @date 2/16/21  8:35 PM
 *  @E-mail android_n@163.com
 */
class RItemHolder(itemView: View?) : BaseViewHolder<Item>(itemView) {

    @BindView(R.id.tvTitle)
    lateinit var tvTitle: TextViewIcon

    @BindView(R.id.frescoCover)
    lateinit var frescoCover: FrescoImage

    private lateinit var itemData : Item

    override fun buildData(t: Item?) {
        if (t != null) {
            itemData = t
            tvTitle.text = t.Id.toString() + "    __________" + t.Name
            frescoCover.setImageURL(t.Cover)
        }
    }

    @OnClick(R.id.itemRootView)
    fun onClicks(view: View){
        when (view.id){
            R.id.itemRootView -> {
                var bundle = Bundle()
                bundle.putSerializable(RItemActivity::class.java.name, itemData)
                openAct(MyApplication.getInstance(), RItemActivity::class.java, bundle)
            }
        }
    }
}