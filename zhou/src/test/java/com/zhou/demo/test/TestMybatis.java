package com.zhou.demo.test;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMybatis {

    private SqlSession sqlSession;  
    
    
    @Before  
    public void before(){  
        try {  
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("spring/mybatis-config.xml"));  
            sqlSession = sqlSessionFactory.openSession();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    @After  
    public void after(){  
        sqlSession.commit();  
        sqlSession.close();  
    }  
      
      
    @Test  
    public void test(){  
        //PersonDao personDao=sqlSession.getMapper(PersonDao.class);  
        //System.out.println(personDao.getPersons().size());  
    }  
}
