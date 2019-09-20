package com.learn.service.impl;

import com.learn.entity.BookInfo;
import com.learn.mapper.BookInfoDao;
import com.learn.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BookInfoServiceImpl implements BookInfoService {
    @Autowired
    BookInfoDao bookInfoDao;
    @Override
    public int insert(BookInfo bookInfo) {
        return bookInfoDao.insert(bookInfo);
    }

    @Override
    public int delete(Object id) {
        return bookInfoDao.delete(id);
    }

    @Override
    public int update(BookInfo bookInfo) {
        return bookInfoDao.update(bookInfo);
    }

    @Override
    public BookInfo select(Object id) {
        return bookInfoDao.select(id);
    }

    @Override
    public List<BookInfo> selectAll(String currentBookType, String currentBookName, String currentIsBorrow) {
        return bookInfoDao.selectAll(currentBookType,currentBookName,currentIsBorrow);
    }
}
