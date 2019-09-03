package com.learn.dao.impl;
import com.learn.dao.TopicDaoI;
import com.learn.entity.Topic;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

//由于班级dao只需要查询全部的功能，故只实现查全部的功能
public class TopicDaoImpl implements TopicDaoI {
    @Override
    public List<Topic> selectAll() {
        //构造sql字符串
        String sql="select * from topic ";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        List<Topic> list=null;//将查询结果赋值到改对象
        try {
            list=runner.query(sql,new BeanListHandler<Topic>(Topic.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;//返回结果对象
    }

    @Override
    public int insert(Topic topic) {
        return 0;
    }

    @Override
    public int delete(Topic topic) {
        return 0;
    }

    @Override
    public int update(Topic topic) {
        return 0;
    }

    @Override
    public Topic select(Object parameter) {
        return null;
    }

    @Override
    public List<Topic> selectAll(int page, int pageSize, Object... parameter) {
        return null;
    }

    @Override
    public int selectCount(Object... parameter) {
        return 0;
    }

    @Override
    public List<Object> selectColumn(Object parameter) {
        return null;
    }
}
