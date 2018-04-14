package com.zhou.grad.entity;

public class OrderFood {
    private Integer orderFoodId;

    private Long orderId;

    private String orderCode;

    private Integer foodId;

    private String name;

    private Double price;

    private String detail;

    private Integer number;

    private Double sum;
    
    public Integer getOrderFoodId() {
        return orderFoodId;
    }

    public void setOrderFoodId(Integer orderFoodId) {
        this.orderFoodId = orderFoodId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "OrderFood [orderFoodId=" + orderFoodId + ", orderId=" + orderId + ", orderCode=" + orderCode
                + ", foodId=" + foodId + ", name=" + name + ", price=" + price + ", detail=" + detail + ", number="
                + number + ", sum=" + sum + "]";
    }
}