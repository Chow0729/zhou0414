package com.zhou.grad.entity;

import java.util.Date;

public class FoodCategory {
    private Integer foodCatgId;

    private Integer foodId;

    private Long categoryId;

    private Date createdTime;

    public Integer getFoodCatgId() {
        return foodCatgId;
    }

    public void setFoodCatgId(Integer foodCatgId) {
        this.foodCatgId = foodCatgId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}