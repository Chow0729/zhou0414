package com.zhou.grad.auth.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserInfoModel {

    //用户id
    private int userId;

    //用名称
    private String userName;

    //密码
    private String password;

    //真实名字
    private String realName;

    //性别
    private String sex;
  
    //电话
    private String phone;
 
    //邮箱
    private String mail;
    
    //备注
    private String remark;

    //是否可用
    private String isenable;

    //修改时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editTime;
   
    //创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    
    //部门名称
    private String depName;
    
    //职位
    private String position;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsenable() {
        return isenable;
    }

    public void setIsenable(String isenable) {
        this.isenable = isenable;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "UserInfoModel [userId=" + userId + ", userName=" + userName + ", password=" + password + ", realName="
                + realName + ", sex=" + sex + ", phone=" + phone + ", mail=" + mail + ", remark=" + remark
                + ", isenable=" + isenable + ", updatedTime=" + editTime + ", createdTime=" + createdTime
                + ", depName=" + depName + ", position=" + position + "]";
    }
    
}
