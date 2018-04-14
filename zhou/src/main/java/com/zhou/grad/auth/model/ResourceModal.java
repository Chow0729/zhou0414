package com.zhou.grad.auth.model;

import java.util.Date;
import java.util.List;

public class ResourceModal {
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

    private Date editTime;

    private Date createdTime;
    
    private List<ResourceModal> children;

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

    public List<ResourceModal> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceModal> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ResourceModal [resourceId=" + resourceId + ", pid=" + pid + ", resourceName=" + resourceName
                + ", resourceUrl=" + resourceUrl + ", location=" + location + ", icon=" + icon + ", isenable="
                + isenable + ", remark=" + remark + ", perms=" + perms + ", style=" + style + ", editTime=" + editTime
                + ", createdTime=" + createdTime + ", children=" + children + "]";
    }
}