package com.rollling.net;

/**
 * @author zhangyao
 * @date 2017/11/20:18:00
 * @E-mail android_n@163.com
 */

public class HttpConfig {


    /**
     * 主站vs测试站开关
     * true：主站
     * false：测试站
     */
    static boolean isProduction = false;

    /**
     * 主域名
     */
    public static String URL_HOST = isProduction ? "http://192.168.13.29:8080" : "http://192.168.13.29:8080";


    public static String URL_V1_USER = "/v1/user";

    /**
     * 用户注册
     */
    public static String URL_API_CEEATE_USER = URL_HOST + URL_V1_USER;

    /**
     * 用户登录
     */
    public static String URL_API_LOGIN = URL_HOST + "/login";


}
