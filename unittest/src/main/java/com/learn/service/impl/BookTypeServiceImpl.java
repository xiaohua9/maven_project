package com.learn.service.impl;

import com.learn.entity.BookType;
import com.learn.service.BookTypeServiceI;

import java.util.List;

public class BookTypeServiceImpl implements BookTypeServiceI {
    @Override
    public List<BookType> selectAll() {
        return dao.selectAll();
    }
}
