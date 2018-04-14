package com.zhou.grad.wechat.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhou.grad.entity.OrderFood;

public class OrderDetailModel {

    private Long orderId;

    private String orderCode;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone= "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    private String openId;

    private String nickName;

    private Double sumMoney;
    
    private int cupNumber;

    private String status;

    private String remark;
    
    private List<OrderFood> foods;

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
        this.orderCode = orderCode;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public int getCupNumber() {
        return cupNumber;
    }

    public void setCupNumber(int cupNumber) {
        this.cupNumber = cupNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OrderFood> getFoods() {
        return foods;
    }

    public void setFoods(List<OrderFood> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "OrderDetailModel [orderId=" + orderId + ", orderCode=" + orderCode + ", orderTime=" + orderTime
                + ", openId=" + openId + ", nickName=" + nickName + ", sumMoney=" + sumMoney + ", cupNumber="
                + cupNumber + ", status=" + status + ", remark=" + remark + ", foods=" + foods + "]";
    }
    
}
