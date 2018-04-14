package com.zhou.grad.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Resource {
    private Integer resourceId;

    private Integer pid;

    private String resourceName;

    private String resourceUrl;

    private String location;

    private String icon;

    private Integer isenable;

    private String remark;

    private String perms;

    private String style;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT + 8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT + 8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    
    private boolean checked;
    
    private boolean isParent=true;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getIsParent() {
        if(pid==null||pid==0) {
        return isParent;
        }
        return false;
    }

    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }

    @Override
    public String toString() {
        return "Resource [resourceId=" + resourceId + ", pid=" + pid + ", resourceName=" + resourceName
                + ", resourceUrl=" + resourceUrl + ", location=" + location + ", icon=" + icon + ", isenable="
                + isenable + ", remark=" + remark + ", perms=" + perms + ", style=" + style + ", editTime=" + editTime
                + ", createdTime=" + createdTime + ", checked=" + checked + ", isParent=" + isParent + "]";
    }

}