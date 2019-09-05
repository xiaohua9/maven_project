package com.learn.dao.impl;

import com.learn.dao.BookTypeDaoI;
import com.learn.entity.BookType;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

import static com.learn.dao.DaoI.dataSource;

public class BookTypeDaoImpl implements BookTypeDaoI {
    @Override
    public List<BookType> selectAll() {
        //构造sql字符串
        String sql="select * from book_type ";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        List<BookType> list=null;//将查询结果赋值到改对象
        try {
            list=runner.query(sql,new BeanListHandler<BookType>(BookType.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;//返回结果对象
    }
}
