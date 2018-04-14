package com.zhou.grad.wechat.service;

import java.util.List;

import com.zhou.grad.entity.Order;
import com.zhou.grad.entity.OrderFood;
import com.zhou.grad.wechat.model.OrderDetailModel;

public interface OrderService {

    /**
     * 用户下单
     * @param order 订单的信息
     * @param foods 订单中的食品信息
     */
    void addOrder(Order order, List<OrderFood> foods);
    
    /**
     * 根据openId查询所有订单
     * @param openId
     * @return
     */
    List<OrderDetailModel> selectByOpenid(String openId);
}
