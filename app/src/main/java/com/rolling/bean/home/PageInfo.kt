package com.rolling.bean.home

import com.squareup.moshi.JsonClass

/**
 *  @author zhangyao
 *  @date 2/17/21  11:18 AM
 *  @E-mail android_n@163.com
 */
@JsonClass(generateAdapter = true)
data class PageInfo(
    val currentPage: Int,
    val nextPage: Int,
    val previousPage: Int,
    val totalCount: Int
)