package com.zhou.grad.auth.model;

/**
 * 查询参数实体类
 * @author ChaoQun Zhou
 * @date 2018年3月19日
 */
public class QueryParamsModal {

    //查询字段
    private String selectedFiled;
    //查询条件
    private String condition;
    //状态
    private Integer status;
    //页码
    private Integer start;
    //页大小
    private Integer pageSize;

    public String getSelectedFiled() {
        return selectedFiled;
    }

    public void setSelectedFiled(String selectedFiled) {
        this.selectedFiled = selectedFiled;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "QueryParamsModal [selectedFiled=" + selectedFiled + ", condition=" + condition + ", status=" + status
                + ", start=" + start + ", pageSize=" + pageSize + "]";
    }
}
