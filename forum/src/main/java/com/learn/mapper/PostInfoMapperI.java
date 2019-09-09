package com.learn.mapper;

import com.learn.entity.PostInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostInfoMapperI {
    //插入数据
    public int insert(PostInfo postInfo);
    //删除数据
    public int delete(PostInfo postInfo);
    //修改数据
    public int update(PostInfo postInfo);
    //查询一条数据
    public PostInfo select(Object parameter);
    //按条件查询所有数据
    public List<PostInfo> selectAll(@Param("currentShortTitle") String currentShortTitle, @Param("currentTopicId") String currentTopicId, @Param("currentBegain") String currentBegain, @Param("currentEnd") String currentEnd);
}
