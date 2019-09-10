package com.learn.service.impl;
import com.learn.dao.UserDao;
import com.learn.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao;
    //依赖注入所必须的set方法
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void insert() {
        userDao.insert();//注入成功的情况下就不会有空指针异常
    }
}
