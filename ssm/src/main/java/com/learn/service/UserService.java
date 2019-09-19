package com.learn.service;

import com.learn.entity.User;

import java.util.List;

public interface UserService {
    public int insert(User t);
    public int update(User t);
    public int delete(int id);
    public User findById(int id);
    public List<User> findAll();
}
