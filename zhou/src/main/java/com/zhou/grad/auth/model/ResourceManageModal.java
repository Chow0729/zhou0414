package com.zhou.grad.auth.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhou.grad.entity.Resource;

public class ResourceManageModal {

    private Integer resourceId;

    @JsonProperty("ParentId")
    private Integer parentId;

    private String resourceName;

    private String resourceUrl;

    private String location;

    private String icon;

    private Integer isenable;

    private String remark;
    
    private String style;

    @JsonProperty("Level")
    private Integer level = 1;
    
    private List<Resource> children;
    
    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @JsonIgnore 
    public Integer getParentId() {
        return parentId;
    }

    @JsonIgnore 
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
        this.remark = remark;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @JsonIgnore 
    public Integer getLevel() {
        /*if (ParentId != null && ParentId != 0) {
        }*/
        return level;
    }

    @JsonIgnore 
    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "ResourceModel [resourceId=" + resourceId + ", ParentId=" + parentId + ", resourceName=" + resourceName
                + ", resourceUrl=" + resourceUrl + ", location=" + location + ", icon=" + icon + ", isenable="
                + isenable + ", remark=" + remark + ", style=" + style + ", Level=" + level + ", children=" + children
                + "]";
    }
}
