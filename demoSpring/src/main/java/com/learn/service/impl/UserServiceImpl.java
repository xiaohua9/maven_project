package com.learn.service.impl;
import com.learn.dao.UserDao;
import com.learn.entity.User;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    public void insert(User user) {
        userDao.insert(user);//注入成功的情况下就不会有空指针异常
    }
}
