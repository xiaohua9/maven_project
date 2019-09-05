package com.learn.service;

import com.learn.dao.BookTypeDaoI;
import com.learn.dao.impl.BookTypeDaoImpl;
import com.learn.entity.BookType;

import java.util.List;

public interface BookTypeServiceI {
    BookTypeDaoI dao=new BookTypeDaoImpl();//提取获取依赖对象
    public List<BookType> selectAll();//查询所有数据
}
