package com.learn.dao.impl;

import com.learn.dao.BookInfoDaoI;
import com.learn.entity.BookInfo;
import com.learn.utils.EmptyUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BookInfoDaoImpl implements BookInfoDaoI {
    @Override
    public int insert(BookInfo bookInfo) {
        //构造sql字符串
        String sql="insert into book_info values(null,?,?,?,?,?,?,?)";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,bookInfo.getBook_code(),bookInfo.getBook_name(),bookInfo.getBook_type(),bookInfo.getBook_author(),bookInfo.getPublish_press(),bookInfo.getPublish_date(),bookInfo.getIs_borrow());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;//返回sql影响的行数
    }

    @Override
    public int delete(BookInfo bookInfo) {
        //构造sql字符串
        String sql="delete from book_info where book_id=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,bookInfo.getBook_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;//返回sql影响的行数
    }

    @Override
    public int update(BookInfo bookInfo) {
        //构造sql字符串
        String sql = "update book_info set book_code=?,book_name=?,book_type=?,book_author=?,publish_press=?,publish_date=?,is_borrow=? where book_id=?";
        //构造sql执行对象
        QueryRunner runner = new QueryRunner(dataSource);
        //调用工具执行sql
        int flag = 0;//sql影响的行数
        try {
            flag = runner.update(sql,bookInfo.getBook_code(),bookInfo.getBook_name(),bookInfo.getBook_type(),bookInfo.getBook_author(),bookInfo.getPublish_press(),bookInfo.getPublish_date(),bookInfo.getIs_borrow(),bookInfo.getBook_id() );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;//返回sql影响的行数
    }

    @Override
    public BookInfo select(Object parameter) {
        //构造sql字符串
        String sql="select * from book_info where book_id=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        BookInfo bookInfo=null;//将查询结果赋值到改对象
        try {
            bookInfo=runner.query(sql,new BeanHandler<BookInfo>(BookInfo.class),parameter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookInfo;//返回结果对象
    }

    @Override
    public List<BookInfo> selectAll(int page, int pageSize, Object... parameter) {
        int i = (page - 1) * pageSize;
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        List<BookInfo> list=null;//将查询结果赋值到改对象

        //判断查询条件的有无，分情况构造sql语句,
        String sql=null;
        if (EmptyUtils.isEmpty(parameter[0])&& EmptyUtils.isEmpty(parameter[1])){
            sql="select * from book_info where is_borrow=? limit ?,?";
            try {
                list= runner.query(sql, new BeanListHandler<BookInfo>(BookInfo.class),parameter[2],i,pageSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (!EmptyUtils.isEmpty(parameter[0])&&EmptyUtils.isEmpty(parameter[1])){
            sql="select * from book_info where book_type=? and is_borrow='"+parameter[2]+"' limit ?,?";
            try {
                list= runner.query(sql, new BeanListHandler<BookInfo>(BookInfo.class),parameter[0],i,pageSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(EmptyUtils.isEmpty(parameter[0])&& !EmptyUtils.isEmpty(parameter[1])){
            sql="select * from book_info where book_name=? and is_borrow=?  limit ?,?";
            try {
                list= runner.query(sql, new BeanListHandler<BookInfo>(BookInfo.class),parameter[1],parameter[2],i,pageSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (!EmptyUtils.isEmpty(parameter[0])&& !EmptyUtils.isEmpty(parameter[1])){
            sql="select * from book_info where book_type=? and book_name=? and is_borrow=? limit ?,?";
            try {
                list= runner.query(sql, new BeanListHandler<BookInfo>(BookInfo.class),parameter[0],parameter[1],parameter[2],i,pageSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;//返回结果对象
    }

    @Override
    public int selectCount(Object... parameter) {
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //判断查询条件的有无，分情况构造sql语句,
        String sql=null;
        int count=0;//将查询结果数量保存
        if (EmptyUtils.isEmpty(parameter[0])&&EmptyUtils.isEmpty(parameter[1])){
            sql="select count(1) from book_info where is_borrow=?";
            try {
                count = runner.query(sql, new ScalarHandler<Long>(),parameter[2]).intValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (!EmptyUtils.isEmpty(parameter[0])&&EmptyUtils.isEmpty(parameter[1])){
            sql="select count(1) from book_info where book_type=? and is_borrow=?";
            try {
                count = runner.query(sql, new ScalarHandler<Long>(),parameter[0],parameter[2]).intValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (EmptyUtils.isEmpty(parameter[0])&& !EmptyUtils.isEmpty(parameter[1])){
            sql="select count(1) from book_info where book_name=? and is_borrow=? ";
            try {
                count = runner.query(sql, new ScalarHandler<Long>(),parameter[1],parameter[2]).intValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (!EmptyUtils.isEmpty(parameter[0])&& !EmptyUtils.isEmpty(parameter[1])){
            sql="select count(1) from book_info where book_type=? and book_name=? and is_borrow=? ";
            try {
                count = runner.query(sql, new ScalarHandler<Long>(),parameter[0],parameter[1],parameter[2]).intValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;//返回数量
    }

    @Override
    public List<Object> selectColumn(Object parameter) {
        //构造sql字符串
        String sql="select distinct("+parameter+") from book_info";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        List<Object> object=null;//将查询结果赋值到改对象
        try {
            object= runner.query(sql, new ColumnListHandler<Object>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;//返回结果对象
    }
}
