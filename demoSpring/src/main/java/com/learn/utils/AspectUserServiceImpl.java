package com.learn.utils;

import org.aspectj.lang.ProceedingJoinPoint;

public class AspectUserServiceImpl {
    public void before(){
        System.out.println("执行前的增强");
    }
    public void after(){
        System.out.println("执行后的增强");
    }
    public void around(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("执行前后都要增强的功能1");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("执行前后都要增强的功能1");
    }
}
