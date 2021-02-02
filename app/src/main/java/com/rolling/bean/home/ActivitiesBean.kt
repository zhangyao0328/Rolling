package com.rolling.bean.home

import com.squareup.moshi.JsonClass



data class ActivitiesBean(
    val code: Int,
    val `data`: Data,
    val message: String
)