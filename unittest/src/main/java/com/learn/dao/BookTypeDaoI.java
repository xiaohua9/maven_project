package com.learn.dao;

import com.learn.entity.BookType;

import java.util.List;

public interface BookTypeDaoI {
    //查询所有数据
    public List<BookType> selectAll();
}
