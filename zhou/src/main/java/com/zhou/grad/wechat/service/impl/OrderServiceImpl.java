package com.zhou.grad.wechat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.entity.Order;
import com.zhou.grad.entity.OrderFood;
import com.zhou.grad.util.GetRandomString;
import com.zhou.grad.wechat.dao.OrderDao;
import com.zhou.grad.wechat.dao.OrderFoodDao;
import com.zhou.grad.wechat.model.OrderDetailModel;
import com.zhou.grad.wechat.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private OrderFoodDao ofDao;

    @Override
    public void addOrder(Order order, List<OrderFood> foods) {
        try {
            //生成一个随机数用作订单号
            String random = GetRandomString.getRandom(10);
            order.setOrderCode(random);
            order.setOrderTime(new Date());
            //状态0表示未完成
            order.setStatus("0");
            
            //添加订单
            int result = orderDao.insert(order);
            //添加订单中的商品信息
            for (OrderFood food : foods) {
                food.setOrderCode(order.getOrderCode());
                food.setOrderId(order.getOrderId());
            }
            result = ofDao.insertBatch(foods);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public List<OrderDetailModel> selectByOpenid(String openId) {
        List<OrderDetailModel> list = orderDao.selectByOpenid(openId);
        return list;
    }

}
