package com.rolling.util

/**
 *  @author zhangyao
 *  @date 5/10/21  5:03 PM
 *  @E-mail android_n@163.com
 */

val isView: Boolean = true

fun pri() = print(isView)


class TestKtlin(id: Int) {


    val size = 1


    constructor(name: Int, sex: Int, id: Int) : this(0) {

    }

    companion object {
        const val site = "ni"
    }

    fun s() {
        var list1 = arrayOf(4)
        var list2: Any = list1
        var list3 = arrayListOf("a")
        list3.add("b")
        var map = mutableMapOf("a" to 1, "b" to 2, "c" to 3)
        map.get("a")
        map.toMutableMap()

//        repeat(100){
//            list1.add(it)
//        }

        for (item in list1) {
            item / 3 == 0 ?: item
        }

        var i = list1.size == 3 ?: 1

        var list: ArrayList<in Int> = ArrayList()
        var item = list.get(0)
        item.toString()



    }




}