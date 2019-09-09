package com.learn.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

//单例模式获取数据库操作的持久化对象
public class GetSession {
    private static  SqlSession session=null;
    private GetSession() {
    }
    public static SqlSession getSession(){
        if (session==null){
            Reader reader=null;
            try {
                reader= Resources.getResourceAsReader("mybatis.cfg.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory=builder.build(reader);
            session=factory.openSession();
        }
        return session;
    }
}
