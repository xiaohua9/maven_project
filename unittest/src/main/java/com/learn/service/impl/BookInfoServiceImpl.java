package com.learn.service.impl;

import com.learn.entity.BookInfo;
import com.learn.service.BookInfoServiceI;

import java.util.List;

public class BookInfoServiceImpl implements BookInfoServiceI {
    @Override
    public int insert(BookInfo bookInfo) {
        return dao.insert(bookInfo);
    }

    @Override
    public int delete(BookInfo bookInfo) {
        return dao.delete(bookInfo);
    }

    @Override
    public int update(BookInfo bookInfo) {
        return dao.update(bookInfo);
    }

    @Override
    public BookInfo select(Object id) {
        return dao.select(id);
    }

    @Override
    public List<BookInfo> selectAll(int page, int pageSize, Object... parameter) {
        return dao.selectAll(page,pageSize,parameter);
    }

    @Override
    public int selectCount(Object... parameter) {
        return dao.selectCount(parameter);
    }

    @Override
    public List<Object> selectColumn(Object parameter) {
        return dao.selectColumn(parameter);
    }
}
