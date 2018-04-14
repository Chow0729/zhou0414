package com.zhou.demo.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringMybatis {
    public static void main(String[] args) {
        // 01.加载spring配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        //PersonDao personDao = (PersonDao) ac.getBean("personDao");

        //personDao.addPerson(new Person("小小A", 10));

        //System.out.println(personDao.getPersons().size());
    }
}
