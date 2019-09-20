package com.learn.mapper;

import com.learn.entity.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookInfoDao {
    //插入数据
    public int insert(BookInfo bookInfo);
    //删除数据
    public int delete(Object id);
    //修改数据
    public int update(BookInfo bookInfo);
    //查询一条数据
    public BookInfo select(Object id);
    //按条件查询所有数据
    public List<BookInfo> selectAll(@Param("currentBookType") String currentBookType, @Param("currentBookName") String currentBookName, @Param("currentIsBorrow") String currentIsBorrow);
}
