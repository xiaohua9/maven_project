package com.learn.dao.impl;

import com.learn.dao.UserDao;
import com.learn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;//自动注入获取数据库的持久化操作对象

    public void insert(User user) {
        String sql="insert into user values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,user.getUserName(),user.getUserPassword(),user.getUserGender(),user.getUserAge(),user.getUserAddress(),user.getUserBirthday(),user.getPictureName());
    }

    public List<User> selectAll() {
        String sql="select * from user";
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
        List<User> query = jdbcTemplate.query(sql, rowMapper);
        return query;
    }
}
