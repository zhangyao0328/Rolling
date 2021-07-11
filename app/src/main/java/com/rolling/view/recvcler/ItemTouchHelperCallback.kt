package com.rolling.view.recvcler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.rolling.act.main.frament.home.ritem.adapter.EditDetailAdapter

/**
 *  @author zhangyao
 *  @date 4/14/21  11:31 AM
 *  @E-mail android_n@163.com
 */
class ItemTouchHelperCallback(mAdapter: EditDetailAdapter) : ItemTouchHelper.Callback() {
    private val mAdapter: EditDetailAdapter

    var isMove = true

    init {
        this.mAdapter = mAdapter
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {

        isMove = mAdapter.getItemData(viewHolder.adapterPosition).isMove

        if(isMove){

            val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT//上下拖动
            val swipeFlag = ItemTouchHelper.LEFT //从右向左
            return makeMovementFlags(dragFlag, swipeFlag)
        }

        return ItemTouchHelper.ACTION_STATE_IDLE
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {


        if(!mAdapter.getItemData(target.adapterPosition).isMove){
            return false
        }

        if(mAdapter.getItemData(target.adapterPosition).isMove) {
            mAdapter.onItemChange(viewHolder.getAdapterPosition(), target.getAdapterPosition())
        }
        return true //返回true 表示目标viewholder已经移到目标位置
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
        //返回true支持长按拖动 false不支持
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true //返回true支持滑动 false不支持
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mAdapter.onItemDelete(viewHolder.getAdapterPosition())
    }



}