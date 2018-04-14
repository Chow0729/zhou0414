package com.zhou.grad.wechat.dao;

import com.zhou.grad.entity.FoodCategory;

public interface FoodCategoryDao {
    int deleteByPrimaryKey(Integer foodCatgId);
    
    /**
     * 根据foodId批量删除
     * @param ids
     * @return
     */
    int deleteBatchByFoodId(Integer[] ids);

    int insert(FoodCategory record);

    int insertSelective(FoodCategory record);

    FoodCategory selectByPrimaryKey(Integer foodCatgId);

    int updateByPrimaryKeySelective(FoodCategory record);

    int updateByPrimaryKey(FoodCategory record);
}