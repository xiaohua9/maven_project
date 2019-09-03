package com.learn.mapper.impl;

import com.learn.entity.PostInfo;
import com.learn.mapper.PostInfoMapperI;
import com.learn.utils.GetSession;
import com.learn.utils.LogUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

public class PostInfoMapperImpl implements PostInfoMapperI {
    @Override
    public int insert(PostInfo postInfo) {
        SqlSession session = GetSession.getSession();//获取数据库操作的持久化对象
        int insert = session.insert("com.learn.mapper.MapperI.insert", postInfo);
        session.commit();
        session.close();
        LogUtils.logger.info("插入一条数据"+new Date().toLocaleString());//加上当前的时间可以方便查看
        return insert;
    }

    @Override
    public int delete(PostInfo postInfo) {
        SqlSession session = GetSession.getSession();//获取数据库操作的持久化对象
        int delete = session.delete("com.learn.mapper.MapperI.delete", postInfo.getId());
        session.commit();
        session.close();
        LogUtils.logger.info("删除一条数据"+new Date().toLocaleString());
        return delete;
    }

    @Override
    public int update(PostInfo postInfo) {
        SqlSession session = GetSession.getSession();//获取数据库操作的持久化对象
        int update = session.update("com.learn.mapper.MapperI.update", postInfo);
        session.commit();
        session.close();
        LogUtils.logger.info("更新一条数据"+new Date().toLocaleString());
        return update;
    }

    @Override
    public PostInfo select(Object parameter) {
        SqlSession session = GetSession.getSession();//获取数据库操作的持久化对象
        PostInfo postInfo= (PostInfo)session.selectOne("com.learn.mapper.MapperI.find", parameter);
        session.close();
        LogUtils.logger.info("查询一条数据"+new Date().toLocaleString());
        return postInfo;
    }

    @Override
    public List<PostInfo> selectAll() {
        SqlSession session = GetSession.getSession();//获取数据库操作的持久化对象
        List<PostInfo> postInfos = session.selectList("com.learn.mapper.MapperI.findAll");
        session.close();
        LogUtils.logger.info("查询全部数据"+new Date().toLocaleString());
        return postInfos;
    }
}
