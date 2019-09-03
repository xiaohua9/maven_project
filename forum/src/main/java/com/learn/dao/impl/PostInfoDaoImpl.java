package com.learn.dao.impl;
import com.learn.dao.PostInfoDaoI;
import com.learn.entity.PostInfo;
import com.learn.utils.EmptyUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class PostInfoDaoImpl implements PostInfoDaoI {
    @Override
    public int insert(PostInfo postInfo) {
        //构造sql字符串
        String sql="insert into postinfo values(null,?,?,?,?,?,?)";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,postInfo.getTitle(),postInfo.getPostTime(),postInfo.getClickNum(),postInfo.getContent(),postInfo.getTopicId(),postInfo.getPic());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;//返回sql影响的行数
    }

    @Override
    public int delete(PostInfo postInfo) {
        //构造sql字符串
        String sql="delete from postinfo where id=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        int flag=0;//sql影响的行数
        try {
            flag=runner.update(sql,postInfo.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;//返回sql影响的行数
    }

    @Override
    public int update(PostInfo postInfo) {
            //构造sql字符串
            String sql = "update postinfo set title=?,postTime=?,clickNum=?,content=?,topicId=?,pic=? where id=?";
            //构造sql执行对象
            QueryRunner runner = new QueryRunner(dataSource);
            //调用工具执行sql
            int flag = 0;//sql影响的行数
            try {
                flag = runner.update(sql, postInfo.getTitle(), postInfo.getPostTime(), postInfo.getClickNum(), postInfo.getContent(), postInfo.getTopicId(),postInfo.getPic(), postInfo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return flag;//返回sql影响的行数
    }
///根据编号查一条学生记录
    @Override
    public PostInfo select(Object id) {
        //构造sql字符串
        String sql="select * from postinfo where id=?";
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        PostInfo postInfo=null;//将查询结果赋值到改对象
        try {
            postInfo=runner.query(sql,new BeanHandler<PostInfo>(PostInfo.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postInfo;//返回结果对象
    }
    //按条件查所有分页
    @Override
    public List<PostInfo> selectAll(int page, int pageSize,Object...parameter) {
        int i = (page - 1) * pageSize;
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //调用工具执行sql
        List<PostInfo> list=null;//将查询结果赋值到改对象

        //判断查询条件的有无，分情况构造sql语句,
        String sql=null;
        if (EmptyUtils.isEmpty(parameter[0])&& EmptyUtils.isEmpty(parameter[1])){//条件1和条件2空时
            sql="select * from postinfo where clickNum between ? and ? limit ?,?";
            try {
                list= runner.query(sql, new BeanListHandler<PostInfo>(PostInfo.class),parameter[2],parameter[3],i,pageSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (!EmptyUtils.isEmpty(parameter[0])&&EmptyUtils.isEmpty(parameter[1])){
            sql="select * from postinfo where title like '%"+parameter[0]+"%' and clickNum between ? and ? limit ?,?";
            try {
                list= runner.query(sql, new BeanListHandler<PostInfo>(PostInfo.class),parameter[2],parameter[3],i,pageSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(EmptyUtils.isEmpty(parameter[0])&& !EmptyUtils.isEmpty(parameter[1])){
            sql="select * from postinfo where topicId=? and clickNum between ? and ? limit ?,?";
            try {
                list= runner.query(sql, new BeanListHandler<PostInfo>(PostInfo.class),parameter[1],parameter[2],parameter[3],i,pageSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (!EmptyUtils.isEmpty(parameter[0])&& !EmptyUtils.isEmpty(parameter[1])){
            sql="select * from postinfo where title like '%"+parameter[0]+"%' and topicId=? and clickNum between ? and ? limit ?,?";
            try {
                list= runner.query(sql, new BeanListHandler<PostInfo>(PostInfo.class),parameter[1],parameter[2],parameter[3],i,pageSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;//返回结果对象
    }
    //按条件的数量查询
    @Override
    public int selectCount(Object...parameter) {
        //构造sql执行对象
        QueryRunner runner=new QueryRunner(dataSource);
        //判断查询条件的有无，分情况构造sql语句,
        String sql=null;
        int count=0;//将查询结果数量保存
        if (EmptyUtils.isEmpty(parameter[0])&&EmptyUtils.isEmpty(parameter[1])){
            sql="select count(1) from postinfo where clickNum between ? and ?";
            try {
                count = runner.query(sql, new ScalarHandler<Long>(),parameter[2],parameter[3]).intValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (!EmptyUtils.isEmpty(parameter[0])&&EmptyUtils.isEmpty(parameter[1])){
            sql="select count(1) from postinfo where title like '%"+parameter[0]+"%' and clickNum between ? and ?";
            try {
                count = runner.query(sql, new ScalarHandler<Long>(),parameter[2],parameter[3]).intValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (EmptyUtils.isEmpty(parameter[0])&& !EmptyUtils.isEmpty(parameter[1])){
            sql="select count(1) from postinfo where topicId=? and clickNum between ? and ? ";
            try {
                count = runner.query(sql, new ScalarHandler<Long>(),parameter[1],parameter[2],parameter[3]).intValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (!EmptyUtils.isEmpty(parameter[0])&& !EmptyUtils.isEmpty(parameter[1])){
            sql="select count(1) from postinfo where title like '%"+parameter[0]+"%' and topicId=? and clickNum between ? and ? ";
            try {
                count = runner.query(sql, new ScalarHandler<Long>(),parameter[1],parameter[2],parameter[3]).intValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;//返回数量
    }

    @Override
    public List<Object> selectColumn(Object parameter){
        //构造sql字符串
        String sql="select distinct("+parameter+") from postinfo";
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
