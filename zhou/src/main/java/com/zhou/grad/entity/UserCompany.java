package com.zhou.grad.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserCompany {
    private Integer ucId;

    private Integer userId;

    private Integer companyId;

    private Integer depId;

    private String empNo;

    private String position;

    private String jobTitle;

    private BigDecimal salary;

    private Integer workedTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date entryTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date regularTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date editTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    public Integer getUcId() {
        return ucId;
    }

    public void setUcId(Integer ucId) {
        this.ucId = ucId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo == null ? null : empNo.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle == null ? null : jobTitle.trim();
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getWorkedTime() {
        return workedTime;
    }

    public void setWorkedTime(Integer workedTime) {
        this.workedTime = workedTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getRegularTime() {
        return regularTime;
    }

    public void setRegularTime(Date regularTime) {
        this.regularTime = regularTime;
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

    @Override
    public String toString() {
        return "UserCompany [ucId=" + ucId + ", userId=" + userId + ", companyId=" + companyId + ", depId=" + depId
                + ", empNo=" + empNo + ", position=" + position + ", jobTitle=" + jobTitle + ", salary=" + salary
                + ", workedTime=" + workedTime + ", entryTime=" + entryTime + ", regularTime=" + regularTime
                + ", editTime=" + editTime + ", createdTime=" + createdTime + "]";
    }
}