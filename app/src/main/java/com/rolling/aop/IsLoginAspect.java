package com.rolling.aop;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.hjq.toast.ToastUtils;
import com.rolling.act.login.LoginActivity;
import com.rolling.helper.ActivityStackManager;
import com.rolling.util.LoginUtils;
import com.rolling.util.OpenAcitivtyUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author zhangyao
 * @date 12/5/20  2:25 PM
 * @E-mail android_n@163.com
 */


@Aspect
public class IsLoginAspect {

    /**
     * 方法切入点
     */
    @Pointcut("execution(@com.rolling.aop.IsLogin * *(..))")
    public void method() {
    }

    //TestAspect.java
    @SuppressLint("NewApi")
    @Around("method()&& @annotation(isOpen)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, IsLogin isOpen) throws Throwable {
        if (isOpen.value()) {
            if (!LoginUtils.isLogin()) {

                Activity activity = ActivityStackManager.getInstance().getTopActivity();
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                OpenAcitivtyUtils.openAct(ActivityStackManager.getInstance().getTopActivity(), LoginActivity.class);
            } else {
                ToastUtils.show("登录判断跳转" + LoginUtils.isLogin());
            }
        } else {

            ToastUtils.show("登录判断不跳转" + LoginUtils.isLogin());
        }
    }

}
