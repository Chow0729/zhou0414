package com.zhou.grad.wechat.model;

import java.util.List;

import com.zhou.grad.entity.Food;

public class FoodReturnModel {

    //食品种类的名称
    private String name;
    
    //该种类所有的食品
    private List<Food> foods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
    
    
}
