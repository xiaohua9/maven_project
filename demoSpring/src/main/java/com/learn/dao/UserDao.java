package com.learn.dao;

import com.learn.entity.User;

import java.util.List;

public interface UserDao {
    public void insert(User user);
    public List<User> selectAll();
}
