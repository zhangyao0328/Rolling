package com.rolling.act.main.frament.home.ritem.holder

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import butterknife.BindView
import com.rolling.R
import com.rolling.act.main.frament.home.ritem.adapter.EditDetailAdapter
import com.rolling.base.view.BaseActivity
import com.rolling.bean.home.ImagesBean
import com.rolling.view.recvcler.ItemTouchHelperCallback
import com.rolling.view.recvcler.RlRecyclerView

class EditDetailActivity : BaseActivity() {

    @BindView(R.id.rlRecyclerView)
    lateinit var rlRecyclerView: RlRecyclerView

    lateinit var adapter: EditDetailAdapter


    override fun succeed(t: Any?, tag: Int) {
    }

    override fun error(t: Any?, tag: Int) {
    }

    override fun getLayoutContextView(): Int {
        return R.layout.activity_edit_detail
    }

    override fun init() {

        adapter = EditDetailAdapter(this)
        rlRecyclerView.setLayoutManager(GridLayoutManager(this, 5))
        rlRecyclerView.adapter = adapter
        repeat(8) {
            if (it == 7) {
                adapter.addItem(ImagesBean("https://paipianbang.cdn.cinehello.com/default/avatar.png", false))
            }else{
                adapter.addItem(ImagesBean("https://paipianbang.cdn.cinehello.com/default/avatar.png", true))
            }
        }

        var itemTouchHelperCallback = ItemTouchHelperCallback(adapter)
        var itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(rlRecyclerView)

    }

}