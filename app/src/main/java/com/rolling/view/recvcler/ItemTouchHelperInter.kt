package com.rolling.view.recvcler

/**
 *  @author zhangyao
 *  @date 4/14/21  11:25 AM
 *  @E-mail android_n@163.com
 */
interface ItemTouchHelperInter {

    // 调换位置
    fun onItemChange(formPos: Int, toPos: Int)

    //滑动删除条目
    fun onItemDelete(pos: Int)
}