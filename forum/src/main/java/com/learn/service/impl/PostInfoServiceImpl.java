package com.learn.service.impl;


import com.learn.entity.PostInfo;
import com.learn.service.PostInfoServiceI;

import java.util.List;

//学生服务的实现类
public class PostInfoServiceImpl implements PostInfoServiceI {
    @Override
    public int insert(PostInfo postInfo) {
        return dao.insert(postInfo);
    }

    @Override
    public int delete(PostInfo postInfo) {
        return dao.delete(postInfo);
    }

    @Override
    public int update(PostInfo postInfo) {
        return dao.update(postInfo);
    }

    @Override
    public PostInfo select(Object id) {
        return dao.select(id);
    }

    @Override
    public List<PostInfo> selectAll(int page, int pageSize, Object... parameter) {
        return dao.selectAll(page,pageSize,parameter);
    }

    @Override
    public int selectCount(Object... parameter) {
        return dao.selectCount(parameter);
    }

    @Override
    public List<Object> selectColumn(Object parameter) {
        return dao.selectColumn(parameter);
    }
}
