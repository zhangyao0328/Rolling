package com.rolling.bean.home

import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 *  @author zhangyao
 *  @date 4/14/21  10:30 AM
 *  @E-mail android_n@163.com
 */

@JsonClass(generateAdapter = true)
data class ImagesBean(
        var url: String,//图片地址
        var isMove: Boolean = true //是否可以长按移动
) : Serializable
