package com.learn.service.impl;

import com.learn.entity.Topic;
import com.learn.service.TopicServiceI;

import java.util.List;

public class TopicServiceImpl implements TopicServiceI {
    @Override
    public List<Topic> selectAll() {
        return dao.selectAll();
    }
}
