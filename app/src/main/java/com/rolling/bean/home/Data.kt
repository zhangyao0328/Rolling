package com.rolling.bean.home

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val items: List<Item>?,
    val pageInfo: PageInfo
)