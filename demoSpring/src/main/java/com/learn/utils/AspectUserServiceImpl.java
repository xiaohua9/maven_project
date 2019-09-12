package com.learn.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class AspectUserServiceImpl {
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("执行前后都要增强的功能1");
        Object proceed=null;
        try {
            proceed = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        LogUtils.logger.info("操作时间："+new Date().toLocaleString()+";操作类："+proceedingJoinPoint.getTarget().getClass().getSimpleName()+";操作方法："+proceedingJoinPoint.getSignature());
        System.out.println("执行前后都要增强的功能1");
        return proceed;
    }
}
