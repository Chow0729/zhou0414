package com.zhou.demo.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhou.grad.auth.model.ResourceModal;
import com.zhou.grad.auth.service.ResourceService;
import com.zhou.grad.entity.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class ResourceTest {

    @Autowired
    private ResourceService rs;
    
    @Test
    public void test() {
        List<ResourceModal> list = rs.selectResourceByUserId(1);
        /*for (ResourceModal re : list) {
            System.out.println(re);
        }*/
    }
}
