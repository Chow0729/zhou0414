package com.zhou.grad.business.service;

import java.util.Map;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.entity.Category;
import com.zhou.grad.entity.Food;
import com.zhou.grad.wechat.model.FoodDetailModel;

public interface FoodBusinessService {

    /**
     * 查询所有的菜单
     * @param params
     * @return
     */
    Map<String, Object> selectFoodsByPage(QueryParamsModal params);
    
    /**
     * 添加食品菜单
     * @param food
     * @param cate
     * @return
     */
    Map<String, Object> insertFood(Food food, Category cate);
    
    /**
     * 根据foodId查询
     * @param foodId
     * @return
     */
    FoodDetailModel selectFoodById(Integer foodId);
    
    /**
     * 修改菜单及其种类的关联信息
     * @param food
     * @param cate
     * @return
     */
    Map<String, Object> updataFood(Food food);
    
    /**
     * 批量删除
     * @param ids
     * @return
     */
    Map<String, Object> delFood(Integer[] ids);
}
