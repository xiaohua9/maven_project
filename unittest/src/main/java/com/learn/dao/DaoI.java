package com.learn.dao;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.util.List;

//dao的顶层泛型接口
public interface DaoI<T> {
    //获取数据库连接池，所有的dao都会用到这个池子
    public static final ComboPooledDataSource dataSource=new ComboPooledDataSource();
    //插入数据
    public int insert(T t);
    //删除数据
    public int delete(T t);
    //修改数据
    public int update(T t);
    //查询一条数据
    public T select(Object parameter);
    //按条件分页查询所有数据
    public List<T> selectAll(int page, int pageSize, Object... parameter);
    //按条件查询数据的数量
    public int selectCount(Object... parameter);
    //获取某个字段的集合
    public List<Object> selectColumn(Object parameter);
}
