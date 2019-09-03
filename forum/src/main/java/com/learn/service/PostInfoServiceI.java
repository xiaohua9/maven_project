package com.learn.service;

import com.learn.dao.PostInfoDaoI;
import com.learn.dao.impl.PostInfoDaoImpl;
import com.learn.entity.PostInfo;

import java.util.List;

//学生的服务层接口
public interface PostInfoServiceI {
    PostInfoDaoI dao=new PostInfoDaoImpl();//将依赖的对象提前获取
    public int insert(PostInfo postInfo);//增加数据
    public int delete(PostInfo postInfo);//删除数据
    public int update(PostInfo postInfo);//更改数据
    public PostInfo select(Object id);//根据编号查一条数据
    public List<PostInfo> selectAll(int page, int pageSize, Object... parameter);//根据条件分页查询数据
    public int selectCount(Object... parameter);//根据条件查询数据量
    public List<Object> selectColumn(Object parameter);//查得某个字段的所有数据，后续可用作条件字段的获取
}
