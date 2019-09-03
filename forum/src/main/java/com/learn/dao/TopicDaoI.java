package com.learn.dao;
import com.learn.entity.Topic;
import java.util.List;

//班级的子接口
public interface TopicDaoI extends DaoI<Topic>{
    //查询所有数据
    public List<Topic> selectAll();
}
