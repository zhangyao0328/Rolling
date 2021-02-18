package com.rolling.net

/**
 * @author zhangyao
 * @date 2017/11/20:18:00
 * @E-mail android_n@163.com
 */
object HttpConfig {
    /**
     * 主站vs测试站开关
     * true：主站
     * false：测试站
     */
    const val isProduction = false

    /**
     * 主域名
     */
    @JvmField
    val URL_HOST = if (isProduction) "https://postrock.com.cn" else "http://192.168.124.9:8080"
    const val URL_V1_USER = "/v1/user"

    /**
     * 用户注册
     */
    val URL_API_CEEATE_USER = URL_HOST + URL_V1_USER

    /**
     * 用户登录
     */
    @JvmField
    val URL_API_LOGIN = URL_HOST + "/login"

    /**
     * 用户退出登录
     */
    @JvmField
    val URL_API_LOGOUT = URL_HOST + "/logout"

    /**
     * 获取验证码
     */
    @JvmField
    val URL_API_AUTH_CODE = URL_HOST + "/sms/code"

    /**
     * 首页tab
     */
    @JvmField
    val URL_API_HOME_TABS = URL_HOST + "/home/tabs"

    /**
     * 地理位置
     */
    @JvmField
    val URL_API_LOCATION_CITY = URL_HOST + "/location/city"

    /**
     * 运动类型
     */
    @JvmField
    val URL_API_SPORT_TYPE = URL_HOST + "/sports/type"

    /**
     * 七牛token
     */
    @JvmField
    val URL_API_QINIU_TOKEN = URL_HOST + "/qiniu/token"

    /**
     * 用户信息
     */
    @JvmField
    val URL_API_USER = URL_HOST + "/user"

    /**
     * 用户信息
     */
    @JvmField
    val URL_API_USER_INFO = URL_API_USER + "/info"

    /**
     * 活动item
     */
    @JvmField
    val URL_API_PUNCH = URL_HOST + "/punch"

    /**
     * 活动item 用户状态
     */
    @JvmField
    val URL_API_PUNCH_USER_STATUS = URL_API_PUNCH + "/status"

    /**
     * 参加活动item 的用户列表
     */
    @JvmField
    val URL_API_PUNCH_USERS = URL_API_PUNCH + "/users"
    /**
     * 加入活动
     */
    @JvmField
    val URL_API_PUNCH_ADD= URL_API_PUNCH + "/add"


}