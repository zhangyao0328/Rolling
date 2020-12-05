package com.rolling.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zhangyao
 * @date 12/5/20  2:29 PM
 * @E-mail android_n@163.com
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IsLogin {
    boolean value() default false;
}
