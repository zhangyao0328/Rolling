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
    final static boolean isProduction = false;


    /**
     * 主域名
     */
    public final static String URL_HOST = isProduction ? "https://postrock.com.cn" : "http://192.168.124.5:8080";


    public final static String URL_V1_USER = "/v1/user";

    /**
     * 用户注册
     */
    public final static String URL_API_CEEATE_USER = URL_HOST + URL_V1_USER;

    /**
     * 用户登录
     */
    public final static String URL_API_LOGIN = URL_HOST + "/login";
    /**
     * 获取验证码
     */
    public final static String URL_API_AUTH_CODE = URL_HOST + "/sms/code";


}
