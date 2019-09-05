package com.learn.service;

import com.learn.dao.BookInfoDaoI;
import com.learn.dao.impl.BookInfoDaoImpl;
import com.learn.entity.BookInfo;

import java.util.List;

public interface BookInfoServiceI {
    BookInfoDaoI dao=new BookInfoDaoImpl();//将依赖的对象提前获取
    public int insert(BookInfo bookInfo);//增加数据
    public int delete(BookInfo bookInfo);//删除数据
    public int update(BookInfo bookInfo);//更改数据
    public BookInfo select(Object id);//根据编号查一条数据
    public List<BookInfo> selectAll(int page, int pageSize, Object... parameter);//根据条件分页查询数据
    public int selectCount(Object... parameter);//根据条件查询数据量
    public List<Object> selectColumn(Object parameter);//查得某个字段的所有数据，后续可用作条件字段的获取
}
