package com.learn.Test;

import com.learn.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.insert();//依赖动态注入成功的情况下就可以顺利完成调用
    }
}
