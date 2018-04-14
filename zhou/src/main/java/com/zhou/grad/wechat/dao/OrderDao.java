package com.zhou.grad.wechat.dao;

import java.util.List;

import com.zhou.grad.entity.Order;
import com.zhou.grad.wechat.model.OrderDetailModel;

public interface OrderDao {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);
    
    /**
     * 根据openId查询用户所有的订单
     * @param openId
     * @return
     */
    List<OrderDetailModel> selectByOpenid(String openId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}