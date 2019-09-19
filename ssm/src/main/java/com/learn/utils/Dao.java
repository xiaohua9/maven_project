package com.learn.utils;

import java.util.List;

public interface Dao<T> {
    public int insert(T t);
    public int update(T t);
    public int delete(int id);
    public T findById(int id);
    public List<T> findAll();
}
