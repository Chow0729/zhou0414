package com.zhou.grad.wechat.dao;

import java.util.List;

import com.zhou.grad.entity.OrderFood;

public interface OrderFoodDao {
    int deleteByPrimaryKey(Integer orderFoodId);

    int insert(OrderFood record);
    
    /**
     * 批量添加
     * @param foods
     * @return
     */
    int insertBatch(List<OrderFood> foods);

    int insertSelective(OrderFood record);

    OrderFood selectByPrimaryKey(Integer orderFoodId);

    int updateByPrimaryKeySelective(OrderFood record);

    int updateByPrimaryKey(OrderFood record);
}