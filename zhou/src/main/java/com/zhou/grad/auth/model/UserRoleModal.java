package com.zhou.grad.auth.model;

/**
 * 用户角色详情实体类
 * @author ChaoQun Zhou
 * @date 2018年3月21日
 */
public class UserRoleModal {

    //用户编号
    private int userId; 
    // 用户名称
    private String realName; 
    // 所属部门名称
    private String deptName; 
    // 职位
    private String position; 
    //角色编号
    private int roleId;
    // 角色名称
    private String roleName;
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getDeptName() {
        return deptName;
    }
    
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    } 
}
