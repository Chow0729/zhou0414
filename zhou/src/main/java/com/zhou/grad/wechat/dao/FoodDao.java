package com.zhou.grad.wechat.dao;

import java.util.List;
import java.util.Map;

import com.zhou.grad.entity.Food;
import com.zhou.grad.wechat.model.FoodDetailModel;
import com.zhou.grad.wechat.model.FoodReturnModel;

public interface FoodDao {
    int deleteByPrimaryKey(Integer foodId);
    
    /**
     * 根据foodId批量删除
     * @param ids
     * @return
     */
    int deleteBatchByFooId(Integer[] ids);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer foodId);
    
    /**
     * 根据foodId查询
     * @param foodId
     * @return
     */
    FoodDetailModel selectByFoodId(Integer foodId);
    
    /**
     * 按种类查询所有的食品
     * @return
     */
    List<FoodReturnModel> selectFoodsOrderByCatgid();
    
    /**
     * 分页查询所有的菜单
     * @param map
     * @return
     */
    List<FoodDetailModel> selectFoodsByPage(Map<String, Object> map);
    
    /**
     * 统计所有的菜的数量
     * @param map
     * @return
     */
    Integer countByCondition(Map<String, Object> map);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);
}