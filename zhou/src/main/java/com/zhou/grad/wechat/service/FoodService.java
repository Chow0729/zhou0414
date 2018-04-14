package com.zhou.grad.wechat.service;

import java.util.List;

import com.zhou.grad.wechat.model.FoodReturnModel;

public interface FoodService {

    /**
     * 按种类查询所有的食品
     * @return 所有的商品
     */
    List<FoodReturnModel> selectFoodsOrderByCatgid();
}
