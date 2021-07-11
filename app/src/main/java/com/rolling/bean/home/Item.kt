package com.rolling.bean.home

import com.rolling.base.view.BaseView
import com.rolling.bean.BaseDataBean
import com.squareup.moshi.JsonClass
import java.io.Serializable
import java.util.*

@JsonClass(generateAdapter = true)
data class Item(
    val AdminUser: Int,
    val Cover: String,
    val DeadLine: String,
    val EndDate: Long,
    val Id: Int,
    val Intro: String,
    val Name: String,
    val StartDate: Long,
    val Type: String,
    val ViewType : Int = 0


) : Serializable