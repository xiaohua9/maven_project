package com.learn.service;

import com.learn.dao.TopicDaoI;
import com.learn.dao.impl.TopicDaoImpl;
import com.learn.entity.Topic;

import java.util.List;

public interface TopicServiceI {
    TopicDaoI dao=new TopicDaoImpl();//提取获取依赖对象
    public List<Topic> selectAll();//查询所有数据
}
